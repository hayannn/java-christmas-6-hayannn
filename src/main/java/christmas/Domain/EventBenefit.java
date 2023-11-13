package christmas.Domain;

public class EventBenefit {
    private final String name;
    private final int discountAmount;
    private String description;

    public EventBenefit(String name, int discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
    }

    public void applyEventBenefits(EventPlanner eventPlanner) {
        eventPlanner.addDiscountDetails(name + ": -" + discountAmount + "원");
        eventPlanner.calculateTotalBenefitAmount();
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getValue() {
        return discountAmount;
    }


    public String getDescription() {
        return description;
    }
}
