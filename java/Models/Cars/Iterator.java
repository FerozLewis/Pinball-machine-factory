package Models.Cars;

import java.util.List;

public interface Iterator {
    boolean hasNext();

    // returns the next element
    Object next(List<SedanCar> allSedanCars);
}
