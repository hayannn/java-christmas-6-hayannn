package christmas.View;

import christmas.Domain.Order;
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
    }
}
