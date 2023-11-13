package christmas.Util;

import christmas.Domain.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuUtil {
    private static final List<Menu> menuList = new ArrayList<>();

    static {
        // 애피타이저
        menuList.add(new Menu("양송이수프", 6000, Menu.Category.APPETIZER));
        menuList.add(new Menu("타파스", 5500, Menu.Category.APPETIZER));
        menuList.add(new Menu("시저샐러드", 8000, Menu.Category.APPETIZER));

        // 메인
        menuList.add(new Menu("티본스테이크", 55000, Menu.Category.MAIN));
        menuList.add(new Menu("바비큐립", 54000, Menu.Category.MAIN));
        menuList.add(new Menu("해산물파스타", 35000, Menu.Category.MAIN));
        menuList.add(new Menu("크리스마스파스타", 25000, Menu.Category.MAIN));

        // 디저트
        menuList.add(new Menu("초코케이크", 15000, Menu.Category.DESSERT));
        menuList.add(new Menu("아이스크림", 5000, Menu.Category.DESSERT));

        // 음료
        menuList.add(new Menu("제로콜라", 3000, Menu.Category.DRINK));
        menuList.add(new Menu("레드와인", 60000, Menu.Category.DRINK));
        menuList.add(new Menu("샴페인", 25000, Menu.Category.DRINK));
    }

    public static List<Menu> getMenuList() {
        return menuList;
    }

    public static Optional<Menu> getMenu(String menuName) {
        return menuList.stream().filter(menu -> menu.getName().equalsIgnoreCase(menuName)).findFirst();
    }
}
