package christmas.View;

import java.util.List;

public class OutputView {
    public static void printEventPlannerGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    public static void printOrderPreview(int date, List<Order> orders) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date);
        printOrderMenu(orders);
    }
}
