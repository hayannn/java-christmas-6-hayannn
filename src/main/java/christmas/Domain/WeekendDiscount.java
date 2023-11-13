package christmas.Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendDiscount {
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    private static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 31);

    public void applyDiscount(EventPlanner eventPlanner) {
        LocalDate orderDate = LocalDate.parse(eventPlanner.getVisitDate());
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();

        if (isWeekend(dayOfWeek) && isDuringEventPeriod(orderDate)) {
            List<String> orderedMenu = eventPlanner.getOrderedMenu();
            List<Integer> menuCounts = eventPlanner.getMenuCounts();

            for (int i = 0; i < orderedMenu.size(); i++) {
                String menu = orderedMenu.get(i);
                int count = menuCounts.get(i);

                if (isMain(menu)) {
                    int discountAmount = WEEKEND_DISCOUNT_AMOUNT * count;
                    eventPlanner.addDiscountDetails("주말 할인: -" + discountAmount + "원");
                }
            }

            eventPlanner.calculateTotalDiscountAmount();
        }
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private boolean isDuringEventPeriod(LocalDate orderDate) {
        return !orderDate.isBefore(EVENT_START_DATE) && !orderDate.isAfter(EVENT_END_DATE);
    }

    private boolean isMain(String menu) {
        return menu.contains("티본스테이크") || menu.contains("바비큐립") || menu.contains("해산물파스타") || menu.contains("크리스마스파스타");
    }
}
