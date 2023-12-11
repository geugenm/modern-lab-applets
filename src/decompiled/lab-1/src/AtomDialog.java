import java.awt.*;

class AtomDialog extends Dialog {
    TextField message;
    Button control_button;
    DisplayArea display;
    Panel atom_panel;
    boolean model_in_selection;
    String modelChoice;
    int charge;
    ElementButton curButton;

    public AtomDialog(DisplayArea displayarea) {
        /*  13 */
        super(Util.getFrame(displayarea), false);
        /*  14 */
        this.model_in_selection = false;
        /*  15 */
        hide();
        /*  16 */
        this.display = displayarea;
        /*  17 */
        setLayout(new BorderLayout(2, 3));
        /*  18 */
        this.message = new TextField(40);
        /*  19 */
        this.message.setEditable(false);
        /*  20 */
        this.control_button = new Button("Закрыть");
        /*  21 */
        ButtonGroupVars buttongroupvars = new ButtonGroupVars(0, false);
        /*  22 */
        this.atom_panel = new Panel();
        /*  23 */
        this.atom_panel.setLayout(new GridBagLayout());
        /*  24 */
        add2table(this.atom_panel, new Label("II", 1));
        /*  25 */
        add2table(this.atom_panel, new Label("III", 1));
        /*  26 */
        add2table(this.atom_panel, new Label("IV", 1));
        /*  27 */
        add2table(this.atom_panel, new Label("V", 1));
        /*  28 */
        add2last_col(this.atom_panel, new Label("VI", 1));
        /*  29 */
        add2table(this.atom_panel, new Label(""));
        /*  30 */
        add2table(this.atom_panel, new ElementButton("Al", 13, buttongroupvars));
        /*  31 */
        add2table(this.atom_panel, new ElementButton("Si", 14, buttongroupvars));
        /*  32 */
        add2table(this.atom_panel, new ElementButton("P", 15, buttongroupvars));
        /*  33 */
        add2last_col(this.atom_panel, new ElementButton("S", 16, buttongroupvars));
        /*  34 */
        add2table(this.atom_panel, new ElementButton("Zn", 30, buttongroupvars));
        /*  35 */
        add2table(this.atom_panel, new ElementButton("Ga", 31, buttongroupvars));
        /*  36 */
        add2table(this.atom_panel, new ElementButton("Ge", 32, buttongroupvars));
        /*  37 */
        add2table(this.atom_panel, new ElementButton("As", 33, buttongroupvars));
        /*  38 */
        add2last_col(this.atom_panel, new ElementButton("Se", 34, buttongroupvars));
        /*  39 */
        add2table(this.atom_panel, new ElementButton("Cd", 48, buttongroupvars));
        /*  40 */
        add2table(this.atom_panel, new ElementButton("In", 49, buttongroupvars));
        /*  41 */
        add2table(this.atom_panel, new ElementButton("Sn", 50, buttongroupvars));
        /*  42 */
        add2table(this.atom_panel, new ElementButton("Sb", 51, buttongroupvars));
        /*  43 */
        add2last_col(this.atom_panel, new ElementButton("Te", 52, buttongroupvars));
        /*  44 */
        Panel panel = new Panel();
        /*  45 */
        panel.setLayout(new GridBagLayout());
        /*  46 */
        add2table(panel, this.message, -1, 1, 1.0D);
        /*  47 */
        add2table(panel, this.control_button, 0, 3, 0.0D);
        /*  48 */
        add("South", panel);
        /*  49 */
        add("North", this.atom_panel);
        /*  50 */
        pack();
    }

    private void add2table(Container container, Component component, int i, int j, double d) {
        /*  55 */
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        /*  56 */
        gridbagconstraints.fill = j;
        /*  57 */
        gridbagconstraints.weightx = d;
        /*  58 */
        gridbagconstraints.gridwidth = i;
        /*  59 */
        ((GridBagLayout) container.getLayout()).setConstraints(component, gridbagconstraints);
        /*  60 */
        container.add(component);
    }


    private void add2table(Container container, Component component) {
        /*  65 */
        add2table(container, component, 1, 1, 1.0D);
    }


    private void add2last_col(Container container, Component component) {
        /*  70 */
        add2table(container, component, 0, 1, 1.0D);
    }


