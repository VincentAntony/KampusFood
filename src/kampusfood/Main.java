package kampusfood;

import kampusfood.menu.BundlePackage;
import kampusfood.menu.MenuCategory;
import kampusfood.menu.MenuItem;
import kampusfood.notification.EmailSender;
import kampusfood.notification.NotificationService;
import kampusfood.notification.PushSender;
import kampusfood.notification.PromoNotification;
import kampusfood.notification.SmsSender;
import kampusfood.notification.WhatsAppSender;
import kampusfood.order.DeliveryService;
import kampusfood.order.InventoryService;
import kampusfood.order.OrderFacade;
import kampusfood.order.PaymentService;
import kampusfood.payment.NewPayAdapter;
import kampusfood.payment.NewPayService;
import kampusfood.payment.OldPayAdapter;
import kampusfood.payment.OldPayGateway;
import kampusfood.payment.PaymentProcessor;

public class Main {
    public static void main(String[] args) {
        // ==================== COMPOSITE ====================
        System.out.println("========== COMPOSITE: MENU ==========");
        MenuCategory root = new MenuCategory("Menu KampusFood");
        MenuCategory food = new MenuCategory("Makanan");
        MenuCategory rice = new MenuCategory("Nasi");

        MenuItem chickenRice = new MenuItem("Nasi Ayam", 18000);
        MenuItem friedSnack = new MenuItem("Gorengan", 5000);
        MenuItem icedTea = new MenuItem("Es Teh", 5000);

        rice.add(chickenRice);
        food.add(rice);
        food.add(friedSnack);
        root.add(food);
        root.add(new MenuCategory("Minuman"));

        BundlePackage bundle = new BundlePackage("Paket Hemat", 10);
        bundle.add(chickenRice);
        bundle.add(icedTea);
        bundle.add(friedSnack);
        root.add(bundle);

        root.print(0);
        System.out.printf("Harga Paket Hemat setelah diskon: Rp%,.0f%n", bundle.getPrice());

        // ==================== ADAPTER ====================
        System.out.println("\n========== ADAPTER: PEMBAYARAN ==========");
        PaymentProcessor oldPayment = new OldPayAdapter(new OldPayGateway());
        PaymentProcessor newPayment = new NewPayAdapter(new NewPayService());

        System.out.println("Pilih adapter pembayaran: NewPayService");
        PaymentService paymentService = new PaymentService(newPayment);

        // Demonstrasi kedua adapter secara langsung.
        System.out.println("-- Simulasi OldPayAdapter --");
        oldPayment.process("mahasiswa01", 28000);
        System.out.println("-- Simulasi NewPayAdapter --");
        newPayment.process("mahasiswa01", 28000);

        // ==================== BRIDGE ====================
        System.out.println("\n========== BRIDGE: NOTIFIKASI ==========");
        new PromoNotification(new EmailSender(), "Diskon 10% untuk Paket Hemat!")
                .send("mahasiswa@kampus.ac.id");
        new PromoNotification(new SmsSender(), "Diskon 10% untuk Paket Hemat!")
                .send("081234567890");
        new PromoNotification(new WhatsAppSender(), "Diskon 10% untuk Paket Hemat!")
                .send("081234567890");
        new PromoNotification(new PushSender(), "Diskon 10% untuk Paket Hemat!")
                .send("device-mahasiswa-01");

        // ==================== FACADE ====================
        System.out.println("\n========== FACADE: CHECKOUT END-TO-END ==========");
        InventoryService inventoryService = new InventoryService();
        DeliveryService deliveryService = new DeliveryService();
        NotificationService notificationService =
                new NotificationService(new WhatsAppSender());

        OrderFacade checkout = new OrderFacade(
                inventoryService,
                paymentService,
                deliveryService,
                notificationService
        );

        double bundlePrice = bundle.getPrice();
        checkout.placeOrder(
                "ORD-001", "mahasiswa01", "Paket Hemat",
                1, bundlePrice, "081234567890"
        );

        // Skenario gagal: stok tidak mencukupi.
        System.out.println("\n========== SKENARIO GAGAL: STOK HABIS ==========");
        inventoryService.setStock("Paket Hemat", 0);
        checkout.placeOrder(
                "ORD-002", "mahasiswa01", "Paket Hemat",
                1, bundlePrice, "081234567890"
        );

        // Skenario gagal: pembayaran ditolak, lalu stok dilepas.
        System.out.println("\n========== SKENARIO GAGAL: PEMBAYARAN ==========");
        inventoryService.setStock("Paket Hemat", 3);
        checkout.placeOrder(
                "ORD-003", "fail", "Paket Hemat",
                1, bundlePrice, "081234567890"
        );

        System.out.println("%nDemo KampusFood selesai.");
    }
}
