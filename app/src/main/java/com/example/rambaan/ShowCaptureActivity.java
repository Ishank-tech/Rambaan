package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowCaptureActivity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_capture);

        image = findViewById(R.id.imageCaptured);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        byte[] b = extras.getByteArray("capture");

        if(b!=null){

            Bitmap decodeBitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            Bitmap rotateBitmap = rotate(decodeBitmap);

            image.setImageBitmap(rotateBitmap);
        }

    }

    private Bitmap rotate(Bitmap decodBitmap) {
        int w = decodBitmap.getWidth();
        int h = decodBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(90);

        return Bitmap.createBitmap(decodBitmap, 0, 0, w, h, matrix, true);
    }
}