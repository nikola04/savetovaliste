package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;

import javax.swing.*;

public class PaymentsScreen  extends JPanel implements ISubscriber {
    private static PaymentsScreen instance;

    public static PaymentsScreen getInstance(){
        if(instance == null) {
            instance = new PaymentsScreen();
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
