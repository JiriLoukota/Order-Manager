package orderManager;

import java.time.LocalDateTime;

public class Order {
    private Dish dish;
    private int amount;
    private LocalDateTime orderedTime, fulfilmentTime;
    private boolean paid;

    public Order(Dish dish, int amount, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, boolean paid) {
        this.dish = dish;
        this.amount = amount;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.paid = paid;
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

    //endregion
}
