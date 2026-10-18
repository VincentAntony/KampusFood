package kampusfood.order;

// Subsystem pengiriman.
public class DeliveryService {
    public boolean createDelivery(String orderId) {
        System.out.println("[DeliveryService] Pengiriman dibuat untuk " + orderId);
        return true;
    }
}
