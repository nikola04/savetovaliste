package savetovaliste.gui.view;

import savetovaliste.gui.view.forms.PsihoInfoForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private Button showPsihoBtn;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
            instance.initializeGUI();
        }
        return instance;
    }
    private MainFrame(){}

    public void initialize() {
    }
    private void initializeGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        showPsihoBtn = new Button("Prikazi info o psihoterapeutima");
        int appWidth = screenSize.width * 3/4;
        int appHeight = screenSize.height * 3/4;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(appWidth, appHeight);
        setTitle("Savetovaliste");

        JPanel desktop = new JPanel(new BorderLayout());
        this.getContentPane().add(desktop, BorderLayout.CENTER);
        desktop.add(showPsihoBtn, BorderLayout.SOUTH);

        JFrame frame = new JFrame();
        frame.setSize(920, 400);

        showPsihoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PsihoInfoForm pane = new PsihoInfoForm();
                frame.add(pane.getPanel1(), BorderLayout.CENTER);
                frame.setVisible(true);
            }
        });
    }
}
