/**
 * Enumerates the possible statuses of a purchase order.
 */
enum PurchaseOrderStatus {
    PENDING("pending"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private final String name;

    /**
     * Constructs a new PurchaseOrderStatus with the specified name.
     *
     * @param name the name of the status.
     */
    PurchaseOrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
