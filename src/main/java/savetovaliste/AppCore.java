package savetovaliste;

import savetovaliste.core.ApplicationFramework;
import savetovaliste.core.GUI;
import savetovaliste.gui.SwingGUI;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        GUI swingGui = new SwingGUI();
        appCore.initialize(swingGui);
        appCore.run();
    }
}