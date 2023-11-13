package christmas.Util;

import christmas.Domain.Order;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderUtil {
    public static int calculateTotalPrice(List<Order> orderedMenuList) {
        int totalPrice = 0;
        for (Order order : orderedMenuList) {
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }

    public static String formatCurrency(int amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return numberFormat.format(amount);
    }
}
