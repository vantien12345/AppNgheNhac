package com.example.baitapcuoiky;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListSongAdapter extends BaseAdapter  {
    Context myContext;
    int myLayout;
    List<ListSong> arraySong;

    public ListSongAdapter(Context myContext, int myLayout, List<ListSong> arraySong) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arraySong = arraySong;
    }
    @Override
    public int getCount() {
        return arraySong.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(myLayout,null);
        // amh xa va gan gia ttri
        TextView tvSong=(TextView) convertView.findViewById(R.id.tvSong);
        TextView tvSinger=(TextView) convertView.findViewById(R.id.tvSinger);
        ImageView ivHinh=(ImageView)convertView.findViewById(R.id.ivHinh);

        tvSong.setText(arraySong.get(position).getSong());
        tvSinger.setText(arraySong.get(position).getSinger());
        ivHinh.setImageResource(arraySong.get(position).getHinhanh());
        return convertView;
    }
}
