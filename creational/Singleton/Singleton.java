package Singleton;

public class Singleton {
    // we are using volatile because imagine if we run 2 threads and one thread occupied instance variable then a shared variable stored in the memory can reference a partially constructed object. like second thread will get not null instance but it is still not fully initialize by first thread
    private static volatile Singleton instance;
    private String data;

    private Singleton(String data) {
        this.data = data;
    }

    public static Singleton getInstance(String data) {
    // usage of such a local variable can improve the method overall performance by as much as 40% as everytime we access global instance variable we need to read it direclty from the main memory as it cannot be cached due to volatile keyword. we fetch instance vaariable twice(in if part) if we are not using local variable 
       Singleton result = instance;
       if(result == null){
        synchronized(Singleton.class){
            result = instance;
            if(result == null){
                instance = result = new Singleton(data);
            }
        }
       }
       return result;
    }
    
}
