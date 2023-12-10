import java.awt.*;

public class ControlPanel extends Panel {
    private final Panel displayPanel;
    DisplayArea display;
    Button showBonds;
    Label displayMesg;
    Choice models;

    public ControlPanel(DisplayArea displayArea) {
        this.display = displayArea;
        this.models = new Choice();
        addModelItems();
        this.showBonds = new Button(getShowBondsLabel());
        this.displayMesg = new Label(" ", Label.CENTER);
        this.displayPanel = new Panel(new BorderLayout());
        configureDisplayPanel();
        setLayout(new BorderLayout());
        add(this.displayPanel, BorderLayout.NORTH);
    }

    private void addModelItems() {
        final String[] modelItems = {
                "AlAs", "AlP", "AlSb", "CdS", "CdSe", "CdTe", "GaAs", "GaP", "GaSb",
                "Ge", "InAs", "InP", "InSb", "Si", "ZnS", "ZnSe", "ZnTe"
        };
        for (String item : modelItems) {
            this.models.addItem(item);
        }
    }

    private String getShowBondsLabel() {
        return "Показать границы";
    }

    private String getHideBondsLabel() {
        return "Спрятать границы";
    }

    private void configureDisplayPanel() {
        this.displayPanel.add(this.showBonds, BorderLayout.WEST);
        this.displayPanel.add(this.displayMesg, BorderLayout.CENTER);
        this.displayPanel.add(this.models, BorderLayout.EAST);
    }

    @Override
    public boolean action(Event event, Object obj) {
        if (event.target == this.models) {
            handleModelSelection(obj);
        } else if (event.target == this.showBonds) {
            handleShowBonds(obj);
        }
        return true;
    }

    private void handleModelSelection(Object obj) {
        if (obj instanceof String) {
            final String selectedModel = obj.toString();
            this.display.setModel(selectedModel);
        }
    }

    private void handleShowBonds(Object obj) {
        final String showLabel = getShowBondsLabel();
        final String hideLabel = getHideBondsLabel();

        if (showLabel.equals(obj)) {
            this.display.showBonds();
            this.showBonds.setLabel(hideLabel);
        } else if (hideLabel.equals(obj)) {
            this.display.hideBonds();
            this.showBonds.setLabel(showLabel);
        }
    }
}
