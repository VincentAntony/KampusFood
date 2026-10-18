package kampusfood.notification;

// Concrete Implementor.
public class WhatsAppSender implements MessageSender {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[WhatsApp] Kepada %s: %s%n", recipient, message);
    }
}
