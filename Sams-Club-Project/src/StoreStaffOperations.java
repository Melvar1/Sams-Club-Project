import java.util.List;
import java.util.Optional;

class StoreStaffOperations extends Operations {

    public StoreStaffOperations(User user) {
        super(user);
    }

    public void updateItemQuantity(String storeName, String itemName) {
        this.checkRole(this, Role.STORE_STAFF);
        Store store = findStoreByName(storeName)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeName));
        Item item = findItemByName(store, itemName)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemName));

        long quantity = item.getQuantity();
        if (quantity > 0) {
            item.setQuantity(quantity - 1);
            System.out.println("Item quantity updated: " + item);
        } else {
            System.out.println("Cannot reduce quantity. No stock available for item: " + itemName);
        }
    }

    public void checkInventoryLevel(Store store) {
        this.checkRole(this, Role.STORE_STAFF);
        System.out.println("Inventory level for store '" + store.getName() + "': " + store.getInventory().size());
    }

    // This method seems to be out of place as STORE_STAFF should not be adding items to inventory
    // It could be replaced by a method that just submits a request to the manager

    private Optional<Store> findStoreByName(String storeName) {
        return this.storeList.stream()
                .filter(store -> store.getName().equals(storeName))
                .findFirst();
    }

    private Optional<Item> findItemByName(Store store, String itemName) {
        return store.getInventory().stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();
    }

    public List<Item> requestItemAddition(String storeName, String itemName, String description, Category category, Double price, Long initialQuality, String supplierName) {
        this.checkRole(this, Role.STORE_STAFF);
        Long DEFAULT_THRESHOLD = 500L;
        return storeManagerOperations.addItemToStoreInventory(storeName, itemName, description, category, price, initialQuality, DEFAULT_THRESHOLD, supplierName);
    }

    private StoreManagerOperations storeManagerOperations;

    // Constructor to initialize the store manager operations with the user
    public StoreStaffOperations(User user, StoreManagerOperations storeManagerOperations) {
        super(user);
        this.storeManagerOperations = storeManagerOperations;
    }


}
