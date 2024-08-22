package org.govind;

import jakarta.persistence.*;
import jdk.vm.ci.meta.Value;

@Entity
@Table(name = "alien")
public class Alien { // POJO

    @Id
    private int aid;

    //    @Transient // if transient, the colum will not be stored.
    private String aname;
    private String color;


    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
