import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(3);
        shelf.addBook("The Great Gatsby");
        shelf.addBook("1984");
        shelf.addBook("The Hobbit");

        // Using the for-each loop (Works because of 'Iterable')
        System.out.println("Using For-Each Loop:");
        for (String book : shelf) {
            System.out.println("- " + book);
        }

        System.out.println("\nUsing Iterator Manually:");
        // Using the Iterator explicitly
        Iterator<String> it = shelf.iterator();
        while (it.hasNext()) {
            System.out.println("- " + it.next());
        }
    }
}

