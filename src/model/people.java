package model;

public class people {
    private int pid;
    private String pname;
    private long piden;
    private long hiden;
    private String prela;
    private String pgen;
    private String pbirth;
    private String phometown;
    private String pjob;
    private String pedu;
    private String preli;

    public people() {

    }

    public people(int pid, String pname, long piden, long hiden, String prela, String pgen, String pbirth, String phometown, String pjob, String pedu, String preli) {
        this.pid = pid;
        this.pname = pname;
        this.piden = piden;
        this.hiden = hiden;
        this.prela = prela;
        this.pgen = pgen;
        this.pbirth = pbirth;
        this.phometown = phometown;
        this.pjob = pjob;
        this.pedu = pedu;
        this.preli = preli;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public long getPiden() {
        return piden;
    }

    public long getHiden() {
        return hiden;
    }

    public String getPrela() {
        return prela;
    }

    public String getPgen() {
        return pgen;
    }

    public String getPbirth() {
        return pbirth;
    }

    public String getPhometown() {
        return phometown;
    }

    public String getPjob() {
        return pjob;
    }

    public String getPedu() {
        return pedu;
    }

    public String getPreli() {
        return preli;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPiden(long piden) {
        this.piden = piden;
    }

    public void setHiden(long hiden) {
        this.hiden = hiden;
    }

    public void setPrela(String prela) {
        this.prela = prela;
    }

    public void setPgen(String pgen) {
        this.pgen = pgen;
    }

    public void setPbirth(String pbirth) {
        this.pbirth = pbirth;
    }

    public void setPhometown(String phometown) {
        this.phometown = phometown;
    }

    public void setPjob(String pjob) {
        this.pjob = pjob;
    }

    public void setPedu(String pedu) {
        this.pedu = pedu;
    }

    public void setPreli(String preli) {
        this.preli = preli;
    }

    @Override
    public String toString() {
        return "people{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", piden=" + piden +
                ", hiden=" + hiden +
                ", prela='" + prela + '\'' +
                ", pgen='" + pgen + '\'' +
                ", pbirth='" + pbirth + '\'' +
                ", phometown='" + phometown + '\'' +
                ", pjob='" + pjob + '\'' +
                ", pedu='" + pedu + '\'' +
                ", preli='" + preli + '\'' +
                '}';
    }
}
