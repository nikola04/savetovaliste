package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;

import javax.swing.*;

public class UpcSessionsScreen  extends JPanel implements ISubscriber {
    private static UpcSessionsScreen instance;

    public static UpcSessionsScreen getInstance(){
        if(instance == null) {
            instance = new UpcSessionsScreen();
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
