/**
 * Represents contact information.
 */
class ContactInformation {
    private String phoneNumber;

    /**
     * Constructs a new ContactInformation with the specified phone number.
     *
     * @param phoneNumber the phone number.
     */
    public ContactInformation(String phoneNumber) {
        // Basic phone number format validation (you can customize this as needed)
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // Basic phone number format validation (you can customize this as needed)
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Validates a phone number format.
     *
     * @param phoneNumber the phone number to validate.
     * @return true if the phone number is valid; false otherwise.
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Basic validation: Check if the phone number consists of digits and optional dashes or spaces
        return phoneNumber.matches("^[0-9-\\s]+$");
    }
}
