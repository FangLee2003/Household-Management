package model;

public class absence_tempresidence {
    private int tid;
    private String tname;
    private long tiden;
    private String tdate;
    private String ttemp;
    private String tabsence;
    private String treason;

    public absence_tempresidence() {

    }

    public absence_tempresidence(int tid, String tname, long tiden, String tdate, String ttemp, String tabsence, String treason) {
        this.tid = tid;
        this.tname = tname;
        this.tiden = tiden;
        this.tdate = tdate;
        this.ttemp = ttemp;
        this.tabsence = tabsence;
        this.treason = treason;
    }

    public int getTid() {
        return tid;
    }

    public String getTname() {
        return tname;
    }

    public long getTiden() {
        return tiden;
    }

    public String getTdate() {
        return tdate;
    }

    public String getTtemp() {
        return ttemp;
    }

    public String getTabsence() {
        return tabsence;
    }

    public String getTreason() {
        return treason;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setTiden(long tiden) {
        this.tiden = tiden;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public void setTtemp(String ttemp) {
        this.ttemp = ttemp;
    }

    public void setTabsence(String tabsence) {
        this.tabsence = tabsence;
    }

    public void setTreason(String treason) {
        this.treason = treason;
    }

    @Override
    public String toString() {
        return "temp_residence_absence{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tiden=" + tiden +
                ", tdate='" + tdate + '\'' +
                ", ttemp='" + ttemp + '\'' +
                ", tabsence='" + tabsence + '\'' +
                ", treason='" + treason + '\'' +
                '}';
    }
}
