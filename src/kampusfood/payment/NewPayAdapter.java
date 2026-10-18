package kampusfood.payment;

// Adapter: menyesuaikan PaymentProcessor dengan NewPayService.
public class NewPayAdapter implements PaymentProcessor {
    private final NewPayService newPayService;

    public NewPayAdapter(NewPayService newPayService) {
        this.newPayService = newPayService;
    }

    @Override
    public boolean process(String userId, double amountInRupiah) {
        return newPayService.pay(userId, amountInRupiah, "IDR");
    }
}
