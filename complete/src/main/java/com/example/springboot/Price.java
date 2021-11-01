package com.example.springboot;

public class Price {
    private double BTCBuy;
    private double BTCSell;
    private double ETHBuy;
    private double ETHSell;
    private String platformBTCBuy;
    private String platformBTCSell;
    private String platformETHBuy;
    private String platformETHSell;
    public Price() {
        BTCBuy = 0;
        BTCSell = 0;
        ETHBuy = 0;
        ETHSell = 0;
        platformBTCBuy = "";
        platformBTCSell = "";
        platformETHBuy = "";
        platformETHSell = "";
    }

    public Price(double BTCBuy, double BTCSell, double ETHBuy, double ETHSell, String platformBTCBuy, String platformBTCSell, String platformETHBuy, String platformETHSell) {
        this.BTCBuy = BTCBuy;
        this.BTCSell = BTCSell;
        this.ETHBuy = ETHBuy;
        this.ETHSell = ETHSell;
        this.platformBTCBuy = platformBTCBuy;
        this.platformBTCSell = platformBTCSell;
        this.platformETHBuy = platformETHBuy;
        this.platformETHSell = platformETHSell;
    }

    public double getBTCBuy() {
        return BTCBuy;
    }

    public void setBTCBuy(double BTCBuy) {
        this.BTCBuy = BTCBuy;
    }

    public double getBTCSell() {
        return BTCSell;
    }

    public void setBTCSell(double BTCSell) {
        this.BTCSell = BTCSell;
    }

    public double getETHBuy() {
        return ETHBuy;
    }

    public void setETHBuy(double ETHBuy) {
        this.ETHBuy = ETHBuy;
    }

    public double getETHSell() {
        return ETHSell;
    }

    public void setETHSell(double ETHSell) {
        this.ETHSell = ETHSell;
    }

    public String getPlatformBTCBuy() {
        return platformBTCBuy;
    }

    public void setPlatformBTCBuy(String platformBTCBuy) {
        this.platformBTCBuy = platformBTCBuy;
    }

    public String getPlatformBTCSell() {
        return platformBTCSell;
    }

    public void setPlatformBTCSell(String platformBTCSell) {
        this.platformBTCSell = platformBTCSell;
    }

    public String getPlatformETHBuy() {
        return platformETHBuy;
    }

    public void setPlatformETHBuy(String platformETHBuy) {
        this.platformETHBuy = platformETHBuy;
    }

    public String getPlatformETHSell() {
        return platformETHSell;
    }

    public void setPlatformETHSell(String platformETHSell) {
        this.platformETHSell = platformETHSell;
    }


}
