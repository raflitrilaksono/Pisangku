package com.example.userpc.pisangku;

public class Products {

    String title, photoUrl, price,desc,turunan;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() { return photoUrl; }

    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public String getTurunan() { return turunan; }

    public void setTurunan(String turunan) { this.turunan = turunan; }


    public Products(String title, String photoUrl, String price, String desc, String turunan) {

        this.title = title;
        this.photoUrl = photoUrl;
        this.price = price;
        this.desc = desc;
        this.turunan = turunan;
    }

    public Products() {

    }


}
