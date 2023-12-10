import java.awt.*;
import java.awt.image.ImageObserver;


class centervertex
        extends vertex {
    threeDPoint v0;
    threeDPoint v1;


    centervertex(Atom atom, threeDPoint threedpoint, threeDPoint threedpoint1) {
        /* 14 */
        this.v0 = threedpoint;
        /* 15 */
        this.v1 = threedpoint1;
        /* 16 */
        this.the_atom = atom;

    }


    double Z() {
        /* 21 */
        return (this.v0.Z + this.v1.Z) / 2.0D;

    }


    double Y() {
        /* 26 */
        return (this.v0.Y + this.v1.Y) / 2.0D;

    }


    double X() {
        /* 31 */
        return (this.v0.X + this.v1.X) / 2.0D;

    }


    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 36 */
        this.the_atom.paint(g, ((int) (this.v0.X + this.v1.X) / 2), ((int) (this.v0.Y + this.v1.Y) / 2), imageobserver);

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\centervertex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */