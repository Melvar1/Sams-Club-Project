/**
 * User class that represents user in the system with a name and role
 */

class User {
    private final String name;
    private Role role;

    // Constructor with null checks
    public User(String name, Role role) {
        if (name == null || role == null) {
            throw new IllegalArgumentException("Name and role must not be null.");
        }
        this.name = name;
        this.role = role;
    }

    // Getters for name and role (no setters to make it immutable)
    public String getName() {
        return this.name;
    }

    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
