/**
 * Represents a geographical location.
 */
class Location {
    private final String name;

    /**
     * Constructs a new Location with the specified name.
     *
     * @param name the name of the location.
     */
    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
