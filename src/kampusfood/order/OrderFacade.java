package kampusfood.order;

import kampusfood.notification.NotificationService;

// Facade: satu pintu masuk yang mengoordinasikan subsystem checkout.
public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;
    private final NotificationService notificationService;

    public OrderFacade(
            InventoryService inventoryService,
            PaymentService paymentService,
            DeliveryService deliveryService,
            NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
        this.notificationService = notificationService;
    }

    public boolean placeOrder(
            String orderId,
            String userId,
            String itemName,
            int quantity,
            double totalAmount,
            String recipient) {

        System.out.printf("%n[Checkout] Memproses pesanan %s%n", orderId);

        if (quantity <= 0 || totalAmount <= 0) {
            System.out.println("[Checkout] GAGAL: kuantitas dan total harus positif.");
            return false;
        }

        if (!inventoryService.checkAvailability(itemName, quantity)) {
            System.out.println("[Checkout] GAGAL: stok tidak mencukupi.");
            return false;
        }

        if (!inventoryService.reserveStock(itemName, quantity)) {
            System.out.println("[Checkout] GAGAL: reservasi stok gagal.");
            return false;
        }

        boolean paid;
        try {
            paid = paymentService.processPayment(userId, totalAmount);
        } catch (RuntimeException ex) {
            paid = false;
            System.out.println("[Checkout] Kesalahan pembayaran: " + ex.getMessage());
        }

        if (!paid) {
            inventoryService.releaseStock(itemName, quantity);
            System.out.println("[Checkout] GAGAL: pembayaran ditolak; stok dilepas.");
            return false;
        }

        if (!deliveryService.createDelivery(orderId)) {
            // Sistem nyata perlu refund/kompensasi bila pembayaran sudah berhasil.
            inventoryService.releaseStock(itemName, quantity);
            System.out.println(
                "[Checkout] GAGAL: pengiriman tidak dapat dibuat. " +
                "Diperlukan proses refund/kompensasi."
            );
            return false;
        }

        notificationService.sendOrderConfirmation(recipient, orderId);
        notificationService.sendDeliveryUpdate(recipient, orderId, "Pesanan diterima kurir");

        System.out.println("[Checkout] BERHASIL: pesanan selesai diproses.");
        return true;
    }
}
