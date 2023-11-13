package christmas.View;

import christmas.Domain.Event;
import christmas.Domain.Order;
import christmas.Service.OrderService;
import christmas.Util.EventUtil;
import java.util.List;
import java.util.Optional;

public class OutputView {
    public static void printGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printError1(String errorMessage) {
        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public static void printError2(String errorMessage) {
        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static void printOrderSummary(OrderService orderService) {
        System.out.println(orderService.getFormattedSelectedDate() + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        printMenu(orderService.getOrderedMenuList());
        printTotalPriceBeforeDiscount(orderService.getTotalPriceBeforeDiscount());
        printGiftMenu(orderService.getGiftMenu());
        printAppliedEvents(orderService.getAppliedEvents());
        printTotalDiscountAmount(orderService.getTotalDiscountAmount());
        printTotalPriceAfterDiscount(orderService);
        printBadge(orderService.getTotalDiscountAmount());
    }

    private static void printMenu(List<Order> orderedMenuList) {
        System.out.println("<주문 메뉴>");
        orderedMenuList.forEach(
                order -> System.out.println(order.getMenu().getName() + " " + order.getQuantity() + "개"));
        System.out.println();
    }

    private static void printTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원\n", totalPriceBeforeDiscount));
    }

    private static void printGiftMenu(Optional<String> giftMenu) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftMenu.orElse("없음") + "\n");
    }

    private static void printAppliedEvents(List<Event> appliedEvents) {
        System.out.println("<혜택 내역>");

        if (appliedEvents.isEmpty()) {
            System.out.println("없음");
        }

        appliedEvents.forEach(event -> System.out.println(event.getEventDescription()));

        System.out.println();
    }


    private static void printTotalDiscountAmount(int totalDiscountAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(String.format("-%,d원\n", totalDiscountAmount));
    }

    private static void printTotalPriceAfterDiscount(OrderService orderService) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원\n", orderService.getTotalPriceAfterDiscount()));
    }


    private static void printBadge(int totalDiscountAmount) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(EventUtil.calculateBadge(totalDiscountAmount) + "\n");
    }
}
