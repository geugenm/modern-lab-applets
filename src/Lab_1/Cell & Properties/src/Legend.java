import java.awt.*;
import java.awt.image.ImageObserver;


class Legend {
    static int centX;
    static int centY;
    edge a_edge;
    stick a_stick;
    String label1;
    String label2;
    int offset;
    String lattice_const;
    String atom_dist;


    Legend(Atom atom, Atom atom1, String s, String s1, String s2, String s3) {
        /* 14 */
        this.offset = 10;
        /* 15 */
        this.lattice_const = s2;
        /* 16 */
        this.atom_dist = s3;
        /* 17 */
        this.label1 = s;
        /* 18 */
        this.label2 = s1;
        /* 19 */
        Atom atom2 = atom.reSize(0.7D);
        /* 20 */
        Atom atom3 = atom1.reSize(0.7D);
        /* 21 */
        latticevertex latticevertex1 = new latticevertex(atom2, new threeDPoint((centX - 50), (centY + this.offset), 0.0D));
        /* 22 */
        latticevertex latticevertex2 = new latticevertex(atom2, new threeDPoint((centX + 50), (centY + this.offset), 0.0D));
        /* 23 */
        this.a_edge = new edge(latticevertex1, latticevertex2);
        /* 24 */
        latticevertex1 = new latticevertex(atom2, new threeDPoint((centX - 22), (centY - this.offset), 0.0D));
        /* 25 */
        latticevertex2 = new latticevertex(atom3, new threeDPoint((centX + 22), (centY - this.offset), 0.0D));
        /* 26 */
        this.a_stick = new stick(latticevertex1, latticevertex2);

    }


    Legend(Atom atom, String s, String s1, String s2, String s3) {
        /* 31 */
        this(atom, atom, s, s1, s2, s3);

    }


    public void paint(Graphics g, ImageObserver imageobserver) {
        /* 36 */
        this.a_edge.paint(g, imageobserver);
        /* 37 */
        this.a_stick.paint(g, imageobserver);
        /* 38 */
        g.setColor(Color.white);
        /* 39 */
        g.drawLine(centX - 50, centY + this.offset, centX - 50, centY + this.offset + 20);
        /* 40 */
        g.drawLine(centX + 50, centY + this.offset, centX + 50, centY + this.offset + 20);
        /* 41 */
        g.drawLine(centX - 22, centY - this.offset, centX - 22, centY - this.offset - 20);
        /* 42 */
        g.drawLine(centX + 22, centY - this.offset, centX + 22, centY - this.offset - 20);
        /* 43 */
        g.drawLine(centX - 50, centY + this.offset + 15, centX - 47, centY + this.offset + 17);
        /* 44 */
        g.drawLine(centX - 50, centY + this.offset + 15, centX - 47, centY + this.offset + 13);
        /* 45 */
        g.drawLine(centX - 50, centY + this.offset + 15, centX - 30, centY + this.offset + 15);
        /* 46 */
        g.drawLine(centX + 50, centY + this.offset + 15, centX + 47, centY + this.offset + 17);
        /* 47 */
        g.drawLine(centX + 50, centY + this.offset + 15, centX + 47, centY + this.offset + 13);
        /* 48 */
        g.drawLine(centX + 50, centY + this.offset + 15, centX + 30, centY + this.offset + 15);
        /* 49 */
        g.drawLine(centX - 22, centY - this.offset - 15, centX - 19, centY - this.offset - 17);
        /* 50 */
        g.drawLine(centX - 22, centY - this.offset - 15, centX - 19, centY - this.offset - 13);
        /* 51 */
        g.drawLine(centX - 22, centY - this.offset - 15, centX - 17, centY - this.offset - 15);
        /* 52 */
        g.drawLine(centX + 22, centY - this.offset - 15, centX + 19, centY - this.offset - 17);
        /* 53 */
        g.drawLine(centX + 22, centY - this.offset - 15, centX + 19, centY - this.offset - 13);
        /* 54 */
        g.drawLine(centX + 22, centY - this.offset - 15, centX + 17, centY - this.offset - 15);
        /* 55 */
        Font font = new Font("Courier", 0, 10);
        /* 56 */
        g.setFont(font);
        /* 57 */
        drawCenteredString(this.lattice_const, centX - 30, centX + 30, centY + this.offset + 10, centY + this.offset + 17, g);
        /* 58 */
        drawCenteredString(this.atom_dist, centX - 17, centX + 17, centY - this.offset - 20, centY - this.offset - 13, g);
        /* 59 */
        this.a_edge.v0.paint(g, imageobserver);
        /* 60 */
        this.a_edge.v1.paint(g, imageobserver);
        /* 61 */
        this.a_stick.v0.paint(g, imageobserver);
        /* 62 */
        this.a_stick.v1.paint(g, imageobserver);
        /* 63 */
        g.drawString(this.label1, centX - 43, centY - this.offset + 4);
        /* 64 */
        g.drawString(this.label2, centX + 32, centY - this.offset + 4);

    }


    protected void drawCenteredString(String s, int i, int j, int k, int l, Graphics g) {
        /* 69 */
        FontMetrics fontmetrics = g.getFontMetrics();
        /* 70 */
        int i1 = i + (j - i - fontmetrics.stringWidth(s)) / 2;
        /* 71 */
        int j1 = k + fontmetrics.getAscent() + (l - k - fontmetrics.getAscent() + fontmetrics.getDescent()) / 2;
        /* 72 */
        g.drawString(s, i1, j1);

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\Legend.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */