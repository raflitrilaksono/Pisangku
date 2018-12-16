package com.example.userpc.pisangku;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Review> {

        List<Review> reviewList;
        Context context;
        int resource;
        private SparseBooleanArray sparseBooleanArray;

        public ListViewAdapter(Context context, int resource, List<Review> reviewList){
            super(context, resource, reviewList);
            sparseBooleanArray = new SparseBooleanArray();
            this.context = context;
            this.resource = resource;
            this.reviewList = reviewList;
        }

        @NonNull
        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(context);
            View row = inflater.inflate(resource, null, false);

            TextView textViewItemProduk1 = row.findViewById(R.id.textsItemProduk1);
            TextView textViewItemjumlahProduk1 = row.findViewById(R.id.textsItemjumlahProduk1);
            TextView textViewHarga1 = row.findViewById(R.id.textsHarga1);
            TextView textViewItemProduk2 = row.findViewById(R.id.textsItemProduk2);
            TextView textViewItemjumlahProduk2 = row.findViewById(R.id.textsItemjumlahProduk2);
            TextView textViewHarga2 = row.findViewById(R.id.textsHarga2);
            TextView textViewItemNama = row.findViewById(R.id.textsItemNama);
            TextView textViewItemAlamat = row.findViewById(R.id.textsItemAlamat);
            TextView textViewItemPhone = row.findViewById(R.id.textsItemPhone);
            TextView textViewItemEmail = row.findViewById(R.id.textsItemEmail);
          //  TextView textViewItemTotal = row.findViewById(R.id.textsItemTotal);


            Review review = reviewList.get(pos);

            textViewItemProduk1.setText("Nama Pesanan 1 : "+review.getItemProduk1());
            textViewItemjumlahProduk1.setText("Jumlah Pesanan 1 : "+review.getItemjumlahProduk1());
            textViewHarga1.setText("Harga Pesanan 1 : "+review.getItemHarga1());
            textViewItemProduk2.setText("Nama Pesanan 2 : "+review.getItemProduk2());
            textViewItemjumlahProduk2.setText("Jumlah Pesanan 2 : "+review.getItemjumlahProduk2());
            textViewHarga2.setText("Harga Pesanan 2 : "+review.getItemHarga2());
            textViewItemNama.setText("Nama Konsumen : "+review.getItemNama());
            textViewItemAlamat.setText("Alamat Konsumen : "+review.getItemAlamat());
            textViewItemPhone.setText("No Hp Konsumen : "+review.getItemPhone());
            textViewItemEmail.setText("Email : "+review.getItemEmail());
           // textViewItemTotal.setText("Total : "+review.getItemTotal());
            return row;
        }

        @Override
        public void remove(Review review) {
            reviewList.remove(review);
            notifyDataSetChanged();
        }

        public List<Review> getReviewList() {
            return reviewList;
        }

        public void toggleSelection(int position) {
            selectView(position, !sparseBooleanArray.get(position));
        }

        public void removeSelection() {
            sparseBooleanArray = new SparseBooleanArray();
            notifyDataSetChanged();
        }

        public void selectView(int position, boolean value) {
            if (value)
                sparseBooleanArray.put(position, value);
            else
                sparseBooleanArray.delete(position);
            notifyDataSetChanged();
        }

        public int getSelectedCount() {
            return sparseBooleanArray.size();
        }

        public SparseBooleanArray getSelectedIds() {
            return sparseBooleanArray;
        }
    }

