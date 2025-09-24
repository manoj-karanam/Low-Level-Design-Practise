import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private final String reservationId;
    private final Customer customer;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;

    public Reservation(String reservationId, Customer customer, Car car, LocalDate startDate, LocalDate endDate ){
        this.reservationId = reservationId;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice(){
        long noOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return noOfDays * car.getRentalPricePerDay();
    }
    public String getReservationId() {return reservationId;}

    public double getTotalPrice(){ return totalPrice; }

    public Customer getCustomer() {return customer;}

    public Car getCar() {return car;}

    public LocalDate getStartDate() {return startDate;}

    public LocalDate getEndDate() {return endDate;}
}
