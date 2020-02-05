package com.fixit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_irm")
public class ResultIrm extends Result{

    @Column
    private String img;

    public ResultIrm() {}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
