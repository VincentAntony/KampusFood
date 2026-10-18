package kampusfood.notification;

// Concrete Implementor.
public class SmsSender implements MessageSender {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[SMS] Kepada %s: %s%n", recipient, message);
    }
}
