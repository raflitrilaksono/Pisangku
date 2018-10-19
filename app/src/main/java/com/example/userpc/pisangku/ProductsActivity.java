package com.example.userpc.pisangku;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsActivity extends AppCompatActivity{

    GridView gridView;

    String[] title = {};
    String[] photoUrl ={};
    String[] price = {};
    String[] desc = {};
    String[] turunan = {};

    public static String STORAGE_PATH ="gs://pisang-19c8d.appspot.com";
    public static String DATABASE_PATH ="products";

    CustomAdapter adapter;
    ArrayList arrayList = new ArrayList<>();

    List<String> listTitle, listPhotoUrl, listPrice, listDesc, listTurunan;
    List<Products> productsList;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    Dialog dialog;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        listTitle = new ArrayList<>(Arrays.asList(title));
        listPhotoUrl = new ArrayList<>(Arrays.asList(photoUrl));
        listPrice = new ArrayList<>(Arrays.asList(price));
        listDesc = new ArrayList<>(Arrays.asList(desc));
        listTurunan = new ArrayList<>(Arrays.asList(turunan));

        productsList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        final String userUid = firebaseUser.getUid();

        childChangeListener();
        //childValueChangeListener();

        gridView = findViewById(R.id.gridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {

                dialog = new Dialog(context);
                dialog.setContentView(R.layout.products);
                dialog.setTitle("Title");

                Window window = dialog.getWindow();
                WindowManager.LayoutParams layoutParams = window.getAttributes();

                layoutParams.x = 0;
                layoutParams.y = 0;
                layoutParams.width = 700;
                layoutParams.height = 900;

                TextView tvProductTitle = dialog.findViewById(R.id.product_title);
                ImageView ivProductPhotoUrl = dialog.findViewById(R.id.product_photoUrl);
                TextView tvProductPrice = dialog.findViewById(R.id.product_price);
                TextView tvProductDesc = dialog.findViewById(R.id.product_desc);
                TextView tvProductTurunan = dialog.findViewById(R.id.product_turunan);


                tvProductTitle.setText(title[pos]);
                Glide.with(context).load(photoUrl[pos]).into(ivProductPhotoUrl);
                tvProductPrice.setText(price[pos]);
                tvProductDesc.setText(desc[pos]);
                tvProductTurunan.setText(turunan[pos]);


                Button btDialog = dialog.findViewById(R.id.bt_dialog);
                Button btPesann = dialog.findViewById(R.id.bt_pesann);


                btPesann.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(context, Order.class));
                    }
                });

                dialog.show();

                btDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

    }

    private void storageListener(){

    }

    private void childChangeListener(){
        databaseReference.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Products products = dataSnapshot.getValue(Products.class);

//                title[title.length] = products.ge;
//                price[price.length] = products.price;
//                desc[desc.length] = products.desc;

                listTitle.add(products.title.toString());
                listPhotoUrl.add(products.photoUrl.toString());
                listPrice.add(products.price.toString());
                listDesc.add(products.desc.toString());
                listTurunan.add(products.turunan.toString());

                title = listTitle.toArray(title);
                photoUrl = listPhotoUrl.toArray(photoUrl);
                price = listPrice.toArray(price);
                desc = listDesc.toArray(desc);
                turunan = listTurunan.toArray(turunan);

                productsList.add(products);

            //    Toast.makeText(ProductsActivity.this, "Daftar Pisang", Toast.LENGTH_SHORT).show();
                adapter = new CustomAdapter(ProductsActivity.this, productsList);

                gridView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Products products = dataSnapshot.getValue(Products.class);
//
//                listTitle.remove(products.title.toString());
//                listPrice.remove(products.price.toString());
//                listDesc.remove(products.desc.toString());
//
//                listTitle.add(products.title.toString());
//                listPrice.add(products.price.toString());
//                listDesc.add(products.desc.toString());
//
//                title = listTitle.toArray(title);
//                price = listPrice.toArray(price);
//                desc = listDesc.toArray(desc);
//
//                adapter = new CustomGrid(ProductsActivity.this, title, price, desc);
//
//                gridView.setAdapter(adapter);
//
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Products products = dataSnapshot.getValue(Products.class);

                listTitle.remove(products.title.toString());
                listPhotoUrl.remove(products.photoUrl.toString());
                listPrice.remove(products.price.toString());
                listDesc.remove(products.desc.toString());
                listTurunan.remove(products.turunan.toString());

                title = listTitle.toArray(title);
                photoUrl = listPhotoUrl.toArray(photoUrl);
                price = listPrice.toArray(price);
                desc = listDesc.toArray(desc);
                turunan = listTurunan.toArray(turunan);

                productsList.remove(products);

                adapter = new CustomAdapter(ProductsActivity.this, productsList);

                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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

//        databaseReference.child("products").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()){
//                    Products products = productSnapshot.getValue(Products.class);
//
//                    listTitle.remove(products.title.toString());
//                    listPrice.remove(products.price.toString());
//                    listDesc.remove(products.desc.toString());
//
//                    listTitle.add(products.title.toString());
//                    listPrice.add(products.price.toString());
//                    listDesc.add(products.desc.toString());
//
//                    title = listTitle.toArray(title);
//                    price = listPrice.toArray(price);
//                    desc = listDesc.toArray(desc);
//
//                    adapter = new CustomGrid(ProductsActivity.this, title, price, desc);
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