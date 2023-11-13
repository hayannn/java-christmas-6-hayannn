package christmas.Repository;

import christmas.Domain.EventBenefit;
import java.util.ArrayList;
import java.util.List;

public class EventBenefitRepository {
    private static final List<EventBenefit> eventBenefits = new ArrayList<>();

    public static void addEventBenefit(EventBenefit eventBenefit) {
        eventBenefits.add(eventBenefit);
    }

    public static List<EventBenefit> getAllEventBenefits() {
        return new ArrayList<>(eventBenefits);
    }

    public static EventBenefit getSpecialDiscount() {
        return new EventBenefit("Special Discount", 1000);
    }


    public static EventBenefit getWeekdayDiscount() {
        return new EventBenefit("Weekday Discount", 2023);
    }

    public static EventBenefit getWeekendDiscount() {
        return new EventBenefit("Weekend Discount", 2023);
    }

    public static List<EventBenefit> getRandomEventBenefits() {
        List<EventBenefit> randomBenefits = new ArrayList<>();
        return randomBenefits;
    }
}
