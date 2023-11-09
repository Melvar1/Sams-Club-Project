/**
 * Enumeration representing different user roles in the system
 */
public enum Role {
    ADMIN,
    STORE_MANAGER,
    STORE_STAFF,
    DEFAULT_USER;

    @Override
    public String toString() {
        // Replace underscores with spaces and capitalize each word if needed
        // Or simply return the enum name in lowercase
        return name().toLowerCase();
    }
}

