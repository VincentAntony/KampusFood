package kampusfood.order;

import kampusfood.payment.PaymentProcessor;

// Subsystem yang menggunakan Target Adapter, bukan gateway konkret.
public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean processPayment(String userId, double amount) {
        System.out.println("[PaymentService] Memulai pembayaran...");
        return paymentProcessor.process(userId, amount);
    }
}
