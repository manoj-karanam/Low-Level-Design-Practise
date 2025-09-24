package payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount){
        // process for payment
        return true;
    }
}


