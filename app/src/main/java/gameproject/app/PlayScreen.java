package gameproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PlayScreen extends AppCompatActivity {

    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button redButton, blueButton, yellowButton, greenButton, fb;
    int sequenceCount = 4, n = 0;
    private Object mutex = new Object();
    int[] gameSequence = new int[120];
    int arrayIndex = 0;
    Boolean Playing = false;

    CountDownTimer ct = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        public void onFinish() {

            Playing = true;

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));

          //  startActivity(new Intent(PlayScreen.this, TiltScreen.class));

            Intent i = new Intent(PlayScreen.this, TiltScreen.class);
            i.putExtra("numbers", gameSequence);
            i.putExtra("arrayIndex", arrayIndex);
            startActivity(i);
        }
    };

    public void doPlay(View view) {
        ct.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        redButton = findViewById(R.id.redButton);
        yellowButton = findViewById(R.id.yellowButton);
        greenButton = findViewById(R.id.greenButton);
        blueButton = findViewById(R.id.blueButton);
    }

    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }

    private void oneButton() {
        n = getRandom(sequenceCount);

        switch (n) {
            case 1:
                flashButton(blueButton);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
                flashButton(redButton);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
                flashButton(yellowButton);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
                flashButton(greenButton);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;
        }   // end switch
    }

    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb.setPressed(false);
                        fb.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }

}