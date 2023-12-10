/*    */

import java.awt.*;

/*    */
/*    */ class ControlPanel extends Panel {
    private final Panel displayPanel;
    /*    */ DisplayArea display;
    /*    */ Button showBonds;
    /*    */ Label Type;
    /*    */ Label modelName;
    /*    */ AtomDialog atomButtons;
    Label displayMesg;
    Button abort;
    Choice models;

    /*    */
    /*    */
    public ControlPanel(DisplayArea displayarea) {
        /* 13 */
        this.display = displayarea;
        /* 14 */
        this.models = new Choice();
        /* 15 */
        this.models.addItem("AlAs");
        this.models.addItem("AlP");
        this.models.addItem("AlSb");
        this.models.addItem("CdS");
        this.models.addItem("CdSe");
        this.models.addItem("CdTe");
        this.models.addItem("GaAs");
        this.models.addItem("GaP");
        this.models.addItem("GaSb");
        this.models.addItem("Ge");
        this.models.addItem("InAs");
        this.models.addItem("InP");
        this.models.addItem("InSb");
        /* 22 */
        this.showBonds = new Button("Показать границы");
        /* 23 */
        this.displayMesg = new Label(" ", 1);
        /* 24 */
        this.displayPanel = new Panel();
        /* 25 */
        this.displayPanel.setLayout(new BorderLayout());
        /* 26 */
        this.displayPanel.add("West", this.showBonds);
        /* 27 */
        this.displayPanel.add("Center", this.displayMesg);
        /* 28 */
        this.displayPanel.add("East", this.models);
        /* 29 */
        setLayout(new BorderLayout());
        /* 30 */
        add("North", this.displayPanel);
        /*    */
    }

    /*    */
    /*    */
    public boolean action(Event event, Object obj) {
        /* 35 */
        if (event.target == this.models) {
            /*    */
            /* 37 */
            String s = obj.toString();
            /* 38 */
            if (s.equals("Ещё")) {
                /*    */
                /* 40 */
                if (this.atomButtons == null)
                    /* 41 */ this.atomButtons = new AtomDialog(this.display);
                /* 42 */
                this.atomButtons.show();
                /*    */
            } else {
                /*    */
                /* 45 */
                this.display.setModel(s);
                /*    */
            }
            /* 47 */
            s = null;
            /*    */
        }
        /* 49 */
        if (event.target == this.showBonds)
            /* 50 */ if ("Показать границы".equals(obj)) {
            /*    */
            /* 52 */
            this.display.showBonds();
            /* 53 */
            this.showBonds.setLabel("Спрятать границы");
            /*    */
        }
        /* 55 */
        else if ("Спрятать границы".equals(obj)) {
            /*    */
            /* 57 */
            this.display.hideBonds();
            /* 58 */
            this.showBonds.setLabel("Показать границы");
            /*    */
        }
        /* 60 */
        return true;
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\ControlPanel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */