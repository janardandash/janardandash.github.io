package odhisha.john.jani.jana.onlinequiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import odhisha.john.jani.jana.onlinequiz.Model.Common;

public class GameWon extends AppCompatActivity {
    TextView Scoretext,Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);
        TextView Congratulation=(TextView)findViewById(R.id.Congratulation);
        TextView winner=(TextView)findViewById(R.id.winner);
         Scoretext=(TextView)findViewById(R.id.Scoretext);
         Score=(TextView)findViewById(R.id.Score);

        Score.setText(String.valueOf(Common.Totalscore));

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        Congratulation.setTypeface(typeface);
        winner.setTypeface(typeface);
        Score.setTypeface(typeface);
    }
    //This is onclick listener for button
    //it will navigate from this activity to MainGameActivity
    public void PlayAgain(View view) {
        Intent intent = new Intent(GameWon.this, ListCategory.class);
        startActivity(intent);
        finish();
    }
}
