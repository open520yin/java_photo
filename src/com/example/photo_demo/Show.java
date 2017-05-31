package com.example.photo_demo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.lib.CreateImage;

import java.io.File;
import java.util.*;

/**
 * Created by open_mac on 17/5/21.
 */
public class Show extends Activity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        Log.d("dd", "dd");

        Bundle bundle=getIntent().getExtras();


        buildImageBucketList(bundle.getString("bucket_path"));

        //item_list 图片列表显示，GridView 绑定事件
        gridView = (GridView) findViewById(R.id.item_grid_view);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                HashMap<String,String> map=(HashMap<String,String>)gridView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("image_path", map.get("file_path"));


                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(Show.this, ShowImg.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"有图相册想起------", Toast.LENGTH_SHORT).show();

            }
        });



    }



    //构建图像列表
    private void buildImageBucketList(String path) {


        //HashMap作为临时容器，以相册名为键来对图片分类。最后转换会为List
        GridView gridView = (GridView) findViewById(R.id.item_grid_view);

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();


        // 得到该路径文件夹下所有的文件
        File mfile = new File(path);
        File[] files = mfile.listFiles();

        String tmp_str = null;
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {

            File file = files[i];
            String file_path = file.getPath();
            String prefix=file_path.substring(file_path.lastIndexOf(".")+1);

            if(prefix.indexOf("jpg")>-1 || prefix.indexOf("png")>-1){
                Map<String,Object> listItem = new HashMap<String,Object>();
                listItem.put("file_path", file_path);

                listItem.put("image", CreateImage.createImageThumbnail(file_path));
                list.add(listItem);
            }
            Log.d("prefix :", prefix);
        }
        Button btn = (Button) findViewById(R.id.show_title);
        btn.setText(tmp_str);

        Toast.makeText(getApplicationContext(),"有图相册"+String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
        SimpleAdapter adapter = new ImageSimpleAdapter(this, list, R.layout.itme,new String[]{"name","img_num","image"}, new int[]{ R.id.textView1,R.id.path,R.id.ico_list});


        //实现列表的显示 
        gridView.setAdapter(adapter);

//
//
//        //新建查询列
//        String columns[] = new String[]{
//                MediaStore.Images.Media._ID,
//                MediaStore.Images.Media.DATA,
//                MediaStore.Images.Media.BUCKET_ID,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
//                MediaStore.Images.Media.DESCRIPTION
//        };
//
//        String selection = MediaStore.Images.Media.DATA + " like %?";
//        String[] selectionArgs = {path+"%"};
//
//        //新建查询
//        Cursor cur = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,columns,
//                selection, selectionArgs, MediaStore.Images.Media.DATE_MODIFIED + " desc");
//
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//
//        if (cur.moveToFirst()) {
//            int indexPhotoPath = cur.getColumnIndexOrThrow(columns[1]);
//
//            do {
//                Map<String,Object> listItem = new HashMap<String,Object>();
//                listItem.put("image", CreateImage.createImageThumbnail(cur.getString(indexPhotoPath)));
//                list.add(listItem);
//
//
//
//            } while (cur.moveToNext());
//        }
//        cur.close();
//
//
//        Toast.makeText(getApplicationContext(),"有图相册"+String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
//        SimpleAdapter adapter = new ImageSimpleAdapter(this, list, R.layout.itme,new String[]{"name","img_num","image"}, new int[]{ R.id.textView1,R.id.path,R.id.ico_list});
//
//
//        //实现列表的显示
//        gridView.setAdapter(adapter);
    }

}
