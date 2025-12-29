package Prototype;

import java.util.ArrayList;
import java.util.List;

public class Prototype {
    
}


// main class that have clone method
abstract class Vehicle{
    private String brand;
    private String model;
    private String color;

    protected Vehicle(Vehicle vehicle){
        this.brand = vehicle.brand;
        this.model = vehicle.model;
        this.color = vehicle.color;

    }

    public abstract Vehicle clone();

}

// extending abstract class
class Car extends Vehicle{
    private int topSpeed;
    public Car(Car car){
        super(car);
        this.topSpeed = car.topSpeed; 
    }
    @Override
    public Vehicle clone() {
        return new Car(this);
    }

    public void clone(List<Vehicle> vehicles){
        List<Vehicle> copyList = new ArrayList<>();
        for(Vehicle v : vehicles){
            copyList.add(v.clone());
        }
        
    }
}
