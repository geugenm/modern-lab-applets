/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ class F_Cubic_Cell
        /*    */ extends Cubic_Cell
        /*    */ {
    /*    */   F_Cubic_Cell(threeDPoint threedpoint) {
        /* 12 */
        super(threedpoint);
        /*    */
    }

    /*    */
    /*    */
    /*    */   F_Cubic_Cell(threeDPoint threedpoint, Atom atom, Atom atom1) {
        /* 17 */
        super(threedpoint);
        /* 18 */
        this.ball_N_sticks = new drawingObject[26];
        /* 19 */
        build_cell(atom, atom1);
        /*    */
    }

    /*    */
    /*    */
    /*    */   void build_cell(Atom atom, Atom atom1) {
        /* 24 */
        build_cell(atom);
        /* 25 */
        this.ball_N_sticks[20] = new centervertex(atom1, this.Pts[0], this.Pts[3]);
        /* 26 */
        this.ball_N_sticks[21] = new centervertex(atom1, this.Pts[0], this.Pts[5]);
        /* 27 */
        this.ball_N_sticks[22] = new centervertex(atom1, this.Pts[0], this.Pts[6]);
        /* 28 */
        this.ball_N_sticks[23] = new centervertex(atom1, this.Pts[1], this.Pts[7]);
        /* 29 */
        this.ball_N_sticks[24] = new centervertex(atom1, this.Pts[2], this.Pts[7]);
        /* 30 */
        this.ball_N_sticks[25] = new centervertex(atom1, this.Pts[4], this.Pts[7]);
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\F_Cubic_Cell.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */