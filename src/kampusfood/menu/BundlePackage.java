package kampusfood.menu;

// Composite khusus: paket berisi beberapa komponen menu dan diskon persentase.
public class BundlePackage extends MenuCategory {
    private final double discountPercent;

    public BundlePackage(String name, double discountPercent) {
        super(name);
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Diskon harus antara 0 dan 100 persen.");
        }
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1 - discountPercent / 100.0);
    }

    @Override
    public void print(int level) {
        System.out.printf("%s+ %s (diskon %.0f%%, harga paket: Rp%,.0f)%n",
                "  ".repeat(Math.max(0, level)), getName(), discountPercent, getPrice());
        for (MenuComponent child : getChildren()) {
            child.print(level + 1);
        }
    }
}
