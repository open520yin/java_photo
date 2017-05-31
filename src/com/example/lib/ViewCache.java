package com.example.lib;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.photo_demo.R;

/**
 * Created by open_mac on 2017/5/24.
 */
public class ViewCache {
    private View baseView;
    private TextView textView;
    private ImageView imageView;

    public ViewCache(View baseView) {
        this.baseView = baseView;
    }

    public TextView getTextView() {
        if (textView == null) {
            textView = (TextView) baseView.findViewById(R.id.path);
        }
        return textView;
    }

    public ImageView getImageView() {
        if (imageView == null) {
            imageView = (ImageView) baseView.findViewById(R.id.ico_list);
        }
        return imageView;
    }
}
