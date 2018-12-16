package com.example.userpc.pisangku;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

    EditText etNama, etAlamat, etPhone;
    TextView tvEmail, tvHarga1, tvHarga2;
    Spinner actvProduk1, etJumlahProduk1, actvProduk2, etJumlahProduk2;
    String nama, alamat, produk1, jumlahProduk1, harga1, phone, email, userUid, produk2, jumlahProduk2, harga2;
    Button btOrder;

    Context context = this;

   // int index=-1;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> titleadapter;
    ArrayAdapter<String> numberadapter;

    String[] number = {"Pilih Jumlah", "1","2", "3", "4", "5", "6", "7", "8", "9"};

    String[] title = {"Pilih Barang"};
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

        actvProduk1 = findViewById(R.id.actv_produk1);
        etJumlahProduk1 = findViewById(R.id.et_jumlah_produk1);
        tvHarga1 = findViewById(R.id.tvHarga1);
        actvProduk2 = findViewById(R.id.actv_produk2);
        etJumlahProduk2 = findViewById(R.id.et_jumlah_produk2);
        tvHarga1 = findViewById(R.id.tvHarga1);
       // if (index == -1){
       // tvHarga1.setText("");
       // } else { tvHarga1.setText(price[index]);}
        tvHarga2 = findViewById(R.id.tvHarga2);
      //  if(index == -1){
        //    tvHarga2.setText("");
       // } else { tvHarga2.setText(price[index]);}
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

    //   actvProduk1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(index = Arrays.asList(title).indexOf(actvProduk1.getSelectedItem().toString()))
      // {
      //     @Override
        //   public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position)
          //  if(index == -1)

           // {
             //  tvHarga1.setText("");
           // } else { tvHarga1.setText(price[index]);}
      // }
        //    @Override
        //public void onNothingSelected(AdapterView<?> parentView)
       // {

       // }
       // });

        childChangeListener();

        titleadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,title);
        actvProduk1.setAdapter(titleadapter);
        actvProduk2.setAdapter(titleadapter);

        numberadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,number);
        etJumlahProduk1.setAdapter(numberadapter);
        etJumlahProduk2.setAdapter(numberadapter);


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

            //    index = Arrays.asList(title).indexOf(actvProduk1.getSelectedItem().toString());

                // Toast.makeText(Order.this, "Selamat Berbelanja", Toast.LENGTH_SHORT).show();

                productsList.add(products);

                adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, title);

                actvProduk1.setAdapter(adapter);
                actvProduk2.setAdapter(adapter);

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
        produk1 = actvProduk1.getSelectedItem().toString();
        jumlahProduk1 = etJumlahProduk1.getSelectedItem().toString();
        harga1 = tvHarga1.getText().toString();
        produk2 = actvProduk2.getSelectedItem().toString();
        jumlahProduk2 = etJumlahProduk2.getSelectedItem().toString();
        harga2 = tvHarga2.getText().toString();
        nama = etNama.getText().toString();
        alamat = etAlamat.getText().toString();
        phone = etPhone.getText().toString();
        email = tvEmail.getText().toString();


        //     if(jumlahProduk.isEmpty()){
        //       etJumlahProduk.setError("Mohon isi jumlah produk yang ingin anda beli");
        //     etJumlahProduk.requestFocus();
        //   return;
        //  }

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
        if(produk1 == "Pilih Barang"){
            produk1 = "-";
        }
        if(jumlahProduk1 == "Pilih Jumlah"){
            jumlahProduk1 = "-";
        }
        if(produk2 == "Pilih Barang"){
            produk2 = "-";
        }
        if(jumlahProduk2 == "Pilih Jumlah"){
            jumlahProduk2 = "-";
        }
        if(produk1 == "Pilih Barang"){
            harga1 = "0";
        }
        if(produk2 == "Pilih Barang"){
            harga2 = "0";
        }

        databaseReference.child("Orders").child(userUid).push().setValue(new Order1(produk1, jumlahProduk1, harga1, produk2, jumlahProduk2, harga2,nama, alamat, phone, email));

    }

}
