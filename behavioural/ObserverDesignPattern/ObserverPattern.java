package ObserverDesignPattern;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObserverPattern {
     public static void main(String[] args) {

        Store store = new Store();
        store.getService().subscribe( Event.NEW_ITEM , new EmailMsgListener("geekific@like.com"));
        store.getService().subscribe(Event.SALE, new EmailMsgListener("geekific@like.com"));
        store.getService().subscribe(Event.SALE, new EmailMsgListener("geekific@subs.com"));
        store.getService().subscribe(Event.NEW_ITEM, new MobileAppListener("GeekificLnS"));

        store.newItemPromotion();

        System.out.println("==========================================");

        store.salePromotion();

        System.out.println("==========================================");

        store.getService().unsubscribe(Event.SALE, new EmailMsgListener("geekific@like.com"));
        store.salePromotion();

    }

}

enum Event {

    NEW_ITEM,
    SALE

}

interface Listener {

    void update(Event eventType);

}

record EmailMsgListener(String email) implements Listener {

    @Override
    public void update(Event eventType) {
        // Actually send the mail
        System.out.println("Sending mail to " + email + " concerning " + eventType);
    }

}

record MobileAppListener(String username) implements Listener {

    @Override
    public void update(Event eventType) {
        // Actually send the push notification to username
        System.out.println("Sending mobile app notification to " + username + " concerning " + eventType);
    }

}

class NotificationService {

    private final Map<Event, List<Listener>> customers;

    public NotificationService() {
        customers = new HashMap<>();
        Arrays.stream(Event.values()).forEach(event -> customers.put(event, new ArrayList<>()));
    }

    public void subscribe(Event eventType, Listener listener) {
        customers.get(eventType).add(listener);
    }

    public void unsubscribe(Event eventType, Listener listener) {
        customers.get(eventType).remove(listener);
    }

    public void notifyCustomers(Event eventType) {
        customers.get(eventType).forEach(listener -> listener.update(eventType));
    }

}

class Store {

    private final NotificationService notificationService;

    public Store() {
        notificationService = new NotificationService();
    }

    public void newItemPromotion() {
        notificationService.notifyCustomers(Event.NEW_ITEM);
    }

    public void salePromotion() {
        notificationService.notifyCustomers(Event.SALE);
    }

    public NotificationService getService() {
        return notificationService;
    }

}
