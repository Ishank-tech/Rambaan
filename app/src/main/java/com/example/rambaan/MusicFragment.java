package com.example.rambaan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.ar.core.AugmentedFace;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.AugmentedFaceNode;

import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment {

//    Camera camera;
//
//    Camera.PictureCallback jpegCallback;
//
//    SurfaceView mSurfaceView;
//    SurfaceHolder mSurfaceHolder;
//    final int CAMERA_REQUEST_CODE = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    @Override
//    protected Config getSessionConfiguration(Session session) {
//        Config config = new Config(session);
//        config.setAugmentedFaceMode(Config.AugmentedFaceMode.MESH3D);
//        this.getArSceneView().setupSession(session);
//        return config;
//    }
//
//    @Override
//    protected Set<Session.Feature> getSessionFeatures() {
//        return EnumSet.of(Session.Feature.FRONT_CAMERA);
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);



//        mSurfaceView = rootView.findViewById(R.id.surfaceView);

//        mSurfaceHolder = mSurfaceView.getHolder();
//        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
//        }else{
//            mSurfaceHolder.addCallback(this);
//            mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        }
//
//        ImageView mCapture = rootView.findViewById(R.id.capture);
//        mCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                captureImage();
//            }
//        });
//
//        jpegCallback = new Camera.PictureCallback() {
//            @Override
//            public void onPictureTaken(byte[] bytes, Camera camera) {
//                Intent intent = new Intent(getActivity(), ShowCaptureActivity.class);
//                intent.putExtra("capture", bytes);
//                startActivity(intent);
//            }
//        };
        return rootView;
    }




    //    private void captureImage() {
//        camera.takePicture(null,null, jpegCallback);
//    }
//
//    @Override
//    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
//        camera = Camera.open();
//
//        Camera.Parameters parameters;
//        parameters = camera.getParameters();
//
//        camera.setDisplayOrientation(90);
//        parameters.setPreviewFrameRate(30);
//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//
//        Camera.Size bestSize  = null;
//        List<Camera.Size> sizeList = camera.getParameters().getSupportedPreviewSizes();
//        bestSize = sizeList.get(0);
//        for(int i = 1;i<sizeList.size();i++){
//            if((sizeList.get(i).width * sizeList.get(i).height)>(bestSize.width * bestSize.height)){
//                bestSize = sizeList.get(i);
//            }
//        }
//
//        parameters.setPreviewSize(bestSize.width, bestSize.height);
//
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        camera.startPreview();
//    }
//
//    @Override
//    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch(requestCode){
//            case CAMERA_REQUEST_CODE:{
//                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    mSurfaceHolder.addCallback(this);
//                    mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//                }else{
//                    Toast.makeText(getContext(), "Please provide the permission", Toast.LENGTH_LONG).show();
//                }
//                break;
//            }
//        }
//    }



}