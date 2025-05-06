package savetovaliste;

import savetovaliste.gui.view.MainFrame;
import savetovaliste.model.Psihoterapeut;

public class Session {
    private static Session instance;
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
        MainFrame.getInstance().onRefreshSession();
    }

    public int getUserId() {
        return id;
    }

    public Psihoterapeut getPsihoterapeut() {
        return psihoterapeut;
    }
}
