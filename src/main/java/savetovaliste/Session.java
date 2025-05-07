package savetovaliste;

import savetovaliste.controller.observer.IPublisher;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.gui.view.MainFrame;
import savetovaliste.model.Psihoterapeut;

import java.util.ArrayList;

public class Session implements IPublisher {
    private static Session instance;
    private final ArrayList<ISubscriber> subscribers = new ArrayList<>();
    private int id = -1;
    private Psihoterapeut psihoterapeut;

    private Session() {}
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void loginUser(Psihoterapeut psihoterapeut){
        this.psihoterapeut = psihoterapeut;
        setUserId(psihoterapeut.getId());
    }

    public void logoutUser(){
        this.psihoterapeut = null;
        setUserId(-1);
    }

    private void setUserId(int id) {
        this.id = id;
        publish(id);
    }

    public int getUserId() {
        return id;
    }

    public Psihoterapeut getPsihoterapeut() {
        return psihoterapeut;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void publish(Object value) {
        for (ISubscriber subscriber : new ArrayList<>(subscribers)) {
            subscriber.update(value);
        }
    }
}
