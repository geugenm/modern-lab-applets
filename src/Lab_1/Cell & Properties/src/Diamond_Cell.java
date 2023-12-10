class Diamond_Cell
        extends F_Cubic_Cell {
    Diamond_Cell(threeDPoint threedpoint) {
        /* 12 */
        super(threedpoint);

    }


    Diamond_Cell(threeDPoint threedpoint, Atom atom, Atom atom1, Atom atom2) {
        /* 17 */
        super(threedpoint);
        /* 18 */
        this.ball_N_sticks = new drawingObject[46];
        /* 19 */
        build_cell(atom, atom1, atom2);

    }


    Diamond_Cell(threeDPoint threedpoint, Atom atom, Atom atom1) {
        /* 24 */
        this(threedpoint, atom, atom, atom1);

    }


    void build_cell(Atom atom, Atom atom1, Atom atom2) {
        /* 29 */
        build_cell(atom, atom1);
        /* 30 */
        this.ball_N_sticks[26] = new diamondvertex(atom2, this.Pts[0], this.Pts[3], this.Pts[5], this.Pts[6]);
        /* 31 */
        this.ball_N_sticks[27] = new diamondvertex(atom2, this.Pts[3], this.Pts[0], this.Pts[5], this.Pts[6]);
        /* 32 */
        this.ball_N_sticks[28] = new diamondvertex(atom2, this.Pts[5], this.Pts[0], this.Pts[3], this.Pts[6]);
        /* 33 */
        this.ball_N_sticks[29] = new diamondvertex(atom2, this.Pts[6], this.Pts[0], this.Pts[3], this.Pts[5]);
        /* 34 */
        this.ball_N_sticks[30] = new stick((vertex) this.ball_N_sticks[26], (vertex) this.ball_N_sticks[0]);
        /* 35 */
        this.ball_N_sticks[31] = new stick((vertex) this.ball_N_sticks[26], (vertex) this.ball_N_sticks[20]);
        /* 36 */
        this.ball_N_sticks[32] = new stick((vertex) this.ball_N_sticks[26], (vertex) this.ball_N_sticks[21]);
        /* 37 */
        this.ball_N_sticks[33] = new stick((vertex) this.ball_N_sticks[26], (vertex) this.ball_N_sticks[22]);
        /* 38 */
        this.ball_N_sticks[34] = new stick((vertex) this.ball_N_sticks[27], (vertex) this.ball_N_sticks[3]);
        /* 39 */
        this.ball_N_sticks[35] = new stick((vertex) this.ball_N_sticks[27], (vertex) this.ball_N_sticks[20]);
        /* 40 */
        this.ball_N_sticks[36] = new stick((vertex) this.ball_N_sticks[27], (vertex) this.ball_N_sticks[23]);
        /* 41 */
        this.ball_N_sticks[37] = new stick((vertex) this.ball_N_sticks[27], (vertex) this.ball_N_sticks[24]);
        /* 42 */
        this.ball_N_sticks[38] = new stick((vertex) this.ball_N_sticks[28], (vertex) this.ball_N_sticks[5]);
        /* 43 */
        this.ball_N_sticks[39] = new stick((vertex) this.ball_N_sticks[28], (vertex) this.ball_N_sticks[21]);
        /* 44 */
        this.ball_N_sticks[40] = new stick((vertex) this.ball_N_sticks[28], (vertex) this.ball_N_sticks[23]);
        /* 45 */
        this.ball_N_sticks[41] = new stick((vertex) this.ball_N_sticks[28], (vertex) this.ball_N_sticks[25]);
        /* 46 */
        this.ball_N_sticks[42] = new stick((vertex) this.ball_N_sticks[29], (vertex) this.ball_N_sticks[6]);
        /* 47 */
        this.ball_N_sticks[43] = new stick((vertex) this.ball_N_sticks[29], (vertex) this.ball_N_sticks[22]);
        /* 48 */
        this.ball_N_sticks[44] = new stick((vertex) this.ball_N_sticks[29], (vertex) this.ball_N_sticks[24]);
        /* 49 */
        this.ball_N_sticks[45] = new stick((vertex) this.ball_N_sticks[29], (vertex) this.ball_N_sticks[25]);

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\Diamond_Cell.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */