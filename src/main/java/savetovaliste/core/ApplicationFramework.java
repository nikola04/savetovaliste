package savetovaliste.core;

public class ApplicationFramework {
    private static ApplicationFramework instance;
    protected GUI gui;

    public static ApplicationFramework getInstance(){
        if(instance == null)
            instance = new ApplicationFramework();
        return instance;
    }
    private ApplicationFramework(){}

    public void initialize(GUI gui){
        this.gui = gui;
    }

    public void run(){
        this.gui.start();
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
    public GUI getGui() {
        return gui;
    }
}
