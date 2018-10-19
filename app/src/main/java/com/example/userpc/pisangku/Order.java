package com.example.userpc.pisangku;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order extends AppCompatActivity {

    EditText etJumlahProduk, etNama, etAlamat, etPhone;
    TextView tvEmail;
    Spinner actvProduk;
    String nama, alamat, produk, jumlahProduk, phone, email, userUid;
    Button btOrder;

    Context context = this;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> titleadapter;

    String[] title = {};
    String[] price = {};
    String[] desc = {};
    String[] turunan = {};

    List<String> listTitle, listPrice, listDesc, listTurunan;
    List<Products> productsList;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userUid = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        listTitle = new ArrayList<>(Arrays.asList(title));
        listPrice = new ArrayList<>(Arrays.asList(price));
        listDesc = new ArrayList<>(Arrays.asList(desc));
        listTurunan = new ArrayList<>(Arrays.asList(turunan));

        productsList = new ArrayList<>();

        actvProduk = findViewById(R.id.actv_produk);
        etJumlahProduk = findViewById(R.id.et_jumlah_produk);
        etNama = findViewById(R.id.et_nama_lengkap);
        etAlamat = findViewById(R.id.et_alamat);
        etPhone = findViewById(R.id.et_phone);
        tvEmail = findViewById(R.id.et_email_order);

        tvEmail.setText(firebaseUser.getEmail());

        btOrder = findViewById(R.id.bt_order);
        btOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveUserInformation();
                startActivity(new Intent(context, tas.class));

            }
        });

        childChangeListener();

        titleadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,title);
        actvProduk.setAdapter(titleadapter);

        }

    private void childChangeListener(){
        databaseReference.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Products products = dataSnapshot.getValue(Products.class);

                listTitle.add(products.title.toString());
                listPrice.add(products.price.toString());
                listDesc.add(products.desc.toString());
                listTurunan.add(products.turunan.toString());

                title = listTitle.toArray(title);
                price = listPrice.toArray(price);
                desc = listDesc.toArray(desc);
                turunan = listTurunan.toArray(turunan);

               // Toast.makeText(Order.this, "Selamat Berbelanja", Toast.LENGTH_SHORT).show();

                productsList.add(products);

                adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, title);

                actvProduk.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Products products = dataSnapshot.getValue(Products.class);

                listTitle.remove(products.title);
                listPrice.remove(products.price);
                listDesc.remove(products.desc);
                listTurunan.remove(products.turunan);
//
                title = listTitle.toArray(title);
                price = listPrice.toArray(price);
                desc = listDesc.toArray(desc);
                turunan = listTurunan.toArray(turunan);

                productsList.remove(products);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveUserInformation(){
        produk = actvProduk.getSelectedItem().toString();
        jumlahProduk = etJumlahProduk.getText().toString();
        nama = etNama.getText().toString();
        alamat = etAlamat.getText().toString();
        phone = etPhone.getText().toString();
        email = tvEmail.getText().toString();


        if(jumlahProduk.isEmpty()){
            etJumlahProduk.setError("Mohon isi jumlah produk yang ingin anda beli");
            etJumlahProduk.requestFocus();
            return;
        }

        if(nama.isEmpty()){
            etNama.setError("Mohon isi nama anda");
            etNama.requestFocus();
            return;
        }
        if(alamat.isEmpty()){
            etAlamat.setError("Mohon isi alamat anda");
            etAlamat.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            etPhone.setError("Mohon isi No.Hp anda");
            etPhone.requestFocus();
            return;
        }
        if(email.isEmpty()){
            tvEmail.setError("Mohon isi email anda");
            tvEmail.requestFocus();
            return;
        }

        databaseReference.child("Orders").child(userUid).push().setValue(new Order1(produk, jumlahProduk, nama, alamat, phone, email));

    }

}
