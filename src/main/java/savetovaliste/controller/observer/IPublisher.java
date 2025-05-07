package savetovaliste.controller.observer;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void publish(Object value);
}
