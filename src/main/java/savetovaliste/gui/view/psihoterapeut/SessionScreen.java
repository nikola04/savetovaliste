package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;

import javax.swing.*;

public class SessionScreen extends JPanel implements ISubscriber {
    private static SessionScreen instance;

    public static SessionScreen getInstance(){
        if(instance == null) {
            instance = new SessionScreen();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);
    }

    @Override
    public void update(Object value) {

    }
}
