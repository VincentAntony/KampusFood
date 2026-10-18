package kampusfood.notification;

// Refined Abstraction.
public class DeliveryUpdate extends Notification {
    private final String orderId;
    private final String status;

    public DeliveryUpdate(MessageSender sender, String orderId, String status) {
        super(sender);
        this.orderId = orderId;
        this.status = status;
    }

    @Override
    protected String buildMessage() {
        return "Status pengiriman pesanan " + orderId + ": " + status;
    }
}
