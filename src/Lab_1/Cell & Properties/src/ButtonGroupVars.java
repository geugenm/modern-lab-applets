class ButtonGroupVars {
    int env_charge;
    boolean in_transction;


    ButtonGroupVars(int i, boolean flag) {
        /* 12 */
        this.env_charge = i;
        /* 13 */
        this.in_transction = flag;

    }


    public int getEnvCharge() {
        /* 23 */
        return this.env_charge;

    }


    void setEnvCharge(int i) {
        /* 18 */
        this.env_charge = i;

    }


    void setState(boolean flag) {
        /* 28 */
        this.in_transction = flag;

    }


    public boolean isInTransction() {
        /* 33 */
        return this.in_transction;

    }

}


/* Location:              C:\Users\Evgeniy\IdeaProjects\modern-lab-comp-applets\src\Lab_1\Cell & Properties\!\ButtonGroupVars.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */