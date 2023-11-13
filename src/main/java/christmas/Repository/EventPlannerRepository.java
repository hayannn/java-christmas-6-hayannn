package christmas.Repository;

import christmas.Domain.EventPlanner;
import java.util.ArrayList;
import java.util.List;

public class EventPlannerRepository {
    private static final List<EventPlanner> eventPlanners = new ArrayList<>();

    public static void addEventPlanner(EventPlanner eventPlanner) {
        eventPlanners.add(eventPlanner);
    }

    public static List<EventPlanner> getAllEventPlanners() {
        return new ArrayList<>(eventPlanners);
    }
}
