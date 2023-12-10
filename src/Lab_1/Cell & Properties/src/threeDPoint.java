class threeDPoint {
    double X;
    double Y;
    double Z;


    threeDPoint(double d, double d1, double d2) {
        /* 12 */
        this.X = d;
        /* 13 */
        this.Y = d1;
        /* 14 */
        this.Z = d2;

    }


    threeDPoint(threeDPoint threedpoint) {
        /* 19 */
        this.X = threedpoint.X;
        /* 20 */
        this.Y = threedpoint.Y;
        /* 21 */
        this.Z = threedpoint.Z;

    }


    public void build(double d, double d1, double d2) {
        /* 26 */
        this.X = d;
        /* 27 */
        this.Y = d1;
        /* 28 */
        this.Z = d2;

    }


    public void copy(threeDPoint threedpoint) {
        /* 33 */
        this.X = threedpoint.X;
        /* 34 */
        this.Y = threedpoint.Y;
        /* 35 */
        this.Z = threedpoint.Z;

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\threeDPoint.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */