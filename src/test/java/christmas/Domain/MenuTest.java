package christmas.Domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.Domain.Menu.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @DisplayName("애피타이저 메뉴의 이름을 정확히 반환하는지 테스트한다.")
    @Test
    void 애피타이저_메뉴_이름_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("양송이수프", 6000, Category.APPETIZER);
            assertThat(menu.getName()).isEqualTo("양송이수프");
        });
    }

    @DisplayName("메인 메뉴의 이름을 정확히 반환하는지 테스트한다.")
    @Test
    void 메인_메뉴_이름_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("티본스테이크", 55000, Category.MAIN);
            assertThat(menu.getName()).isEqualTo("티본스테이크");
        });
    }

    @DisplayName("디저트 메뉴의 이름을 정확히 반환하는지 테스트한다.")
    @Test
    void 디저트_메뉴_이름_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("초코케이크", 15000, Category.DESSERT);
            assertThat(menu.getName()).isEqualTo("초코케이크");
        });
    }

    @DisplayName("음료 메뉴의 이름을 정확히 반환하는지 테스트한다.")
    @Test
    void 음료_메뉴_이름_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("제로콜라", 3000, Category.DRINK);
            assertThat(menu.getName()).isEqualTo("제로콜라");
        });
    }

    @DisplayName("메뉴의 <애피타이저> 카테코리를 정확히 반환하는지 테스트한다.")
    @Test
    void 애피타이저_메뉴_카테고리_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("타파스", 5500, Menu.Category.APPETIZER);
            assertThat(menu.getCategory()).isEqualTo(Menu.Category.APPETIZER);
        });
    }

    @DisplayName("메뉴의 <메인> 카테코리를 정확히 반환하는지 테스트한다.")
    @Test
    void 메인_메뉴_카테고리_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("바비큐립", 54000, Category.MAIN);
            assertThat(menu.getCategory()).isEqualTo(Category.MAIN);
        });
    }

    @DisplayName("메뉴의 <디저트> 카테코리를 정확히 반환하는지 테스트한다.")
    @Test
    void 디저트_메뉴_카테고리_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("아이스크림", 5000, Category.DESSERT);
            assertThat(menu.getCategory()).isEqualTo(Category.DESSERT);
        });
    }

    @DisplayName("메뉴의 <음료> 카테코리를 정확히 반환하는지 테스트한다.")
    @Test
    void 음료_메뉴_카테고리_테스트() {
        assertSimpleTest(() -> {
            Menu menu = new Menu("레드와인", 60000, Category.DRINK);
            assertThat(menu.getCategory()).isEqualTo(Category.DRINK);
        });
    }
}
