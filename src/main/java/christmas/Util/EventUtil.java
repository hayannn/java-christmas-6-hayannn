package christmas.Util;

import christmas.Domain.Event;
import christmas.Domain.Menu;
import christmas.Domain.Order;
import christmas.Service.OrderService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventUtil {
    private static final List<Event> events = new ArrayList<>();

    static {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);
        int baseDiscountAmount = 1000;
        int increaseAmountPerDay = 100;

        while (!startDate.isAfter(endDate)) {
            events.add(new Event("크리스마스 디데이 할인", baseDiscountAmount));
            startDate = startDate.plusDays(1);
            baseDiscountAmount += increaseAmountPerDay;
        }
    }

    public static int applyChristmasDiscount(List<Order> orderedMenuList, LocalDate selectedDate) {
        int christmasDiscount = 0;

        for (Order order : orderedMenuList) {
            Menu menu = order.getMenu();
            if (menu.getCategory() == Menu.Category.MAIN && isChristmasDiscountDay(selectedDate)) {
                int discountPerItem = getChristmasDiscountAmount(selectedDate);
                christmasDiscount += discountPerItem * order.getQuantity();
            }
        }

        return christmasDiscount;
    }

    private static boolean isChristmasDiscountDay(LocalDate date) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private static int getChristmasDiscountAmount(LocalDate date) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        int baseDiscountAmount = 1000;
        int increaseAmountPerDay = 100;

        long daysBetween = ChronoUnit.DAYS.between(startDate, date);
        if (daysBetween >= 0 && daysBetween <= 24) {
            int discountAmount = (baseDiscountAmount + increaseAmountPerDay * (int) daysBetween) / 2;
            return Math.min(discountAmount, 3400);
        }

        return 0;
    }


    public static int applyWeekdayDiscount(List<Order> orderedMenuList, LocalDate selectedDate) {
        int weekdayDiscount = 0;

        for (Order order : orderedMenuList) {
            Menu menu = order.getMenu();
            if (menu.getCategory() == Menu.Category.DESSERT) {
                weekdayDiscount += order.getQuantity() * 2023;
            }
        }

        return weekdayDiscount;
    }

    public static int applyWeekendDiscount(List<Order> orderedMenuList, LocalDate selectedDate) {
        int weekendDiscount = 0;

        for (Order order : orderedMenuList) {
            Menu menu = order.getMenu();
            if (menu.getCategory() == Menu.Category.MAIN) {
                weekendDiscount += order.getQuantity() * 2023;
            }
        }

        return weekendDiscount;
    }

    public static int applyMenuDiscount(List<Order> orderedMenuList) {
        int dessertDiscount = 0;
        int mainDiscount = 0;

        for (Order order : orderedMenuList) {
            Menu menu = order.getMenu();
            int discountPerItem = 2023 * order.getQuantity();

            if (menu.getCategory() == Menu.Category.DESSERT) {
                dessertDiscount += discountPerItem;
            }

            if (menu.getCategory() == Menu.Category.MAIN) {
                mainDiscount += discountPerItem;
            }
        }

        return dessertDiscount + mainDiscount;
    }


    public static int applySpecialDiscount(LocalDate selectedDate, List<Order> orderedMenuList) {
        int specialDiscount = 0;

        if (hasStarEvent(selectedDate)) {
            specialDiscount = 1000;
        }

        return specialDiscount;
    }

    private static boolean hasStarEvent(LocalDate date) {
        List<LocalDate> starDates = Arrays.asList(
                LocalDate.of(2023, 12, 3),
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 24),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31)
        );

        return starDates.contains(date);
    }

    public static Optional<String> applyGiftEvent(OrderService orderService) {
        int totalPriceBeforeDiscount = orderService.getTotalPriceBeforeDiscount();
        Optional<String> result = Optional.empty();

        if (totalPriceBeforeDiscount >= 120000) {
            result = Optional.of("샴페인 1개");
        }

        return result;
    }


    public static String calculateBadge(int totalDiscountAmount) {
        String badge = "없음"; // 기본값으로 초기화

        if (totalDiscountAmount >= 20000) {
            badge = "산타";
        }
        if (totalDiscountAmount >= 10000 && totalDiscountAmount < 20000 && !"산타".equals(badge)) {
            badge = "트리";
        }
        if (totalDiscountAmount >= 5000 && totalDiscountAmount < 10000 && !"산타".equals(badge) && !"트리".equals(badge)) {
            badge = "별";
        }

        return badge;
    }


    public static List<Event> getEvents() {
        return events;
    }
}
