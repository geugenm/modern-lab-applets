import java.awt.*;


class ControlPanel extends Panel {
    private final Panel displayPanel;
    DisplayArea display;
    Button showBonds;
    Label displayMesg;
    Choice models;


    public ControlPanel(DisplayArea displayarea) {
        this.display = displayarea;
        this.models = new Choice();
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
        this.models.addItem("Si");
        this.models.addItem("ZnS");
        this.models.addItem("ZnSe");
        this.models.addItem("ZnTe");
        this.showBonds = new Button("Показать границы");
        this.displayMesg = new Label(" ", 1);
        this.displayPanel = new Panel();
        this.displayPanel.setLayout(new BorderLayout());
        this.displayPanel.add("West", this.showBonds);
        this.displayPanel.add("Center", this.displayMesg);
        this.displayPanel.add("East", this.models);
        setLayout(new BorderLayout());
        add("North", this.displayPanel);
    }


    @Override
    public boolean action(Event event, Object obj) {
        /* 35 */
        if (event.target == this.models) {
            String s = obj.toString();
            this.display.setModel(s);
        }
        /* 49 */
        if (event.target == this.showBonds)
            /* 50 */ if ("Показать границы".equals(obj)) {

            /* 52 */
            this.display.showBonds();
            /* 53 */
            this.showBonds.setLabel("Спрятать границы");

        }
        /* 55 */
        else if ("Спрятать границы".equals(obj)) {

            /* 57 */
            this.display.hideBonds();
            /* 58 */
            this.showBonds.setLabel("Показать границы");

        }
        /* 60 */
        return true;

    }

}