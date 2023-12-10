/*    */

import java.applet.Applet;
import java.awt.*;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ class Util
        /*    */ {
    /*    */
    public static Frame getFrame(Component component) {
        /* 15 */
        Object obj = component;
        /* 16 */
        if (obj instanceof Frame)
            /* 17 */ return (Frame) obj;
        /* 18 */
        while ((obj = ((Component) obj).getParent()) != null) {
            /* 19 */
            if (obj instanceof Frame)
                /* 20 */ return (Frame) obj;
            /* 21 */
        }
        return null;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    public static Applet getApplet(Component component) {
        /* 26 */
        Object obj = component;
        /* 27 */
        if (obj instanceof Applet)
            /* 28 */ return (Applet) obj;
        /* 29 */
        while ((obj = ((Component) obj).getParent()) != null) {
            /* 30 */
            if (obj instanceof Applet)
                /* 31 */ return (Applet) obj;
            /* 32 */
        }
        return null;
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\Util.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */