package kampusfood.notification;

// Layanan notifikasi: menggunakan Bridge tanpa bergantung pada kanal tertentu.
public class NotificationService {
    private final MessageSender defaultSender;

    public NotificationService(MessageSender defaultSender) {
        this.defaultSender = defaultSender;
    }

    public void sendOrderConfirmation(String recipient, String orderId) {
        new OrderConfirmation(defaultSender, orderId).send(recipient);
    }

    public void sendPromo(String recipient, String promoText) {
        new PromoNotification(defaultSender, promoText).send(recipient);
    }

    public void sendDeliveryUpdate(String recipient, String orderId, String status) {
        new DeliveryUpdate(defaultSender, orderId, status).send(recipient);
    }
}
