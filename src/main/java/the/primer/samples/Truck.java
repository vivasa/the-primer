package the.primer.samples;

public class Truck extends Vehicle {
    private int loadCapacity;
    private int currentLoad;

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    public void haul(int load) {
        currentLoad += load;
    }
}
