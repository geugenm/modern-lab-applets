import java.applet.Applet;
import java.awt.*;

public class CrystalViewer extends Applet {
    private static final int EXIT_EVENT_ID = 201;
    ControlPanel controls;
    DisplayArea display;
    boolean inAnApplet;

    public CrystalViewer() {
        inAnApplet = true;
    }

    public static void main(String[] args) {
        Frame frame = new Frame("Molecular Model Viewer");
        CrystalViewer crystalViewer = new CrystalViewer();
        crystalViewer.inAnApplet = false;

        frame.add("Center", crystalViewer);
        frame.setSize(500, 500);
        crystalViewer.init();
        crystalViewer.start();
        frame.setVisible(true);
    }

    @Override
    public void init() {
        setLayout(new BorderLayout(10, 10));
        display = new DisplayArea();
        add("Center", display);
        controls = new ControlPanel(display);
        add("North", controls);
    }

    @Override
    public void start() {
        display.start();
    }

    @Override
    public void stop() {
        display.stop();
    }

    @Override
    public boolean handleEvent(Event event) {
        if (event.id == EXIT_EVENT_ID) {
            System.exit(0);
            return true;
        } else {
            return false;
        }
    }
}
