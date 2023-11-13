package christmas.Domain;

import christmas.Repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private final String visitDate;
    private final Order order;
    private final List<String> orderedMenu;
    private final List<Integer> menuCounts;
    private final List<String> discountDetails;
    private int totalOrderAmount;
    private int totalDiscountAmount;
    private int totalBenefitAmount;

    private SpecialDiscount specialDiscount;

    private ChristmasDdayDiscount christmasDdayDiscount;
    private WeekdayDiscount weekdayDiscount;

    protected WeekendDiscount weekendDiscount;
    private EventBenefit eventBenefit;

    public EventPlanner(String visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
        this.orderedMenu = new ArrayList<>();
        this.menuCounts = new ArrayList<>();
        this.discountDetails = new ArrayList<>();
        this.totalOrderAmount = 0;
        this.totalDiscountAmount = 0;
        this.totalBenefitAmount = 0;
    }

    public void addDiscountDetails(String discountDetail) {
        discountDetails.add(discountDetail);
    }


    public void calculateTotalOrderAmount() {
        for (int i = 0; i < orderedMenu.size(); i++) {
            String menu = orderedMenu.get(i);
            int quantity = menuCounts.get(i);
            totalOrderAmount += OrderRepository.getOrderPrice(menu) * quantity;
        }
    }

    public void applyDiscounts() {
        calculateTotalOrderAmount();
        if (specialDiscount != null) {
            specialDiscount.applyDiscountSpecial(this);
        }

        if (christmasDdayDiscount != null) {
            christmasDdayDiscount.applyDiscountChristmas(this);
        }

        if (weekdayDiscount != null) {
            weekdayDiscount.applyDiscount(this);
        }

        if (weekendDiscount != null) {
            weekendDiscount.applyDiscount(this);
        }

        applyEventBenefits();
    }

    private void applyEventBenefits() {
        if (eventBenefit != null) {
            eventBenefit.applyEventBenefits(this);
        }
    }

    public void calculateTotalDiscountAmount() {
        for (String detail : discountDetails) {
            String[] discountInfo = detail.split(":");
            totalDiscountAmount += Integer.parseInt(discountInfo[1].trim().replace(",", ""));
        }
    }

    public void calculateTotalBenefitAmount() {
        totalBenefitAmount = totalDiscountAmount;
    }

    public int calculateTotalAmountToPay() {
        return Math.max(totalOrderAmount - totalDiscountAmount, 0);
    }

    public String getVisitDate() {
        return visitDate;
    }

    public List<String> getOrderedMenu() {
        return orderedMenu;
    }

    public List<Integer> getMenuCounts() {
        return menuCounts;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public void setSpecialDiscount(SpecialDiscount specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public void setWeekdayDiscount(WeekdayDiscount weekdayDiscount) {
        this.weekdayDiscount = weekdayDiscount;
    }

    public void setWeekendDiscount(WeekendDiscount weekendDiscount){this.weekendDiscount = weekendDiscount; }
    public void setEventBenefit(EventBenefit eventBenefit) {
        this.eventBenefit = eventBenefit;
    }

    @Override
    public String toString() {
        return "EventPlanner{" +
                "visitDate='" + visitDate + '\'' +
                ", order=" + order +
                ", orderedMenu=" + orderedMenu +
                ", menuCounts=" + menuCounts +
                ", discountDetails=" + discountDetails +
                ", totalOrderAmount=" + totalOrderAmount +
                ", totalDiscountAmount=" + totalDiscountAmount +
                ", totalBenefitAmount=" + totalBenefitAmount +
                ", specialDiscount=" + specialDiscount +
                ", weekdayDiscount=" + weekdayDiscount +
                ", weekendDiscount=" + weekendDiscount +
                ", eventBenefit=" + eventBenefit +
                '}';
    }
}
