public class CCPayment implements PaymentProcessor {

    @Override
    public void pay(double amount) {
        System.out.println("CC Payment: "+ amount);
    }
    
}
