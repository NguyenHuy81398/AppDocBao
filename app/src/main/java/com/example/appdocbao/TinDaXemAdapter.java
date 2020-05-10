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

public class TinDaXemAdapter extends BaseAdapter {

    Context context;
    ArrayList<ItemTinTuc> listTinDaXem;

    public TinDaXemAdapter(Context context, ArrayList<ItemTinTuc> listTinDaXem) {
        this.context = context;
        this.listTinDaXem = listTinDaXem;
    }

    @Override
    public int getCount() {
        return listTinDaXem.size();
    }

    @Override
    public Object getItem(int i) {
        return listTinDaXem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.item_tindaxem, viewGroup, false);

        TextView txtTitle = v.findViewById(R.id.txtTitleTinDaXem);
        TextView txtPubDate = v.findViewById(R.id.txtPubDateTinDaXem);
        ImageView imgTinTuc = v.findViewById(R.id.imgTinDaXem);

        ItemTinTuc itemTinTuc = listTinDaXem.get(i);

        txtTitle.setText(itemTinTuc.getTitle());
        txtPubDate.setText(itemTinTuc.getPubDate());

        Glide.with(context).load(itemTinTuc.getImg()).into(imgTinTuc);
        return v;
    }
}
