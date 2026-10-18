package kampusfood.order;

import kampusfood.menu.MenuComponent;
import java.util.HashMap;
import java.util.Map;

// Subsystem untuk memeriksa dan mereservasi stok.
public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("Paket Hemat", 5);
        stock.put("Nasi Ayam", 10);
        stock.put("Es Teh", 20);
    }

    public boolean checkAvailability(String itemName, int quantity) {
        return stock.getOrDefault(itemName, 0) >= quantity;
    }

    public boolean reserveStock(String itemName, int quantity) {
        if (quantity <= 0 || !checkAvailability(itemName, quantity)) {
            return false;
        }
        stock.put(itemName, stock.get(itemName) - quantity);
        System.out.printf("[Inventory] Stok %s berhasil direservasi sebanyak %d.%n",
                itemName, quantity);
        return true;
    }

    public void releaseStock(String itemName, int quantity) {
        stock.merge(itemName, quantity, Integer::sum);
        System.out.printf("[Inventory] Reservasi %s dilepas sebanyak %d.%n",
                itemName, quantity);
    }

    // Membantu demo agar skenario stok habis mudah diuji.
    public void setStock(String itemName, int quantity) {
        stock.put(itemName, Math.max(0, quantity));
    }
}
