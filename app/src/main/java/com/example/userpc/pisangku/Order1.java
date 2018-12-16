package com.example.userpc.pisangku;

import java.util.List;

public class Order1 {
    String produk1, jumlahProduk1, harga1, produk2, jumlahProduk2, harga2, nama, alamat, phone, email;

    public Order1() {
    }

    public Order1(String produk1, String jumlahProduk1, String harga1, String produk2, String jumlahProduk2, String harga2, String nama, String alamat, String phone, String email) {
        this.produk1 = produk1;
        this.jumlahProduk1 = jumlahProduk1;
        this.harga1 = harga1;
        this.produk2 = produk2;
        this.jumlahProduk2 = jumlahProduk2;
        this.harga2 = harga2;
        this.nama = nama;
        this.alamat = alamat;
        this.phone = phone;
        this.email = email;
      //  this.total = total;
    }

    public String getProduk1() { return produk1; }

    public void setProduk1(String produk1) { this.produk1 = produk1; }

    public String getJumlahProduk1() {
        return jumlahProduk1;
    }

    public void setJumlahProduk1(String jumlahProduk1) {
        this.jumlahProduk1 = jumlahProduk1;
    }

    public String getHarga1() { return harga1; }

    public void setHarga1(String harga1) {
        this.harga1 = harga1;
    }

    public String getProduk2() { return produk2; }

    public void setProduk2(String produk2) { this.produk2 = produk2; }

    public String getJumlahProduk2() {
        return jumlahProduk2;
    }

    public String getHarga2() {
        return harga2;
    }

    public void setHarga2(String harga2) {
        this.harga2 = harga2;
    }

    public void setJumlahProduk2(String jumlahProduk2) {
        this.jumlahProduk2 = jumlahProduk2;
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

  //  public String getTotal() {
    //    return total;
   // }

   // public void setTotal(String total) {
     //   this.total = total;
    //}
}