package christmas.Controller;

import christmas.Domain.Menu;
import christmas.Exception.DateException;
import christmas.Exception.MenuException;
import christmas.Service.OrderService;
import christmas.Util.MenuUtil;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.List;
import java.util.stream.Collectors;

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

    private void validateMenu(String orderedMenu) {
        List<Menu> menuList = MenuUtil.getMenuList();
        List<String> menuNames = menuList.stream().map(Menu::getName).collect(Collectors.toList());

        try {
            int orderCount = getOrderCount(orderedMenu);

            MenuException.checkMenuNameAndCount(menuNames, orderedMenu, orderCount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] " + e.getMessage());
        }
    }

    private int getOrderCount(String orderedMenu) {
        String[] menuInfos = orderedMenu.split(",");
        int orderCount = 0;

        try {
            for (String menuInfo : menuInfos) {
                String[] parts = menuInfo.trim().split("-");
                int quantity = Integer.parseInt(parts[1]);
                orderCount += quantity;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        return orderCount;
    }

    private int getTotalPrice(String orderedMenu) {
        String[] menuInfos = orderedMenu.split(",");
        int totalPrice = 0;

        for (String menuInfo : menuInfos) {
            String[] parts = menuInfo.trim().split("-");
            String menuName = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            Menu menu = MenuUtil.getMenu(menuName)
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 메뉴 이름: " + menuName));

            int itemTotalPrice = menu.getPrice() * quantity;

            totalPrice += itemTotalPrice;
        }
        MenuException.exceedMaxOrderCountCase(getOrderCount(orderedMenu));
        return totalPrice;
    }
}
