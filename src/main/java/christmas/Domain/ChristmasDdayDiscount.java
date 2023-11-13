package christmas.Domain;

import java.time.LocalDate;

public class ChristmasDdayDiscount {
    private static final int INITIAL_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREASE = 100;

    private static final LocalDate CHRISTMAS_EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_EVENT_END_DATE = LocalDate.of(2023, 12, 25);

    public int applyDiscountChristmas(EventPlanner eventPlanner) {
        LocalDate orderDate = LocalDate.parse(eventPlanner.getVisitDate());
        if (isDuringEventPeriod(orderDate)) {
            int daysBetween = (int) (orderDate.toEpochDay() - CHRISTMAS_EVENT_START_DATE.toEpochDay()) + 1;
            int dailyDiscount = INITIAL_DISCOUNT_AMOUNT + (daysBetween - 1) * DAILY_DISCOUNT_INCREASE;
            return Math.max(dailyDiscount, 0);
        }

        return 0;
    }

    private boolean isDuringEventPeriod(LocalDate orderDate) {
        return !orderDate.isBefore(CHRISTMAS_EVENT_START_DATE) && !orderDate.isAfter(CHRISTMAS_EVENT_END_DATE);
    }
}
