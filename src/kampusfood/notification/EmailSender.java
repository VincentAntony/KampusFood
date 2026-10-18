package kampusfood.notification;

// Concrete Implementor.
public class EmailSender implements MessageSender {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[Email] Kepada %s: %s%n", recipient, message);
    }
}
