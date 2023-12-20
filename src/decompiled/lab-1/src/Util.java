import java.applet.Applet;
import java.awt.*;

public class Util {

    public static Frame getFrame(Component component) {
        Component currentComponent = component;
        while (currentComponent != null) {
            if (currentComponent instanceof Frame) {
                return (Frame) currentComponent;
            }
            currentComponent = currentComponent.getParent();
        }
        return null;
    }

    public static Applet getApplet(Component component) {
        Component currentComponent = component;
        while (currentComponent != null) {
            if (currentComponent instanceof Applet) {
                return (Applet) currentComponent;
            }
            currentComponent = currentComponent.getParent();
        }
        return null;
    }
}
