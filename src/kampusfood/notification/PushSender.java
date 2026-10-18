package kampusfood.notification;

// Concrete Implementor.
public class PushSender implements MessageSender {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[Push] Kepada %s: %s%n", recipient, message);
    }
}
