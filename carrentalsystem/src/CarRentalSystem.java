import payment.CreditCardPaymentProcessor;
import payment.PaymentProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import payment.*;

public class CarRentalSystem {

    private static CarRentalSystem instance;
    private Map<String, Car> cars;
    private Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    public CarRentalSystem(){
        cars = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentProcessor = new CreditCardPaymentProcessor();
    }

    public static synchronized CarRentalSystem getInstance(){
        if(instance == null)
            return new CarRentalSystem();

        return instance;
    }

    public void addCar(Car car){
        cars.put(car.getLicensePlate(), car);
    }

    public void removeCar(String licensePlateNumber){
        cars.remove(licensePlateNumber);
    }

    public List<Car> searchCars(String make, String model,
                                    LocalDate startDate, LocalDate endDate){
        List<Car> availableCars = new ArrayList<>();

        for(Car car: cars.values()){
            if(car.isAvailable() && car.getMake().equalsIgnoreCase(make)
                    && car.getModel().equalsIgnoreCase(model)){
                if(isCarAvailable(car, startDate, endDate)){
                    availableCars.add(car);
                }
            }
        }
        return availableCars;
    }

    public boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate){

        for(Reservation reservation: reservations.values()){
            if(reservation.getCar().equals(car)){
                if(startDate.isBefore(reservation.getEndDate()) &&
                                        endDate.isAfter(reservation.getStartDate())){
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized Reservation makeReservation(Customer customer, Car car,
                                                        LocalDate startDate, LocalDate endDate){
        if(isCarAvailable(car, startDate, endDate)){
            String reservationId = generateReservationId();
            Reservation reservation = new Reservation(reservationId, customer, car, startDate, endDate);
            reservations.put(reservationId, reservation);
            car.setAvailable(false);
            return reservation;
        }
        return null;
    }

    public synchronized void cancelReservation(String reservationId){
        Reservation reservation = reservations.remove(reservationId);
        if(reservation != null){
            reservation.getCar().setAvailable(true);
        }
    }

    public boolean processPayment(Reservation reservation){
       return paymentProcessor.processPayment(reservation.getTotalPrice());
    }

    public String generateReservationId(){
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }



}
