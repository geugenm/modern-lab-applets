import java.awt.*;
import java.awt.image.ImageObserver;

abstract class LatticeCell {
    static boolean show_bonds;
    threeDPoint[] origPts;
    threeDPoint[] Pts;
    drawingObject[] ball_N_sticks;
    int[] zSeq;
    double xMin;
    double xMax;
    double yMin;
    double yMax;
    double zMin;
    double zMax;
    int tx;
    int ty;
    int tz;
    threeDMatrix m;
    boolean transformed;

    public LatticeCell(threeDPoint threedpoint) {
        /*  14 */
        this.Pts = new threeDPoint[8];
        /*  15 */
        this.origPts = new threeDPoint[8];
        /*  16 */
        build_lattice(threedpoint);
        /*  17 */
        for (int i = 0; i < 8; i++) {
            /*  18 */
            this.Pts[i] = new threeDPoint(this.origPts[i]);
        }
        /*  20 */
        this.m = new threeDMatrix();
        /*  21 */
        cubeSize();
    }

    abstract void build_lattice(threeDPoint paramthreeDPoint);

    void build_cell(Atom atom) {
        /*  28 */
        this.zSeq = new int[this.ball_N_sticks.length];
        /*  29 */
        for (int i = 0; i < this.zSeq.length; i++) {
            /*  30 */
            this.zSeq[i] = i;
        }
        /*  32 */
        for (int j = 0; j < 8; j++) {
            /*  33 */
            this.ball_N_sticks[j] = new latticevertex(atom, this.Pts[j]);
        }
        /*  35 */
        this.ball_N_sticks[8] = new edge((vertex) this.ball_N_sticks[0], (vertex) this.ball_N_sticks[1]);
        /*  36 */
        this.ball_N_sticks[9] = new edge((vertex) this.ball_N_sticks[0], (vertex) this.ball_N_sticks[2]);
        /*  37 */
        this.ball_N_sticks[10] = new edge((vertex) this.ball_N_sticks[0], (vertex) this.ball_N_sticks[4]);
        /*  38 */
        this.ball_N_sticks[11] = new edge((vertex) this.ball_N_sticks[3], (vertex) this.ball_N_sticks[1]);
        /*  39 */
        this.ball_N_sticks[12] = new edge((vertex) this.ball_N_sticks[3], (vertex) this.ball_N_sticks[2]);
        /*  40 */
        this.ball_N_sticks[13] = new edge((vertex) this.ball_N_sticks[3], (vertex) this.ball_N_sticks[7]);
        /*  41 */
        this.ball_N_sticks[14] = new edge((vertex) this.ball_N_sticks[5], (vertex) this.ball_N_sticks[4]);
        /*  42 */
        this.ball_N_sticks[15] = new edge((vertex) this.ball_N_sticks[5], (vertex) this.ball_N_sticks[7]);
        /*  43 */
        this.ball_N_sticks[16] = new edge((vertex) this.ball_N_sticks[5], (vertex) this.ball_N_sticks[1]);
        /*  44 */
        this.ball_N_sticks[17] = new edge((vertex) this.ball_N_sticks[6], (vertex) this.ball_N_sticks[7]);
        /*  45 */
        this.ball_N_sticks[18] = new edge((vertex) this.ball_N_sticks[6], (vertex) this.ball_N_sticks[4]);
        /*  46 */
        this.ball_N_sticks[19] = new edge((vertex) this.ball_N_sticks[6], (vertex) this.ball_N_sticks[2]);
    }


    void cubeSize() {
        /*  51 */
        this.xMin = this.xMax = (this.Pts[0]).X;
        /*  52 */
        this.yMin = this.yMax = (this.Pts[0]).Y;
        /*  53 */
        this.zMin = this.zMax = (this.Pts[0]).Z;
        /*  54 */
        int i = 1;
    }


    public void paint(Graphics g, ImageObserver imageobserver) {
        /*  80 */
        zSort();
        /*  81 */
        edge.get_minZ(this.zMin);
        /*  82 */
        for (int i = 0; i < this.ball_N_sticks.length; i++) {
            /*  83 */
            if (show_bonds || this.zSeq[i] < 30) {
                /*  84 */
                this.ball_N_sticks[this.zSeq[i]].paint(g, imageobserver);
            }
        }
    }

    protected void zSort() {
        /*  90 */
        for (int i = 1; i < this.zSeq.length; i++) {

            /*  92 */
            int j = i;
            /*  93 */
            int k = this.zSeq[i];
            /*  94 */
            for (double d = this.ball_N_sticks[k].Z(); j > 0 && d < this.ball_N_sticks[this.zSeq[j - 1]].Z(); j--) {
                /*  95 */
                this.zSeq[j] = this.zSeq[j - 1];
            }
            /*  97 */
            this.zSeq[j] = k;
        }
    }


    protected void ptTransformXYZ() {
        /* 104 */
        if (this.transformed) {
            return;
        }


        /* 109 */
        this.m.transform3D(this.origPts, this.Pts);
        /* 110 */
        cubeSize();
    }
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\LatticeCell.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */