package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;

import javax.swing.*;

public class ClientApplicScreen extends JPanel implements ISubscriber {
    private static ClientApplicScreen instance;

    public static ClientApplicScreen getInstance(){
        if(instance == null) {
            instance = new ClientApplicScreen();
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
