public class IIBSIBOrderExample {
 
   {
       System.out.println("Instance Block#1");
   }

   {
       System.out.println("Instance Block#2");
   }

   IIBSIBOrderExample() {
       System.out.println("Constructor");
   }

   static {
       System.out.println("Static Block#1");
   }

   static {
       System.out.println("Static Block#2");
   }

   public static void main(String[] args) {
       IIBSIBOrderExample order = new IIBSIBOrderExample();
   }
}
