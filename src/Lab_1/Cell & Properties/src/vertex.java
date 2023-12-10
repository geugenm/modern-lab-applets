import java.awt.*;
import java.awt.image.ImageObserver;


abstract class vertex
        extends drawingObject {
    Atom the_atom;


    abstract double X();


    abstract double Y();


    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 18 */
        this.the_atom.paint(g, (int) X(), (int) Y(), imageobserver);

    }


    double radius() {
        /* 23 */
        return this.the_atom.radius();

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\vertex.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */