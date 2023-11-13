package christmas.Domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.Domain.Menu.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @DisplayName("주문한 '애피타이저' 메뉴를 정확히 반환하는지 테스트한다.")
    @Test
    void 애피타이저_주문_메뉴_테스트() {
        assertSimpleTest(() -> {
            Menu 시저샐러드 = new Menu("시저샐러드", 8000, Category.APPETIZER);
            Order order = new Order(시저샐러드, 1);

            assertThat(order.getMenu()).isEqualTo(시저샐러드);
        });
    }
    @DisplayName("주문한 '메인' 메뉴를 정확히 반환하는지 테스트한다.")
    @Test
    void 메인_주문_메뉴_테스트() {
        assertSimpleTest(() -> {
            Menu 해산물파스타 = new Menu("해산물파스타", 35000, Menu.Category.MAIN);
            Order order = new Order(해산물파스타, 2);

            assertThat(order.getMenu()).isEqualTo(해산물파스타);
        });
    }

    @DisplayName("주문한 '디저트' 메뉴를 정확히 반환하는지 테스트한다.")
    @Test
    void 디저트_주문_메뉴_테스트() {
        assertSimpleTest(() -> {
            Menu 초코케이크 = new Menu("초코케이크", 15000, Category.DESSERT);
            Order order = new Order(초코케이크, 1);

            assertThat(order.getMenu()).isEqualTo(초코케이크);
        });
    }

    @DisplayName("주문한 '음료' 메뉴를 정확히 반환하는지 테스트한다.")
    @Test
    void 음료_주문_메뉴_테스트() {
        assertSimpleTest(() -> {
            Menu 샴페인 = new Menu("샴페인", 25000, Category.DRINK);
            Order order = new Order(샴페인, 2);

            assertThat(order.getMenu()).isEqualTo(샴페인);
        });
    }

    @DisplayName("애피타이저 메뉴의 주문한 수량을 정확히 반환하는지 테스트한다.")
    @Test
    void 애피타이저_주문_수량_테스트() {
        assertSimpleTest(() -> {
            Menu 양송이수프 = new Menu("양송이수프", 6000, Category.APPETIZER);
            Order order = new Order(양송이수프, 3);

            assertThat(order.getQuantity()).isEqualTo(3);
        });
    }

    @DisplayName("메인 메뉴의 주문한 수량을 정확히 반환하는지 테스트한다.")
    @Test
    void 메인_주문_수량_테스트() {
        assertSimpleTest(() -> {
            Menu 크리스마스파스타 = new Menu("크리스마스파스타", 25000, Category.MAIN);
            Order order = new Order(크리스마스파스타, 1);

            assertThat(order.getQuantity()).isEqualTo(1);
        });
    }

    @DisplayName("디저트 메뉴의 주문한 수량을 정확히 반환하는지 테스트한다.")
    @Test
    void 디저트_주문_수량_테스트() {
        assertSimpleTest(() -> {
            Menu 아이스크림 = new Menu("아이스크림", 5000, Category.DESSERT);
            Order order = new Order(아이스크림, 5);

            assertThat(order.getQuantity()).isEqualTo(5);
        });
    }

    @DisplayName("음료 메뉴의 주문한 수량을 정확히 반환하는지 테스트한다.")
    @Test
    void 음료_주문_수량_테스트() {
        assertSimpleTest(() -> {
            Menu 제로콜라 = new Menu("제로콜라", 3000, Category.DRINK);
            Order order = new Order(제로콜라, 4);

            assertThat(order.getQuantity()).isEqualTo(4);
        });
    }

    @DisplayName("애피타이저 주문의 총 가격을 정확히 계산하는지 테스트한다.")
    @Test
    void 애피타이저_주문_총가격_테스트() {
        assertSimpleTest(() -> {
            Menu 타파스 = new Menu("타파스", 5500, Menu.Category.APPETIZER);
            Order order = new Order(타파스, 4);

            assertThat(order.getTotalPrice()).isEqualTo(5500 * 4);
        });
    }

    @DisplayName("메인 주문의 총 가격을 정확히 계산하는지 테스트한다.")
    @Test
    void 메인_주문_총가격_테스트() {
        assertSimpleTest(() -> {
            Menu 바비큐립 = new Menu("바비큐립", 54000, Category.MAIN);
            Order order = new Order(바비큐립, 3);

            assertThat(order.getTotalPrice()).isEqualTo(54000 * 3);
        });
    }

    @DisplayName("디저트 주문의 총 가격을 정확히 계산하는지 테스트한다.")
    @Test
    void 디저트_주문_총가격_테스트() {
        assertSimpleTest(() -> {
            Menu 초코케이크 = new Menu("초코케이크", 15000, Category.DESSERT);
            Order order = new Order(초코케이크, 2);

            assertThat(order.getTotalPrice()).isEqualTo(15000 * 2);
        });
    }

    @DisplayName("음료 주문의 총 가격을 정확히 계산하는지 테스트한다.")
    @Test
    void 음료_주문_총가격_테스트() {
        assertSimpleTest(() -> {
            Menu 레드와인 = new Menu("레드와인", 60000, Category.DRINK);
            Order order = new Order(레드와인, 1);

            assertThat(order.getTotalPrice()).isEqualTo(60000 * 1);
        });
    }
}
