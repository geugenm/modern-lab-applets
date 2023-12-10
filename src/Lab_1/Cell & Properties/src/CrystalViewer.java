import java.applet.Applet;
import java.awt.*;


public class CrystalViewer extends Applet {
    ControlPanel controls;
    DisplayArea display;
    String fileName;
    Thread athread;
    boolean inAnApplet;


    public CrystalViewer() {
        /* 56 */
        this.inAnApplet = true;

    }


    public static void main(String[] args) {
        /* 44 */
        Frame frame = new Frame("Molecular Model Viewer");
        /* 45 */
        CrystalViewer crystalviewer = new CrystalViewer();
        /* 46 */
        crystalviewer.inAnApplet = false;
        /* 47 */
        frame.add("Center", crystalviewer);
        /* 48 */
        frame.resize(500, 500);
        /* 49 */
        crystalviewer.init();
        /* 50 */
        crystalviewer.start();
        /* 51 */
        frame.show();

    }


    public void init() {
        /* 14 */
        setLayout(new BorderLayout(10, 10));
        /* 15 */
        this.display = new DisplayArea();
        /* 16 */
        add("Center", this.display);
        /* 17 */
        this.controls = new ControlPanel(this.display);
        /* 18 */
        add("North", this.controls);

    }


    public void start() {
        /* 23 */
        this.display.start();

    }


    public void stop() {
        /* 28 */
        this.display.stop();

    }


    public boolean handleEvent(Event event) {
        /* 33 */
        switch (event.id) {


            case 201:
                /* 36 */
                System.exit(0);
                /* 37 */
                return true;

        }
        /* 39 */
        return false;

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\CrystalViewer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */