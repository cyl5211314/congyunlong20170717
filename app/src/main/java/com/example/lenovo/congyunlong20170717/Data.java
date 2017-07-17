package com.example.lenovo.congyunlong20170717;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/7/17 9:32
 */

public class Data {
    private String name;
    private int dnum;
    private boolean falg;
    private double jcar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDnum() {
        return dnum;
    }

    public void setDnum(int dnum) {
        this.dnum = dnum;
    }

    public boolean isFalg() {
        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }

    public double getJcar() {
        return jcar;
    }

    public void setJcar(double jcar) {
        this.jcar = jcar;
    }

    public Data(String name, int dnum, boolean falg, double jcar) {
        this.name = name;
        this.dnum = dnum;
        this.falg = falg;
        this.jcar = jcar;
    }
}
