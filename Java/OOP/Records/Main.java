public class Main {

   public static void main(String[] args) {

       // Traditional final class
       User user1 = new User(1, "Alice", "alice@example.com");
       User user2 = new User(2, "Bob", "bob@example.com");

       // Record
       Customer customer1 = new Customer(1, "Alice", "alice@example.com");
       Customer customer2 = new Customer(2, "Bob", "bob@example.com");

       // Accessing fields
       System.out.println(user1.getName());    // Alice
       System.out.println(customer1.name());   // Alice

       // Equality check
       System.out.println(user1.equals(user2));         // true
       System.out.println(customer1.equals(customer2)); // true

       // toString()
       System.out.println(user1);      // User{id=1, name='Alice', email='alice@example.com'}
       System.out.println(customer1);  // Customer[id=1, name=Alice, email=alice@example.com]
   }
}
