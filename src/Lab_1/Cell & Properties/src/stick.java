import java.awt.*;
import java.awt.image.ImageObserver;


class stick
        extends drawingObject {
    vertex v0;
    vertex v1;
    double ratio0;
    double ratio1;
    Color ln_color;


    stick(vertex vertex1, vertex vertex2) {
        /* 15 */
        this.ln_color = Color.blue;
        /* 16 */
        this.v0 = vertex1;
        /* 17 */
        this.v1 = vertex2;
        /* 18 */
        double d = this.v0.X() - this.v1.X();
        /* 19 */
        double d1 = this.v0.Y() - this.v1.Y();
        /* 20 */
        double d2 = this.v0.Z() - this.v1.Z();
        /* 21 */
        double d3 = Math.sqrt(d * d + d1 * d1 + d2 * d2);
        /* 22 */
        this.ratio0 = this.v0.radius() / d3;
        /* 23 */
        this.ratio1 = this.v1.radius() / d3;

    }


    double Z() {
        /* 28 */
        return (this.v0.Z() + this.v1.Z()) / 2.0D;

    }


    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 33 */
        double d = this.v0.X();
        /* 34 */
        double d1 = this.v1.X();
        /* 35 */
        double d2 = this.v0.Y();
        /* 36 */
        double d3 = this.v1.Y();
        /* 37 */
        int i = (int) (d + (d1 - d) * this.ratio0);
        /* 38 */
        int j = (int) (d1 + (d - d1) * this.ratio1);
        /* 39 */
        int k = (int) (d2 + (d3 - d2) * this.ratio0);
        /* 40 */
        int l = (int) (d3 + (d2 - d3) * this.ratio1);
        /* 41 */
        g.setColor(this.ln_color);
        /* 42 */
        g.drawLine(i, k, j, l);
        /* 43 */
        g.drawLine(i - 1, k - 1, j + 1, l - 1);
        /* 44 */
        g.drawLine(i - 1, k + 1, j + 1, l + 1);
        /* 45 */
        g.drawLine(i - 1, k - 1, j + 1, l + 2);
        /* 46 */
        g.drawLine(i - 1, k + 1, j + 1, l - 2);
        /* 47 */
        g.drawLine(i - 1, k - 2, j + 1, l + 1);
        /* 48 */
        g.drawLine(i - 1, k + 2, j + 1, l - 1);

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\stick.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */