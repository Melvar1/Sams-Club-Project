import java.util.ArrayList;
import java.util.List;

/**
 *Operations class for user operations
 *that contains lists to manage stores, suppliers, and purchase orders
 */
public class Operations {
    public final User user;
    static final List<Store> storeList = new ArrayList<>();
    static final List<Supplier> supplierList = new ArrayList<>();
    static final List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

    //user performing the operations
    public Operations(User user) {
        this.user = user;
    }

    //checks if the user has the required role to perform the operation
// checks if the user has the required role to perform the operation
    public void checkRole(Operations operations, Role requiredRole) {
        if (this.user.getRole() != requiredRole) {
            throw new RuntimeException("Operation not allowed. Required role: " + requiredRole);
        }
    }
}
