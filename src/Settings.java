package orderManager;

import java.time.format.DateTimeFormatter;

public class Settings {
    public static final String ordersFilePath = "SavedFiles/Orders",cookBookFilePath = "SavedFiles/Dishes", currency = "kč";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd- HH:mm");
}
