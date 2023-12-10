import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;

class Atom {
    /*  89 */   private static final byte[] imageData = new byte[6400];
    private static final int hx = 15;
    private static final int hy = 15;
    private static final int R = 40;
    private static final int bgGrey = 192;
    private static final int nBalls = 16;
    private static final int maxr;

    static {
        /*  90 */
        int i = 0;
        /*  91 */
        for (int j = 80; --j >= 0; ) {

            /*  93 */
            int k = (int) (Math.sqrt((1600 - (j - 40) * (j - 40))) + 0.5D);
            /*  94 */
            int l = j * 80 + 40 - k;
            /*  95 */
            for (int i1 = -k; i1 < k; i1++) {

                /*  97 */
                int j1 = i1 + 15;
                /*  98 */
                int k1 = j - 40 + 15;
                /*  99 */
                int l1 = (int) (Math.sqrt((j1 * j1 + k1 * k1)) + 0.5D);
                /* 100 */
                if (l1 > i)
                    /* 101 */ i = l1;
                /* 102 */
                imageData[l++] = (l1 > 0) ? (byte) l1 : 1;
            }
        }


        /* 107 */
        maxr = i;
    }

    private final int red;
    private final int green;
    private final int blue;
    private final int diameter;
    private Image sphere;

    Atom(int i, int j, int k, int l) {
        /*  14 */
        this.diameter = i;
        /*  15 */
        this.red = j;
        /*  16 */
        this.green = k;
        /*  17 */
        this.blue = l;
    }

    Atom(Atom atom) {
        /*  22 */
        this.diameter = atom.diameter;
        /*  23 */
        this.red = atom.red;
        /*  24 */
        this.green = atom.green;
        /*  25 */
        this.blue = atom.blue;
    }

    Atom reSize(double d) {
        /*  30 */
        return new Atom((int) (this.diameter * d), this.red, this.green, this.blue);
    }

    private final int blend(int i, int j, float f) {
        /*  35 */
        return (int) (j + (i - j) * f);
    }

    private void Setup() {
        /*  40 */
        byte[] abyte0 = new byte[256];
        /*  41 */
        abyte0[0] = -64;
        /*  42 */
        byte[] abyte1 = new byte[256];
        /*  43 */
        abyte1[0] = -64;
        /*  44 */
        byte[] abyte2 = new byte[256];
        /*  45 */
        abyte2[0] = -64;
        /*  46 */
        for (int i = maxr; i >= 1; i--) {

            /*  48 */
            float f = i / maxr;
            /*  49 */
            abyte0[i] = (byte) blend(blend(this.red, 255, f), 192, 1.0F);
            /*  50 */
            abyte1[i] = (byte) blend(blend(this.green, 255, f), 192, 1.0F);
            /*  51 */
            abyte2[i] = (byte) blend(blend(this.blue, 255, f), 192, 1.0F);
        }

        /*  54 */
        IndexColorModel indexcolormodel = new IndexColorModel(8, maxr + 1, abyte0, abyte1, abyte2, 0);
        /*  55 */
        this.sphere = (new Canvas()).createImage(new MemoryImageSource(80, 80, indexcolormodel, imageData, 0, 80));
    }

    public int radius() {
        /*  60 */
        return this.diameter / 2;
    }

    public void paint(Graphics g, double d, double d1, ImageObserver imageobserver) {
        /*  65 */
        Image image = this.sphere;
        /*  66 */
        if (image == null) {

            /*  68 */
            Setup();
            /*  69 */
            image = this.sphere;
        }
        /*  71 */
        g.drawImage(image, (int) (d - (this.diameter / 2)), (int) (d1 - (this.diameter / 2)), this.diameter, this.diameter, imageobserver);
    }
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\Atom.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */