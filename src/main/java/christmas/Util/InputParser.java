package christmas.Util;

import christmas.Domain.Order;
import christmas.Repository.OrderRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    public static int parseDate(String input) {
        return Integer.parseInt(input);
    }

    public static List<Order> parseOrders(String input) throws IllegalArgumentException {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(orderString -> {
                    Order order = new Order();
                    try {
                        String[] menuAndQuantity = orderString.split("-");
                        if (menuAndQuantity.length != 2) {
                            throw new IllegalArgumentException("[ERROR] 주문 형식이 올바르지 않습니다. 다시 입력해 주세요.");
                        }
                        String menu = menuAndQuantity[0].trim();
                        int quantity = Integer.parseInt(menuAndQuantity[1].trim());
                        order.addMenuItem(menu, quantity, OrderRepository.getOrderPrice(menu));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }
                    return order;
                })
                .collect(Collectors.toList());
    }


    public static List<String> getMenuList() {
        return Arrays.asList(
                "양송이수프", "타파스", "시저샐러드",
                "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타",
                "초코케이크", "아이스크림",
                "제로콜라", "레드와인", "샴페인"
        );
    }
}
