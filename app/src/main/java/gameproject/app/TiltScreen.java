package gameproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

public class TiltScreen extends AppCompatActivity implements SensorEventListener{

    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;
    Button rightButton, upButton, downButton, leftButton;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    int[] playedSequence = new int[120];
    int counter = 0, playerArrayIndex = 0, centeredCounter = 1, arrayIndex;
    Boolean Playing;
    int[] gameSequenceB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilt_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle extras = getIntent().getExtras();
        gameSequenceB = extras.getIntArray("numbers");
        arrayIndex = extras.getInt("arrayIndex");
        Playing = extras.getBoolean("playing");

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

        if (y < -3) {
            leftButton.performClick();
            leftButton.setPressed(true);
            leftButton.invalidate();
            leftButton.setPressed(false);
            leftButton.invalidate();

            if(centeredCounter > counter && Playing == true) {
                counter++;
                playedSequence[playerArrayIndex++] = BLUE;
                if (counter == 4) {
                    Playing = false;

                    if (Arrays.equals(gameSequenceB, playedSequence) == true) {
                        counter = 0;
                        centeredCounter = 1;
                        Intent i = new Intent(TiltScreen.this, PlayScreen.class);
                        startActivity(i);
                    } else {
                        int finalPoints = 0;

                        for (int i = 0; i < arrayIndex; i++) {
                            if (playedSequence[i] == gameSequenceB[i]) {
                                finalPoints++;
                                Log.d("game sequence", String.valueOf(playedSequence[i]));
                            }
                        }

                        Intent i = new Intent(TiltScreen.this, gameoverActivity.class);
                        i.putExtra("points", finalPoints);
                        startActivity(i);
                    }
                }
            }

        } else if (y > 3) {
            rightButton.performClick();
            rightButton.setPressed(true);
            rightButton.invalidate();
            rightButton.setPressed(false);
            rightButton.invalidate();

            if(centeredCounter > counter && Playing == true) {
                counter++;
                playedSequence[playerArrayIndex++] = YELLOW;
                if (counter == 4) {
                    Playing = false;

                    if (Arrays.equals(gameSequenceB, playedSequence) == true) {
                        counter = 0;
                        centeredCounter = 1;
                        Intent i = new Intent(TiltScreen.this, PlayScreen.class);
                        startActivity(i);
                    } else {
                        int finalPoints = 0;

                        for (int i = 0; i < arrayIndex; i++) {
                            if (playedSequence[i] == gameSequenceB[i]) {
                                finalPoints++;
                                Log.d("game sequence", String.valueOf(playedSequence[i]));
                            }
                        }


                        Intent i = new Intent(TiltScreen.this, gameoverActivity.class);
                        i.putExtra("points", finalPoints);
                        startActivity(i);
                    }
                }
            }
        } else if (x > 9)
        {
            downButton.performClick();
            downButton.setPressed(true);
            downButton.invalidate();
            downButton.setPressed(false);
            downButton.invalidate();

            if(centeredCounter > counter & Playing == true) {
                counter++;
                playedSequence[playerArrayIndex++] = RED;
                if (counter == 4) {
                    Playing = false;

                    if (Arrays.equals(gameSequenceB, playedSequence) == true) {
                        counter = 0;
                        centeredCounter = 1;
                        Intent i = new Intent(TiltScreen.this, PlayScreen.class);
                        startActivity(i);
                    } else {
                        int finalPoints = 0;

                        for (int i = 0; i < arrayIndex; i++) {
                            if (playedSequence[i] == gameSequenceB[i]) {
                                finalPoints++;
                                Log.d("game sequence", String.valueOf(playedSequence[i]));
                            }
                        }

                        Intent i = new Intent(TiltScreen.this, gameoverActivity.class);
                        i.putExtra("points", finalPoints);
                        startActivity(i);
                    }
                }
            }
        }
        else if(x<3)
        {
            upButton.performClick();
            upButton.setPressed(true);
            upButton.invalidate();
            upButton.setPressed(false);
            upButton.invalidate();

            if(centeredCounter > counter && Playing == true) {
                counter++;
                playedSequence[playerArrayIndex++] = GREEN;
                if (counter == 4) {
                    Playing = false;

                    if (Arrays.equals(gameSequenceB, playedSequence) == true) {
                        counter = 0;
                        centeredCounter = 1;
                        Intent i = new Intent(TiltScreen.this, PlayScreen.class);
                        startActivity(i);
                    } else {
                        int finalPoints = 0;

                        for (int i = 0; i < arrayIndex; i++) {
                            if (playedSequence[i] == gameSequenceB[i]) {
                                finalPoints++;
                                Log.d("game sequence", String.valueOf(playedSequence[i]));
                            }
                        }

                        Intent i = new Intent(TiltScreen.this, gameoverActivity.class);
                        i.putExtra("points", finalPoints);
                        startActivity(i);
                    }
                }
            }


        }
        else
        {
            if (counter >= centeredCounter && Playing == true)
            {
                centeredCounter++;
            }
        }

    }

    /*public void Order() {

        Playing = false;

        if (Arrays.equals(gameSequenceB, playedSequence) == true)
        {
            counter = 0;
            Intent i = new Intent(TiltScreen.this, PlayScreen.class);
            startActivity(i);
        } else {
            int finalPoints = 0;

            for (int i = 0; i < arrayIndex; i++) {
                if (playedSequence[i] == gameSequenceB[i])
                {
                    finalPoints++;
                }
            }

            Intent i = new Intent(TiltScreen.this, gameoverActivity.class);
            i.putExtra("points", finalPoints);
            startActivity(i);
        }
    }*/

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}