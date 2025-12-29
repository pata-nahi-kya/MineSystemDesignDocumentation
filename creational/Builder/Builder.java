package Builder;


public class Builder{
    public static void main(String[] args) {
        ProductBuilder pb = new ProductBuilder();
        ProductBuilder pb2 = new ProductBuilder();
        pb.setField1("rakshit").setField2(0).setField3(true).build();

        Director dirc = new Director();
        dirc.buildSpecificProduct(pb2);
    }

    
}

// director defines the order in which we should call the construction steps so that we can reuse specific configuration of the products we are building

// it hides the details of the product construction from client code
class Director{
    public void buildSpecificProduct(ProductBuilder builder){
        builder.setField1("null").setField2(0).setField3(false);
    }

    public void buildSpecificProduct2(ProductBuilder builder){
        builder.setField1("nullem").setField2(10100).setField3(true);
    }

}
class ProductBuilder {  
    private String field1;
    private int field2;
    private boolean field3;

    public ProductBuilder setField1(String field1) {
        this.field1 = field1;
        return this;
    }
    public ProductBuilder setField2(int field2) {
        this.field2 = field2;
        return this;
    }
    public ProductBuilder setField3(boolean field3) {
        this.field3 = field3;
        return this;
    }
    public Product build() {
        return new Product(field1, field2, field3);
    }
    
}

class Product {
    private String field1;
    private int field2;
    private boolean field3;
    Product(String field1 , int field2 , boolean field3){
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

}
