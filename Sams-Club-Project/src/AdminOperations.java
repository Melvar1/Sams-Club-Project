import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Administrative operations that can be performed by the admin
 *  and provides admin functions
 */
class AdminOperations extends Operations {
    private final List<Category> categoryList = new ArrayList<>();

    // constructor to initialize the admin operations with the user
    public AdminOperations(User user) {
        super(user);
    }

    //searches for a store manager by store name
    public User searchStoreManagerByStoreName(String storeName) {
        checkRole();
        Store store = this.searchStoreByName(storeName);
        return store.getStoreManager();
    }

//view purchase order
public PurchaseOrder viewPurchaseOrder(String purchaseOrderNumber) {
    checkRole();
    for (PurchaseOrder purchaseOrder : purchaseOrderList) {
        if (purchaseOrder.getNumber() != null && purchaseOrder.getNumber().toString().equals(purchaseOrderNumber)) {
            System.out.println("Purchase order found: " + purchaseOrder);
            return purchaseOrder;
        }
    }
    throw new RuntimeException("Purchase order not found...");
}


    /**
     * Creates a new store with the provided details.
     *
     * @param storeManager The manager of the store.
     * @param name The name of the store.
     * @param location The location of the store.
     * @param contactInformation Contact details of the store.
     * @param storeType The type of the store.
     * @return The newly created store.
     */
    public Store createStore(User storeManager, String name, Location location, ContactInformation contactInformation, String storeType) {
        checkRole();
        if (storeManager.getRole().equals(Role.STORE_MANAGER)) {
            Store store = new Store(name, location, contactInformation, storeManager, storeType, LocalDate.now());
            storeList.add(store);
            System.out.println("Store created: " + store);
            return store;
        } else {
            throw new RuntimeException("User not store manager...");
        }
    }

    //searches store by name
    public Store searchStoreByName(String storeName) {
        checkRole();
        for (Store store : storeList) {
            if (store.getName().trim().equalsIgnoreCase(storeName.trim())) {
                return store;
            }
        }

        StringBuilder availableStores = new StringBuilder();
        for (Store store : storeList) {
            if (!availableStores.isEmpty()) {
                availableStores.append(", ");
            }
            availableStores.append(store.getName());
        }

        System.out.println("Available stores: " + availableStores);
        System.out.println("Store searched for: '" + storeName + "'");
        return null;
    }



    //updates store details
    public void updateStore(Store store) {
        checkRole();
        int index = -1;
        for (int i = 0; i < storeList.size(); i++) {
            if (storeList.get(i).getName().equals(store.getName())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            storeList.set(index, store);
            System.out.println("Store updated: " + store);
        } else {
            throw new RuntimeException("Store not found...");
        }
    }


    //deletes a store
    public void deleteStore(Store store) {
        checkRole();
        boolean removed = storeList.removeIf(s -> s.getName().equals(store.getName()));
        if (!removed) {
            throw new RuntimeException("Store not found...");
        }
        System.out.println("Store deleted");
    }


    public User createUser(String storeName, String userName, Role role) {
        checkRole();
        for (Store store : storeList) {
            if (store.getName().equals(storeName)) {
                User user = new User(userName, role);
                store.addStaff(user);
                System.out.println("User created: " + user);
                return user;
            }
        }
        throw new RuntimeException("Store not found...");
    }

// updates the role of a user in a specific store
    public void updateRole(String storeName, String userName, Role role) {
        checkRole();
        for (Store store : storeList) {
            if (store.getName().equals(storeName)) {
                for (User user : store.getStaff()) {
                    if (user.getName().equals(userName)) {
                        user.setRole(role);
                        System.out.println("Role updated: " + user);
                        return;
                    }
                }
                throw new RuntimeException("User not found...");
            }
        }
        throw new RuntimeException("Store not found...");
    }


    public Category createCategory(String name) {
        checkRole();
        Category category = new Category(name);
        this.categoryList.add(category);
        System.out.println("Category created: " + category);
        return category;
    }

    /**
     * Generates a purchase order for a specified item in a store.
     *
     * @param store The store where the purchase is made.
     * @param item The item being purchased.
     * @param quantity The quantity of the item being purchased.
     * @return The generated purchase order.
     */
    public PurchaseOrder generatePurchaseOrder(Store store, Item item, Long quantity) {
        checkRole();
        PurchaseOrder purchaseOrder = new PurchaseOrder(store, item, quantity);
        purchaseOrderList.add(purchaseOrder);
        System.out.println("Purchase order generated: " + purchaseOrder);
        return purchaseOrder;
    }

    public Item searchItemByName(String storeName, String itemName) {
        checkRole();
        for (Store store : storeList) {
            if (store.getName().equals(storeName)) {
                for (Item item : store.getInventory()) {
                    if (item.getName().equals(itemName)) {
                        return item;
                    }
                }
                throw new RuntimeException("Item not found...");
            }
        }
        throw new RuntimeException("Store not found...");
    }


    public Supplier createSupplier(String supplierName) {
        checkRole();
        Supplier supplier = new Supplier(supplierName);
        supplierList.add(supplier);
        return supplier;
    }

    public Item createItem(String storeName, String itemName, String description, Category category, Double price, Long initialQuality, Long threshold, Supplier supplier) {
        this.checkRole();
        Store store = this.searchStoreByName(storeName);
        Item item = new Item(itemName, description, category, price, initialQuality, threshold, supplier);
        store.addInventory(item);
        System.out.println("Item created: " + item);
        return item;
    }


    private void checkRole() {
        if (!this.user.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Insufficient privileges to perform this operation.");
        }
    }
}