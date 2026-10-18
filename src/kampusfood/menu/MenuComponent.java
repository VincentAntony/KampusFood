package kampusfood.menu;

// Component: antarmuka umum untuk Leaf dan Composite.
public abstract class MenuComponent {
    public abstract String getName();
    public abstract double getPrice();
    public abstract void print(int level);
}
