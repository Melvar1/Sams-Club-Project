/**
 * Represents a product category.
 */
class Category {
    private String name;

    /**
     * Constructs a new Category with the specified name.
     *
     * @param name the name of the category (must not be empty or null).
     * @throws IllegalArgumentException if the provided name is empty or null.
     */
    public Category(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be empty or null.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be empty or null.");
        }
        this.name = name;
    }
}
