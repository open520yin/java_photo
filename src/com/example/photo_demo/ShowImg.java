package com.example.photo_demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import com.example.lib.ImageFactory;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by open_mac on 17/5/21.
 */
public class ShowImg extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_img);
        Log.d("show_img", "开始进入");

        Bundle bundle=getIntent().getExtras();

        //ImageView show_img = (ImageView) findViewById(R.id.show_img);
        String image_path= bundle.getString("image_path");
        Toast.makeText(getApplicationContext(),"show_img 开始进入------"+image_path, Toast.LENGTH_SHORT).show();

        //show_img_btn.setText(image_path);


        PhotoView iv_photo2 = (PhotoView) findViewById(R.id.iv_photo1);
        iv_photo2.setImageBitmap(ImageFactory.ratio(image_path,1280f,1280f));

        //show_img.setImageBitmap(ImageFactory.getBitmap(image_path));



    }





}
