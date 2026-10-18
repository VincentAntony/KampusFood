package kampusfood.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Composite: kategori/subkategori yang dapat menampung MenuComponent lain.
public class MenuCategory extends MenuComponent {
    private final String name;
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuCategory(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama kategori tidak boleh kosong.");
        }
        this.name = name;
    }

    public void add(MenuComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Komponen menu tidak boleh null.");
        }
        children.add(component);
    }

    public boolean remove(MenuComponent component) {
        return children.remove(component);
    }

    public List<MenuComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return children.stream().mapToDouble(MenuComponent::getPrice).sum();
    }

    @Override
    public void print(int level) {
        System.out.printf("%s+ %s (total isi: Rp%,.0f)%n",
                "  ".repeat(Math.max(0, level)), name, getPrice());
        for (MenuComponent child : children) {
            child.print(level + 1);
        }
    }
}
