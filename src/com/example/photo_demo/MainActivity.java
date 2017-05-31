package com.example.photo_demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.lib.CreateImage;
import com.example.lib.ImageAndTextListAdapter;

public class MainActivity extends Activity {


	//private ListView listView;
    private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {




		super.onCreate(savedInstanceState);


        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		Log.d("dd", "dd");





		buildImageBucketList();

        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                HashMap<String,String> map=(HashMap<String,String>)gridView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putInt("num", 1);
                bundle.putString("message", map.get("bucket_path"));
                bundle.putString("bucket_path", map.get("bucket_path"));


                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, Show.class);
                startActivity(intent);
            }
        });



    }

	//构建图像列表
	private void buildImageBucketList() {
		//HashMap作为临时容器，以相册名为键来对图片分类。最后转换会为List
		HashMap<String, ImageBucket> tBucketMap = new HashMap<>();
        gridView = (GridView) findViewById(R.id.gridView);

		//新建查询列
		String columns[] = new String[]{
				MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATA,
				MediaStore.Images.Media.BUCKET_ID,
				MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
				MediaStore.Images.Media.DESCRIPTION
		};
		//新建查询
		Cursor cur = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				columns, null, null, MediaStore.Images.Media.DATE_MODIFIED + " desc");

		if (cur.moveToFirst()) {
			int indexPhotoID = cur.getColumnIndexOrThrow(columns[0]);
			int indexPhotoPath = cur.getColumnIndexOrThrow(columns[1]);
			int indexBucketID = cur.getColumnIndexOrThrow(columns[2]);
			int indexBucketDisplay = cur.getColumnIndexOrThrow(columns[3]);
			int indexBucketDescrption = cur.getColumnIndexOrThrow(columns[4]);

			do {
				String id = cur.getString(indexPhotoID); //图片id
				String path = cur.getString(indexPhotoPath);//获取图片路径
				String bucketID = cur.getString(indexBucketID);//获取资源id
				String bucketDisplay = cur.getString(indexBucketDisplay);//相册目录名字
				String BucketDescrption = cur.getString(indexBucketDescrption);
				//得到相册
				ImageBucket bucket = tBucketMap.get(bucketID);
				//如果没有该相册，则新建一个
				if (bucket == null) {
					bucket = new ImageBucket();
					bucket.setImageList(new ArrayList<ImageItem>());
					bucket.setBucketName(bucketDisplay);
					bucket.setCover_path(path);
                    bucket.setBacketPath(path.substring(0, path.lastIndexOf("/")));
					tBucketMap.put(bucketID, bucket);
				}
				//更新相册图片
				ImageItem image = new ImageItem();
				image.setImageId(id);
				image.setImagePath(path);
				image.setBucket(bucket);
				bucket.getImageList().add(image);
			} while (cur.moveToNext());
		}
		cur.close();

		//HashMap转List
		Iterator<Map.Entry<String, ImageBucket>> itr = tBucketMap.entrySet().iterator();

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		while (itr.hasNext()) {
			Map.Entry<String, ImageBucket> entry = itr.next();

			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("name", entry.getValue().getBucketName());
			listItem.put("img_num", "图片（"+entry.getValue().getImageList().size()+")张");
			listItem.put("image", CreateImage.createImageThumbnail(entry.getValue().getCover_path()));
            listItem.put("bucket_path", entry.getValue().getBacketPath());
			listItem.put("image_path",entry.getValue().getCover_path());
			list.add(listItem);
		}



		gridView.setAdapter(new ImageAndTextListAdapter(this, list, gridView));

		//SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.itme,new String[]{"name","img_num","image"}, new int[]{ R.id.textView1,R.id.path,R.id.ico_list});

		//实现列表的显示
        //gridView.setAdapter(adapter);
	}






	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
