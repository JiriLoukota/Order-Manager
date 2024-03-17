package orderManager;

import orderManager.exceptions.FileSavingException;
import orderManager.exceptions.InvalidDataException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    static Dish dish1, dish2, dish3, dish4;
    public static void main(String[] args) {
        //1
        try {
            EvidenceManager.loadEvidence();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        //2.1
        createTestDishes();
        //2.2
        createTestOrders();
        //3 cena pro 15
        printCostForTable(15);
        //4
        useRestaurantManagerMethods();
        //5
        try {
            EvidenceManager.saveEvidence();
        } catch (FileSavingException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    public static void createTestDishes(){
        try {
            dish1 = new Dish("Kuřecí řízek obalovaný 150g", BigDecimal.valueOf(120), 20);
            dish2 = new Dish("Hranolky 150g", BigDecimal.valueOf(50), 10);
            dish3 = new Dish("Pstruh na víně 200g", BigDecimal.valueOf(180), 35);
            dish4 = new Dish("Kofola 0,5l", BigDecimal.valueOf(30), 2);
            CookBook.addDish(dish1);
            CookBook.addDish(dish2);
            CookBook.addDish(dish3);
            CookBook.addDish(dish4);
        } catch (InvalidDataException e) {
            System.err.println("Couldn't create dish :" + e.getLocalizedMessage());
        }
    }
    public static void  createTestOrders(){
        Order order1 = new Order(CookBook.getDish(0), 15, 2,
                LocalDateTime.of(2024,3,12,18,0),
                false);
        Order order2 = new Order(CookBook.getDish(1), 15, 2, LocalDateTime.of
                (2024, 3, 12, 18, 0), false);
        Order order3 = new Order(CookBook.getDish(3), 15, 2,
                LocalDateTime.of(2024,3,12,18,0),
                LocalDateTime.of(2024,3,12, 18,10),
                false);
        Order order4 = new Order(CookBook.getDish(2), 2, 4, LocalDateTime.of(2024,3,12,19,20),
                true);
    }
    public static void printCostForTable(int tableNo){
        List<Order> orderList = OrderManager.getOrdersForTable(tableNo);
        BigDecimal price = BigDecimal.ZERO;
        for(Order order : orderList){
            price = price.add(order.getDish().getPrice().multiply(
                    BigDecimal.valueOf(order.getAmount())));
        }
        System.out.println("Celková cena pro stůl " + tableNo + " činí: " + price);
    }
    public static void useRestaurantManagerMethods(){
        System.out.println("Počet nedokončených objednávek: " + RestaurantManager.numberOfUnfinishedOrders());
        RestaurantManager.alignOrdersByOrderTime();
        System.out.println("Průměrná doba zpracování objednávek: " + RestaurantManager.getAverageTimeOfProcessingOrder());
        System.out.println(RestaurantManager.getListOfOrderedDishesToday());
        System.out.println(RestaurantManager.getFormattedOrdersForTable(15));

    }
}