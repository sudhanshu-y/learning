import java.util.Optional;

class Price {
    private Double amount;
    Price(Double amount) { this.amount = amount; }
    public Double getAmount() { return amount; }
}

class Product {
    private Price price;
    Product(Price price) { this.price = price;}
    public Price getPrice() { return price; }
}

class CartItem {
    private Product product;
    private Integer quantity;

    CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public Integer getQuantity() { return quantity; }
}

public class Optional5CodeExample {

    public static void main(String[] args) {

        CartItem item1 = new CartItem(new Product(new Price(50.0)), 2);
        CartItem item2 = new CartItem(new Product(new Price(null)), 3);
        CartItem item3 = new CartItem(new Product(null), 3);
        CartItem item4 = new CartItem(null, 3);
        CartItem item5 = new CartItem(new Product(new Price(20.0)), null);
        CartItem item6 = new CartItem(new Product(new Price(-10.0)), 3);
        CartItem item7 = new CartItem(new Product(new Price(15.0)), -2);

        System.out.println(getTotalPrice(item1)); // 100.0
        System.out.println(getTotalPrice(item2)); // 0.0
        System.out.println(getTotalPrice(item3)); // 0.0
        System.out.println(getTotalPrice(item4)); // 0.0
        System.out.println(getTotalPrice(item5)); // 0.0
        System.out.println(getTotalPrice(item6)); // 0.0
        System.out.println(getTotalPrice(item7)); // 0.0
    }

    // ---------------------------------------------------------
    // Computes total price using Optional chaining
    // Rules:
    // - If product/price/amount/quantity missing => return 0.0
    // - If amount <= 0 or quantity <= 0 => return 0.0
    // ---------------------------------------------------------
    public static double getTotalPrice(CartItem cartItem) {
        return Optional.ofNullable(cartItem)
        .map(CartItem::getProduct)           // if item null → stop
        .map(Product::getPrice)              // if product null → stop
        .map(Price::getAmount)               // if price null → stop
        .filter(amount -> amount > 0)        // reject <= 0
        .flatMap(
            amount -> Optional.ofNullable(cartItem)
                        .map(CartItem::getQuantity)
                        .filter(quantity -> quantity > 0)  // reject <= 0
                        .map(quantity -> amount * quantity)
        )
        .orElse(0.0);                 // default if anything fails
    }
}
