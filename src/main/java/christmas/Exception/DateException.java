package christmas.Exception;

public class DateException {
    private final static String ERROR = "[ERROR] ";

    public void checkDate(String dates, int date) {
        notRightDate(date); //1 이상 31 이하의 숫자가 아닌 경우
    }

    public void notRightDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ERROR + "유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
