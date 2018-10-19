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

            TextView textViewItemProduk = row.findViewById(R.id.textsItemProduk);
            TextView textViewItemjumlahProduk = row.findViewById(R.id.textsItemjumlahProduk);
            TextView textViewItemNama = row.findViewById(R.id.textsItemNama);
            TextView textViewItemAlamat = row.findViewById(R.id.textsItemAlamat);
            TextView textViewItemPhone = row.findViewById(R.id.textsItemPhone);
            TextView textViewItemEmail = row.findViewById(R.id.textsItemEmail);


            Review review = reviewList.get(pos);

            textViewItemProduk.setText(review.getItemProduk());
            textViewItemjumlahProduk.setText(review.getItemjumlahProduk());
            textViewItemNama.setText(review.getItemNama());
            textViewItemAlamat.setText(review.getItemAlamat());
            textViewItemPhone.setText(review.getItemPhone());
            textViewItemEmail.setText(review.getItemEmail());
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

