package com.example.opencvproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	
	
	private static final String TAG="MainActivity";

	private int type = 0;// 0表示腐蚀erode, 1表示膨胀dilate
	private int kernalprogress = 3; // 结构元素(内核矩阵)的尺寸

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ImageView img = (ImageView) findViewById(R.id.img);

		final Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.zuozhu)).getBitmap();

		RadioGroup handle = (RadioGroup) findViewById(R.id.rg_handle);
		handle.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < group.getChildCount(); i++) {
					RadioButton rb = (RadioButton) group.getChildAt(i);
					if (rb.isChecked()) {
						type = i;
						img.setImageBitmap(handleBitmap(bitmap));
					}
				}
			}
		});

		SeekBar kernel = (SeekBar) findViewById(R.id.sb_kernel);

		kernel.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				kernalprogress = (int) (progress * 9 / 50.0 + 3);
				Log.i(TAG, "progress:"+kernalprogress);
				img.setImageBitmap(handleBitmap(bitmap));
			}
		});
		img.setImageBitmap(handleBitmap(bitmap));


	}

	private Bitmap handleBitmap(Bitmap b) {
		int w = b.getWidth(), h = b.getHeight();
		int[] pix = new int[w * h];
		b.getPixels(pix, 0, w, 0, 0, w, h);
		int[] resultPixes = NdkUtils.filter(pix, w, h,type,kernalprogress);
		Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
		result.setPixels(resultPixes, 0, w, 0, 0, w, h);
		return result;
	}
}
