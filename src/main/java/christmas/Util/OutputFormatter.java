package christmas.Util;

import christmas.Domain.Order;

import java.text.NumberFormat;
import java.util.List;

public class OutputFormatter {
    public static void printOrderSummary(List<Order> orders, int totalDiscount, int totalBenefits) {
        System.out.println("<주문 메뉴>");
        printOrderMenu(orders);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(formatCurrency(calculateTotalOrderAmount(orders)));

        System.out.println("\n<혜택 내역>");
        printBenefitDetails("크리스마스 디데이 할인", totalDiscount);
        printBenefitDetails("평일 할인", totalBenefits);
        printBenefitDetails("특별 할인", totalBenefits);
        printBenefitDetails("증정 이벤트", totalBenefits);
        System.out.println();

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formatCurrency(calculateTotalOrderAmount(orders) - totalDiscount));
    }

    public static String formatCurrency(int amount) {
        return NumberFormat.getInstance().format(amount) + "원";
    }

    private static void printOrderMenu(List<Order> orders) {
        for (Order order : orders) {
            order.getMenuItems().forEach((menu, quantity) ->
                    System.out.println(menu + " " + quantity + "개"));
        }
    }

    private static int calculateTotalOrderAmount(List<Order> orders) {
        return orders.stream().mapToInt(Order::getTotalPrice).sum();
    }

    private static void printBenefitDetails(String title, int amount) {
        System.out.println(title + ": " + formatCurrency(amount));
    }
}
