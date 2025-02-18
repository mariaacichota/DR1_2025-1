import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import net.jqwik.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CalculoIMCTest {

    // Teste tradicional de cálculo de peso
    @Test
    void testCalcularPeso() {
        assertEquals(22.09, CalculoIMC.calcularPeso(70, 1.78), 0.01);
        assertEquals(24.69, CalculoIMC.calcularPeso(80, 1.80), 0.01);
        assertEquals(31.14, CalculoIMC.calcularPeso(90, 1.70), 0.01);
    }

    // Teste tradicional de classificação do IMC
    @Test
    void testClassificarIMC() {
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(15.9));
        assertEquals("Magreza moderada", CalculoIMC.classificarIMC(16.5));
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(17.5));
        assertEquals("Saudável", CalculoIMC.classificarIMC(22.0));
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(27.5));
        assertEquals("Obesidade Grau I", CalculoIMC.classificarIMC(32.5));
        assertEquals("Obesidade Grau II", CalculoIMC.classificarIMC(37.5));
        assertEquals("Obesidade Grau III", CalculoIMC.classificarIMC(42.0));
    }

    // Teste tradicional de valores inválidos
    @Test
    void testValoresInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(-10, 1.75));
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(70, -1.75));
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(0, 1.75));
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(70, 0));
    }

    // Teste tradicional de limites extremos
    @Test
    void testLimites() {
        assertEquals(250.76, CalculoIMC.calcularPeso(650, 1.61), 0.01);
        assertEquals(73.96, CalculoIMC.calcularPeso(500, 2.60), 0.01);
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(700, 1.75));
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(70, 3.5));
    }

    @Property
     void testCalculoIMC(@ForAll @DoubleRange(min = 30, max = 200) double peso,
                        @ForAll @DoubleRange(min = 1.2, max = 2.5) double altura) {
        double imc = CalculoIMC.calcularPeso(peso, altura);
        assertTrue(imc > 0, "O IMC deve ser maior que zero");
    }

    @Property
    void testValoresInvalidos(@ForAll @DoubleRange(min = -100, max = 0) double peso,
                              @ForAll @DoubleRange(min = -1.0, max = 1.0) double altura) {
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(peso, altura));
    }

    @Property
    void testIMCNegativo(@ForAll @Positive double peso, @ForAll @Positive double altura) {
        double imc = peso / (altura * altura);
        assertThat(imc).isGreaterThanOrEqualTo(0);
    }

    @Property
    void testIMCLimites(@ForAll @Positive double peso, @ForAll @Positive double altura) {
        if (peso > 0 && altura > 0) {
            double imc = peso / (altura * altura);
            assertThat(imc).isGreaterThanOrEqualTo(0);
        }
    }

    @Test
    void testCalculoIMCComMock() {
        IMCService imcService = mock(IMCService.class);
        when(imcService.calcularIMC(80, 1.80)).thenReturn(24.69);

        double imc = imcService.calcularIMC(80, 1.80);
        assertThat(imc).isEqualTo(24.69);
    }

    @Property
    void testIMCComCasosEspecíficos(@Example double peso, @Example double altura) {
        double imc = peso / (altura * altura);
        assertThat(imc).isBetween(10.0, 50.0);
    }

}
