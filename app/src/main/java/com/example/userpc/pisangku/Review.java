package com.example.userpc.pisangku;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class Review {
        String itemProduk, itemjumlahProduk, itemNama, itemAlamat, itemPhone, itemEmail;

        public Review(String itemProduk, String itemjumlahProduk, String itemNama, String itemAlamat, String itemPhone, String itemEmail){

            this.itemProduk = itemProduk;
            this.itemjumlahProduk = itemjumlahProduk;
            this.itemNama = itemNama;
            this.itemAlamat = itemAlamat;
            this.itemPhone = itemPhone;
            this.itemEmail = itemEmail;
        }

        public String getItemProduk() {
            return itemProduk;
        }

        public void setItemProduk(String itemProduk) {
            this.itemProduk = itemProduk;
        }

        public String getItemjumlahProduk() {
            return itemjumlahProduk;
        }

        public void setItemjumlahProduk(String itemjumlahProduk) {
            this.itemjumlahProduk = itemjumlahProduk;
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
    }