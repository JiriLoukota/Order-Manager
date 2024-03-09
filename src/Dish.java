package orderManager;

import orderManager.exceptions.InvalidDataException;

import java.math.BigDecimal;

public class Dish {
    private String title, image;
    private BigDecimal price;
    private int noInCookBook, preparationTime;//in minutes

    public Dish(String title, BigDecimal price, int preparationTime, String image) throws InvalidDataException {
        this.title = title;
        this.image = image;
        setPrice(price);
        setPreparationTime(preparationTime);
    }

    public Dish(String title, BigDecimal price, int preparationTime) throws InvalidDataException {
        this(title, price, preparationTime, "blank");
    }
    //region Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws InvalidDataException {
        if(price.compareTo(BigDecimal.valueOf(0)) > 0) this.price = price;
        else throw new InvalidDataException(getClass() + "; value should be zero or bigger number.");
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) throws InvalidDataException {
        if(preparationTime > 0) this.preparationTime = preparationTime;
        else throw new InvalidDataException(getClass() + "; value should be higher than zero.");
    }

    public int getNoInCookBook() {
        return noInCookBook;
    }

    public void setNoInCookBook(int noInCookBook) {
        this.noInCookBook = noInCookBook;
    }

    //endregion
    public String toSavingFormat(){
        return title + "\t" + price + "\t" + preparationTime + "\t" + image;
    }
    @Override
    public String toString(){
        return"Dish: title: " + this.title + "; price: " + this.price.toString() + Settings.currency + "; preparation time: " +
                this.preparationTime + " minutes; image: " + image;
    }
}
