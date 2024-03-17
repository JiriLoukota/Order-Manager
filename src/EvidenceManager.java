package orderManager;

import orderManager.exceptions.FileLoadingException;
import orderManager.exceptions.FileSavingException;
import orderManager.exceptions.InvalidDataException;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class EvidenceManager {
    public static void saveEvidence() throws FileSavingException {
        //saving orders
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(Settings.ordersFilePath, false)))){
            for(Order order : OrderManager.getOrderList()){
                String line = order.toSavingFormat();
                printWriter.println(line);
            }
        }catch (Exception e){
            throw new FileSavingException(Settings.ordersFilePath + "(error in class EvidenceManager while saving orders)" +
                    e.getLocalizedMessage());
        }
        //saving dishes
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(Settings.cookBookFilePath, false)))){
            for(Dish dish : CookBook.getCookBook()){
                String line = dish.toSavingFormat();
                printWriter.println(line);
            }
        }catch (Exception e){
            throw new FileSavingException(Settings.cookBookFilePath + "(error in class EvidenceManager while saving dishes)"+
                    e.getLocalizedMessage());
        }
    }
    public static void loadEvidence() throws FileLoadingException, InvalidDataException {
        //loading Cook book
        CookBook.clear();
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(Settings.cookBookFilePath)))) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                Dish dish = new Dish(parts[0], BigDecimal.valueOf(Long.parseLong(parts[1])), Integer.parseInt(parts[2]), parts[3]);
                CookBook.addDish(dish);
            }
        } catch (FileNotFoundException e) {
            throw new FileLoadingException(Settings.cookBookFilePath + "(error in class EvidenceManager while loading dishes)" +
                    e.getLocalizedMessage());
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Could not load dishes because of corrupted data" + e.getLocalizedMessage());
        }
        //loading orders
        OrderManager.clear();
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(Settings.ordersFilePath)))) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                LocalDateTime fullfilmentTime = null;
                    fullfilmentTime = LocalDateTime.parse(parts[4], Settings.formatter);
                Order order = new Order(CookBook.getDish(Integer.parseInt(parts[0])), Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]), LocalDateTime.parse(parts[3], Settings.formatter),
                        fullfilmentTime, parts[5].equals("true"));
            }
        } catch (FileNotFoundException e) {
            throw new FileLoadingException(Settings.ordersFilePath + "(error in class EvidenceManager while loading orders)" +
                    e.getLocalizedMessage());
        }
    }

}
