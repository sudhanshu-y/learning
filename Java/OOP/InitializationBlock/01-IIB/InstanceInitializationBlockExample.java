public class InstanceInitializationBlockExample {
   int x;

   {
       x = 10;
       System.out.println("Instance Initialization Block");
   }

   InstanceInitializationBlockExample(){
    System.out.println("Constructor");
   }

   public static void main(String[] args) {
    System.out.println("Main method");
    InstanceInitializationBlockExample ex = new InstanceInitializationBlockExample();
   }

}
