package kampusfood.notification;

// Refined Abstraction.
public class OrderConfirmation extends Notification {
    private final String orderId;

    public OrderConfirmation(MessageSender sender, String orderId) {
        super(sender);
        this.orderId = orderId;
    }

    @Override
    protected String buildMessage() {
        return "Pesanan " + orderId + " berhasil dikonfirmasi.";
    }
}
