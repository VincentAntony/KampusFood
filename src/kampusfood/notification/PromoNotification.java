package kampusfood.notification;

// Refined Abstraction.
public class PromoNotification extends Notification {
    private final String promoText;

    public PromoNotification(MessageSender sender, String promoText) {
        super(sender);
        this.promoText = promoText;
    }

    @Override
    protected String buildMessage() {
        return "Promo KampusFood: " + promoText;
    }
}