    public boolean action(Event event, Object obj) {
        /*  75 */
        if (event.target == this.control_button) {

            /*  77 */
            if ("Закрыть".equals(obj)) {
                /*  78 */
                hide();
            }
            /*  80 */
            else if ("Отмена".equals(obj)) {

                /*  82 */
                this.model_in_selection = false;
                /*  83 */
                this.control_button.setLabel("Закрыть");
                /*  84 */
                this.message.setText("");
                /*  85 */
                Component[] acomponent = this.atom_panel.getComponents();
                /*  86 */
                for (int i = 0; i < acomponent.length; i++) {
                    /*  87 */
                    if (acomponent[i] instanceof ElementButton) {

                        /*  89 */
                        ElementButton elementbutton = (ElementButton) acomponent[i];
                        /*  90 */
                        elementbutton.group_var.setEnvCharge(0);
                        /*  91 */
                        elementbutton.group_var.setState(false);
                        /*  92 */
                        elementbutton.setForeground(elementbutton.orig_color);
                        /*  93 */
                        elementbutton.enable();
                    }
                }
            }
            /*  97 */
            return true;
        }
        /*  99 */
        if (event.target instanceof ElementButton) {

            /* 101 */
            if (this.model_in_selection) {

                /* 103 */
                if ((ElementButton) event.target == this.curButton) {

                    /* 105 */
                    this.model_in_selection = false;
                    /* 106 */
                    this.control_button.setLabel("Закрыть");
                    /* 107 */
                    this.message.setText("");
                    /* 108 */
                    Component[] acomponent1 = this.atom_panel.getComponents();
                    /* 109 */
                    for (int j = 0; j < acomponent1.length; j++) {
                        /* 110 */
                        if (acomponent1[j] instanceof ElementButton) {

                            /* 112 */
                            ElementButton elementbutton1 = (ElementButton) acomponent1[j];
                            /* 113 */
                            elementbutton1.group_var.setEnvCharge(0);
                            /* 114 */
                            elementbutton1.group_var.setState(false);
                            /* 115 */
                            elementbutton1.setForeground(elementbutton1.orig_color);
                            /* 116 */
                            elementbutton1.enable();
                        }
                    }
                    /* 119 */
                    return true;
                }
                /* 121 */
                if (this.charge + ((ElementButton) event.target).charge() == 0) {

                    /* 123 */
                    if (((ElementButton) event.target).charge() < 0) {
                        /* 124 */
                        this.display.setModel(String.valueOf(this.modelChoice) + obj.toString());
                    } else {
                        /* 126 */
                        this.display.setModel(String.valueOf(obj.toString()) + this.modelChoice);
                        /* 127 */
                    }
                    this.model_in_selection = false;
                    /* 128 */
                    this.control_button.setLabel("Закрыть");
                    /* 129 */
                    this.message.setText("");
                    /* 130 */
                    return true;
                }
                /* 132 */
                Color color = this.message.getForeground();
                /* 133 */
                Color color1 = this.message.getBackground();
                /* 134 */
                for (int k = 0; k < 10; k++) {

                    /* 136 */
                    this.message.setForeground((k % 2 != 0) ? Color.red : color1);
                    /* 137 */
                    this.message.repaint();

                    try {
                        /* 140 */
                        Thread.currentThread();
                        /* 141 */
                        Thread.sleep(20L);

                    }
                    /* 144 */ catch (InterruptedException interruptedException) {
                        break;
                    }
                }
                /* 148 */
                this.message.setForeground(color);
                /* 149 */
                this.message.repaint();
            } else {

                /* 152 */
                this.charge = ((ElementButton) event.target).charge();
                /* 153 */
                this.modelChoice = new String(obj.toString());
                /* 154 */
                if (this.charge == 4) {

                    /* 156 */
                    this.display.setModel(this.modelChoice);
                    /* 157 */
                    return true;
                }
                /* 159 */
                this.curButton = (ElementButton) event.target;
                /* 160 */
                this.model_in_selection = true;
                /* 161 */
                if (this.charge == 2)
                    /* 162 */ this.message.setText("Выберите S, Se, or Te.");
                /* 163 */
                if (this.charge == 3)
                    /* 164 */ this.message.setText("Выберите P, As, or Sb.");
                /* 165 */
                if (this.charge == -3)
                    /* 166 */ this.message.setText("Выберите Al, Ga, or In.");
                /* 167 */
                if (this.charge == -2)
                    /* 168 */ this.message.setText("Выберите Zn or Cd");
                /* 169 */
                this.control_button.setLabel("Отмена");
            }
            /* 171 */
            return true;
        }

        /* 174 */
        return false;
    }
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\AtomDialog.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */