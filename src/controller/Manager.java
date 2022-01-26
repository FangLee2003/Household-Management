package controller;

public class Manager {
    private String madd;
    private String mpass;

    public Manager() {

    }

    public Manager(String madd, String mpass) {
        this.madd = madd;
        this.mpass = mpass;
    }

    public String getMadd() {
        return madd;
    }

    public String getMpass() {
        return mpass;
    }

    public void setMadd(String madd) {
        this.madd = madd;
    }

    public void setMpass(String mpass) {
        this.mpass = mpass;
    }
}
