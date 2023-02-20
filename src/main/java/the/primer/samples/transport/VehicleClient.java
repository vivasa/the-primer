package the.primer.samples.transport;

public class VehicleClient {
    public static void main(String[] args) {
        Vehicle myVehicle = new Vehicle();
        myVehicle.setMake("Toyota");
        System.out.println("Make of my " +
                "vehicle is " + myVehicle.getMake());

        Car myCar = new Car();
        myCar.setMake("Hyundai");

        System.out.println("Make of my Car is " + myCar.getMake());
    }
}
