package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;

import javax.swing.*;

public class PastSessionsScreen extends JPanel implements ISubscriber {
    private static PastSessionsScreen instance;

    public static PastSessionsScreen getInstance(){
        if(instance == null) {
            instance = new PastSessionsScreen();
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
