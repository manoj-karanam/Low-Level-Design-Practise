public class Car {

    private final String make;
    private final String model;
    private final int year;
    private final double rentalPricePerDay;
    private final String licensePlate;
    private boolean available;

    public Car(String make, String model, int year, String licensePlate, double rentalPricePerDay) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalPricePerDay = rentalPricePerDay;
        this.licensePlate = licensePlate;
        this.available = true;
    }

    public String getLicensePlate() { return licensePlate;}

    public double getRentalPricePerDay() { return rentalPricePerDay;}

    public int getYear() {return year;}

    public String getModel() {return model;}

    public String getMake() {return make;}

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }
}
