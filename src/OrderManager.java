package orderManager;

import orderManager.exceptions.InvalidDataException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderManager {
    private static List<Order> orderList = new ArrayList<>();

    //region Getters and setters

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static void setOrderList(List<Order> orderList) {
        OrderManager.orderList = orderList;
    }

    //endregion

    public static void addOrder(Order order){
        orderList.add(order);
    }
    public static void clear(){
        orderList.clear();
    }
    public static int getAmountOfUnfinishedOrders(){
        int amountOfUnfinishedOrders = 0;
        for (Order order : orderList){
            if(order.getFulfilmentTime()==null) amountOfUnfinishedOrders++;
        }
        return amountOfUnfinishedOrders;
    }
    public static void orderOrdersByOrderedTime(){
        orderList.sort(Comparator.comparing(Order::getOrderedTime));
    }
    public static int getAverageProcessingTime(){
        int numberOfFinishedOrders=0, timeCombined =0;
        for (Order order: orderList){
            try {
                timeCombined += order.getProcessingTime();
                numberOfFinishedOrders++;
            } catch (InvalidDataException ignored) {}
        }
        return timeCombined/numberOfFinishedOrders;
    }
    public static List<Dish> orderedDishesToday(){
        List<Dish> dishes = new ArrayList<>();
        for (Order order : orderList){
            if(LocalDate.of(order.getOrderedTime().getYear(), order.getOrderedTime().getMonthValue(),
                    order.getOrderedTime().getDayOfMonth()).isEqual(LocalDate.now())){
                boolean alreadyWritten;
                for(int i =0; i < dishes.size(); i++){
                    alreadyWritten = false;
                    if(dishes.get(i)==order.getDish()){
                        alreadyWritten = true;
                        break;
                    }
                    if (!alreadyWritten) dishes.add(order.getDish());
                }
            }
        }
        return dishes;
    }
    public static List<Order> getOrdersForTable(int tableNo){
        List<Order> ordersForTable = new ArrayList<>();
        for(Order order : orderList){
            if(order.getTableNo()==tableNo) ordersForTable.add(order);
        }
        return ordersForTable;
    }

    public static List<String> getFormattedOrdersForTable(int tableNo){
        List<Order> orders = getOrdersForTable(tableNo);
        List<String> ordersInFormat = new ArrayList<>();
        for(int i =0; i < orders.size(); i++){
            Order o = orders.get(i);
            String paid = "";
            if (o.isPaid()) paid = "paid";
            String fulfilmentTime = "";
            if(o.getFulfilmentTime()!=null) fulfilmentTime= o.getFulfilmentTime().toLocalTime().toString();
            ordersInFormat.add(i+1 + ". " + o.getDish().getTitle() + " " +
                    o.getAmount() + " (" + o.getDish().getPrice().multiply(
                    BigDecimal.valueOf(o.getAmount())) + " " + Settings.currency
                     + "):\t" + o.getOrderedTime().toLocalTime() + "-" +
                    fulfilmentTime + "\t" + paid);
        }
        return ordersInFormat;
    }
}
