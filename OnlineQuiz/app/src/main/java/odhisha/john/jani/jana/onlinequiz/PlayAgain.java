package odhisha.john.jani.jana.onlinequiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import odhisha.john.jani.jana.onlinequiz.Model.Common;

public class PlayAgain extends AppCompatActivity {
    Button playAgain,Tscore;
    TextView wrongAnsText,scoretext;
    int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);
        //Initialize
        playAgain = (Button) findViewById(R.id.playAgainButton);
        wrongAnsText = (TextView)findViewById(R.id.wrongAns);
        Tscore=(Button)findViewById(R.id.Tscore);
        scoretext=(TextView)findViewById(R.id.scoretext);
        Tscore.setText(String.valueOf(Common.Totalscore));


        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, ListCategory.class);
                startActivity(intent);
                finish();
            }
        });

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
       // Tscore.setTypeface(typeface);
        scoretext.setTypeface(typeface);
    }

}

