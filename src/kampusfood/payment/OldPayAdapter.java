package kampusfood.payment;

// Adapter: menyesuaikan PaymentProcessor dengan API OldPayGateway.
public class OldPayAdapter implements PaymentProcessor {
    private final OldPayGateway oldPayGateway;

    public OldPayAdapter(OldPayGateway oldPayGateway) {
        this.oldPayGateway = oldPayGateway;
    }

    @Override
    public boolean process(String userId, double amountInRupiah) {
        // Rupiah ke satuan cents: Rp 1.000,00 -> 100000 cents.
        int amountInCents = (int) Math.round(amountInRupiah * 100);
        return oldPayGateway.makePayment(userId, amountInCents);
    }
}
