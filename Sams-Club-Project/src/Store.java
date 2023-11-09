import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a store with details
 */
class Store {
    private String name;
    private Location location;
    private ContactInformation contactInformation;
    private final List<Item> inventory = new ArrayList<>();
    private User storeManager;
    private String storeType;
    private LocalDate openingDate;
    private final List<User> staff = new ArrayList<>();

    /**
     * Constructs a new Store with the given details.
     *
     * @param name               Name of the store
     * @param location           Location of the store
     * @param contactInformation Contact details of the store
     * @param storeManager       Manager of the store
     * @param storeType          Type or category of the store
     * @param openingDate        Date when the store was opened
     */
    public Store(String name, Location location, ContactInformation contactInformation, User storeManager, String storeType, LocalDate openingDate) {
        this.name = name;
        this.location = location;
        this.contactInformation = contactInformation;
        this.storeManager = storeManager;
        this.staff.add(storeManager);
        this.storeType = storeType;
        this.openingDate = openingDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ContactInformation getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public void addInventory(Item item) {
        this.inventory.add(item);
    }

    public User getStoreManager() {
        return this.storeManager;
    }

    public void setStoreManager(User storeManager) {
        if (storeManager == null || storeManager.getRole() != Role.STORE_MANAGER) {
            throw new IllegalArgumentException("The store manager cannot be null and must have the STORE_MANAGER role.");
        }

        // If the new manager is different from the current manager
        if (!storeManager.equals(this.storeManager)) {
            // Remove the old manager if they exist
            if (this.storeManager != null) {
                this.staff.remove(this.storeManager);
            }
            // Assign the new manager
            this.storeManager = storeManager;
            // Add the new manager to the staff list if not already present
            if (!this.staff.contains(storeManager)) {
                this.staff.add(storeManager);
            }
        }
    }

    public String getStoreType() {
        return this.storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public LocalDate getOpeningDate() {
        return this.openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public List<User> getStaff() {
        return this.staff;
    }

    public void addStaff(User staff) {
        this.staff.add(staff);
    }
}

