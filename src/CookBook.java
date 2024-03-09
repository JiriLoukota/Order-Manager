package orderManager;

import java.util.ArrayList;
import java.util.List;

public class CookBook {
    private static List<Dish> cookBook = new ArrayList<>();
    //region Getters, setters and special methods
    public static List<Dish> getCookBook() {
        return cookBook;
    }
    public static void setCookBook(List<Dish> cookBook) {
        CookBook.cookBook = cookBook;
    }
    public static void clear(){
        cookBook.clear();
    }
    public static void addDish(Dish dish){
        cookBook.add(dish);
        dish.setNoInCookBook(cookBook.size());
    }
    public static void removeDish(int index){
        cookBook.remove(index);
        reloadDishNumbers();
    }
    public static void editDish(int index, Dish dish){
        cookBook.set(index, dish);
        reloadDishNumbers();
    }
    public static Dish getDish(int index){
        return cookBook.get(index);
    }
    public static void reloadDishNumbers(){
        for(int i =0; i < cookBook.size(); i++){
            cookBook.get(i).setNoInCookBook(i)
            ;
        }
    }
    //endregion
}
