package kampusfood.payment;

// Target interface untuk Adapter Pattern.
public interface PaymentProcessor {
    boolean process(String userId, double amountInRupiah);
}
