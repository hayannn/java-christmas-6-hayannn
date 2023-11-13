package christmas.Exception;

import java.util.List;

public class MenuException {
    private final static String ERROR = "[ERROR] ";

    public static void checkMenuNameAndCount(List<String> menuList, String orderedMenu, int menuCount){
        notRightMenuCountCase(menuCount); // 메뉴의 개수가 1 이상의 숫자만 입력되도록 한다 -> 이외의 입력값 예외 처리
        notRightMenuFormatCase(orderedMenu); //메뉴 형식이 예시와 다른 경우
        duplicationOrderedMenuCase(menuList, orderedMenu); //중복 메뉴를 입력한경우(e.g. 시저샐러드-1, 시저셀러드-1)
        menuNotInMenuListCase(menuList, orderedMenu); //고객이 메뉴판에 없는 메뉴를 입력하는 경우
    }
    public static void notRightMenuCountCase(int menuCount) {
        if (menuCount <= 0) {
            throw new IllegalArgumentException(ERROR + "유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void notRightMenuFormatCase(String orderedMenu) {
        if (orderedMenu.trim().isEmpty() || !orderedMenu.matches("^[a-zA-Z0-9가-힣 ]+-\\d+([,][a-zA-Z0-9가-힣 ]+-\\d+)*$")) {
            throw new IllegalArgumentException(ERROR + "유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }


    public static void duplicationOrderedMenuCase(List<String> menuList, String orderedMenu) {
        if (menuList.contains(orderedMenu)) {
            throw new IllegalArgumentException(ERROR + "유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void menuNotInMenuListCase(List<String> menuList, String orderedMenu) {
        String[] menuAndQuantity = orderedMenu.split("-");
        String menu = menuAndQuantity[0].trim();

        if (!menuList.contains(menu)) {
            throw new IllegalArgumentException(ERROR + "유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
