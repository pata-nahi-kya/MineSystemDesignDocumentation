
public class StateDes {
    public static void main(String[] args) {
        Phone ph = new Phone();
        simulation(ph);

    }

    private static void simulation(Phone phone){
        System.out.println(phone.clickPower());
        System.out.println(phone.clickPower());
        System.out.println(phone.clickHome());
        System.out.println(phone.clickHome());
        System.out.println(phone.clickHome());
        System.out.println(phone.clickPower());
        System.out.println(phone.clickPower());
        System.out.println(phone.clickHome());

    }

    
}

// make class who have state like here we have state of phone
class Phone{
    private State state;

    public Phone(){
       state = new OffState(this);
    }

    public void setState(State state){
        this.state = state;
    }

    public String lock() {
        return "Locking phone and turning off the screen";
    }

    public String home() {
        return "Going to home-screen";
    }

    public String unlock() {
        return "Unlocking the phone to home";
    }

    public String turnOn() {
        return "Turning screen on, device still locked";
    }

    public String clickHome() {
        return state.onHome();
    }

    public String clickPower() {
        return state.onOffOn();
    }

}

// an abstract class which defines state transaction like here we define state transaction onHome and onOffOn which changes the state of phone 
abstract class State {
    protected Phone phone;
    public State(Phone phone){
        this.phone = phone;
    }

    public abstract String onHome();
    public abstract String onOffOn();
}

class ReadyState extends State{
    public ReadyState (Phone phone){
        super(phone);
    }

    @Override
    public String onHome() {
       return phone.home();
    }

    @Override
    public String onOffOn() {
        return phone.lock();
    }
    
}

class OffState extends State {

    public OffState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }

    @Override
    public String onOffOn() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }

}

class LockedState extends State {

    public LockedState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        phone.setState(new ReadyState(phone));
        return phone.unlock();
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }

}

