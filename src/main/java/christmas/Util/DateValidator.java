package christmas.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    private static final String DATE_PATTERN = "^(0?[1-9]|[12][0-9]|3[01])$";

    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

    public static boolean isValidDate(String date) {
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
