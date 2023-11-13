package christmas.Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekdayDiscount {
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    private static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 31);

    public void applyDiscount(EventPlanner eventPlanner) {
        LocalDate orderDate = LocalDate.parse(eventPlanner.getVisitDate());
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();

        if (isWeekday(dayOfWeek) && isDuringEventPeriod(orderDate)) {
            List<String> orderedMenu = eventPlanner.getOrderedMenu();
            List<Integer> menuCounts = eventPlanner.getMenuCounts();

            for (int i = 0; i < orderedMenu.size(); i++) {
                String menu = orderedMenu.get(i);
                int count = menuCounts.get(i);

                if (isDessert(menu)) {
                    int discountAmount = WEEKDAY_DISCOUNT_AMOUNT * count;
                    eventPlanner.addDiscountDetails("평일 할인: -" + discountAmount + "원");
                }
            }

            eventPlanner.calculateTotalDiscountAmount();
        }
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private boolean isDuringEventPeriod(LocalDate orderDate) {
        return !orderDate.isBefore(EVENT_START_DATE) && !orderDate.isAfter(EVENT_END_DATE);
    }

    private boolean isDessert(String menu) {
        return menu.contains("초코케이크") || menu.contains("아이스크림");
    }
}
