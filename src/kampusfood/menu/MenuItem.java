package kampusfood.menu;

// Leaf: item menu tunggal yang tidak memiliki anak.
public class MenuItem extends MenuComponent {
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama menu tidak boleh kosong.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif.");
        }
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print(int level) {
        System.out.printf("%s- %s: Rp%,.0f%n", "  ".repeat(Math.max(0, level)), name, price);
    }
}
