package com.example.userpc.pisangku;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity implements View.OnClickListener {

    Button btKcp, btProducts, btCheckout, btPesan, btLogout;
    TextView tvProfileName;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(!firebaseUser.isEmailVerified()){
            finish();
            startActivity(new Intent(this, status.class));
        }

        tvProfileName = findViewById(R.id.tv_profile_name);
        btKcp = findViewById(R.id.bt_kcp);
        btProducts = findViewById(R.id.bt_products);
        btCheckout = findViewById(R.id.bt_checkout);
   //     btPesan = findViewById(R.id.bt_pesan);
        btLogout = findViewById(R.id.bt_logout_home);

        btKcp.setOnClickListener(this);
        btProducts.setOnClickListener(this);
        btCheckout.setOnClickListener(this);
     //   btPesan.setOnClickListener(this);
        btLogout.setOnClickListener(this);

        tvProfileName.setText(firebaseUser.getEmail());

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_kcp){
            startActivity(new Intent(this, KCP1.class));

        }
        if(view.getId() == R.id.bt_products){
            startActivity(new Intent(this, ProductsActivity.class));

        }
       if(view.getId() == R.id.bt_checkout){
          startActivity(new Intent(this, tas.class));

        }
   //     if(view.getId() == R.id.bt_pesan){
      //      startActivity(new Intent(this, Order.class));
     //   }
        if(view.getId() == R.id.bt_logout_home){
            finish();
            firebaseAuth.signOut();
            startActivity(new Intent(this, login.class));
        }
    }
}
