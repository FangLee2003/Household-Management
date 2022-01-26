package model;

public class household {
    private int hid;
    private String hname;
    private long hiden;
    private String hadd;

    public household() {

    }

    public household(int hid, String hname, long hiden, String hadd) {
        this.hid = hid;
        this.hname = hname;
        this.hiden = hiden;
        this.hadd = hadd;
    }

    public int getHid() {
        return hid;
    }

    public String getHname() {
        return hname;
    }

    public long getHiden() {
        return hiden;
    }

    public String getHadd() {
        return hadd;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public void setHiden(long hiden) {
        this.hiden = hiden;
    }

    public void setHadd(String hadd) {
        this.hadd = hadd;
    }

    @Override
    public String toString() {
        return "household{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                ", hiden=" + hiden +
                ", hadd='" + hadd + '\'' +
                '}';
    }
}
