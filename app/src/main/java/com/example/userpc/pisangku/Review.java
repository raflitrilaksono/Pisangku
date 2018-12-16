package com.example.userpc.pisangku;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class Review {
        String itemProduk1, itemjumlahProduk1, itemHarga1, itemProduk2, itemjumlahProduk2, itemHarga2, itemNama, itemAlamat, itemPhone, itemEmail;

        public Review(String itemProduk1, String itemjumlahProduk1, String itemHarga1, String itemProduk2, String itemjumlahProduk2, String itemHarga2, String itemNama, String itemAlamat, String itemPhone, String itemEmail){

            this.itemProduk1 = itemProduk1;
            this.itemjumlahProduk1 = itemjumlahProduk1;
            this.itemHarga1 = itemHarga1;
            this.itemProduk2 = itemProduk2;
            this.itemjumlahProduk2 = itemjumlahProduk2;
            this.itemHarga2 = itemHarga2;
            this.itemNama = itemNama;
            this.itemAlamat = itemAlamat;
            this.itemPhone = itemPhone;
            this.itemEmail = itemEmail;
           // this.itemTotal = itemTotal;
        }

        public String getItemProduk1() {
            return itemProduk1;
        }

        public void setItemProduk1(String itemProduk1) {
            this.itemProduk1 = itemProduk1;
        }

        public String getItemjumlahProduk1() {
            return itemjumlahProduk1;
        }

        public void setItemjumlahProduk1(String itemjumlahProduk1) {
            this.itemjumlahProduk1 = itemjumlahProduk1;
        }

    public String getItemHarga1() {
        return itemHarga1;
    }

    public void setItemHarga1(String itemHarga1) {
        this.itemHarga1 = itemHarga1;
    }

    public String getItemProduk2() {
        return itemProduk2;
    }

    public void setItemProduk2(String itemProduk2) {
        this.itemProduk2 = itemProduk2;
    }

    public String getItemjumlahProduk2() {
        return itemjumlahProduk2;
    }

    public void setItemjumlahProduk2(String itemjumlahProduk2) {
        this.itemjumlahProduk2 = itemjumlahProduk2;
    }

    public String getItemHarga2() {
        return itemHarga2;
    }

    public void setItemHarga2(String itemHarga2) {
        this.itemHarga2 = itemHarga2;
    }

    public String getItemNama() {
            return itemNama;
        }

        public void setItemNama(String itemNama) {
            this.itemNama = itemNama;
        }

        public String getItemAlamat() {
            return itemAlamat;
        }

        public void setItemAlamat(String itemAlamat) {
            this.itemAlamat = itemAlamat;
        }

        public String getItemPhone() {
            return itemPhone;
        }
        public void setItemPhone(String itemPhone) {
            this.itemPhone = itemPhone;
        }

        public String getItemEmail() {
            return itemEmail;
        }

        public void setItemEmail(String itemEmail) {
            this.itemEmail = itemEmail;
        }

 //   public String getItemTotal() {
   //     return itemTotal;
    //}

    //public void setItemTotal(String itemTotal) {
      //  this.itemTotal = itemTotal;
    //}
}