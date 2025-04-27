package savetovaliste.gui;

import savetovaliste.core.GUI;
import savetovaliste.gui.view.MainFrame;

public class SwingGUI implements GUI {
    private MainFrame mainFrameInstance;
    @Override
    public void start() {
        mainFrameInstance = MainFrame.getInstance();
        mainFrameInstance.setVisible(true);
    }
}
