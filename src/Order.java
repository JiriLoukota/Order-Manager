package orderManager;

import orderManager.exceptions.InvalidDataException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

public class Order {
    private Dish dish;
    private int tableNo, amount;
    private LocalDateTime orderedTime, fulfilmentTime;
    private boolean paid;

    public Order(Dish dish, int tableNo, int amount, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, boolean paid) {
        this.dish = dish;
        this.tableNo = tableNo;
        this.amount = amount;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.paid = paid;
        OrderManager.addOrder(this);
    }
    public Order(Dish dish, int tableNo, int amount, LocalDateTime orderedTime, boolean paid) {
        this(dish, tableNo, amount, orderedTime, null, paid);
    }
    //region Getters and setters

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    //endregion

    public int getProcessingTime() throws InvalidDataException {
        if(fulfilmentTime !=null ) return (int) ChronoUnit.MINUTES.between(orderedTime, fulfilmentTime);
        throw new InvalidDataException("method getProcessingTime in class Order (order is not finished yet)");
    }

    public String toSavingFormat(){
        return dish.getNoInCookBook() + "\t" + tableNo + "\t" +amount + "\t" + orderedTime.format(Settings.formatter)
                + "\t" +fulfilmentTime.format(Settings.formatter) + "\t" +paid;
    }
    @Override
    public String toString(){
        return "Order: table number: " + tableNo + "; dish: " + dish.getTitle() + "; amount: "
                + amount + "; orderedTime: " + orderedTime + "; fulfilmentTime: " + fulfilmentTime +
                "; paid: " + true;
    }
}
