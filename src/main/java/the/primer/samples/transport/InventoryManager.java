package the.primer.samples.transport;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Car> cars;
    private List<Truck> trucks;
    private List<Car> loanedCars;
    private List<Truck> loanedTrucks;

    public InventoryManager() {
        cars = new ArrayList<>();
        trucks = new ArrayList<>();
        loanedCars = new ArrayList<>();
        loanedTrucks = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addTruck(Truck truck) {
        trucks.add(truck);
    }

    public int countCars() {
        return cars.size();
    }

    public int countTrucks() {
        return trucks.size();
    }

    public void loanCar(Car car) {
        cars.remove(car);
        loanedCars.add(car);
    }

    public void returnCar(Car car) {
        loanedCars.remove(car);
        cars.add(car);
    }

    public void loanTruck(Truck truck) {
        trucks.remove(truck);
        loanedTrucks.add(truck);
    }

    public void returnTruck(Truck truck) {
        loanedTrucks.remove(truck);
        trucks.add(truck);
    }

    public Car getCar(String make, String model) {
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(make) && car.getModel().equalsIgnoreCase(model)) {
                return car;
            }
        }
        return null;
    }

    public Truck getTruck(String make, String model) {
        for (Truck truck : trucks) {
            if (truck.getMake().equalsIgnoreCase(make) && truck.getModel().equalsIgnoreCase(model)) {
                return truck;
            }
        }
        return null;
    }

}
