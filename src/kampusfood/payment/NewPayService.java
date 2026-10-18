package kampusfood.payment;

// Adaptee: simulasi layanan pembayaran baru.
public class NewPayService {
    public boolean pay(String userId, double amount, String currency) {
        System.out.printf(
            "[NewPayService] Pembayaran %s sebesar %.2f %s diproses.%n",
            userId, amount, currency
        );
        return amount > 0 && !"fail".equalsIgnoreCase(userId);
    }
}
