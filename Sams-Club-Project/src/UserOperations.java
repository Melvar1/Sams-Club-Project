import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

/**
 * Contains utility methods for user-related operations.
 */
class UserOperations extends Operations {

    public UserOperations() {
        super(new User("Default User", Role.DEFAULT_USER));
    }

    /**
     * Searches for stores based on location, store type, and opening date.
     *
     * @param location    The location of the store.
     * @param storeType   The type or category of the store.
     */
    public static void performStoreSearch(String location, String storeType, String openingDateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate searchOpeningDate = LocalDate.parse(openingDateString, formatter);

            // Assuming 'storeList' is a List<Store> and each Store object has 'location', 'storeType', and 'openingDate' properties
            List<Store> matchingStores = storeList.stream()
                    .filter(store -> store.getLocation().getName().equalsIgnoreCase(location))
                    .filter(store -> store.getStoreType().equalsIgnoreCase(storeType))
                    .filter(store -> store.getOpeningDate().equals(searchOpeningDate))
                    .toList();

            if (matchingStores.isEmpty()) {
                System.out.println("No stores found with the given criteria.");
            } else {
                System.out.println("Matching stores:");
                for (Store store : matchingStores) {
                    System.out.println(store);
                    // Here 'store.toString()' needs to provide a meaningful representation of the store
                }
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date. Please use the format dd/MM/yyyy.");
            // Handle the exception by maybe re-prompting the user for the date
        }
    }

    /**
     * Searches for an item within a store based on item name and category.
     *
     * @param storeName   The name of the store.
     * @param itemName    The name of the item.
     * @param categoryName The category of the item.
     */
    public static void performItemSearch(String storeName, String itemName, String categoryName) {
        Optional<Store> matchingStore = storeList.stream()
                .filter(store -> store.getName().equals(storeName))
                .findFirst();

        if (matchingStore.isPresent()) {
            Store store = matchingStore.get();
            Optional<Item> matchingItem = store.getInventory().stream()
                    .filter(item -> item.getName().equals(itemName)
                            && item.getCategory().equals(categoryName))
                    .findFirst();

            if (matchingItem.isPresent()) {
                System.out.println("Item found: " + matchingItem.get());
            } else {
                System.out.println("Item not found in the specified store.");
            }
        } else {
            System.out.println("Store not found.");
        }
    }
}
