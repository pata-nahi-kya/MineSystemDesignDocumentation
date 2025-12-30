package MediatorDesignPattern;

public class MediatorPatternDemo {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatRoom();
        
        User user1 = new ChatUser("User1", chatMediator);
        User user2 = new ChatUser("User2", chatMediator);
        User user3 = new ChatUser("User3", chatMediator);
        user1.sendMessage("Hello, everyone!");
        user2.sendMessage("Hi, User1!");
        user3.sendMessage("Hey there!");
    }
    
}

// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User sender);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    @Override
    public void sendMessage(String message, User sender) {
        // Broadcast the message to all users except the sender
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }
}

// Colleague Interface
interface User {
    void sendMessage(String message);
    void receiveMessage(String message);
}

// Concrete Colleague
class ChatUser implements User {
    private String name;
    private ChatMediator mediator;
    public ChatUser(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    @Override
    public void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " receives: " + message);
    }
}