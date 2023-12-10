/*    */

import java.awt.*;

/*    */
/*    */ class ElementButton
        /*    */ extends Button {
    /*    */ int group_num;
    /*    */ int atomic_num;
    /*    */ ButtonGroupVars group_var;
    /*    */ Color orig_color;

    /*    */
    /*    */
    public ElementButton(String s, int i, ButtonGroupVars buttongroupvars) {
        /* 14 */
        super(s);
        /* 15 */
        this.orig_color = getForeground();
        /* 16 */
        this.atomic_num = i;
        /* 17 */
        if (this.atomic_num > 0 && this.atomic_num < 3)
            /* 18 */ this.group_num = this.atomic_num;
        /* 19 */
        if (this.atomic_num > 2 && this.atomic_num < 26)
            /* 20 */ this.group_num = (this.atomic_num - 2) % 8;
        /* 21 */
        if (this.atomic_num > 29 && this.atomic_num < 44)
            /* 22 */ this.group_num = (this.atomic_num - 4) % 8;
        /* 23 */
        if (this.atomic_num > 46 && this.atomic_num < 58)
            /* 24 */ this.group_num = (this.atomic_num - 6) % 8;
        /* 25 */
        if (this.atomic_num > 25 && this.atomic_num < 29)
            /* 26 */ this.group_num = 8;
        /* 27 */
        if (this.atomic_num > 43 && this.atomic_num < 47)
            /* 28 */ this.group_num = 8;
        /* 29 */
        if (this.atomic_num > 58)
            /* 30 */ System.out.println("Button for elment No. " + this.atomic_num + "is not implemented yet.");
        /* 31 */
        if (this.atomic_num < 1)
            /* 32 */ System.out.println("Illegal atomic number");
        /* 33 */
        this.group_var = buttongroupvars;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    public boolean action(Event event, Object obj) {
        /* 38 */
        Component[] acomponent = getParent().getComponents();
        /* 39 */
        if (this.group_var.isInTransction() && this.group_var.getEnvCharge() + charge() == 0) {
            /*    */
            /* 41 */
            this.group_var.setEnvCharge(0);
            /* 42 */
            this.group_var.setState(false);
            /* 43 */
            for (int i = 0; i < acomponent.length; i++)
                /*    */ {
                /* 45 */
                Component component = acomponent[i];
                /* 46 */
                if (component instanceof ElementButton)
                    /*    */ {
                    /* 48 */
                    component.setForeground(((ElementButton) component).orig_color);
                    /* 49 */
                    component.enable();
                    /*    */
                }
                /*    */
                /*    */
            }
            /*    */
            /* 54 */
        } else if (!this.group_var.isInTransction() && charge() != 4) {
            /*    */
            /* 56 */
            setForeground(Color.red);
            /* 57 */
            this.group_var.setEnvCharge(charge());
            /* 58 */
            this.group_var.setState(true);
            /* 59 */
            for (int j = 0; j < acomponent.length; j++) {
                /* 60 */
                if (acomponent[j] instanceof ElementButton) {
                    /*    */
                    /* 62 */
                    ElementButton elementbutton = (ElementButton) acomponent[j];
                    /* 63 */
                    if (elementbutton != this)
                        /* 64 */ if (elementbutton.charge() + this.group_var.getEnvCharge() == 0) {
                        /*    */
                        /* 66 */
                        elementbutton.setForeground(Color.green);
                        /*    */
                    } else {
                        /*    */
                        /* 69 */
                        elementbutton.setForeground(Color.gray);
                        /* 70 */
                        elementbutton.disable();
                        /*    */
                    }
                    /*    */
                }
                /*    */
            }
            /*    */
        }
        /* 75 */
        getParent().repaint();
        /* 76 */
        return super.action(event, obj);
        /*    */
    }

    /*    */
    /*    */
    /*    */
    public int charge() {
        /* 81 */
        if (this.group_num <= 4) {
            /* 82 */
            return this.group_num;
            /*    */
        }
        /* 84 */
        return this.group_num - 8;
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\ElementButton.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */