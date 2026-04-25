// The Collection (The "Iterable")

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BookShelf implements Iterable<String>{

    private String[] books;
    private int size = 0;

    public BookShelf(int capacity ){
        this.books = new String[capacity];
    }

    public void addBook(String title){
        if(size < books.length){
            books[size++] = title;
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new BookIterator();
    }

    // The "Iterator"
    // Usually implemented as an inner class to access private data
    private class BookIterator implements Iterator<String> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public String next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return books[currentIndex++];
        }
        
    }

}
