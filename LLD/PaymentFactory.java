public class PaymentFactory {
    public static PaymentProcessor getPaymentProcessor(String paymentMethod) {
        if(paymentMethod.equals("CREDIT_CARD")){
            return new CCPayment();
        }
        if(paymentMethod.equals("PayPal")){
            return new PayPalPayment();
        }

        return null;
    }
}
