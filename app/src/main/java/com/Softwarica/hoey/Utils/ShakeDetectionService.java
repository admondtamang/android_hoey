package com.Softwarica.hoey.Utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.Softwarica.hoey.UI.MainActivity;

public class ShakeDetectionService extends Service implements SensorEventListener {

    public final int MIN_TIME_BETWEEN_SHAKE=1000;
    SensorManager sensorManager=null;

    Vibrator vibrator=null;
    private long lastShakeTime=0;
    private Float shakeThreshold=30.0f;
    private static final String TAG = "ShakeDetectionService";
    
    @Override
    public void onCreate() {
        super.onCreate();

        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            Sensor accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            long curTime=System.currentTimeMillis();
            if((curTime - lastShakeTime)>MIN_TIME_BETWEEN_SHAKE){
                float x=event.values[0];
                float y=event.values[1];
                float z=event.values[2];

                double acceleration=Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
                if(acceleration>shakeThreshold){
                    lastShakeTime=curTime;
                    Toast.makeText(this, "Phone is shaking!!!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onSensorChanged: Vibrate" );
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
