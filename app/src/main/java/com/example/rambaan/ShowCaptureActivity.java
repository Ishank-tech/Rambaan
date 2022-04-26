package com.example.rambaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;

public class ShowCaptureActivity extends AppCompatActivity {

    DriveServiceHelper driveServiceHelper;

    ImageView image;
    ImageButton save, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_capture);

        requestSignIn();

        save = findViewById(R.id.save);
        close = findViewById(R.id.close);


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        byte[] b = extras.getByteArray("capture");

        if(b!=null){

            image = findViewById(R.id.imageCaptured);

            Bitmap decodeBitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            Bitmap rotateBitmap = rotate(decodeBitmap);

            image.setImageBitmap(rotateBitmap);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                FileOutputStream fos = null;
                File file = Environment.getExternalStorageDirectory();
                File dir = new File(file.getAbsolutePath() + "/MyPics");
                dir.mkdirs();
                String filename = String.format("%d.jpeg", System.currentTimeMillis());
                File outfile = new File(dir, filename);
                try {
                   fos = new FileOutputStream(outfile);
                }catch (Exception e){
                   e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                try {
                    fos.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

                ProgressDialog progressDialog = new ProgressDialog(ShowCaptureActivity.this);
                progressDialog.setTitle("Uploading to Google Drive");
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                driveServiceHelper.createFile(filename).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        progressDialog.dismiss();
                        Toast.makeText(ShowCaptureActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(ShowCaptureActivity.this, "Check Your Google Drive Api key", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }

    private void requestSignIn() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);

        startActivityForResult(client.getSignInIntent(), 400);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 400:
                if(resultCode == RESULT_OK){
                    handleSignInIntent(data);
                }
                break;
        }
    }

    private void handleSignInIntent(Intent data) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                        GoogleAccountCredential credential = GoogleAccountCredential.
                                usingOAuth2(ShowCaptureActivity.this, Collections.singleton(DriveScopes.DRIVE_FILE));
                        credential.setSelectedAccount(googleSignInAccount.getAccount());

                        Drive drive = new Drive.Builder(
                                AndroidHttp.newCompatibleTransport(),
                                new GsonFactory(),
                                credential
                        ).setApplicationName("My Drive")
                                .build();

                        driveServiceHelper = new DriveServiceHelper(drive);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private Bitmap rotate(Bitmap decodBitmap) {
        int w = decodBitmap.getWidth();
        int h = decodBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(270);

        return Bitmap.createBitmap(decodBitmap, 0, 0, w, h, matrix, true);
    }
}