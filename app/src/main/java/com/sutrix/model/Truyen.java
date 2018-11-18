package com.sutrix.model;

import java.io.Serializable;
import java.util.List;

public class Truyen implements Serializable {

    private Id _id;
    private String tieuDe;
    private String tacGia;
    private List<String> theLoai;
    private String trangThai;
    private int soChuong;
    private String ngayUp;
    private String ngayCapNhat;
    private String nhomDich;
    private String moTa;
    private String hinh;

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    private List<String> noiDung;

    public Truyen() {
    }

    public Truyen(Id _id, String tieuDe, String tacGia, List<String> theLoai, String trangThai, int soChuong, String ngayUp, String ngayCapNhat, String nhomDich, String moTa, String hinh, List<String> noiDung) {
        this._id = _id;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.trangThai = trangThai;
        this.soChuong = soChuong;
        this.ngayUp = ngayUp;
        this.ngayCapNhat = ngayCapNhat;
        this.nhomDich = nhomDich;
        this.moTa = moTa;
        this.hinh = hinh;
        this.noiDung = noiDung;
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

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public List<String> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(List<String> theLoai) {
        this.theLoai = theLoai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoChuong() {
        return soChuong;
    }

    public void setSoChuong(int soChuong) {
        this.soChuong = soChuong;
    }

    public String getNgayUp() {
        return ngayUp;
    }

    public void setNgayUp(String ngayUp) {
        this.ngayUp = ngayUp;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getNhomDich() {
        return nhomDich;
    }

    public void setNhomDich(String nhomDich) {
        this.nhomDich = nhomDich;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<String> getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(List<String> noiDung) {
        this.noiDung = noiDung;
    }
}
