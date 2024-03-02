package orderManager;

import java.util.List;

public class CookBook {
    private static List<Dish> cookBook;

    public static void addDish(Dish dish){
        cookBook.add(dish);
    }
    public static void removeDish(int index){
        cookBook.remove(index);
    }
    public static void editDish(int index, Dish dish){
        cookBook.set(index, dish);
    }
}
