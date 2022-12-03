package com.example.duan_n6_cp17303.DTO_N6_CP17303;

public class KhachHangDTO {
    int idkhachhang;
    String tenkhachhang;
    String phone;
    String email;
    String diachi;
    String username;

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public KhachHangDTO() {
    }
}
