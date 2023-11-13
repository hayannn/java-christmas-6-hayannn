package christmas.Domain;

public class Event {
    private String eventName;
    private int discountAmount;

    public Event(String eventName, int discountAmount) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return String.format("%s: -%,d원", eventName, discountAmount);
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
