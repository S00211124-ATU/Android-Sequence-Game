package gameproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class TiltScreen extends AppCompatActivity implements SensorEventListener{

    Button rightButton, upButton, downButton, leftButton;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilt_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle extras = getIntent().getExtras();
        int gamesequenceB = extras.getInt("numbers");
        upButton = findViewById(R.id.upButton);
        rightButton = findViewById(R.id.rightButton);
        downButton = findViewById(R.id.downButton);
        leftButton = findViewById(R.id.leftButton);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x, y, z;
        x = Math.abs(sensorEvent.values[0]);
        y = sensorEvent.values[1];
        z = Math.abs(sensorEvent.values[2]);

        if (y < -2) {
            leftButton.performClick();
            leftButton.setPressed(true);
            leftButton.invalidate();
            leftButton.setPressed(false);
            leftButton.invalidate();
        } else if (y > 2) {
            rightButton.performClick();
            rightButton.setPressed(true);
            rightButton.invalidate();
            rightButton.setPressed(false);
            rightButton.invalidate();
        } else if (x > 9)
        {
            upButton.performClick();
            upButton.setPressed(true);
            upButton.invalidate();
            upButton.setPressed(false);
            upButton.invalidate();
         //  Toast.makeText(getApplicationContext(), "Down", Toast.LENGTH_SHORT);
        }
        else if(x<3)
        {
            downButton.performClick();
            downButton.setPressed(true);
            downButton.invalidate();
            downButton.setPressed(false);
            downButton.invalidate();
           // Toast.makeText(getApplicationContext(), "Up", Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}