package odhisha.john.jani.jana.onlinequiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;
import odhisha.john.jani.jana.onlinequiz.Model.Common;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Time_Up extends AppCompatActivity {
    FButton playAgainButton;
    TextView timeUpText,Scoretext,Score;
    int strScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__up);
        //Initialize
        playAgainButton = (FButton)findViewById(R.id.playAgainButton);
        timeUpText = (TextView)findViewById(R.id.timeUpText);
        Scoretext = (TextView)findViewById(R.id.Scoretext);
        Score = (TextView)findViewById(R.id.Score);
        Score.setText(String.valueOf(Common.Totalscore));

        //play again button onclick listener
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time_Up.this,ListCategory.class);
                startActivity(intent);
                finish();



            }
        });


        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        timeUpText.setTypeface(typeface);
        Score.setTypeface(typeface);
        playAgainButton.setTypeface(typeface);

        Scoretext.setTypeface(typeface);
    }

}
