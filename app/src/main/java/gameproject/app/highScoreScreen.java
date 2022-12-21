package gameproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

       // Bundle extras = getIntent().getExtras();
       // int Points = extras.getInt("points");

      //  tvPoints2.setText("" + Points);

        int Points = getIntent().getIntExtra("points", 0);
        tvPoints2.setText(String.valueOf(Points));

        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();     // empty table if required

        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");
        db.addHiScore(new HiScore("20 OCT 2020", "Harry2678", 25));
        db.addHiScore(new HiScore("28 OCT 2020", "Yourmom96", 16));
        db.addHiScore(new HiScore("20 NOV 2020", "WhoAsked", 15));
        db.addHiScore(new HiScore("20 NOV 2020", "N0b0dy", 14));
        db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 10));


        List<HiScore> top5 = db.getTopFiveScores();

        top5 = db.getTopFiveScores();
        List<String> PointsString;
        PointsString = new ArrayList<>();

        for (HiScore hiScore : top5)
        {
            PointsString.add(hiScore.getScore() + "\t      " + hiScore.getPlayer_name());
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, PointsString);
        lvPoints.setAdapter(itemsAdapter);

    }

    public void PlayMore(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}