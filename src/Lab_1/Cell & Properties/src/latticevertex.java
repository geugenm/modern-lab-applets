/*    */

import java.awt.*;
import java.awt.image.ImageObserver;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ class latticevertex
        /*    */ extends vertex
        /*    */ {
    /*    */ threeDPoint position;

    /*    */
    /*    */   latticevertex(Atom atom, threeDPoint threedpoint) {
        /* 14 */
        this.position = threedpoint;
        /* 15 */
        this.the_atom = atom;
        /*    */
    }

    /*    */
    /*    */
    /*    */   double Z() {
        /* 20 */
        return this.position.Z;
        /*    */
    }

    /*    */
    /*    */
    /*    */   double Y() {
        /* 25 */
        return this.position.Y;
        /*    */
    }

    /*    */
    /*    */
    /*    */   double X() {
        /* 30 */
        return this.position.X;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 35 */
        this.the_atom.paint(g, (int) this.position.X, (int) this.position.Y, imageobserver);
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\latticevertex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */