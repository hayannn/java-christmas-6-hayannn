package christmas.Repository;

import christmas.Domain.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();
    private static final Map<String, Integer> menuPrices = MenuPrices();

    public static void addOrder(List<Order> orders) {
        // Check if all menu items in the order are valid
        for (Order order : orders) {
            for (String menu : order.getMenuItems().keySet()) {
                if (!menuPrices.containsKey(menu)) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
            }
        }

        OrderRepository.orders.addAll(orders);
    }

    public static List<Order> getAllOrders(Order order) {
        return new ArrayList<>(orders);
    }

    public static int getOrderPrice(String menu) {
        int totalOrderPrice = 0;

        for (Order order : orders) {
            int quantity = order.getMenuItems().getOrDefault(menu, 0);
            totalOrderPrice += quantity * menuPrices.getOrDefault(menu, 0);
        }

        return menuPrices.getOrDefault(menu, 0);
    }

    public static Map<String, Integer> MenuPrices() {
        Map<String, Integer> prices = new HashMap<>();

        // Categorize the menu items
        Map<String, List<String>> categorizedMenu = new HashMap<>();
        categorizedMenu.put("<애피타이저>", Arrays.asList("양송이수프", "타파스", "시저샐러드"));
        categorizedMenu.put("<메인>", Arrays.asList("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"));
        categorizedMenu.put("<디저트>", Arrays.asList("초코케이크", "아이스크림"));
        categorizedMenu.put("<음료>", Arrays.asList("제로콜라", "레드와인", "샴페인"));

        // Populate prices map
        for (Map.Entry<String, List<String>> entry : categorizedMenu.entrySet()) {
            for (String menuItem : entry.getValue()) {
                prices.put(menuItem, getPriceForMenuItem(menuItem));
            }
        }

        return prices;
    }

    private static int getPriceForMenuItem(String menuItem) {
        if ("양송이수프".equals(menuItem)) {
            return 6000;
        }
        if ("타파스".equals(menuItem)) {
            return 5500;
        }
        if ("시저샐러드".equals(menuItem)) {
            return 8000;
        }
        if ("티본스테이크".equals(menuItem)) {
            return 55000;
        }
        if ("바비큐립".equals(menuItem)) {
            return 54000;
        }
        if ("해산물파스타".equals(menuItem)) {
            return 35000;
        }
        if ("크리스마스파스타".equals(menuItem)) {
            return 25000;
        }
        if ("초코케이크".equals(menuItem)) {
            return 15000;
        }
        if ("아이스크림".equals(menuItem)) {
            return 5000;
        }
        if ("제로콜라".equals(menuItem)) {
            return 3000;
        }
        if ("레드와인".equals(menuItem)) {
            return 60000;
        }
        if ("샴페인".equals(menuItem)) {
            return 25000;
        }

        return 0; // Default case
    }
}
