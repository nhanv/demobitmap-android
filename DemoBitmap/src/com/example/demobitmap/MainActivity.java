package com.example.demobitmap;

import nhanv.example.utils.ImageUtils;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	private static final String TAG = "main_activity";
	private static boolean isLoaded;

	private Button mBtnLoadImg;
	private ImageView mImgView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// init ActionBar
		setContentView(R.layout.activity_main);

		initView();
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		initData();
	}

	private void initData() {
		isLoaded = false;
	}

	private void initView() {
		mBtnLoadImg = (Button) findViewById(R.id.activity_main_load_img);
		mImgView = (ImageView) findViewById(R.id.activity_main_imgview);

		mBtnLoadImg.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_main_load_img:
			if (!isLoaded) {
				loadImg();
				isLoaded = true;
			}
			break;

		default:
			break;
		}
	}

	/**
	 * load image from resource into imageView
	 */
	private void loadImg() {
		int imgId = R.drawable.img_demo;
		Resources resource = getResources();

		// check image size, image type
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resource, imgId, options);

		int imgWidth = options.outWidth;
		int imgHeight = options.outHeight;
		String imgType = options.outMimeType;

		Log.e(TAG, "Img: height = " + imgHeight + " ,width = " + imgWidth
				+ " ,style = " + imgType);

		// calculate inSampleSize
		options.inSampleSize = ImageUtils.calculateSampleSize(imgHeight,
				imgWidth, 100, 100);

		// load image into imageView
		// restore inJustDecodeBounds to return bitMap
		options.inJustDecodeBounds = false;
		Bitmap bm = BitmapFactory.decodeResource(resource, imgId, options);
		mImgView.setImageBitmap(bm);
	}
}
