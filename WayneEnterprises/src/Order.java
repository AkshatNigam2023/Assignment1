// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Order {
    private final int cargoWeight;
    private final String destination;
    public Order(int newCargoWeight, String newDestination) {
        this.cargoWeight = newCargoWeight;
        this.destination = newDestination;
    }
    public int getCargoWeight() {
        return cargoWeight;
    }
    public String getDestination() {
        return destination;
    }


}
