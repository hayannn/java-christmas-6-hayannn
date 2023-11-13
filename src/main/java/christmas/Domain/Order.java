package christmas.Domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static final int MAX_MENU_ITEMS = 20;
    private final Map<String, Integer> menuItems;

    public Order() {
        this.menuItems = new HashMap<>();
    }

    public void addMenuItem(String menu, int quantity, int price) {
        if (menuItems.size() + quantity > MAX_MENU_ITEMS) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        menuItems.put(menu, menuItems.getOrDefault(menu, 0) + quantity);
    }

    public Map<String, Integer> getMenuItems() {
        return new HashMap<>(menuItems);
    }

    public int getPrice(String menu) {
        return menuItems.getOrDefault(menu, 0);
    }

    public int getTotalPrice() {
        return menuItems.entrySet().stream()
                .mapToInt(entry -> getPrice(entry.getKey()) * entry.getValue())
                .sum();
    }
}
