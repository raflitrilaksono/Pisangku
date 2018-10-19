package com.example.userpc.pisangku;

import java.util.List;

public class Order1 {
    String produk, jumlahProduk, nama, alamat, phone, email;

    public Order1() {
    }

    public Order1(String produk, String jumlahProduk, String nama, String alamat, String phone, String email) {
        this.produk = produk;
        this.jumlahProduk = jumlahProduk;
        this.nama = nama;
        this.alamat = alamat;
        this.phone = phone;
        this.email = email;
    }

    public String getProduk() { return produk; }

    public void setProduk(String produk) { this.produk = produk; }

    public String getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(String jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

}