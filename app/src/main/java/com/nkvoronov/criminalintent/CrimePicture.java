package com.nkvoronov.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class CrimePicture extends AppCompatActivity {
    private static final String EXTRA_PHOTO_FILE = "com.nkvoronov.criminalintent.photo_file";
    private static final String TAG = "DEBUG";

    private File mPhotoFile;
    private ImageView mPhotoView;

    public static Intent newIntent(Context packageContext, File file) {
        Intent intent = new Intent(packageContext, CrimePicture.class);
        Log.d(TAG, "file - " + file.getAbsolutePath());
        intent.putExtra(EXTRA_PHOTO_FILE, file);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_crime);

        mPhotoFile = (File) getIntent().getSerializableExtra(EXTRA_PHOTO_FILE);
        Log.d(TAG, "file - " + mPhotoFile.getAbsolutePath());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        mPhotoView = findViewById(R.id.picture_max);
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap origBitmap = BitmapFactory.decodeFile(mPhotoFile.getPath());
            int rotate = PictureUtils.getRotation(mPhotoFile.getPath());
            Matrix matrix = new Matrix();
            matrix.postRotate(rotate);
            Bitmap outBitmap = Bitmap.createBitmap(origBitmap, 0, 0, origBitmap.getWidth(), origBitmap.getHeight(), matrix, true);
            mPhotoView.setImageBitmap(outBitmap);
        }
    }

}
