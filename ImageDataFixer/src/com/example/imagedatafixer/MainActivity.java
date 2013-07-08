package com.example.imagedatafixer;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}
	
	private void initView() {
		findViewById(R.id.btn_fix_image).setOnClickListener(this);
	}

	private void fixImage() {
		ContentResolver cr = getContentResolver();
		int count = cr.delete(Images.Media.EXTERNAL_CONTENT_URI, "_data = ?", new String [] {"", });
		Toast.makeText(this, getString(R.string.count) + count + getString(R.string.data), Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_fix_image :
				fixImage();
				break;

			default :
				break;
		}
	}

}
