package com.example.photo_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by open_mac on 2017/5/23.
 */
public class AlbumItemAdapter extends BaseAdapter {
    private List<Map<String,Object>> list;
    private LayoutInflater layoutInflater;

    public AlbumItemAdapter(Context context,List<Map<String,Object>> list){
        this.list = list;
        layoutInflater = LayoutInflater.from(context); //加载布局
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.itme,null); //加载layout

        //加载相册名称
        TextView textView = (TextView) convertView.findViewById(R.id.path);
        textView.setText(list.get(position).get("name").toString());

        //加载相册封面图
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ico_list);
     //   imageView.setImageBitmap((Bitmap) list.get(position).get("image"));
        loadView(imageView,(Bitmap) list.get(position).get("image"));

        return convertView;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            Map<String,Object> msg_map  = (Map<String, Object>) msg.obj;
            ImageView imageView = (ImageView) msg_map.get("imageView");
            Bitmap bitmap = (Bitmap) msg_map.get("img");
            imageView.setImageBitmap(bitmap);
        }
    };


    private void loadView(final ImageView imageView,final Bitmap img){

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Message message = handler.obtainMessage();
                Map<String,Object> msg_map = new HashMap<String,Object>();
                msg_map.put("imageView",imageView);
                msg_map.put("img",img);
                message.obj=imageView;
                // imageView.setImageBitmap(img);
                handler.sendMessage(message);
            }
        };
        thread.start();
        thread=null;

    }




}