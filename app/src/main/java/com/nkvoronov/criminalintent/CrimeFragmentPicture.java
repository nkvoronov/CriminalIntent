package com.nkvoronov.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.UUID;

public class CrimeFragmentPicture extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID = "com.nkvoronov.criminalintent.crime_id";

    private Crime mCrime;
    private File mPhotoFile;
    private ImageView mPhotoView;

    public static Intent newIntent(Context packageContext, UUID crimeID) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath());
            int rt = PictureUtils.getRotation(mPhotoFile.getPath());
            mPhotoView.setImageBitmap(bitmap);
            mPhotoView.setRotation(rt);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_crime);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(this).getCrime(crimeId);
        mPhotoFile = CrimeLab.get(this).getPhotoFile(mCrime);

        mPhotoView = findViewById(R.id.picture_max);
        updatePhotoView();

    }

}
