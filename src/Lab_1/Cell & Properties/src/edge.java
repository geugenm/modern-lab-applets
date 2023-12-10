/*    */

import java.awt.*;
import java.awt.image.ImageObserver;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ class edge
        /*    */ extends stick
        /*    */ {
    /*    */   static double minZ;

    /*    */
    /*    */   edge(vertex vertex1, vertex vertex2) {
        /* 15 */
        super(vertex1, vertex2);
        /*    */
    }

    /*    */
    /*    */
    /*    */
    static void get_minZ(double d) {
        /* 20 */
        minZ = d;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 25 */
        this.ln_color = (this.v0.Z() != minZ && this.v1.Z() != minZ) ? Color.white : Color.gray;
        /* 26 */
        double d = this.v0.X();
        /* 27 */
        double d1 = this.v1.X();
        /* 28 */
        double d2 = this.v0.Y();
        /* 29 */
        double d3 = this.v1.Y();
        /* 30 */
        int i = (int) (d + (d1 - d) * this.ratio0);
        /* 31 */
        int j = (int) (d1 + (d - d1) * this.ratio1);
        /* 32 */
        int k = (int) (d2 + (d3 - d2) * this.ratio0);
        /* 33 */
        int l = (int) (d3 + (d2 - d3) * this.ratio1);
        /* 34 */
        g.setColor(this.ln_color);
        /* 35 */
        g.drawLine(i, k, j, l);
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\edge.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */