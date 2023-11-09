import java.util.Random;

/**
 * Represents a purchase order.
 */
class PurchaseOrder {
    private Integer number = this.generateOrderNumber();
    private Store store;
    private Item item;
    private Long quantity;
    private PurchaseOrderStatus purchaseOrderStatus;

    /**
     * Constructs a new PurchaseOrder for the specified store, item, and quantity.
     *
     * @param store    the store where the purchase order is generated.
     * @param item     the item for which the purchase order is generated.
     * @param quantity the quantity of the item in the purchase order.
     */
    public PurchaseOrder(Store store, Item item, Long quantity) {
        this.store = store;
        this.item = item;
        this.quantity = quantity;
        this.purchaseOrderStatus = PurchaseOrderStatus.APPROVED;
    }

    /**
     * Generates a unique order number.
     *
     * @return a unique order number.
     */
    private Integer generateOrderNumber() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        return random.nextInt(max - min + 1) + min;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return this.purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }
}

