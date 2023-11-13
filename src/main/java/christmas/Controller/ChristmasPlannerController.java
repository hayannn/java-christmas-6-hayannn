package christmas.Controller;

import christmas.Service.OrderService;
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
}
