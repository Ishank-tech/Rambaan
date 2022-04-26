package com.example.rambaan;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

//    android.hardware.Camera camera;
//    SurfaceHolder holder;

    public ShowCamera (Context context, Camera camera) {
        super(context);
//        this.camera = camera;
//        holder = getHolder();
//        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
//        android.hardware.Camera.Parameters parameters = camera.getParameters();
//
//        //Change the orinentation of camera
//        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
//        Camera.Size msize = null;
//
//        for(Camera.Size size : sizes){
//            msize = size;
//        }
//
//        if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){
//            parameters.set("orientation", "portrait");
//            camera.setDisplayOrientation(90);
//            parameters.setRotation(90);
//        }else{
//            parameters.set("orientation","landscape");
//            camera.setDisplayOrientation(0);
//            parameters.setRotation(0);
//        }
//
//        parameters.setPictureSize(msize.width, msize.height);
//
//        camera.setParameters(parameters);
//        try {
//            camera.setPreviewDisplay(holder);
//            camera.startPreview();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
//        camera.stopPreview();
//        camera.release();
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        FrameLayout frameLayout = (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);
//
//        return frameLayout;
//    }
}
