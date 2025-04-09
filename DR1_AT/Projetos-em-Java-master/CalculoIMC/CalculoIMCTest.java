import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculoIMCTest {

    @Test
    public void testCalculoIMCComValoresValidos() {
        double peso = 70;
        double altura = 1.75;
        double imc = CalculoIMC.calcularPeso(peso, altura);
        assertEquals(22.86, imc, 0.01);
    }

    @Test
    public void testClassificacaoMagrezaGrave() {
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(15.9));
    }

    @Test
    public void testClassificacaoMagrezaModerada() {
        assertEquals("Magreza moderada", CalculoIMC.classificarIMC(16.5));
    }

    @Test
    public void testClassificacaoMagrezaLeve() {
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(17.5));
    }

    @Test
    public void testClassificacaoSaudavel() {
        assertEquals("Saudável", CalculoIMC.classificarIMC(22.0));
    }

    @Test
    public void testClassificacaoSobrepeso() {
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(27.0));
    }

    @Test
    public void testClassificacaoObesidadeGrau1() {
        assertEquals("Obesidade Grau I", CalculoIMC.classificarIMC(32.0));
    }

    @Test
    public void testClassificacaoObesidadeGrau2() {
        assertEquals("Obesidade Grau II", CalculoIMC.classificarIMC(37.0));
    }

    @Test
    public void testClassificacaoObesidadeGrau3() {
        assertEquals("Obesidade Grau III", CalculoIMC.classificarIMC(42.0));
    }

    @Test
    public void testValorLimiteEntreSaudavelESobrepeso() {
        assertEquals("Saudável", CalculoIMC.classificarIMC(24.9));
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(25.0));
    }

    @Test
    public void testValorLimiteEntreMagrezaLeveESaudavel() {
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(18.49));
        assertEquals("Saudável", CalculoIMC.classificarIMC(18.5));
    }
}