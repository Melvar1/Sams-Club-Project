/**
 * Represents a supplier with a specific name.
 */
class Supplier {
    private final String name;

    /**
     * Constructs a new Supplier with the given name.
     *
     * @param name Name of the supplier
     */
    public Supplier(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
