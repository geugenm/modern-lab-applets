import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

class DisplayArea
        extends Canvas
        implements Runnable {
    /* 319 */   static Hashtable atomTable = new Hashtable();

    static {
        /* 320 */
        atomTable.put("Zn", new Atom(25, 100, 20, 255));
        /* 321 */
        atomTable.put("Cd", new Atom(25, 150, 20, 255));
        /* 322 */
        atomTable.put("Al", new Atom(25, 20, 100, 255));
        /* 323 */
        atomTable.put("Ga", new Atom(25, 20, 150, 255));
        /* 324 */
        atomTable.put("In", new Atom(25, 20, 200, 255));
        /* 325 */
        atomTable.put("Si", new Atom(25, 60, 60, 60));
        /* 326 */
        atomTable.put("Ge", new Atom(25, 80, 80, 80));
        /* 327 */
        atomTable.put("Sn", new Atom(25, 100, 100, 100));
        /* 328 */
        atomTable.put("P", new Atom(25, 255, 0, 100));
        /* 329 */
        atomTable.put("As", new Atom(25, 255, 0, 150));
        /* 330 */
        atomTable.put("Sb", new Atom(25, 255, 0, 200));
        /* 331 */
        atomTable.put("S", new Atom(25, 255, 100, 0));
        /* 332 */
        atomTable.put("Se", new Atom(25, 255, 150, 0));
        /* 333 */
        atomTable.put("Te", new Atom(25, 255, 200, 0));
        /* 334 */
        atomTable.put("Cu", new Atom(25, 100, 100, 255));
    }

    LatticeCell cell;
    Legend legend;
    double tx;
    double ty;
    double xTheta;
    double yTheta;
    int prevx;
    int prevy;
    threeDPoint center;
    Thread athread;
    threeDMatrix amat;
    threeDMatrix tmat;
    boolean painted;
    boolean moved;
    Image backBuffer;
    Graphics backGC;
    String model;
    String status;

    DisplayArea() {
        /* 291 */
        this.amat = new threeDMatrix();
        /* 292 */
        this.tmat = new threeDMatrix();
        /* 293 */
        this.painted = false;
        /* 294 */
        this.moved = true;
    }

    public void run() {
        /*  19 */
        Thread.currentThread().setPriority(6);
        /*  20 */
        this.backBuffer = createImage((size()).width, (size()).height);
        /*  21 */
        this.backGC = this.backBuffer.getGraphics();
        /*  22 */
        setModel("GaAs");
        /*  23 */
        while (this.athread != null) {


            try {
                /*  27 */
                Thread.sleep(20L);
            }
            /*  29 */ catch (InterruptedException interruptedException) {
                return;
            }

            /*  33 */
            repaint();
        }
    }

    public void start() {
        /*  39 */
        if (this.athread == null) {

            /*  41 */
            this.athread = new Thread(this);
            /*  42 */
            this.athread.start();
        }
    }

    public void stop() {
        /*  48 */
        reset();
        /*  49 */
        this.athread.stop();
        /*  50 */
        this.athread = null;
    }

    private void reset() {
        /*  55 */
        repaint();
    }

    public void setModel(String s) {
        /*  60 */
        if (this.model == null || !this.model.equals(s)) {

            /*  62 */
            this.model = s;
            /*  63 */
            this.center = new threeDPoint((size()).width / 2.0D - 5.0D, (size()).height / 2.0D - 20.0D, 0.0D);
            /*  64 */
            this.xTheta = 0.0D;
            /*  65 */
            this.yTheta = 0.0D;
            /*  66 */
            this.tx = 0.0D;
            /*  67 */
            this.ty = 0.0D;
            /*  68 */
            this.amat.unit();
            /*  69 */
            this.tmat.unit();
            /*  70 */
            Legend.centX = (size()).width - 80;
            /*  71 */
            Legend.centY = (size()).height - 37;
            /*  72 */
            this.legend = null;
            /*  73 */
            if (this.model.equals("S")) {
                /*  74 */
                this.cell = new Cubic_Cell(this.center, (Atom) atomTable.get("S"));
            }
            /*  76 */
            else if (this.model.equals("Cu")) {
                /*  77 */
                this.cell = new Cubic_Cell(this.center, (Atom) atomTable.get("Cu"));
            }
            /*  79 */
            else if (this.model.equals("ZnS")) {
                /*  80 */
                setModel("Zn", "S", "5.4093A", "2.3423");
            }
            /*  82 */
            else if (this.model.equals("ZnSe")) {
                /*  83 */
                setModel("Zn", "Se", "5.6676A", "2.4541");
            }
            /*  85 */
            else if (this.model.equals("ZnTe")) {
                /*  86 */
                setModel("Zn", "Te", "6.101A", "2.642");
            }
            /*  88 */
            else if (this.model.equals("CdS")) {
                /*  89 */
                setModel("Cd", "S", "5.832A", "2.525");
            }
            /*  91 */
            else if (this.model.equals("CdSe")) {
                /*  92 */
                setModel("Cd", "Se", "6.05A", "2.62");
            }
            /*  94 */
            else if (this.model.equals("CdTe")) {
                /*  95 */
                setModel("Cd", "Te", "6.477A", "2.805");
            }
            /*  97 */
            else if (this.model.equals("AlP")) {
                /*  98 */
                setModel("Al", "P", "5.451A", "2.360");
            }
            /* 100 */
            else if (this.model.equals("AlAs")) {
                /* 101 */
                setModel("Al", "As", "5.6622A", "2.4518");
            }
            /* 103 */
            else if (this.model.equals("AlSb")) {
                /* 104 */
                setModel("Al", "Sb", "6.1355A", "2.6567");
            }
            /* 106 */
            else if (this.model.equals("GaP")) {
                /* 107 */
                setModel("Ga", "P", "5.4505A", "2.3601");
            }
            /* 109 */
            else if (this.model.equals("GaAs")) {
                /* 110 */
                setModel("Ga", "As", "5.6533A", "2.4480");
            }
            /* 112 */
            else if (this.model.equals("GaSb")) {
                /* 113 */
                setModel("Ga", "Sb", "6.0954A", "2.6394");
            }
            /* 115 */
            else if (this.model.equals("InP")) {
                /* 116 */
                setModel("In", "P", "5.8688A", "2.5412");
            }
            /* 118 */
            else if (this.model.equals("InAs")) {
                /* 119 */
                setModel("In", "As", "6.0584A", "2.8509");
            }
            /* 121 */
            else if (this.model.equals("InSb")) {
                /* 122 */
                setModel("In", "Sb", "6.4788A", "2.8054");
            }
            /* 124 */
            else if (this.model.equals("Si")) {
                /* 125 */
                setModel("Si", "Si", "5.4310", "2.3517");
            }
            /* 127 */
            else if (this.model.equals("Ge")) {
                /* 128 */
                setModel("Ge", "Ge", "5.6461", "2.4448");
            }
            /* 130 */
            else if (this.model.equals("Sn")) {
                /* 131 */
                setModel("Sn", "Sn");
            } else {
                return;
                /* 134 */
            }
            Applet applet = Util.getApplet(this);

            try {
                /* 137 */
                applet.getAppletContext().showDocument(new URL(applet.getDocumentBase(), this.model + ".html"), "rightFrame");
            }
            /* 139 */ catch (MalformedURLException malformedURLException) {
            }
            /* 140 */
            this.painted = false;
            /* 141 */
            for (int i = 0; i < 40; i++) {
                /* 142 */
                if (!this.painted)
                    /* 143 */ repaint();
            }
            /* 145 */
            Applet applet1 = Util.getApplet(this);
            /* 146 */
            if (applet1 instanceof CrystalViewer) {
                /* 147 */
                ((CrystalViewer) applet1).controls.displayMesg.setText("Изображение ячейки " + this.model + ".");
            }
        }
    }

    private void setModel(String s, String s1) {
        /* 153 */
        Atom atom = (Atom) atomTable.get(s);
        /* 154 */
        Atom atom1 = (Atom) atomTable.get(s1);
        /* 155 */
        this.cell = new Diamond_Cell(this.center, atom, atom1);
    }

    private void setModel(String s, String s1, String s2, String s3) {
        /* 160 */
        Atom atom = (Atom) atomTable.get(s);
        /* 161 */
        Atom atom1 = (Atom) atomTable.get(s1);
        /* 162 */
        this.cell = new Diamond_Cell(this.center, atom, atom1);
        /* 163 */
        this.legend = new Legend(atom, atom1, s, s1, s2, s3);
    }

    public void showBonds() {
        try {
            /* 170 */
            LatticeCell.show_bonds = true;
        }
        /* 172 */ catch (IllegalAccessError illegalAccessError) {
        }
        /* 173 */
        this.painted = false;
        /* 174 */
        for (int i = 0; i < 40; i++) {
            /* 175 */
            if (!this.painted) {
                /* 176 */
                repaint();
            }
        }
    }

    public void hideBonds() {
        try {
            /* 184 */
            LatticeCell.show_bonds = false;
        }
        /* 186 */ catch (IllegalAccessError illegalAccessError) {
        }
        /* 187 */
        this.painted = false;
        /* 188 */
        for (int i = 0; i < 40; i++) {
            /* 189 */
            if (!this.painted) {
                /* 190 */
                repaint();
            }
        }
    }

    public boolean mouseDown(Event event, int i, int j) {
        /* 196 */
        this.prevx = i;
        /* 197 */
        this.prevy = j;
        /* 198 */
        return true;
    }

    public boolean mouseDrag(Event event, int i, int j) {
        /* 203 */
        this.xTheta += ((this.prevy - j) * 360.0F / (size()).width);
        /* 204 */
        this.yTheta += ((i - this.prevx) * 360.0F / (size()).height);
        /* 205 */
        this.amat.unit();
        /* 206 */
        this.amat.translateXYZ(0, this.tx, this.ty, 0.0D);
        /* 207 */
        this.tmat.unit();
        /* 208 */
        this.tmat.rotateXYZ(1, this.xTheta);
        /* 209 */
        this.amat.multiply4X4(this.tmat);
        /* 210 */
        this.tmat.unit();
        /* 211 */
        this.tmat.rotateXYZ(2, this.yTheta);
        /* 212 */
        this.amat.multiply4X4(this.tmat);
        /* 213 */
        this.painted = false;
        /* 214 */
        this.moved = true;
        /* 215 */
        this.prevx = i;
        /* 216 */
        this.prevy = j;
        /* 217 */
        return true;
    }

    private void transform(int i, int j, int k, int l) {
        /* 222 */
        if (i == 1)
            /* 223 */ this.tx += (5 * j);
        /* 224 */
        if (i == 2)
            /* 225 */ this.ty += (5 * j);
        /* 226 */
        if (k == 1)
            /* 227 */ this.xTheta += (2 * l);
        /* 228 */
        if (k == 2)
            /* 229 */ this.yTheta += (2 * l);
        /* 230 */
        this.amat.unit();
        /* 231 */
        this.amat.translateXYZ(0, this.tx, this.ty, 0.0D);
        /* 232 */
        this.tmat.unit();
        /* 233 */
        this.tmat.rotateXYZ(1, this.xTheta);
        /* 234 */
        this.amat.multiply4X4(this.tmat);
        /* 235 */
        this.tmat.unit();
        /* 236 */
        this.tmat.rotateXYZ(2, this.yTheta);
        /* 237 */
        this.amat.multiply4X4(this.tmat);
        /* 238 */
        this.painted = false;
        /* 239 */
        this.moved = true;
    }

    public void update(Graphics g) {
        /* 244 */
        if (this.backBuffer == null) {

            /* 246 */
            g.clearRect(0, 0, (size()).width, (size()).height);
            /* 247 */
            g.setColor(Color.blue);
            /* 248 */
            g.draw3DRect(5, 5, (size()).width - 15, (size()).height - 15, false);
            /* 249 */
            g.setColor(getBackground());
        }
        /* 251 */
        if (!this.painted) {
            /* 252 */
            paint(g);
        }
    }

    public void paint(Graphics g) {
        /* 257 */
        if (this.cell != null) {

            /* 259 */
            if (this.moved) {

                /* 261 */
                this.cell.m.unit();
                /* 262 */
                this.cell.m.translateXYZ(0, this.cell.tx, this.cell.ty, this.cell.tz);
                /* 263 */
                threeDMatrix threedmatrix = new threeDMatrix();
                /* 264 */
                threedmatrix.translateXYZ(0, -this.cell.tx, -this.cell.ty, -this.cell.tz);
                /* 265 */
                this.amat.multiply4X4(threedmatrix);
                /* 266 */
                this.cell.m.multiply4X4(this.amat);
                /* 267 */
                this.cell.ptTransformXYZ();
                /* 268 */
                this.cell.transformed = false;
                /* 269 */
                threedmatrix = null;
                /* 270 */
                this.moved = false;
            }
            /* 272 */
            if (this.backBuffer != null) {

                /* 274 */
                this.backGC.setColor(Color.black);
                /* 275 */
                this.backGC.fillRect(0, 0, (size()).width, (size()).height);
                /* 276 */
                this.cell.paint(this.backGC, this);
                /* 277 */
                if (this.legend != null)
                    /* 278 */ this.legend.paint(this.backGC, this);
                /* 279 */
                g.setColor(Color.black);
                /* 280 */
                g.drawImage(this.backBuffer, 0, 0, this);
            } else {

                /* 283 */
                this.cell.paint(g, this);
            }
        }
        /* 286 */
        this.painted = true;
    }
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\DisplayArea.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */