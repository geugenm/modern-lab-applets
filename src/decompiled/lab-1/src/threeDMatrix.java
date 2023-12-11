class threeDMatrix {
    static final double pi = 3.14159265D;
    double[][] m;

    threeDMatrix() {
        /*  12 */
        this.m = new double[4][4];
        /*  13 */
        unit();
    }


    void copy(threeDMatrix threedmatrix) {
        /*  18 */
        for (int i = 0; i < 4; i++) {

            /*  20 */
            /*  21 */
            System.arraycopy(threedmatrix.m[i], 0, this.m[i], 0, 4);
        }
    }


    void unit() {
        /*  29 */
        for (int i = 0; i < 4; i++) {

            /*  31 */
            for (int j = 0; j < 4; j++) {
                /*  32 */
                this.m[i][j] = (i == j) ? 1.0D : 0.0D;
            }
        }
    }


    void rotateXYZ(int i, double d) {
        /*  40 */
        d *= 0.017453292500000002D;
        /*  41 */
        double d1 = Math.cos(d);
        /*  42 */
        double d2 = Math.sin(d);
        /*  43 */
        switch (i) {

            case 1:
                /*  46 */
                this.m[1][1] = d1;
                /*  47 */
                this.m[2][2] = d1;
                /*  48 */
                this.m[1][2] = -d2;
                /*  49 */
                this.m[2][1] = d2;
                return;

            case 2:
                /*  53 */
                this.m[0][0] = d1;
                /*  54 */
                this.m[2][2] = d1;
                /*  55 */
                this.m[2][0] = -d2;
                /*  56 */
                this.m[0][2] = d2;
                return;

            case 3:
                /*  60 */
                this.m[0][0] = d1;
                /*  61 */
                this.m[1][1] = d1;
                /*  62 */
                this.m[0][1] = -d2;
                /*  63 */
                this.m[1][0] = d2;
        }
    }


    void translateXYZ(int i, double d, double d1, double d2) {
        /*  70 */
        if (i == 0 || i == 1)
            /*  71 */ this.m[0][3] = d;
        /*  72 */
        if (i == 0 || i == 2)
            /*  73 */ this.m[1][3] = d1;
        /*  74 */
        if (i == 0 || i == 3) {
            /*  75 */
            this.m[2][3] = d2;
        }
    }

    void scaleXYZ(int i, float f, float f1, float f2) {
        /*  80 */
        if (i == 0 || i == 1)
            /*  81 */ this.m[0][0] = f;
        /*  82 */
        if (i == 0 || i == 2)
            /*  83 */ this.m[1][1] = f1;
        /*  84 */
        if (i == 0 || i == 3) {
            /*  85 */
            this.m[2][2] = f2;
        }
    }

    void multiply4X4(threeDMatrix threedmatrix) {
        /*  90 */
        threeDMatrix threedmatrix1 = new threeDMatrix();
        /*  91 */
        for (int i = 0; i < 4; i++) {

            /*  93 */
            for (int j = 0; j < 4; j++) {

                /*  95 */
                threedmatrix1.m[i][j] = 0.0D;
                /*  96 */
                for (int k = 0; k < 4; k++) {
                    /*  97 */
                    threedmatrix1.m[i][j] = threedmatrix1.m[i][j] + this.m[i][k] * threedmatrix.m[k][j];
                }
            }
        }


        /* 103 */
        copy(threedmatrix1);
    }


    void transform3D(threeDPoint[] athreedpoint) {
        /* 108 */
        for (int i = 0; i < athreedpoint.length; i++) {

            /* 110 */
            double d = (athreedpoint[i]).X;
            /* 111 */
            double d1 = (athreedpoint[i]).Y;
            /* 112 */
            double d2 = (athreedpoint[i]).Z;
            /* 113 */
            (athreedpoint[i]).X = this.m[0][0] * d + this.m[0][1] * d1 + this.m[0][2] * d2 + this.m[0][3];
            /* 114 */
            (athreedpoint[i]).Y = this.m[1][0] * d + this.m[1][1] * d1 + this.m[1][2] * d2 + this.m[1][3];
            /* 115 */
            (athreedpoint[i]).Z = this.m[2][0] * d + this.m[2][1] * d1 + this.m[2][2] * d2 + this.m[2][3];
        }
    }


    void transform3D(threeDPoint[] athreedpoint, threeDPoint[] athreedpoint1) {
        /* 122 */
        for (int i = 0; i < athreedpoint1.length; i++) {

            /* 124 */
            double d = (athreedpoint[i]).X;
            /* 125 */
            double d1 = (athreedpoint[i]).Y;
            /* 126 */
            double d2 = (athreedpoint[i]).Z;
            /* 127 */
            (athreedpoint1[i]).X = this.m[0][0] * d + this.m[0][1] * d1 + this.m[0][2] * d2 + this.m[0][3];
            /* 128 */
            (athreedpoint1[i]).Y = this.m[1][0] * d + this.m[1][1] * d1 + this.m[1][2] * d2 + this.m[1][3];
            /* 129 */
            (athreedpoint1[i]).Z = this.m[2][0] * d + this.m[2][1] * d1 + this.m[2][2] * d2 + this.m[2][3];
        }
    }
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\threeDMatrix.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */