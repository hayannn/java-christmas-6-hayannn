package christmas.Domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SpecialDiscount {
    private static final List<Integer> STAR_DATES = Arrays.asList(3, 10, 17, 24, 31);
    private static final LocalDate SPECIAL_EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate SPECIAL_EVENT_END_DATE = LocalDate.of(2023, 12, 31);



    public void applyDiscountSpecial(EventPlanner eventPlanner) {
        LocalDate orderDate = LocalDate.parse(eventPlanner.getVisitDate());

        if (STAR_DATES.contains(orderDate.getDayOfMonth()) && isDuringEventPeriod(orderDate)) {
            int additionalDiscount = 1000;
            eventPlanner.addDiscountDetails("특별 할인: -" + additionalDiscount + "원");
            eventPlanner.calculateTotalDiscountAmount();
        }
    }

    private boolean isDuringEventPeriod(LocalDate orderDate) {
        return !orderDate.isBefore(SPECIAL_EVENT_START_DATE) && !orderDate.isAfter(SPECIAL_EVENT_END_DATE);
    }
}
