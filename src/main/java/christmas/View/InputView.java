package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Util.InputParser;

public class InputView {
    public static int getDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return InputParser.parseDate(Console.readLine());
    }
}
