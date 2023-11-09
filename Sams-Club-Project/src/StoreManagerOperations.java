import java.util.ArrayList;
import java.util.List;

/**
 * Operations that can be performed by a store manager
 */
class StoreManagerOperations extends Operations {

    //constructor to initialize the store manager operations with the user
    public StoreManagerOperations(User user) {
        super(user);
    }

    public List<Item> addItemToStoreInventory(String storeName, String itemName, String description, Category category, Double price, Long initialQuality, Long itemThreshold, String supplierName) {
        this.checkRole(this, Role.STORE_MANAGER);        Store store = findStoreByName(storeName);

        // Look for an existing item
        Item existingItem = store.getInventory().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Update the quantity of the existing item
            existingItem.setQuantity(existingItem.getQuantity() + initialQuality);
            System.out.println("Item already exists, updated quantity: " + existingItem);
        } else {
            // Add a new item
            Supplier supplier = new Supplier(supplierName);
            Item newItem = new Item(itemName, description, category, price, initialQuality, itemThreshold, supplier);
            store.getInventory().add(newItem);
            System.out.println("Item added to inventory: " + newItem);
        }
        return store.getInventory(); // Or return a copy/unmodifiable view
    }

    public void generatePurchaseOrder(Store store, Item item, Long quantity) {
        this.checkRole(this, Role.STORE_MANAGER);        PurchaseOrder purchaseOrder = new PurchaseOrder(store, item, quantity);
        this.purchaseOrderList.add(purchaseOrder);
        System.out.println("Purchase order generated: " + purchaseOrder);
    }

    public List<Item> requestItemAddition(String storeName, String itemName, String description, Category category, Double price, Long initialQuality, String supplierName) {
        this.checkRole(this, Role.STORE_MANAGER);        Long DEFAULT_THRESHOLD = 500L;
        return this.addItemToStoreInventory(storeName, itemName, description, category, price, initialQuality, DEFAULT_THRESHOLD, supplierName);
    }

    public void checkInventoryLevel(Store store) {
        this.checkRole(this, Role.STORE_MANAGER);        System.out.println("Inventory level: " + store.getInventory().size());
    }

    // Helper method to find a store by name
    private Store findStoreByName(String storeName) {
        for (Store store : Operations.storeList) { // Access via class name
            if (store.getName().equalsIgnoreCase(storeName)) {
                return store;
            }
        }
        throw new RuntimeException("Store not found...");
    }


    public List<PurchaseOrder> getPurchaseOrdersForStore(Store store) {
        this.checkRole(this, Role.STORE_MANAGER);
        List<PurchaseOrder> ordersForStore = new ArrayList<>();
        for (PurchaseOrder order : Operations.purchaseOrderList) { // Access via class name
            if (order.getStore().equals(store)) {
                ordersForStore.add(order);
            }
        }
        return ordersForStore;
    }
}
