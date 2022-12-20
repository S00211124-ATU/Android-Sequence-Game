package gameproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class highScoreScreen extends AppCompatActivity {

    ListView lvPoints;
    TextView tvPoints2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        lvPoints = findViewById(R.id.lvPoints);
        tvPoints2 = findViewById(R.id.tvPoints2);

        Bundle extras = getIntent().getExtras();
        int Points = extras.getInt("points");

        tvPoints2.setText("" + Points);

    }
}