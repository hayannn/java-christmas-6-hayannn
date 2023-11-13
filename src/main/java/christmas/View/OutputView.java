package christmas.View;

import christmas.Domain.Order;
import christmas.Util.OutputFormatter;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";
    private static final String EVENT_DETAILS_TITLE = "<혜택 내역>";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_TITLE = "<증정 메뉴>";
    private static final String TOTAL_BENEFITS_AMOUNT_TITLE = "<총혜택 금액>";
    private static final String TOTAL_PAYMENT_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    public static void printEventPlannerGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    public static void printOrderPreview(int date, List<Order> orders) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date);
        printOrderMenu(orders);
    }

    public static void printOrderMenu(List<Order> orders) {
        System.out.println(ORDER_MENU_TITLE);
        for (Order order : orders) {
            Map<String, Integer> menuItems = order.getMenuItems();
            for (Map.Entry<String, Integer> entry : menuItems.entrySet()) {
                String menuName = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(menuName + " " + quantity + "개");
            }
        }
        System.out.println();
    }

    public static void printTotalOrderAmount(int totalAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT_TITLE);
        System.out.println(OutputFormatter.formatCurrency(totalAmount));
        System.out.println();
    }

    public static void printGiftMenu(String giftMenu) {
        System.out.println(GIFT_MENU_TITLE);
        System.out.println(giftMenu);
        System.out.println();
    }

    public static void printEventBenefits(List<String> eventBenefitDetails) {
        System.out.println(EVENT_DETAILS_TITLE);
        if (eventBenefitDetails.isEmpty()) {
            System.out.println("없음");
        } else {
            for (String benefitDetail : eventBenefitDetails) {
                System.out.println(benefitDetail);
            }
        }
        System.out.println();
    }

    public static void printTotalBenefitsAmount(int totalBenefitsAmount) {
        System.out.println(TOTAL_BENEFITS_AMOUNT_TITLE);
        System.out.println(OutputFormatter.formatCurrency(totalBenefitsAmount));
        System.out.println();
    }

    public static void printTotalPaymentAmount(int totalAmount, int totalDiscount) {
        System.out.println(TOTAL_PAYMENT_AMOUNT_TITLE);
        System.out.println(OutputFormatter.formatCurrency(totalAmount - totalDiscount));
        System.out.println();
    }

    public static class EventBadge {
        private String name;

        public EventBadge(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void printEventBadge(EventBadge eventBadge) {
        System.out.println(EVENT_BADGE_TITLE);
        System.out.println(eventBadge != null ? eventBadge.getName() : "없음");
        System.out.println();
    }
}
