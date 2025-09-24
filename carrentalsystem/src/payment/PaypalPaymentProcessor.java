package payment;

public class PaypalPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount){
        // process for payment
        return true;
    }
}
