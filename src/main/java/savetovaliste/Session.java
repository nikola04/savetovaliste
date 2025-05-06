package savetovaliste;

import savetovaliste.core.ApplicationFramework;
import savetovaliste.gui.view.MainFrame;

public class Session {
    private static Session instance;

    private int id = -1;
    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void loginUser(int id){
        setUserId(id);
    }

    public void logoutUser(){
        setUserId(-1);
    }

    private void setUserId(int id) {
        this.id = id;
        MainFrame.getInstance().onRefreshSession();
    }

    public int getUserId() {
        return id;
    }
}
