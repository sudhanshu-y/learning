public class Main {
    public static void main(String[] args) {
        PaymentProcessor pp = PaymentFactory.getPaymentProcessor("CREDIT_CARD");
        pp.pay(100);
    }
}