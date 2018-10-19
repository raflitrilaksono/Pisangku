package com.example.userpc.pisangku;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

 public class tas extends AppCompatActivity {

     ListViewAdapter listViewAdapter;
     ListView listView;
     private ArrayList<Review> reviewList;

     String[] produk = {};
     String[] jumlahProduk = {};
     String[] nama = {};
     String[] alamat = {};
     String[] phone = {};
     String[] email = {};

     ArrayList arrayList = new ArrayList<>();

     List<String> listProduk, listJumlahProduk, listNama, listAlamat, listPhone, listEmail;

     private DatabaseReference databaseReference;
     private FirebaseAuth firebaseAuth;
     private FirebaseUser firebaseUser;

     String UserUid;
     String KeyUid;

     Context context = this;


     @Override
     protected void onCreate(Bundle saveInstanceState) {
         super.onCreate(saveInstanceState);
         setContentView(R.layout.activity_tas);

         firebaseAuth = FirebaseAuth.getInstance();
         firebaseUser = firebaseAuth.getCurrentUser();
         UserUid = firebaseUser.getUid();

         reviewList = new ArrayList<>();
         listView = findViewById(R.id.listview0);

         listProduk = new ArrayList<>(Arrays.asList(produk));
         listJumlahProduk = new ArrayList<>(Arrays.asList(jumlahProduk));
         listNama = new ArrayList<>(Arrays.asList(nama));
         listAlamat = new ArrayList<>(Arrays.asList(alamat));
         listPhone = new ArrayList<>(Arrays.asList(phone));
         listEmail = new ArrayList<>(Arrays.asList(email));

         databaseReference = FirebaseDatabase.getInstance().getReference();

         childChangeListener();

         listViewAdapter = new ListViewAdapter(this, R.layout.custom_view, reviewList);
         listView.setAdapter(listViewAdapter);

         listView = findViewById(R.id.listview0);
         listViewAdapter.notifyDataSetChanged();

     }

     private void childChangeListener() {
         databaseReference.child("Orders").child(UserUid).addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 Order1 order1 = dataSnapshot.getValue(Order1.class);

                 reviewList.add(new Review(order1.produk, order1.jumlahProduk, order1.nama, order1.alamat, order1.phone, order1.email));
                 listViewAdapter.notifyDataSetChanged();

//                title[title.length] = products.ge;
//                price[price.length] = products.price;
//                desc[desc.length] = products.desc;

                 listProduk.add(order1.produk.toString());
                 listJumlahProduk.add(order1.jumlahProduk.toString());
                 listNama.add(order1.nama.toString());
                 listAlamat.add(order1.alamat.toString());
                 listPhone.add(order1.phone.toString());
                 listEmail.add(order1.email.toString());

                 produk = listProduk.toArray(produk);
                 jumlahProduk = listJumlahProduk.toArray(jumlahProduk);
                 nama = listNama.toArray(nama);
                 alamat = listAlamat.toArray(alamat);
                 phone = listPhone.toArray(phone);
                 email = listEmail.toArray(email);

                 Toast.makeText(tas.this, "Pemesanan Berhasil", Toast.LENGTH_LONG).show();

         }


             @Override
             public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                 Order1 order1 = dataSnapshot.getValue(Order1.class);
//
//                listProduk.remove(order1.produk.toString());
////              listJumlahProduk.remove(order1.jumlahProduk.toString());
//                listNama.remove(order1.nama.toString());
//                listAlamat.remove(order1.alamat.toString());
//                listPhone.remove(order1.phone.toString())
//                listEmail.remove(order1.email.toString());
//
//                listProduk.add(order1.produk.toString());
////              listJumlahProduk.add(order1.jumlahProduk.toString());
//                listNama.add(order1.nama.toString());
//                listAlamat.add(order1.alamat.toString());
//                listPhone.add(order1.phone.toString());
//                listEmail.add(order1.email.toString());
//
//
//                produk = listProduk.toArray(produk);
////              jumlahProduk = listJumlahProduk.toArray(jumlahProduk);
//                nama = listNama.toArray(nama);
//                alamat = listAlamat.toArray(alamat);
//                phone = listPhone.toArray(phone);
//                email = listEmail.toArray(email);
//
//
             }

             @Override
             public void onChildRemoved(DataSnapshot dataSnapshot) {
                 Order1 order1 = dataSnapshot.getValue(Order1.class);

                 reviewList.remove(new Review(order1.produk, order1.jumlahProduk, order1.nama, order1.alamat, order1.phone, order1.email));
                 listViewAdapter.notifyDataSetChanged();

                 listProduk.remove(order1.produk.toString());
                 listJumlahProduk.remove(order1.jumlahProduk.toString());
                 listNama.remove(order1.nama.toString());
                 listAlamat.remove(order1.alamat.toString());
                 listPhone.remove(order1.phone.toString());
                 listEmail.remove(order1.email.toString());

                 produk = listProduk.toArray(produk);
                 jumlahProduk = listJumlahProduk.toArray(jumlahProduk);
                 nama = listNama.toArray(nama);
                 alamat = listAlamat.toArray(alamat);
                 phone = listPhone.toArray(phone);
                 email = listEmail.toArray(email);

         }
             @Override
             public void onChildMoved(DataSnapshot dataSnapshot, String s) {

             }
             @Override
             public void onCancelled(DatabaseError databaseError) {

             }

     });

     }

     private void childValueChangeListener(){

//        databaseReference.child("Orders").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot order1Snapshot : dataSnapshot.getChildren()){
//                    Order1 order1 = order1Snapshot.getValue(Order1.class);
//
//                    listProduk.remove(order1.Produk.toString());
////                  listJumlahProduk.remove(order1.jumlahProduk.toString());
//                    listNama.remove(order1.nama.toString());
//                    listAlamat.remove(order1.alamat.toString());
//         l          listPhone.remove(order1.phone.toString());
//                    listEmail.remove(order1.email.toString());
//
//                    listProduk.add(order1.Produk.toString());
//////                listJumlahProduk.add(order1.jumlahProduk.toString());
//                    listNama.add(order1.nama.toString());
////                  listAlamat.add(order1.alamat.toString());
////         l        listPhone.add(order1.phone.toString());
////                  listEmail.add(order1.email.toString());
//
//
//                    produk = listProduk.toArray(produk);
////                  jumlahProduk = listJumlahProduk.toArray(jumlahProduk);
//                    nama = listNama.toArray(nama);
//                    alamat = listAlamat.toArray(alamat);
//                    phone = listPhone.toArray(phone);
//                    email = listEmail.toArray(email);
//
//                    adapter = new CustomGrid(tas.this, nama, alamat, produk, jumlahProduk, phone, email);
//
//                    gridView.setAdapter(adapter);
//
//                    adapter.notifyDataSetChanged();
//
////                    Toast.makeText(ProductsActivity.this, title[0], Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
     }

     @Override
     protected void onStart() {
         super.onStart();



//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()){
//                    Products products = productSnapshot.getValue(Products.class);
//
//                    if(!listTitle.contains(products.title) == false ||
//                            !listPrice.contains(products.price) == false ||
//                            !listDesc.contains(products.desc) == false){
//
//                        listTitle.add(listTitle.size(), products.title);
//                        listPrice.add(listPrice.size(), products.price);
//                        listDesc.add(listDesc.size(), products.desc);
//
//                        adapter.notifyDataSetChanged();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
     }
 }
