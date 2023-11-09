/**
 * Represents an item or product in the store.
 */
class Item {
        private String name;
        private String description;
        private Category category;
        private Double price;
        private Long initialQuality;
        private Long quantity;
        private Long itemThreshold;
        private Supplier supplier;

        /**
         * Constructs a new Item with the specified attributes.
         *
         * @param name           the name of the item.
         * @param description    a brief description of the item.
         * @param category       the category to which the item belongs.
         * @param price          the price of the item.
         * @param initialQuality the initial quality of the item.
         * @param itemThreshold  the threshold value for the item.
         * @param supplier       the supplier of the item.
         */
        public Item(String name, String description, Category category, Double price, Long initialQuality, Long itemThreshold, Supplier supplier) {
                this.name = name;
                this.description = description;
                this.category = category;
                this.price = price;
                this.initialQuality = initialQuality;
                this.quantity = initialQuality; // Initialize quantity directly with initialQuality
                this.itemThreshold = itemThreshold;
                this.supplier = supplier;
        }

        // Getters and setters (no need for individual comments for each)
        public String getName() {
                return this.name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return this.description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Category getCategory() {
                return this.category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public Double getPrice() {
                return this.price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public Long getInitialQuality() {
                return this.initialQuality;
        }

        public void setInitialQuality(Long initialQuality) {
                this.initialQuality = initialQuality;
        }

        public Long getQuantity() {
                return this.quantity;
        }

        public void setQuantity(Long quantity) {
                this.quantity = quantity;
        }

        public Long getItemThreshold() {
                return this.itemThreshold;
        }

        public void setItemThreshold(Long itemThreshold) {
                this.itemThreshold = itemThreshold;
        }

        public Supplier getSupplier() {
                return this.supplier;
        }

        public void setSupplier(Supplier supplier) {
                this.supplier = supplier;
        }
}
