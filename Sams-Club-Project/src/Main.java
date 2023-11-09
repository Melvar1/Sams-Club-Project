import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitRequested = false;

        while (!exitRequested) {
            displayMenu();  // Display the menu options

            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (input) {
                    case 1:
                        performSearchOperation();
                        break;
                    case 2:
                        performOtherOperations();
                        break;
                    case 3:
                        exitRequested = true;
                        scanner.close(); // Close the scanner when exiting
                        System.out.println("Exiting the application...");
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the newline to reset the scanner
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Please select operation to perform: ");
        System.out.println("1. Search for store or item");
        System.out.println("2. Operations");
        System.out.println("3. Exit");
        System.out.print("Your choice: ");
    }


    public static void performOtherOperations() {
        System.out.println(" Please select role: ");
        System.out.println(" 1. ADMIN");
        System.out.println(" 2. STORE MANAGER");
        System.out.println(" 3. STORE STAFF");
        System.out.print(" (1/2/3)?: ");

        try {
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    performAdminOperations();
                    break;
                case 2:
                    performStoreManagerOperations();
                    break;
                case 3:
                    performStoreStaffOperations();
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
            scanner.nextLine(); // This will discard the incorrect input
        }
    }

    /**
     * Handles operations specific to the ADMIN role.
     * Allows an ADMIN user to perform tasks such as creating or updating stores, users, and items.
     */
    public static void performAdminOperations() {
        boolean keepRunning = true;
        System.out.print(" Enter name (Firstname Lastname): ");
        String adminName = scanner.nextLine(); // Get the admins name
        User admin = new User(adminName, Role.ADMIN);
        AdminOperations adminOperations = new AdminOperations(admin);

        while (keepRunning) {
            System.out.println(" Please select operation to perform: ");
            System.out.println(" 1. Create store ");
            System.out.println(" 2. Update store ");
            System.out.println(" 3. Delete store ");
            System.out.println(" 4. Create user ");
            System.out.println(" 5. Update user roles ");
            System.out.println(" 6. Create category ");
            System.out.println(" 7. Generate purchase order ");
            System.out.println(" 8. Create item ");
            System.out.println(" 9. View purchase order ");
            System.out.print(" Input: ");

            int operationInput;
            try {
                operationInput = scanner.nextInt();
                scanner.nextLine(); // To consume the rest of the line after the number
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number for the operation.");
                scanner.nextLine(); // To discard the incorrect input
                continue; // Skip to the next iteration of the loop
            }

        Store store;
        switch (operationInput) {
            case 1:
                System.out.println(" Please enter comma separated values Store manager name, store name, store location, store phone number (xxx xxxx xxx), store type");
                String storeCreateInput = scanner.nextLine();
                String[] storeCreateInputArray = storeCreateInput.split(",");

                // Call isValidInput to check if the input has the expected length
                if (isValidInput(storeCreateInputArray)) {
                    // If valid, proceed with creating the store
                    User storeManager = new User(storeCreateInputArray[0].trim(), Role.STORE_MANAGER); // trim() is used to remove any leading or trailing spaces
                    store = adminOperations.createStore(
                            storeManager,
                            storeCreateInputArray[1].trim(),
                            new Location(storeCreateInputArray[2].trim()),
                            new ContactInformation(storeCreateInputArray[3].trim()),
                            storeCreateInputArray[4].trim()
                    );
                    System.out.println("Store created: " + store);
                }
                break;
            case 2:
                System.out.println(" Please enter store name to be updated");
                String storeNameInput = scanner.nextLine();
                store = adminOperations.searchStoreByName(storeNameInput);
                System.out.println(" Please enter comma separated values new store manager name, new store name, new store location, new store phone number (xxx xxxx xxx)");
                storeNameInput = scanner.nextLine();
                String[] storeUpdateInputArray = storeNameInput.split(",");
                if (storeUpdateInputArray.length == 4) {
                    User storeManager = new User(storeUpdateInputArray[0], Role.STORE_MANAGER);
                    store.setStoreManager(storeManager);
                    store.setName(storeUpdateInputArray[1]);
                    Location location = new Location(storeUpdateInputArray[2]);
                    store.setLocation(location);
                    ContactInformation contactInformation = new ContactInformation(storeUpdateInputArray[3]);
                    store.setContactInformation(contactInformation);
                    adminOperations.updateStore(store);
                } else {
                    System.out.println("Invalid input");
                }
                break;
            case 3:
                System.out.println(" Please enter store name to be deleted");
                String storeNameToBeDeleted = scanner.nextLine();
                Store storeToBeDeleted = adminOperations.searchStoreByName(storeNameToBeDeleted);
                if (storeToBeDeleted != null) {
                    adminOperations.deleteStore(storeToBeDeleted);
                } else {
                    System.out.println("Store not found.");
                }

                break;
            case 4:
                System.out.println(" Please enter comma separated values Store name, User name, role (available roles: ADMIN, STORE_MANAGER OR STORE_STAFF)");
                String userCreationInput = scanner.nextLine();
                String[] userCreationInputArray = userCreationInput.split(",");
                if (userCreationInputArray.length == 3) {
                    Role role;
                    if (userCreationInputArray[2].equalsIgnoreCase("ADMIN")) {
                        role = Role.ADMIN;
                    } else if (userCreationInputArray[2].equalsIgnoreCase("STORE_MANAGER")) {
                        role = Role.STORE_MANAGER;
                    } else {
                        role = Role.STORE_STAFF;
                    }

                    User user = adminOperations.createUser(userCreationInputArray[0], userCreationInputArray[1], role);
                    System.out.println("User created: " + user);
                } else {
                    System.out.println("Invalid input");
                }
                break;
            case 5:
                System.out.println(" Please enter comma separated values Store name, user name, new role (available roles: ADMIN, STORE_MANAGER OR STORE_STAFF)");
                String userUpdateInput = scanner.nextLine();
                String[] userUpdateInputArray = userUpdateInput.split(",");
                if (userUpdateInputArray.length == 3) {
                    Role role;
                    if (userUpdateInputArray[2].equalsIgnoreCase("ADMIN")) {
                        role = Role.ADMIN;
                    } else if (userUpdateInputArray[2].equalsIgnoreCase("STORE_MANAGER")) {
                        role = Role.STORE_MANAGER;
                    } else {
                        role = Role.STORE_STAFF;
                    }

                    adminOperations.updateRole(userUpdateInputArray[0], userUpdateInputArray[1], role);
                } else {
                    System.out.println("Invalid input");
                }
                break;
            case 6:
                System.out.println(" Please enter category name");
                String categoryInput = scanner.nextLine();
                adminOperations.createCategory(categoryInput);
                break;
            case 7:
                System.out.println(" Please enter comma separated values Store name, item name, quantity");
                String purchaseOrderGenerationInput = scanner.nextLine();
                String[] purchaseOrderGenerationInputArray = purchaseOrderGenerationInput.split(",");
                if (purchaseOrderGenerationInputArray.length == 3) {
                    try {
                        Store storeSearch = adminOperations.searchStoreByName(purchaseOrderGenerationInputArray[0]);
                        if (storeSearch == null) {
                            System.out.println("Store not found.");
                            break; // Exit the case if the store is not found
                        }
                        Item itemSearch = adminOperations.searchItemByName(purchaseOrderGenerationInputArray[0], purchaseOrderGenerationInputArray[1]);
                        if (itemSearch == null) {
                            System.out.println("Item not found.");
                            break; // Exit the case if the item is not found
                        }
                        long quantity = Long.parseLong(purchaseOrderGenerationInputArray[2]);
                        PurchaseOrder purchaseOrder = adminOperations.generatePurchaseOrder(storeSearch, itemSearch, quantity);
                        System.out.println("Purchase order created: " + purchaseOrder);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for quantity. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid input");
                }
                break;
            case 8:
                System.out.println(" Please enter comma separated values Store name, item name, item description, item category, item price, item initial quantity, item threshold, item supplier name");
                String createItemInput = scanner.nextLine();
                String[] createItemInputArray = createItemInput.split(",");
                if (createItemInputArray.length == 8) {
                    try {
                        Category category = adminOperations.createCategory(createItemInputArray[3]);
                        Supplier supplier = adminOperations.createSupplier(createItemInputArray[7]);
                        double price = Double.parseDouble(createItemInputArray[4]);
                        double initialQuantity = Double.parseDouble(createItemInputArray[5]);
                        long threshold = Long.parseLong(createItemInputArray[6]);
                        Item item = adminOperations.createItem(createItemInputArray[0], createItemInputArray[1], createItemInputArray[2], category, price, (long) initialQuantity, threshold, supplier);
                        System.out.println("Item created: " + item);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for numeric values. Please ensure your price, initial quantity, and threshold are numbers.");
                    }
                } else {
                    System.out.println("Invalid input");
                }
                break;
            case 9:
                System.out.println(" Please enter purchase order number");
                String purchaseOrderNumberInput = scanner.nextLine();
                PurchaseOrder purchaseOrder = adminOperations.viewPurchaseOrder(purchaseOrderNumberInput);
                System.out.println("Purchase order found: " + purchaseOrder);
                break;
            default:
                System.out.println("Unknown operation. Please try again.");
                break;

        }
            System.out.print("Do you want to perform another operation? (yes/no): ");
            String userDecision = scanner.nextLine().trim().toLowerCase();
            if (!userDecision.equals("yes")) {
                keepRunning = false;
            }
        }
    }


    /**
     * Handles operations specific to the STORE MANAGER role.
     * Allows a STORE MANAGER to perform tasks such as adding items to inventory or generating purchase orders.
     */
    public static void performStoreManagerOperations() {
        System.out.print("Enter store name: ");
        String storeName = scanner.nextLine();

        User systemUser = new User("SYSTEM_GENERATED_USER", Role.ADMIN);
        AdminOperations adminOperations = new AdminOperations(systemUser);
        Store store = adminOperations.searchStoreByName(storeName);

        User storeManager = adminOperations.searchStoreManagerByStoreName(storeName);
        StoreManagerOperations storeManagerOperations = new StoreManagerOperations(storeManager);

        System.out.println(" Please select operation to perform: ");
        System.out.println(" 1. Add items to inventory ");
        System.out.println(" 2. Get inventory level ");
        System.out.println(" 3. Generate purchase order ");
        System.out.println(" 4. View purchase order ");
        System.out.print(" Input: ");

        int operationInput = scanner.nextInt();
        scanner.nextLine();

        switch (operationInput) {
            case 1:
                System.out.println("Please enter comma separated values item name, description, category name, " +
                        "item price, initial quality, item threshold, supplier name");
                String itemCreation = scanner.nextLine();
                String[] itemCreationArray = itemCreation.split(",");
                if(itemCreationArray.length == 7) {
                    storeManagerOperations.addItemToStoreInventory(storeName, itemCreationArray[0], itemCreationArray[1],
                            new Category(itemCreationArray[2]), Double.parseDouble(itemCreationArray[3]),
                            Long.valueOf(itemCreationArray[4]), Long.valueOf(itemCreationArray[5]),
                            itemCreationArray[6]);
                } else
                    System.out.println("Invalid input");
                break;
            case 2:
                storeManagerOperations.checkInventoryLevel(store);
                break;
            case 3:
                System.out.println("Please enter comma separated values item name, quantity");
                String purchaseOrderCreation = scanner.nextLine();
                String[] purchaseOrderCreationArray = purchaseOrderCreation.split(",");
                if(purchaseOrderCreationArray.length == 2) {
                    Item item = adminOperations.searchItemByName(store.getName(), purchaseOrderCreationArray[0]);
                    storeManagerOperations.generatePurchaseOrder(store, item, Long.valueOf(purchaseOrderCreationArray[1]));
                } else
                    System.out.println("Invalid input");
                break;
            case 4:
                System.out.println(" Please enter purchase order number");
                String purchaseOrderNumberInput = scanner.nextLine();
                adminOperations.viewPurchaseOrder(purchaseOrderNumberInput);
                break;
        }
    }

    // Handles the addition of items to the store's inventory
    private static void handleAddItemsToInventory(StoreManagerOperations managerOperations, Store store) {
        System.out.println("Enter the details of the item to add to the inventory:");
        System.out.print("Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Category: ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Initial Quantity: ");
        long initialQuantity = Long.parseLong(scanner.nextLine());
        System.out.print("Supplier Name: ");
        String supplierName = scanner.nextLine();

        List<Item> updatedInventory = managerOperations.addItemToStoreInventory(
                store.getName(), itemName, description, category, price, initialQuantity, 10L, supplierName
        );

        System.out.println("Item added to inventory. Updated Inventory: " + updatedInventory);
    }

    // Handles the retrieval of inventory levels for the store
    private static void handleGetInventoryLevel(StoreManagerOperations managerOperations, Store store) {
        System.out.println("The inventory level for the store '" + store.getName() + "' is:");
        managerOperations.checkInventoryLevel(store);
    }

    // Handles the generation of a purchase order for the store
    private static void handleGeneratePurchaseOrder(StoreManagerOperations managerOperations, Store store) {
        System.out.println("Enter the details for generating a new purchase order:");
        System.out.print("Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Quantity: ");
        long quantity = Long.parseLong(scanner.nextLine());


        Item item = store.getInventory().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found in inventory."));

        managerOperations.generatePurchaseOrder(store, item, quantity);
        System.out.println("Purchase order generated for " + quantity + " of " + itemName);
    }

    // Handles the viewing of purchase orders for the store
    private static void handleViewPurchaseOrder(StoreManagerOperations managerOperations, Store store) {
        List<PurchaseOrder> purchaseOrders = managerOperations.getPurchaseOrdersForStore(store);
        if (purchaseOrders.isEmpty()) {
            System.out.println("There are no purchase orders for the store: " + store.getName());
        } else {
            System.out.println("Purchase orders for the store '" + store.getName() + "':");
            purchaseOrders.forEach(System.out::println);
        }
    }


    /**
     * Handles operations specific to the STORE STAFF role.
     * Allows a STORE STAFF member to perform tasks such as checking inventory levels or requesting item additions.
     */
    public static void performStoreStaffOperations() {
        System.out.print("Enter comma-separated store name, user name): ");
        String inputString = scanner.nextLine();
        String[] inputStringArray = inputString.split(",");

        if (inputStringArray.length == 2) {
            User systemUser = new User("SYSTEM_GENERATED_USER", Role.ADMIN);
            AdminOperations adminOperations = new AdminOperations(systemUser);
            User storeStaff = adminOperations.searchStoreManagerByStoreName(inputStringArray[0].trim());
            Store store = adminOperations.searchStoreByName(inputStringArray[1].trim());
            StoreStaffOperations storeStaffOperations = new StoreStaffOperations(storeStaff);

            boolean isRunning = true;
            while (isRunning) {
                System.out.println("Please select operation to perform:");
                System.out.println("1. Get inventory level");
                System.out.println("2. Request item addition");
                System.out.println("3. Update item quantities");
                System.out.println("0. Exit");
                System.out.print("Input (0-3): ");

                try {
                    int operationInput = Integer.parseInt(scanner.nextLine());
                    switch (operationInput) {
                        case 1:
                            storeStaffOperations.checkInventoryLevel(store);
                            break;
                        case 2:
                        case 3:
                            handleItemOperation(store, storeStaffOperations, operationInput);
                            break;
                        case 0:
                            isRunning = false; // Exit the loop and the method
                            System.out.println("Exiting staff operations.");
                            break;
                        default:
                            System.out.println("Invalid option selected. Please choose between 0 and 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (0-3).");
                }
            }
        } else {
            System.out.println("Invalid input format. Please enter two values separated by a comma.");
        }
    }

    private static void handleItemOperation(Store store, StoreStaffOperations storeStaffOperations, int operation) {
        System.out.println("Please enter comma separated values item name, item description, item category, item price, item initial quantity, item threshold, item supplier name");
        String createItemInput = scanner.nextLine();
        String[] createItemInputArray = createItemInput.split(",");

        if (createItemInputArray.length == 7) {
            Category category = new Category(createItemInputArray[2].trim());
            if (operation == 2) {
                List<Item> itemList = storeStaffOperations.requestItemAddition(store.getName(), createItemInputArray[0].trim(), createItemInputArray[1].trim(), category, Double.parseDouble(createItemInputArray[3].trim()), Long.parseLong(createItemInputArray[4].trim()), createItemInputArray[5].trim());
                System.out.println("Items found: " + itemList);
            } else {
                storeStaffOperations.updateItemQuantity(store.getName(), createItemInputArray[0].trim());
            }
        } else {
            System.out.println("Invalid input. Please enter seven values separated by commas.");
        }
    }


    /**
     * Provides options for users to search either for a store or an item.
     */
    public static void performSearchOperation() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("1. Search for store");
            System.out.println("2. Search for item");
            System.out.println("0. Exit");
            System.out.print("Choose an option (1/2) or 0 to exit: ");

            try {
                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        handleStoreSearch();
                        break;
                    case 2:
                        handleItemSearch();
                        break;
                    case 0:
                        isRunning = false; // Exit the loop and the method
                        System.out.println("Exiting search operation.");
                        break;
                    default:
                        System.out.println("Invalid option selected. Please choose 1, 2, or 0.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 0).");
            }
        }
    }

    private static void handleStoreSearch() {
        System.out.println("Please enter comma separated values: Location, Store type, Opening date (dd/MM/yyyy)");
        String[] storeSearchValues = scanner.nextLine().split(",");
        if (storeSearchValues.length == 3) {
            performStoreSearch(storeSearchValues[0].trim(), storeSearchValues[1].trim(), storeSearchValues[2].trim());
        } else {
            System.out.println("Invalid input. Please enter three values separated by commas.");
        }
    }

    private static void handleItemSearch() {
        System.out.println("Please enter comma separated values: Store name, Item name, Category name, Price range (x - y)");
        String input = scanner.nextLine();
        String[] itemSearchValues = splitInput(input); // Expecting 4 values

        if (itemSearchValues != null) {
            String priceRange = itemSearchValues[3].trim();
            String[] prices = priceRange.split("-");
            if (prices.length == 2 && isValidPrice(prices[0].trim()) && isValidPrice(prices[1].trim())) {
                performItemSearch(itemSearchValues[0].trim(), itemSearchValues[1].trim(), itemSearchValues[2].trim());
            } else {
                System.out.println("Invalid price range. Please enter a valid range (x - y).");
            }
        }
    }

    private static boolean isValidInput(String[] inputArray) {
        if (inputArray.length == 5) {
            return true;
        } else {
            System.out.println("Invalid input: Expected " + 5 + " fields but got " + inputArray.length);
            return false;
        }
    }
    private static String[] splitInput(String input) {
        String[] parts = input.split(",");
        if (parts.length == 4) {
            return parts;
        } else {
            System.out.println("Invalid input. Expected " + 4 + " values separated by commas.");
            return null;
        }
    }

    private static boolean isValidPrice(String priceStr) {
        try {
            Double.parseDouble(priceStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    /**
     * Performs a search for stores based on location, store type, and opening date.
     *
     * @param location The location of the store.
     * @param storeType The type of the store.
     * @param openingDate The opening date of the store.
     */
    public static void performStoreSearch(String location, String storeType, String openingDate) {
        UserOperations.performStoreSearch(location, storeType, openingDate);
    }

    /**
     * Performs a search for items based on store name, item name, category name, and price range.
     *
     * @param storeName The name of the store where the item is located.
     * @param itemName The name of the item.
     * @param categoryName The category of the item.
     */
    public static void performItemSearch(String storeName, String itemName, String categoryName) {
        UserOperations.performItemSearch(storeName, itemName, categoryName);
    }

    }

