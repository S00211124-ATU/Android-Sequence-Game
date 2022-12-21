package gameproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class gameoverActivity extends AppCompatActivity {

    TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        tvPoints = findViewById(R.id.tvPoints);

        Bundle extras = getIntent().getExtras();
        int finalPoints = extras.getInt("points");

        tvPoints.setText("Final Points: " + finalPoints);
    }

    public void playAgain(View view)
    {
        Intent i = new Intent(this, PlayScreen.class);
        startActivity(i);
    }

    public void Scoreboard(View view)
    {
        Intent i = new Intent(this, highScoreScreen.class);
        startActivity(i);
    }
}