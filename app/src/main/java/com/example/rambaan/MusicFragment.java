package com.example.rambaan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment{

    private Camera camera=null;
    private FrameLayout frameLayout;
//    ShowCamera showCamera;
    private ImageView capture;

    private boolean isCameraInitialized;

//    private Camera mcamera = null;
//
    private static SurfaceHolder myHolder;
      private static CameraPreview mPreview;
//
//    private FrameLayout preview;

    private static final String[] PERMISSIONS = {
      Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int REQUEST_PERMISSIONS = 34;

    private static final int PERMISSIONS_COUNT = 3;
//
    private ImageButton flashB;
    private ImageButton switchCameraButton;
//
    private static OrientationEventListener orientationEventListener = null;
//
    private static boolean fM;
//
    @SuppressLint("NewApi")
    private boolean arePermissionsDenied(){
        for(int i = 0;i<PERMISSIONS_COUNT;i++){
            if(getActivity().checkSelfPermission(PERMISSIONS[i])!=PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
        }
        return false;
    }

//    Camera camera;
//
//    Camera.PictureCallback jpegCallback;
//
//    SurfaceView mSurfaceView;
//    SurfaceHolder mSurfaceHolder;
    final int CAMERA_REQUEST_CODE = 1;

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

    private void initCam(){
        if(!whichCamera){
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        }else{
            camera = Camera.open();
        }
        mPreview = new CameraPreview(getContext(), camera);
        frameLayout.addView(mPreview);
        rotateCamera();
        if(hasFlash()){
            flashB.setVisibility(View.VISIBLE);
        }else{
            flashB.setVisibility(View.GONE);
        }
    }

    private void init(){

        initCam();

        switchCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.release();
                switchCamera();
                rotateCamera();
                try{
                    camera.setPreviewDisplay(myHolder);
                }catch (Exception e){

                }
                camera.startPreview();
                if(hasFlash()){
                    flashB.setVisibility(View.VISIBLE);
                }else{
                    flashB.setVisibility(View.GONE);
                }
            }
        });

        orientationEventListener = new OrientationEventListener(getActivity()) {
            @Override
            public void onOrientationChanged(int i) {
                rotateCamera();
            }
        };
        orientationEventListener.enable();
        frameLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(whichCamera){
                    if(fM){
                        p.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                    }else{
                        p.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                    }
                    try{
                        camera.setParameters(p);
                    }catch (Exception e){

                    }
                    fM = !fM;
                }
                return true;
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);
        frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayoutC);
        capture = (ImageView) rootView.findViewById(R.id.capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camera != null){
                    try{
                        camera.takePicture(null, null, mPictureCallback);
//                        camera.startPreview();
                        initCam();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

//      preview = (FrameLayout) rootView.findViewById(R.id.frameLayoutC);
        flashB = (ImageButton) rootView.findViewById(R.id.flash);
        switchCameraButton = (ImageButton) rootView.findViewById(R.id.switchCamera);
//
        if(!isCameraInitialized){
            init();
            isCameraInitialized = true;
        }

        return rootView;
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSIONS && grantResults.length > 0){
            if(arePermissionsDenied()){
                // for permission
                getActivity().requestPermissions(new String[]{PERMISSIONS[0]},34);
                //
                ((ActivityManager)(getActivity().getSystemService(Context.ACTIVITY_SERVICE))).clearApplicationUserData();
                getActivity().recreate();
            }else{
                onResume();
            }
        }
    }

    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {

            Intent intent = new Intent(getActivity(), ShowCaptureActivity.class);
            intent.putExtra("capture", bytes);
            startActivity(intent);


            //commenting another correct one
//            try {
//                FileOutputStream fos = new FileOutputStream(saveFile(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)))+".jpg");
//                fos.write(bytes);
//                fos.close();
//            }catch (IOException e){
//                e.printStackTrace();
//            }

            //commented correct code
//            File picture_file = getOutputMediaFile();
//
//            if(picture_file == null){
//                return;
//            }else{
//                try {
//                    FileOutputStream fos = new FileOutputStream(picture_file);
//                    fos.write(bytes);
//                    fos.close();
//
//                    camera.startPreview();
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
        }
    };

    private static String saveFile(String filePath){
        File f = new File(filePath);
        if(!f.exists()){
            f.mkdir();
        }
        return f+"/"+new SimpleDateFormat("MMDDHHmmss").format(Calendar.getInstance().getTime());
    }

//    Correct code commented for change

//    private File getOutputMediaFile(){
//        String state = Environment.getExternalStorageState();
//        if(!state.equals(Environment.MEDIA_MOUNTED)){
//            return null;
//        }else{
//            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");
//
//            if(!folder_gui.exists()){
//                folder_gui.mkdirs();
//            }
//            File outputFile = new File(folder_gui, "temp.jpg");
//            return outputFile;
//        }
//    }


    @Override
    public void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && arePermissionsDenied()){
            requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            return;
        }
    }

    private void switchCamera(){
        if(whichCamera){
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        }else{
            camera = Camera.open();
        }
        whichCamera = !whichCamera;
    }

    private void releaseCamera(){
        if(camera != null){
            frameLayout.removeView(mPreview);
            camera.release();
            orientationEventListener.disable();
            camera = null;
            whichCamera = !whichCamera;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    private static List<String> camEffects;

    private static boolean hasFlash(){
        camEffects = p.getSupportedColorEffects();
        final List<String> flashModes = p.getSupportedFlashModes();
        if(flashModes == null){
            return false;
        }
        for(String flashMode:flashModes){
            if(Camera.Parameters.FLASH_MODE_ON.equals(flashMode)){
                return true;
            }
        }
        return false;
    }

    private static int rotation;

    private static boolean whichCamera = true;

    private static Camera.Parameters p;

    private void rotateCamera(){
        if(camera != null){
            rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
            if(rotation == 0){
                rotation = 90;
            }else if(rotation == 1){
                rotation = 0;
            }else if(rotation == 2){
                rotation = 270;
            }else{
                rotation = 180;
            }
            camera.setDisplayOrientation(rotation);
            if(!whichCamera){
                if(rotation == 90){
                    rotation = 270;
                }else if(rotation == 270){
                    rotation = 90;
                }
            }
            p = camera.getParameters();
            p.setRotation(rotation);
            camera.setParameters(p);
        }
    }
//
    private static class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
        private static SurfaceHolder mHolder;
        private static Camera mCamera;

        public CameraPreview(Context context, Camera camera) {
            super(context);
            mCamera = camera;
            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

            Camera.Parameters params = mCamera.getParameters();

            List<Camera.Size> sizes = params.getSupportedPictureSizes();
            Camera.Size mSize = null;

            for(Camera.Size size : sizes){
                mSize = size;
            }

            myHolder = mHolder;
            params.setPictureSize(mSize.width, mSize.height);
            mCamera.setParameters(params);
            try {
                mCamera.setPreviewDisplay(surfaceHolder);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
            mCamera.stopPreview();
            mCamera.release();
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }
    }
}