class Student {
   // Private variables (data hiding)
   private String name;
   private int age;

   // Public getter
   public String getName() {
       return name;
   }

   // Public setter
   public void setName(String name) {
       this.name = name;
   }

   // Public getter
   public int getAge() {
       return age;
   }

   // Public setter with validation
   public void setAge(int age) {
       if (age > 0) {
           this.age = age;
       }
   }
}
