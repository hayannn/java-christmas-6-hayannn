package christmas.Controller;

import christmas.Exception.DateException;
import christmas.Service.OrderService;
import christmas.View.InputView;
import christmas.View.OutputView;

public class ChristmasPlannerController {
    public void run() {
        OutputView.printGreeting();
        while (true) {
            int selectedDate = readDate();
            String orderedMenuInput = readOrderedMenu();
            try {
                OrderService orderService = new OrderService(selectedDate, orderedMenuInput);
                orderService.processOrder();

                OutputView.printOrderSummary(orderService);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printError2(e.getMessage());
            }
        }
    }
    private int readDate() {
        while (true) {
            try {
                int date = InputView.readDate();
                DateException dateException = new DateException();
                dateException.checkDate(date);
                return date;
            } catch (IllegalArgumentException e) {
                OutputView.printError1(e.getMessage());
            }
        }
    }

    private String readOrderedMenu() {
        while (true) {
            try {
                String orderedMenu = InputView.readOrderedMenu();
                if (orderedMenu.trim().isEmpty()) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
                validateMenu(orderedMenu);
                return orderedMenu;
            } catch (IllegalArgumentException e) {
                OutputView.printError2(e.getMessage());
            }
        }
    }
}
