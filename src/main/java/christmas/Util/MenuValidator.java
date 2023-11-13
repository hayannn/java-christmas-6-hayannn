package christmas.Util;

import christmas.Exception.MenuException;
public class MenuValidator {
    private static final String MENU_PATTERN = "^\\s*([가-힣a-zA-Z0-9]+-[1-9]\\d*)\\s*$";

    public static void validateMenu(String menu) throws IllegalArgumentException {
        if (!menu.matches(MENU_PATTERN)) {
            throw new IllegalArgumentException(menu);
        }
    }
}
