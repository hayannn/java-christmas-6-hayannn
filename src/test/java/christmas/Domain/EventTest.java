package christmas.Domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @DisplayName("크리스마스 디데이 할인의 이름을 확인한다.")
    @Test
    void 크리스마스_디데이_할인_이름_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("크리스마스 디데이 할인", 1200);
            assertThat(event.getEventName()).isEqualTo("크리스마스 디데이 할인");
        });
    }

    @DisplayName("평일 할인의 이름을 확인한다.")
    @Test
    void 평일_할인_이름_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("평일 할인",  4046);
            assertThat(event.getEventName()).isEqualTo("평일 할인");
        });
    }

    @DisplayName("주말 할인의 이름을 확인한다.")
    @Test
    void 주말_할인_이름_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("주말 할인",  2023);
            assertThat(event.getEventName()).isEqualTo("주말 할인");
        });
    }

    @DisplayName("특별 할인의 이름을 확인한다.")
    @Test
    void 특별_할인_이름_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("특별 할인",  1000);
            assertThat(event.getEventName()).isEqualTo("특별 할인");
        });
    }

    @DisplayName("증정 이벤트의 이름을 확인한다.")
    @Test
    void 증정_이벤트_이름_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("증정 이벤트",  25000);
            assertThat(event.getEventName()).isEqualTo("증정 이벤트");
        });
    }

    @DisplayName("크리스마스 디데이 할인의 출력이 되는지 확인한다.")
    @Test
    void 크리스마스_디데이_할인_출력_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("크리스마스 디데이 할인", 1200);
            assertThat(event.getEventDescription()).isEqualTo("크리스마스 디데이 할인: -1,200원");
        });
    }

    @DisplayName("평일 할인의 출력이 되는지 확인한다.")
    @Test
    void 평일_할인_출력_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("평일 할인", 4046);
            assertThat(event.getEventDescription()).isEqualTo("평일 할인: -4,046원");
        });
    }

    @DisplayName("주말 할인의 출력이 되는지 확인한다.")
    @Test
    void 주말_할인_출력_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("주말 할인", 2023);
            assertThat(event.getEventDescription()).isEqualTo("주말 할인: -2,023원");
        });
    }

    @DisplayName("특별 할인의 출력이 되는지 확인한다.")
    @Test
    void 특별_할인_출력_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("특별 할인", 1000);
            assertThat(event.getEventDescription()).isEqualTo("특별 할인: -1,000원");
        });
    }
    @DisplayName("증정 이벤트의 출력이 되는지 확인한다.")

    @Test
    void 증정_이벤트_출력_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("증정 이벤트", 25000);
            assertThat(event.getEventDescription()).isEqualTo("증정 이벤트: -25,000원");
        });
    }

    @DisplayName("크리스마스 디데이 할인의 금액이 잘 인식되는지 확인한다.")
    @Test
    void 크리스마스_디데이_할인_금액_일치_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("크리스마스 디데이 할인", 1200);
            assertThat(event.getDiscountAmount()).isEqualTo(1200);
        });
    }

    @DisplayName("평일 할인의 금액이 잘 인식되는지 확인한다.")
    @Test
    void 평일_할인_금액_일치_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("평일 할인", 4046);
            assertThat(event.getDiscountAmount()).isEqualTo(4046);
        });
    }

    @DisplayName("주말 할인의 금액이 잘 인식되는지 확인한다.")
    @Test
    void 주말_할인_금액_일치_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("주말 할인", 2023);
            assertThat(event.getDiscountAmount()).isEqualTo(2023);
        });
    }

    @DisplayName("특별 할인의 금액이 잘 인식되는지 확인한다.")
    @Test
    void 특별_할인_금액_일치_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("특별 할인", 1000);
            assertThat(event.getDiscountAmount()).isEqualTo(1000);
        });
    }

    @DisplayName("증정 이벤트의 금액이 잘 인식되는지 확인한다.")
    @Test
    void 증정_이벤트_금액_일치_테스트() {
        assertSimpleTest(() -> {
            Event event = new Event("증정 이벤트", 25000);
            assertThat(event.getDiscountAmount()).isEqualTo(25000);
        });
    }
}
