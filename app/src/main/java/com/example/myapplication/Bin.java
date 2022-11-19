package com.example.myapplication;

public class Bin {

    public String binId;
    public int nastyReports;
    public int fullReports;
    public int damagedReports;
    public int stolenReports;
    //public int totalReports;

    public Bin() {
    }

    public Bin(String binId, int nastyReports, int fullReports, int damagedReports, int stolenReports) {
        this.binId = binId;
        this.nastyReports = nastyReports;
        this.fullReports = fullReports;
        this.damagedReports = damagedReports;
        this.stolenReports = stolenReports;
        //this.totalReports = totalReports ;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public int getNastyReports() {
        return nastyReports;
    }

    public void setNastyReports(int nastyReports) {
        this.nastyReports = nastyReports;
    }

    public int getFullReports() {
        return fullReports;
    }

    public void setFullReports(int fullReports) {
        this.fullReports = fullReports;
    }

    public int getDamagedReports() {
        return damagedReports;
    }

    public void setDamagedReports(int damagedReports) {
        this.damagedReports = damagedReports;
    }

    public int getStolenReports() {
        return stolenReports;
    }

    public void setStolenReports(int stolenReports) {
        this.stolenReports = stolenReports;
    }

    public int getTotal(){
        return fullReports+stolenReports+damagedReports+nastyReports;
    }

    //public int getTotalReports() { return  totalReports; }

   // public void setTotalReports(int totalReports) {this.totalReports = totalReports ; }
}
