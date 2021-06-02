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
public class pet {
    public String idpet ;
    public float tuoi ;
    public String maloai ;
    public String magiong ;
    public int gia ; 
    public int soluong ;

    public pet(String idpet, float tuoi, String maloai, String magiong, int gia, int soluong) {
        this.idpet = idpet;
        this.tuoi = tuoi;
        this.maloai = maloai;
        this.magiong = magiong;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getIdpet() {
        return idpet;
    }

    public void setIdpet(String idpet) {
        this.idpet = idpet;
    }

    public float getTuoi() {
        return tuoi;
    }

    public void setTuoi(float tuoi) {
        this.tuoi = tuoi;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getMagiong() {
        return magiong;
    }

    public void setMagiong(String magiong) {
        this.magiong = magiong;
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
