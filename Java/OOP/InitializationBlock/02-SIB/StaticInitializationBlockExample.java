public class StaticInitializationBlockExample {
   static int x;

   static {
       x = 20;
       System.out.println("Static Initialization Block");
   }

   public static void main(String[] args) {
    System.out.println("Main method");
   }
}
