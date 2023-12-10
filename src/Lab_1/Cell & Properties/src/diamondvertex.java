/*    */

import java.awt.*;
import java.awt.image.ImageObserver;

/*    */
/*    */
/*    */ class diamondvertex
        /*    */ extends vertex
        /*    */ {
    /*    */ threeDPoint v0;
    /*    */ threeDPoint v1;
    /*    */ threeDPoint v2;
    /*    */ threeDPoint v3;

    /*    */
    /*    */   diamondvertex(Atom atom, threeDPoint threedpoint, threeDPoint threedpoint1, threeDPoint threedpoint2, threeDPoint threedpoint3) {
        /* 14 */
        this.v0 = threedpoint;
        /* 15 */
        this.v1 = threedpoint1;
        /* 16 */
        this.v2 = threedpoint2;
        /* 17 */
        this.v3 = threedpoint3;
        /* 18 */
        this.the_atom = atom;
        /*    */
    }

    /*    */
    /*    */
    /*    */   double Z() {
        /* 23 */
        return (5.0D * this.v0.Z + this.v1.Z + this.v2.Z + this.v3.Z) / 8.0D;
        /*    */
    }

    /*    */
    /*    */
    /*    */   double Y() {
        /* 28 */
        return (5.0D * this.v0.Y + this.v1.Y + this.v2.Y + this.v3.Y) / 8.0D;
        /*    */
    }

    /*    */
    /*    */
    /*    */   double X() {
        /* 33 */
        return (5.0D * this.v0.X + this.v1.X + this.v2.X + this.v3.X) / 8.0D;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 38 */
        this.the_atom.paint(g, ((int) (5.0D * this.v0.X + this.v1.X + this.v2.X + this.v3.X) / 8), ((int) (5.0D * this.v0.Y + this.v1.Y + this.v2.Y + this.v3.Y) / 8), imageobserver);
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\diamondvertex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */