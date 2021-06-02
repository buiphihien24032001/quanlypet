/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class bill {
    public String mahd ;
    public Date ngaymua ;
    public String tennv ;
    public String tenkh ;
    public String tenpet ; 
    public int slpet ;   
    public String tenta ;
    public int slta ;
    public String tendd ;
    public int sldd ;
    public int tongtien;

    public bill(String mahd, Date ngaymua, String tennv, String tenkh, String tenpet, int slpet, String tenta, int slta, String tendd, int sldd, int tongtien) {
        this.mahd = mahd;
        this.ngaymua = ngaymua;
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.tenpet = tenpet;
        this.slpet = slpet;
        this.tenta = tenta;
        this.slta = slta;
        this.tendd = tendd;
        this.sldd = sldd;
        this.tongtien = tongtien;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public Date getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(Date ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTenpet() {
        return tenpet;
    }

    public void setTenpet(String tenpet) {
        this.tenpet = tenpet;
    }

    public int getSlpet() {
        return slpet;
    }

    public void setSlpet(int slpet) {
        this.slpet = slpet;
    }

    public String getTenta() {
        return tenta;
    }

    public void setTenta(String tenta) {
        this.tenta = tenta;
    }

    public int getSlta() {
        return slta;
    }

    public void setSlta(int slta) {
        this.slta = slta;
    }

    public String getTendd() {
        return tendd;
    }

    public void setTendd(String tendd) {
        this.tendd = tendd;
    }

    public int getSldd() {
        return sldd;
    }

    public void setSldd(int sldd) {
        this.sldd = sldd;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    
    
}
