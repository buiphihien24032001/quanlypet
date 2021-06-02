/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class food {
    public String mata ;
    public String tenta ;
    public String petan ;
    public int gia;
    public int soluong;

    public food(String mata, String tenta, String petan, int gia, int soluong) {
        this.mata = mata;
        this.tenta = tenta;
        this.petan = petan;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getMata() {
        return mata;
    }

    public void setMata(String mata) {
        this.mata = mata;
    }

    public String getTenta() {
        return tenta;
    }

    public void setTenta(String tenta) {
        this.tenta = tenta;
    }

    public String getPetan() {
        return petan;
    }

    public void setPetan(String petan) {
        this.petan = petan;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
