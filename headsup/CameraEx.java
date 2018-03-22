package n.headsup;

import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.IOException;


/**
 * Created by Norah on 4/7/16 AD.
 */
public class CameraEx extends Sensors implements SurfaceHolder.Callback {

     private MediaRecorder recorder;
     private SurfaceHolder holder;
     private boolean recording = false;
    Camera camera;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        rootView = super.onCreateView(inflater,container, savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        camera = Camera.open (1);
        camera.setDisplayOrientation (0);
        camera.unlock ();
        recorder = new MediaRecorder();
        setRecoder();


        SurfaceView cameraView = (SurfaceView) rootView.findViewById(R.id.CameraV);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        return rootView;
    }
    public void setRecoder(){

        recorder.setCamera(camera);
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);

        CamcorderProfile cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(cpHigh);
        String name = super.getPlayer().getVideoName();
        recorder.setOutputFile("/sdcard/"+name+".mp4");
        recorder.setMaxDuration(90000); // Timer
        recorder.setMaxFileSize(900000000);

    }
    public void setSurface(){
        SurfaceView cameraView = (SurfaceView) rootView.findViewById(R.id.CameraV);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void prepareRecorder() {
        recorder.setPreviewDisplay(holder.getSurface());

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            getActivity().finish();
        } catch (IOException e) {
            getActivity().finish();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        prepareRecorder();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        getActivity().finish();
    }

    public void startRecorder(){
        recorder.start();
    }
    public void stoprRecording(){
        recorder.stop();
    }
    @Override
    public void onPause() {
        super.onPause();
        recorder.release();
        camera.release();
        getActivity().finish();
    }

}

