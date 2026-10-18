package kampusfood.notification;

// Implementor: antarmuka kanal pengiriman untuk Bridge Pattern.
public interface MessageSender {
    void send(String recipient, String message);
}
