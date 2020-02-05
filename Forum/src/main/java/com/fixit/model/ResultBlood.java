package com.fixit.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_blood")
public class ResultBlood extends Result{

    private double wbc;
    private double rbc;
    private double hb;
    private double hct;
    private double mcv;
    private double plt;
    private double sue;
    private double ast;
    private double alt;

    public ResultBlood() {
    }

    public double getWbc() {
        return wbc;
    }

    public void setWbc(double wbc) {
        this.wbc = wbc;
    }

    public double getRbc() {
        return rbc;
    }

    public void setRbc(double rbc) {
        this.rbc = rbc;
    }

    public double getHb() {
        return hb;
    }

    public void setHb(double hb) {
        this.hb = hb;
    }

    public double getHct() {
        return hct;
    }

    public void setHct(double hct) {
        this.hct = hct;
    }

    public double getMcv() {
        return mcv;
    }

    public void setMcv(double mcv) {
        this.mcv = mcv;
    }

    public double getPlt() {
        return plt;
    }

    public void setPlt(double plt) {
        this.plt = plt;
    }

    public double getSue() {
        return sue;
    }

    public void setSue(double sue) {
        this.sue = sue;
    }

    public double getAst() {
        return ast;
    }

    public void setAst(double ast) {
        this.ast = ast;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }
}
