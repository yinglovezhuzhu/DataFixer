package com.example.imagedatafixer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video;
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
		findViewById(R.id.btn_fix_vedio).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_fix_image :
				new FixData().execute(Images.Media.EXTERNAL_CONTENT_URI);
				break;
			case R.id.btn_fix_vedio :
				new FixData().execute(Video.Media.EXTERNAL_CONTENT_URI);
				break;
			default :
				break;
		}
	}

	
	private class FixData extends AsyncTask<Uri, Void, Integer> {
		
		private ProgressDialog mmProgressDialog = null;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mmProgressDialog = ProgressDialog.show(MainActivity.this, null, getString(R.string.fixing), true, false);
		}

		@Override
		protected Integer doInBackground(Uri... params) {
			ContentResolver cr = getContentResolver();
			int count = cr.delete(params[0], "_data = ?", new String [] {"", });
			return count;
		}
		
		
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			mmProgressDialog.cancel();
			Toast.makeText(MainActivity.this, getString(R.string.count) + result + getString(R.string.data), Toast.LENGTH_LONG).show();
		}
	}
}
