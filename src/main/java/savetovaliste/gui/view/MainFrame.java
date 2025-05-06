package savetovaliste.gui.view;

import savetovaliste.Session;
import savetovaliste.gui.view.psihoterapeut.MainScreenPsih;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private static MainScreenPsih mainScreenPsih;
    private static MainScreen mainScreenLogin;
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
            instance.initializeGUI();
        }
        return instance;
    }
    private MainFrame(){}

    public void onRefreshSession(){
        if(Session.getInstance().getUserId() == -1) {
            this.setContentPane(MainScreen.getInstance());
        }else this.setContentPane(MainScreenPsih.getInstance());

        this.revalidate();
        this.repaint();
    }

    public void initialize() {
    }

    public void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Savetovaliste");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int appWidth = screenSize.width * 3 / 4;
        int appHeight = screenSize.height * 3 / 4;

        setSize(appWidth, appHeight);

        setLayout(new BorderLayout());

        this.setContentPane(MainScreen.getInstance());
    }
}
