package christmas.Util;

import christmas.Domain.Event;
import christmas.Domain.Menu;
import christmas.Domain.Menu.Category;
import christmas.Domain.Order;
import christmas.Service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class EventUtilTest {

    @DisplayName("크리스마스 디데이 할인의 계산이 잘 되는지 테스트한다.")
    @Test
    void 크리스마스_디데이_할인_계산_테스트() {
        LocalDate christmasDate = LocalDate.of(2023, 12, 3);
        List<Order> orderedMenuList = Arrays.asList(
                new Order(new Menu("티본스테이크", 55000, Menu.Category.MAIN), 1),
                new Order(new Menu("바비큐립", 54000, Category.MAIN), 1),
                new Order(new Menu("초코케이크", 15000, Category.DESSERT), 2),
                new Order(new Menu("제로콜라", 3000, Category.DRINK), 1)
        );

        int christmasDiscount = EventUtil.applyChristmasDiscount(orderedMenuList, christmasDate);

        assertThat(christmasDiscount).isEqualTo(1200);
    }

    @DisplayName("평일 할인의 계산이 잘 되는지 테스트한다.")
    @Test
    void 평일_할인_계산_테스트() {
        LocalDate weekdayDate = LocalDate.of(2023, 12, 3);
        List<Order> orderedMenuList = Arrays.asList(
                new Order(new Menu("티본스테이크", 55000, Menu.Category.MAIN), 1),
                new Order(new Menu("바비큐립", 54000, Category.MAIN), 1),
                new Order(new Menu("초코케이크", 15000, Category.DESSERT), 2),
                new Order(new Menu("제로콜라", 3000, Category.DRINK), 1)
        );

        int weekdayDiscount = EventUtil.applyWeekdayDiscount(orderedMenuList, weekdayDate);

        assertThat(weekdayDiscount).isEqualTo(4046);
    }

    @DisplayName("주말 할인의 계산이 잘 되는지 테스트한다.")
    @Test
    void 주말_할인_계산_테스트() {
        LocalDate weekendDate = LocalDate.of(2023, 12, 9);
        List<Order> orderedMenuList = Arrays.asList(
                new Order(new Menu("티본스테이크", 55000, Menu.Category.MAIN), 1),
                new Order(new Menu("바비큐립", 54000, Category.MAIN), 1),
                new Order(new Menu("초코케이크", 15000, Category.DESSERT), 2),
                new Order(new Menu("제로콜라", 3000, Category.DRINK), 1)
        );

        int weekendDiscount = EventUtil.applyWeekendDiscount(orderedMenuList, weekendDate);

        assertThat(weekendDiscount).isEqualTo(4046);
    }

    @DisplayName("특별 할인의 계산이 잘 되는지 테스트한다.")
    @Test
    void 특별_할인_계산_테스트() {
        LocalDate specialDate = LocalDate.of(2023, 12, 3);
        List<Order> orderedMenuList = Arrays.asList(
                new Order(new Menu("티본스테이크", 55000, Menu.Category.MAIN), 1),
                new Order(new Menu("바비큐립", 54000, Category.MAIN), 1),
                new Order(new Menu("초코케이크", 15000, Category.DESSERT), 2),
                new Order(new Menu("제로콜라", 3000, Category.DRINK), 1)
        );

        int specialDiscount = EventUtil.applySpecialDiscount(specialDate, orderedMenuList);

        assertThat(specialDiscount).isEqualTo(1000);
    }

    @DisplayName("증정 이벤트의 계산이 잘 되는지 테스트한다.")
    @Test
    void 증정_이벤트_계산_테스트() {
        OrderService orderService = new OrderService(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        Optional<String> giftMenu = EventUtil.applyGiftEvent(orderService);

        assertThat(giftMenu).contains("샴페인 1개");
    }

    @DisplayName("총헤택 금액에 따른 이벤트 배지가 잘 부여돼는지 테스트한다.")
    @Test
    void 총혜택금액에_따른_이벤트_배지_부여_테스트() {
        String santaBadge = EventUtil.calculateBadge(25000);
        String treeBadge = EventUtil.calculateBadge(15000);
        String starBadge = EventUtil.calculateBadge(8000);
        String nonebadge = EventUtil.calculateBadge(3000);

        assertThat(santaBadge).isEqualTo("산타");
        assertThat(treeBadge).isEqualTo("트리");
        assertThat(starBadge).isEqualTo("별");
        assertThat(nonebadge).isEqualTo("없음");
    }

    @DisplayName("다른 할인과 기간이 다른 크리스마스 디데이 할인의 이벤트 개수가 25개가 맞는지 테스트한다.")
    @Test
    void 크리스마스_디데이_할인_기간_테스트() {
        List<Event> events = EventUtil.getEvents();

        assertThat(events).hasSize(25);
    }
}