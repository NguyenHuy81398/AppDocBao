package com.example.appdocbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListSearch_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ItemTinTuc> itemTinTucs;
    ArrayList<ItemTinTuc> listSearch;

    public ListSearch_Adapter(Context context, ArrayList<ItemTinTuc> itemTinTucs) {
        this.context = context;
        this.itemTinTucs = itemTinTucs;
        this.listSearch = new ArrayList<>();
        listSearch.addAll(itemTinTucs);
    }

    @Override
    public int getCount() {
        return itemTinTucs.size();
    }

    @Override
    public Object getItem(int i) {
        return itemTinTucs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void filter(String query){
        itemTinTucs.clear();
        if(query.length() == 0){
            itemTinTucs.addAll(listSearch);
        }else {
            for (ItemTinTuc itemTinTuc: listSearch){
                if(itemTinTuc.getTitle().toLowerCase().contains(query.toLowerCase())){
                    itemTinTucs.add(itemTinTuc);
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.item_tintuc, viewGroup, false);


        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtPubDate = v.findViewById(R.id.txtPubDate);
        ImageView imageView = v.findViewById(R.id.imgTinTuc);

        ItemTinTuc itemTinTuc = itemTinTucs.get(i);
        txtTitle.setText(itemTinTuc.getTitle());
        txtPubDate.setText(itemTinTuc.getPubDate());
        Glide.with(context).load(itemTinTuc.getImg()).into(imageView);

        return v;
    }
}
