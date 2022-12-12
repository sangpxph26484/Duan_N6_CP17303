package com.example.duan_n6_cp17303.DTO_N6_CP17303;

public class BinhLuanDTO {
    int idbinhluan;
    String binhluan;
    int idsanpham;
    int idkhachhang;
    String tenkhachhang;

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public int getIdbinhluan() {
        return idbinhluan;
    }

    public void setIdbinhluan(int idbinhluan) {
        this.idbinhluan = idbinhluan;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }

    public BinhLuanDTO() {
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }
}
