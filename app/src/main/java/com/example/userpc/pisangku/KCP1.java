package com.example.userpc.pisangku;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class KCP1 {

    String photokcp, namakcp, singkatkcp, visikcp, misikcp, mottokcp;

    public String getPhotokcp() { return photokcp; }

    public void setPhotokcp(String photokcp) { this.photokcp = photokcp; }

    public String getNamakcp() { return namakcp; }

    public void setNamakcp(String namakcp) { this.namakcp = namakcp; }

    public String getSingkatkcp() { return singkatkcp; }

    public void setSingkatkcp(String singkatkcp) { this.singkatkcp = singkatkcp; }

    public String getVisikcp() { return visikcp; }

    public void setVisikcp(String visikcp) { this.visikcp = visikcp; }

    public String getMisikcp() { return misikcp; }

    public void setMisikcp(String misikcp) { this.misikcp = misikcp; }

    public String getMottokcp() { return mottokcp; }

    public void setMottokcp(String mottokcp) { this.mottokcp = mottokcp; }

    public KCP1(String photokcp, String namakcp, String singkatkcp, String visikcp, String misikcp, String mottokcp) {

        this.photokcp = photokcp;
        this.namakcp = namakcp;
        this.singkatkcp = singkatkcp;
        this.visikcp = visikcp;
        this.misikcp = misikcp;
        this.mottokcp = mottokcp;
    }

    public KCP1() {

    }


}
