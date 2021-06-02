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
public class supp {
    public String mancc ;
    public String ten ;
    public String mathang ; 
    public String sdt ; 
    public String diachi ; 
    public String ghichu ; 

    public supp(String mancc, String ten, String mathang, String sdt, String diachi, String ghichu) {
        this.mancc = mancc;
        this.ten = ten;
        this.mathang = mathang;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ghichu = ghichu;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMathang() {
        return mathang;
    }

    public void setMathang(String mathang) {
        this.mathang = mathang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
}
