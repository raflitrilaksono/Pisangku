package com.example.userpc.pisangku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private String[] title, photoUrl, price, rp, desc, turunan;
    List<Products> productsList;

    public CustomAdapter(Context context, List<Products> productsList){
        this.context = context;
        this.productsList = productsList;
       // this.title = title;
       // this.photoUrl = photoUrl;
       // this.price = price;
        // this.rp = rp;
       // this.desc = desc;
       // this.turunan = turunan;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        grid = new View(context);

        if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        grid = inflater.inflate(R.layout.customgrid, parent, false);

    } else {
        grid = (View) convertView;
    }

            Products products = productsList.get(position);
            ImageView itemImage = (ImageView) grid.findViewById(R.id.photoUrl);
            TextView itemTitle = (TextView) grid.findViewById(R.id.item_title);
 //          TextView itemPrice = (TextView) grid.findViewById(R.id.item_price);
//            TextView itemDesc = (TextView) grid.findViewById(R.id.item_desc);
//            itemTitle.setText(title[position]);
//            itemPrice.setText(price[position]);
//            itemDesc.setText(desc[position]);

            Glide.with(context).load(products.photoUrl).into(itemImage);
            itemTitle.setText(products.title);
   //         itemPrice.setText(products.price);

            return grid;
    }

}
