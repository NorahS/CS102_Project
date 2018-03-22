/**
 * Wala Kalema
 *
 * Wala Kalema is  an Andriod game  (c) 2016-2017 held jointly by the individual
 * authors.
 *
 * Wala Kalema was implemented as a CS101 course project at Prince Sultan University by Norah Alsabti,Nora ALshaalan, Sara Hussain, Nouf Almoajel And Sara sweed.
 * Date:Spring 2016
 */
package n.headsup;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;


public class Sensors extends Setup implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer, senMagnetometer;
    private Player p1 =new Player();

    private boolean isSet=false;
    private float mGravity[], mGeomagnetic[];
    private int rotDegs[] = new int[12];
    private long lastUpdate = 0;
    private int degIndex = 0;
    private int lowThreshold = 15;//25;
    private int highThreshold = 150;//90;
    private int transitionDelay = 280;//230;
    private MediaPlayer playSE;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = super.onCreateView( inflater,container, savedInstanceState);

        senSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senMagnetometer = senSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_UI);
        senSensorManager.registerListener(this, senMagnetometer, SensorManager.SENSOR_DELAY_UI);

        playSE = MediaPlayer.create(getContext(), R.raw.play);
        return rootView;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public  void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_UI);
        senSensorManager.registerListener(this, senMagnetometer, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause() {

        super.onPause();
        senSensorManager.unregisterListener(this);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        int ranIndex;
        if(!HelperFunctions.Done) {
            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mGravity = event.values;

            } else if (mySensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic = event.values;
            }
            if (mGeomagnetic != null && mGravity != null && isSet) {
                float rotation[] = new float[9];
                senSensorManager.getRotationMatrix(rotation, null, mGravity, mGeomagnetic);

                float orientation[] = new float[9];
                senSensorManager.getOrientation(rotation, orientation);


                float rot = orientation[2];
                int rotDeg = (int) Math.round(Math.toDegrees(rot));
                rotDegs[degIndex] = rotDeg;
                degIndex++;
                if (degIndex == rotDegs.length) {

                    int devRot = this.state();
                    long curTime = System.currentTimeMillis();
                    if ((devRot < lowThreshold) && (curTime - lastUpdate) > transitionDelay) {
                        if (p1 != null)
                            p1.addS("F");
                        p1.addP(HelperFunctions.next());
//                    pass = MediaPlayer.create(getContext(), R.raw.sss);
//                    pass.start();

                        playSE.start();

                        lastUpdate = System.currentTimeMillis();

                    } else if (devRot > highThreshold && (curTime - lastUpdate) > transitionDelay) {

                        p1.addS("T");
                        p1.setScore();

                        playSE.start();
                        p1.addP(HelperFunctions.next());
                        lastUpdate = System.currentTimeMillis();

                    } else {
                    }

                }


            }
        }
    }

    private int state() {
        int sum = 0;
        for (int i = 0; i != rotDegs.length; i++)
            sum += rotDegs[i];
        degIndex = 0;
        int aver = sum / rotDegs.length;
        int n = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if (n == Surface.ROTATION_90)
            aver *= -1;
        return aver;
    }

    public void stopSensors(){
        this.onPause();
    }

    public void addToP(String m){
        p1.addP(m);
    }
   protected Player getPlayer(){
       return p1;
   }
    public void setPlayer(){
        p1 = null;
    }

    public void setIsSet(){
        isSet=true;
    }
    public void unsetIsSet(){
        isSet=false;
    }
}
