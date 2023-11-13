package christmas.Service;

import christmas.Domain.Event;
import christmas.Domain.Menu;
import christmas.Domain.Order;
import christmas.Util.EventUtil;
import christmas.Util.MenuUtil;
import christmas.Util.OrderUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private LocalDate selectedDate;
    private List<Order> orderedMenuList;
    private int totalDiscountAmount;
    private List<Event> appliedEvents;
    private int totalPriceBeforeDiscount;
    private String giftMenu;

    public OrderService(int selectedDate, String orderedMenu) {
        this.selectedDate = LocalDate.of(2023, 12, selectedDate);
        this.orderedMenuList = createOrderList(orderedMenu);
        this.totalDiscountAmount = 0;
        this.appliedEvents = new ArrayList<>();
        this.totalPriceBeforeDiscount = OrderUtil.calculateTotalPrice(orderedMenuList);
        this.giftMenu = null;
        validateOrderCount(orderedMenu);
    }

    private void validateOrderCount(String orderedMenu) {
        String[] menuInfos = orderedMenu.split(",");

        if (menuInfos.length > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void processOrder() {
        int totalOrderAmount = OrderUtil.calculateTotalPrice(orderedMenuList);

        if (totalOrderAmount >= 10000) {
            applyDiscounts();
        }
    }

    private void applyDiscounts() {
        applyChristmasDiscount();
        applyDateDiscount();
        applySpecialDiscount();
        applyGiftEvent();
    }

    private void applyDateDiscount() {
        int dayOfWeek = selectedDate.getDayOfWeek().getValue();

        int weekdayDiscount = 0;
        if (dayOfWeek >= 1 && dayOfWeek <= 4 || dayOfWeek == 7) {
            weekdayDiscount = EventUtil.applyWeekdayDiscount(orderedMenuList, selectedDate);
        }

        totalDiscountAmount += weekdayDiscount;
        if (weekdayDiscount > 0) {
            appliedEvents.add(new Event("평일 할인", weekdayDiscount));
            return;
        }

        int weekendDiscount = 0;
        if (dayOfWeek >= 6 || dayOfWeek <= 7) {
            weekendDiscount = EventUtil.applyWeekendDiscount(orderedMenuList, selectedDate);
        }

        totalDiscountAmount += weekendDiscount;
        if (weekendDiscount > 0) {
            appliedEvents.add(new Event("주말 할인", weekendDiscount));
        }
    }


    private void applySpecialDiscount() {
        int specialDiscount = EventUtil.applySpecialDiscount(selectedDate, orderedMenuList);
        totalDiscountAmount += specialDiscount;
        if (specialDiscount > 0) {
            appliedEvents.add(new Event("특별 할인", specialDiscount));
        }
    }

    private void applyChristmasDiscount() {
        int christmasDiscount = EventUtil.applyChristmasDiscount(orderedMenuList, selectedDate);
        totalDiscountAmount += christmasDiscount;
        if (christmasDiscount > 0) {
            appliedEvents.add(new Event("크리스마스 디데이 할인", christmasDiscount));
        }
    }

    private void applyGiftEvent() {
        Optional<String> giftMenuOption = EventUtil.applyGiftEvent(this);

        if (giftMenuOption.isPresent()) {
            giftMenu = giftMenuOption.get();
            int giftEventDiscount = 25000;
            totalDiscountAmount += giftEventDiscount;
            appliedEvents.add(new Event("증정 이벤트", giftEventDiscount));
        }
    }

    private List<Order> createOrderList(String orderedMenu) {
        List<Order> orderList = new ArrayList<>();
        String[] menuInfos = orderedMenu.split(",");

        boolean isDrinkOnlyOrder = true;

        for (String menuInfo : menuInfos) {
            String[] menuAndQuantity = menuInfo.trim().split("-");
            String menuName = menuAndQuantity[0].trim();
            int menuCount = Integer.parseInt(menuAndQuantity[1].trim());

            processMenu(orderList, menuName, menuCount, isDrinkOnlyOrder);
        }

        return orderList;
    }

    private void processMenu(List<Order> orderList, String menuName, int menuCount, boolean isDrinkOnlyOrder) {
        Optional<Menu> optionalMenu = MenuUtil.getMenu(menuName);
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();

            if (menu.getCategory() != Menu.Category.DRINK) {
                isDrinkOnlyOrder = false;
            }
            Order order = new Order(menu, menuCount);
            orderList.add(order);
            return;
        }

        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }


    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public List<Order> getOrderedMenuList() {
        return orderedMenuList;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public List<Event> getAppliedEvents() {
        return appliedEvents;
    }

    public String getDiscountDetails() {
        StringBuilder discountDetails = new StringBuilder("<혜택 내역>\n");

        List<Event> appliedEvents = getAppliedEvents();
        for (Event event : appliedEvents) {
            discountDetails.append(event.getEventName()).append(": ")
                    .append(OrderUtil.formatCurrency(event.getDiscountAmount())).append("\n");
        }

        Optional<String> giftMenuOption = getGiftMenu();
        giftMenuOption.ifPresent(giftMenu -> {
            discountDetails.append("증정 이벤트: ").append(giftMenu).append("\n");
        });

        return discountDetails.toString();
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public Optional<String> getGiftMenu() {
        return Optional.ofNullable(giftMenu);
    }

    public int getTotalPriceAfterDiscount() {
        // 할인 전 총 주문 금액에 할인 금액을 뺀 값에 증정 메뉴의 가격을 더하고, 증정 메뉴가 있는 경우에만 25,000원을 추가하여 반환
        int totalPriceAfterDiscount = totalPriceBeforeDiscount - totalDiscountAmount + getGiftMenuPrice();
        if (getGiftMenu().isPresent()) {
            totalPriceAfterDiscount += 25000;
        }
        return totalPriceAfterDiscount;
    }

    private int getGiftMenuPrice() {
        return getGiftMenu().map(menuName -> {
            Optional<Menu> giftMenu = MenuUtil.getMenu(menuName);
            return giftMenu.map(Menu::getPrice).orElse(0);
        }).orElse(0);
    }

    public String getFormattedSelectedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 d일");
        return selectedDate.format(formatter);
    }
}
