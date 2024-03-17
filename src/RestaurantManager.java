package orderManager;

import java.util.List;

public class RestaurantManager {
    public static int numberOfUnfinishedOrders() {
        return OrderManager.getAmountOfUnfinishedOrders();
    }

    public static void alignOrdersByOrderTime() {
        OrderManager.orderOrdersByOrderedTime();
    }

    public static int getAverageTimeOfProcessingOrder() {
        return OrderManager.getAverageProcessingTime();
    }

    public static List<Dish> getListOfOrderedDishesToday() {
        return OrderManager.orderedDishesToday();
    }

    public static List<String> getFormattedOrdersForTable(int tableNo){
        return OrderManager.getFormattedOrdersForTable(tableNo);
    }
}
