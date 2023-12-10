class Cubic_Cell
        extends LatticeCell {


    Cubic_Cell(threeDPoint threedpoint) {
        /* 25 */
        super(threedpoint);

    }


    Cubic_Cell(threeDPoint threedpoint, Atom atom) {
        /* 30 */
        super(threedpoint);
        /* 31 */
        this.ball_N_sticks = new drawingObject[20];
        /* 32 */
        build_cell(atom);

    }

    void build_lattice(threeDPoint threedpoint) {
        /* 12 */
        char c = '';
        /* 13 */
        this.origPts[0] = new threeDPoint(threedpoint.X - (c / 2), threedpoint.Y + (c / 2), threedpoint.Z - (c / 2));
        /* 14 */
        this.origPts[4] = new threeDPoint(threedpoint.X - (c / 2), threedpoint.Y - (c / 2), threedpoint.Z - (c / 2));
        /* 15 */
        this.origPts[5] = new threeDPoint(threedpoint.X + (c / 2), threedpoint.Y - (c / 2), threedpoint.Z - (c / 2));
        /* 16 */
        this.origPts[1] = new threeDPoint(threedpoint.X + (c / 2), threedpoint.Y + (c / 2), threedpoint.Z - (c / 2));
        /* 17 */
        this.origPts[2] = new threeDPoint(threedpoint.X - (c / 2), threedpoint.Y + (c / 2), threedpoint.Z + (c / 2));
        /* 18 */
        this.origPts[6] = new threeDPoint(threedpoint.X - (c / 2), threedpoint.Y - (c / 2), threedpoint.Z + (c / 2));
        /* 19 */
        this.origPts[7] = new threeDPoint(threedpoint.X + (c / 2), threedpoint.Y - (c / 2), threedpoint.Z + (c / 2));
        /* 20 */
        this.origPts[3] = new threeDPoint(threedpoint.X + (c / 2), threedpoint.Y + (c / 2), threedpoint.Z + (c / 2));

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\Cubic_Cell.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */