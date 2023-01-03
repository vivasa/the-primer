package the.primer.samples;

public class Vehicle {
    private String make;
    private String model;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void drive() {
        System.out.println("Driving vehicle: " + make + " " + model);
    }

    public void stop() {
        System.out.println("Stopping vehicle: " + make + " " + model);
    }
}
