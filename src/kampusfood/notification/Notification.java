package kampusfood.notification;

// Abstraction: menyimpan referensi kanal dan mendelegasikan pengiriman kepadanya.
public abstract class Notification {
    protected final MessageSender sender;

    protected Notification(MessageSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException("MessageSender tidak boleh null.");
        }
        this.sender = sender;
    }

    protected abstract String buildMessage();

    public void send(String recipient) {
        sender.send(recipient, buildMessage());
    }
}
