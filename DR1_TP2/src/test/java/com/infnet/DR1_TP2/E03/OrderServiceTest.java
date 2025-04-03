package com.infnet.DR1_TP2.E03;

import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private PaymentProcessor paymentProcessorMock;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        paymentProcessorMock = Mockito.mock(PaymentProcessor.class);
        orderService = new OrderService(paymentProcessorMock);
    }

    @Test
    void testProcessOrder_PaymentApproved() {
        when(paymentProcessorMock.processPayment(100.0)).thenReturn(true);

        boolean result = orderService.processOrder(100.0);

        assertTrue(result, "O pedido deve ser confirmado se o pagamento for aprovado.");
        verify(paymentProcessorMock, times(1)).processPayment(100.0);
    }

    @Test
    void testProcessOrder_PaymentDeclined() {
        when(paymentProcessorMock.processPayment(50.0)).thenReturn(false);

        boolean result = orderService.processOrder(50.0);
        assertFalse(result, "O pedido deve ser recusado se o pagamento for negado.");
        verify(paymentProcessorMock, times(1)).processPayment(50.0);
    }
}