package msc.photint.MentorAppstennis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.KeyEvent;
import android.widget.VideoView;

public class Recordservefemale extends Activity {

final static int REQUEST_VIDEO_CAPTURED = 1;
private static final int CAPTURE_PICTURE_INTENT = 0;
Uri mCapturedImageURI ;
VideoView videoviewPlay;
Intent intent;

/** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
       

      Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);  
      intent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);  
      startActivityForResult(intent, CAPTURE_PICTURE_INTENT);
  }

  public boolean onKeyDown(int keyCode, KeyEvent event) {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
     	 Intent intent = new Intent(Recordservefemale.this, Previewservefemale.class);
       startActivity(intent);
    	
          return true;
      }
      return super.onKeyDown(keyCode, event);
  }
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	  super.onActivityResult(requestCode, resultCode, intent);

	  if (resultCode != RESULT_OK) return;

	  try {
	    AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(intent.getData(), "r");
	    FileInputStream fis = videoAsset.createInputStream();
	    File tmpFile = new File(Environment.getExternalStorageDirectory(),"photint/servefemale.3gp"); 
	    FileOutputStream fos = new FileOutputStream(tmpFile);

	    byte[] buf = new byte[1024];
	    int len;
	    while ((len = fis.read(buf)) > 0) {
	        fos.write(buf, 0, len);
	    }       
	    fis.close();
	    fos.close();
	    Intent inte = new Intent(Recordservefemale.this,Compare.class);
      	startActivity(inte);
      	finish();
	  } catch (IOException io_e) {
	    // TODO: handle error
	  }
  }
}