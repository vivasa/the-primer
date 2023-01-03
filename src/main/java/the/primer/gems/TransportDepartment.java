package the.primer.gems;

import the.primer.samples.Car;
import the.primer.samples.InventoryManager;
import the.primer.samples.Truck;

import java.util.Scanner;

public class TransportDepartment {
    private InventoryManager manager;
    private Scanner scanner;

    public TransportDepartment() {
        manager = new InventoryManager();
        scanner = new Scanner(System.in);
    }

    public void onboard() {
        System.out.print("Enter number of cars: ");
        int numCars = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        for (int i = 0; i < numCars; i++) {
            Car car = new Car();

            System.out.print("Enter car make: ");
            car.setMake(scanner.nextLine());

            System.out.print("Enter car model: ");
            car.setModel(scanner.nextLine());

            System.out.print("Enter number of doors: ");
            car.setDoors(scanner.nextInt());
            scanner.nextLine();  // Consume newline character

            System.out.print("Enter car color: ");
            car.setColor(scanner.nextLine());

            manager.addCar(car);
        }

        System.out.print("Enter number of trucks: ");
        int numTrucks = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        for (int i = 0; i < numTrucks; i++) {
            Truck truck = new Truck();

            System.out.print("Enter truck make: ");
            truck.setMake(scanner.nextLine());

            System.out.print("Enter truck model: ");
            truck.setModel(scanner.nextLine());

            System.out.print("Enter truck load capacity: ");
            truck.setLoadCapacity(scanner.nextInt());
            scanner.nextLine();  // Consume newline character

            System.out.print("Enter truck current load: ");
            truck.setCurrentLoad(scanner.nextInt());
        }
    }

    public static void main(String[] args) {
        TransportDepartment department = new TransportDepartment();

        department.onboard();
        department.distribute();
    }

    public void distribute() {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter vehicle type (car/truck) or 'done' to finish: ");
            String type = scanner.nextLine();

            if (type.equalsIgnoreCase("done")) {
                break;
            }

            if (type.equalsIgnoreCase("car")) {
                if (manager.countCars() == 0) {
                    System.out.println("No cars available to loan.");
                    continue;
                }

                System.out.print("Enter car make: ");
                String make = scanner.nextLine();

                System.out.print("Enter car model: ");
                String model = scanner.nextLine();

                Car car = manager.getCar(make, model);
                if (car == null) {
                    System.out.println("Car not found in inventory.");
                    continue;
                }

                manager.loanCar(car);
                System.out.println("Loaned car: " + car.getMake() + " " + car.getModel());
            } else if (type.equalsIgnoreCase("truck")) {
                if (manager.countTrucks() == 0) {
                    System.out.println("No trucks available to loan.");
                    continue;
                }

                System.out.print("Enter truck make: ");
                String make = scanner.nextLine();

                System.out.print("Enter truck model: ");
                String model = scanner.nextLine();

                Truck truck = manager.getTruck(make, model);


                if (truck == null) {
                    System.out.println("Truck not found in inventory.");
                    continue;
                }

                manager.loanTruck(truck);
                System.out.println("Loaned truck: " + truck.getMake() + " " + truck.getModel());
            } else {
                System.out.println("Invalid vehicle type. Must be 'car' or 'truck'.");
            }
        }
    }

}
