package com.example.lib;

import java.util.List;
import java.util.Map;

import com.example.lib.AsyncImageLoader.ImageCallback;
import com.example.photo_demo.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAndTextListAdapter extends ArrayAdapter<Map<String,Object>> {

    private GridView gridView;
    private AsyncImageLoader asyncImageLoader;
    public ImageAndTextListAdapter(Activity activity, List<Map<String,Object>> imageAndTexts, GridView gridView1) {
        super(activity, 0, imageAndTexts);
        this.gridView = gridView1;
        asyncImageLoader = new AsyncImageLoader();
    }

    @SuppressLint({ "ViewHolder", "InflateParams" })
	public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("get_view"," get view ~~~~~"+ Integer.toString(position));

        Activity activity = (Activity) getContext();//获取正在运行的activity


        View rowView = convertView;
        LayoutInflater inflater = activity.getLayoutInflater();
        rowView = inflater.inflate(R.layout.itme, null);//找到gridView

        Map<String,Object> ItemData = getItem(position);//获取指定position item 的数据
        String imageUrl = ItemData.get("image_path").toString();//获取本item的image_path

        ImageView imageView = (ImageView)rowView.findViewById(R.id.ico_list);
        imageView.setTag(imageUrl);//保证系统回调更新

        Drawable cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback() {
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                ImageView imageViewByTag = (ImageView) gridView.findViewWithTag(imageUrl);
                if (imageViewByTag != null) {
                    imageViewByTag.setImageDrawable(imageDrawable);
                }
            }
        });

        if (cachedImage == null) {
            imageView.setImageResource(R.drawable.no_image);
        }else{
            imageView.setImageDrawable(cachedImage);
        }
        // Set the text on the TextView
//        TextView textView = viewCache.getTextView();
//        textView.setText(imageAndText.get("img_num").toString());
        return rowView;
    }

}
