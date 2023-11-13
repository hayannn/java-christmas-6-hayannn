package christmas.Service;

import christmas.Domain.EventBenefit;
import christmas.Domain.Order;
import christmas.Exception.DateException;
import christmas.Repository.EventBenefitRepository;
import java.util.ArrayList;
import java.util.List;

public class ChristmasPlannerService {
    private static final int DATE_TO_APPLY_SPECIAL_DISCOUNT = 25;

    private final List<Order> orders;
    private final EventBenefitRepository eventBenefitRepository;
    private final List<EventBenefit> appliedBenefits;

    public ChristmasPlannerService(EventBenefitRepository eventBenefitRepository) {
        this.orders = new ArrayList<>();
        this.eventBenefitRepository = eventBenefitRepository;
        this.appliedBenefits = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }



    public void processOrders(int date, List<Order> orders) {
        validateDate(date);

        this.orders.clear();
        this.appliedBenefits.clear();

        this.orders.addAll(orders);

        // 할인 및 혜택 적용
        applySpecialDiscount(date);
        applyWeekdayDiscount(date);
        applyWeekendDiscount(date);
        applyEventBenefits();
    }

    private void validateDate(int date) {
        DateException dateException = new DateException();
        dateException.checkDate("유효하지 않은 날짜입니다. 다시 입력해 주세요.", date);
    }

    private void applySpecialDiscount(int date) {
        if (date == DATE_TO_APPLY_SPECIAL_DISCOUNT) {
            appliedBenefits.add(eventBenefitRepository.getSpecialDiscount());
        }
    }

    private void applyWeekdayDiscount(int date) {
        // 주말이 아닌 평일에 할인 적용
        if (date % 7 != 0 && date % 7 != 6) {
            appliedBenefits.add(eventBenefitRepository.getWeekdayDiscount());
        }
    }

    private void applyWeekendDiscount(int date) {
        // 주말 할인 적용
        if (date % 7 == 0 || date % 7 == 6) {
            appliedBenefits.add(eventBenefitRepository.getWeekendDiscount());
        }
    }

    private void applyEventBenefits() {
        appliedBenefits.addAll(eventBenefitRepository.getRandomEventBenefits());
    }

    public List<String> calculateEventBenefits() {
        List<String> eventBenefitDetails = new ArrayList<>();
        for (EventBenefit benefit : appliedBenefits) {
            if (benefit != null && benefit.getDescription() != null) {
                eventBenefitDetails.add(benefit.getDescription());
            } else {
                eventBenefitDetails.add("없음");
            }
        }
        return eventBenefitDetails;
    }

    public int calculateTotalDiscount() {
        int totalDiscount = 0;
        for (EventBenefit benefit : appliedBenefits) {
            totalDiscount += benefit.getDiscountAmount();
        }
        return totalDiscount;
    }

    public int calculateTotalBenefit() {
        int totalBenefits = 0;
        for (EventBenefit benefit : appliedBenefits) {
            totalBenefits += benefit.getValue();
        }
        return totalBenefits;
    }

    public String getEventBadge() {
        if (appliedBenefits.isEmpty()) {
            return "없음";
        }

        // 총 혜택이 특정 금액 이상인 경우 이벤트 배지 부여
        int totalBenefits = calculateTotalBenefit();

        if (totalBenefits >= 20000) {
            return "산타";
        }

        if (totalBenefits >= 10000) {
            return "트리";
        }

        if (totalBenefits >= 5000) {
            return "별";
        }

        return "없음";
    }
}
