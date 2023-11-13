package christmas.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

    @DisplayName("총주문금액이 10,000원 미만인 경우 할인이 적용되지 않는 것이 잘 동작하는지 테스트한다.")
    @Test
    void 총주문금액_최소금액_미만_할인미적용_테스트() {
        String orderedMenu = "양송이수프-1";
        OrderService orderService = new OrderService(12, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("할인 전 총주문 금액이 주문한 금액을 계산할 결과와 일치하는지 테스트한다.")
    @Test
    void 할인전_총주문_금액_일치_테스트() {
        String orderedMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderService orderService = new OrderService(3, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalPriceBeforeDiscount()).isEqualTo(142000);
    }

    @DisplayName("모든 할인이 적용된 금액이 기대하는 총혜택 금액이 맞는지 테스트한다.")
    @Test
    void 모든_할인_적용_테스트() {
        String orderedMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderService orderService = new OrderService(3, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalDiscountAmount()).isEqualTo(31246);
        assertThat(orderService.getAppliedEvents()).extracting("eventName").contains("크리스마스 디데이 할인");
        assertThat(orderService.getAppliedEvents()).extracting("eventName").contains("평일 할인");
        assertThat(orderService.getAppliedEvents()).extracting("eventName").contains("증정 이벤트");
    }

    @DisplayName("할인 후의 예상 결제 금액이 기대하는 값과 일치하는지 테스트한다.")
    @Test
    void 할인후_예상_결제_금액_일치_테스트() {
        String orderedMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderService orderService = new OrderService(3, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalPriceAfterDiscount()).isEqualTo(135754);
    }

    @DisplayName("할인 후 예상 결제 금액에 증정이벤트인 샴페인 가격은 제외되는지 테스트한다.")
    @Test
    void 할인후_예상_결제_금액_증정이벤트_가격제외_일치_테스트() {
        String orderedMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderService orderService = new OrderService(3, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalPriceAfterDiscount()).isEqualTo(135754);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 미만일 때의 증정 이벤트 미적용을 테스트한다.")
    @Test
    void 증정이벤트_미적용_테스트() {
        String orderedMenu = "타파스-1,제로콜라-1";
        OrderService orderService = new OrderService(26, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalPriceBeforeDiscount()).isEqualTo(8500);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이상일 때의 증정 이벤트 적용을 테스트한다.")
    @Test
    void 증정이벤트_적용_테스트() {
        String orderedMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderService orderService = new OrderService(3, orderedMenu);

        orderService.processOrder();

        assertThat(orderService.getTotalPriceBeforeDiscount()).isEqualTo(142000);
    }

    @DisplayName("고객이 메뉴에 없는 주문을 할 경우 예외 메시지를 출력하는지 테스트한다.")
    @Test
    void 메뉴에_없는_주문_예외_테스트() {
        String orderedMenu = "파스타-1";

        OrderService orderService = new OrderService(3, orderedMenu);
        orderService.processOrder();

        assertThat(orderService.getOrderedMenuList()).isEmpty();
    }

    @DisplayName("고객이 입력하는 날짜를 프로그램이 이용하는 날짜와 정확히 포맷하는지 테스트한다.")
    @Test
    void 날짜_포맷_테스트() {
        OrderService orderService = new OrderService(26, "타파스-1,제로콜라-1");

        String formattedDate = orderService.getFormattedSelectedDate();

        assertThat(formattedDate).isEqualTo("12월 26일");
    }
}

