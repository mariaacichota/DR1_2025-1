package com.infnet.DR1_TP2.E03;

public class OrderService {
    private PaymentProcessor paymentProcessor;
    public OrderService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean processOrder(double amount) {
        if (paymentProcessor.processPayment(amount)) {
            System.out.println("Pedido confirmado.");
            return true;
        } else {
            System.out.println("Pagamento recusado.");
            return false;
        }
    }
}
