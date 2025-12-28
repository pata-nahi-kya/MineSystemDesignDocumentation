package AbstractFactory;

public class AbstractFactory {
    public static void main(String[] args) {
        AbstractFactoryOrganiser productTypeA = new ConcreteFactory1();
        productTypeA.createConcreteProductA().create();
        productTypeA.createConcreteProductB().create();
        
    }
}

// like gpu
interface ProductA {
    void create();
}

// like monitor 
interface ProductB {
    void create();
}

//concrete product like with company or type like hp monitor and hp gpu or modern chair and modern table

// we assume that there one specific type of company 1 whose combination of type A and B makes one unit
class ConcreteProductA1 implements ProductA{

    @Override
    public void create() {
        System.out.println("creating a components of product type 1 ");
    }


}

class ConcreteProductB1  implements ProductB{

    @Override
    public void create() {
        System.out.println("creating a components of product type 1 ");
    }

}

// we assume that there one specific type of company 2 whose combination of type A and B makes one unit
class ConcreteProductA2 implements ProductA{

    @Override
    public void create() {
        System.out.println("creating a components of product type 2 ");
    }


}

class ConcreteProductB2  implements ProductB{

    @Override
    public void create() {
        System.out.println("creating a components of product type 2 ");
    }

}


//-----------------------------------------
// abstract which will be used manufactuer class to create similar products
abstract class AbstractFactoryOrganiser {
    public abstract ProductA createConcreteProductA();
    public abstract ProductB createConcreteProductB();
}

//-------------------------------
// now we will create concrete factory of company 1 and 2

class ConcreteFactory1 extends AbstractFactoryOrganiser{

    @Override
    public ProductA createConcreteProductA() {

        return new ConcreteProductA1();
        
    }

    @Override
    public ProductB createConcreteProductB() {
        return new ConcreteProductB1();
        
    }
       

}

class ConcreteFactory2 extends AbstractFactoryOrganiser{

    @Override
    public ProductA createConcreteProductA() {
        return new ConcreteProductA2();
        
    }

    @Override
    public ProductB createConcreteProductB() {
        return new ConcreteProductB2();
       
    }
       

}


