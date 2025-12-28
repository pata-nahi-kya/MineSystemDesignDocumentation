package Factory;

public class FactoryDesignPattern {
    public static void main(String[] args) {
        Creator creatorOfA = new concreteCreatorA();
        Product ACreated = creatorOfA.createProduct();
        ACreated.doStuff();
    }

}

interface Product {
    void doStuff();
}

class ConcreteProductA implements Product {

    @Override
    public void doStuff() {
        System.out.println("doing some stuff through class concrete product A");
    }

}

class ConcreteProductB implements Product {

    @Override
    public void doStuff() {
        System.out.println("doing some stuff through class concrete product B");
    }

}

abstract class Creator {
    Product someOperation() {
        System.out.println("doing some operation and returning Product object");
        Product p = createProduct();
        p.doStuff();
        return p;
    }

    abstract Product createProduct();
}

class concreteCreatorA extends Creator {

    @Override
    Product createProduct() {
        return new ConcreteProductA();
    }

}

class concreteCreatorB extends Creator {

    @Override
    Product createProduct() {
        return new ConcreteProductB();
    }

}
