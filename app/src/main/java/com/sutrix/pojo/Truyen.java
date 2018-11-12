package com.sutrix.pojo;

public class Truyen {

    private Id _id;
    private String tieuDe;
    private String noiDung;
    private String moTa;

    public Truyen(Id _id, String tieuDe, String noiDung, String moTa) {
        this._id = _id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.moTa = moTa;
    }

    public Truyen(String tieuDe, String noiDung, String moTa) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.moTa = moTa;
    }

    public Truyen() {
    }

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
