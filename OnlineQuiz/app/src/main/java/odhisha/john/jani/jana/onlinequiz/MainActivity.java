package odhisha.john.jani.jana.onlinequiz;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.hoang8f.widget.FButton;


public class MainActivity extends AppCompatActivity {
    FButton playGame,quit,highScore;
    ImageView privacypolicy;
    TextView tQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playGame =(FButton)findViewById(R.id.playGame);
        quit = (FButton) findViewById(R.id.quit);
        highScore = (FButton) findViewById(R.id.highScore);
        tQ = (TextView)findViewById(R.id.tQ);
        privacypolicy=findViewById(R.id.privacypolicy);
        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListCategory.class);
                startActivity(intent);
            }
        });
        //Quit button - This will quit the game
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ScoreShowActivity.class);
                startActivity(intent);
            }
        });
        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
        highScore.setTypeface(typeface);

        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cdn.rawgit.com/janardandash/janardandash.github.io/c1ae00d1/PrivacyPolicy.html"));
                startActivity(browserIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();


    }

}
