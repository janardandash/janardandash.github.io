package odhisha.john.jani.jana.onlinequiz;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import odhisha.john.jani.jana.onlinequiz.Database.DatabaseHelper;


public class ScoreShowActivity extends AppCompatActivity {
TextView CAtegory10,Score10,CAtegory1,Score1,CAtegory2,Score2,CAtegory3,Score3,CAtegory4,Score4,
        CAtegory5,Score5,CAtegory6,Score6,CAtegory7,Score7,CAtegory8,Score8,CAtegory9,Score9,triviaQuizText;
    ArrayList<String> list;
    ArrayList<String> list1;
    Typeface tb, sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show);
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText=(TextView)findViewById(R.id.triviaQuizText);
        triviaQuizText.setTypeface(sb);
        CAtegory1=(TextView)findViewById(R.id.CAtegory1);
        CAtegory1.setTypeface(sb);
        CAtegory2=(TextView)findViewById(R.id.CAtegory2);
        CAtegory2.setTypeface(sb);
        CAtegory3=(TextView)findViewById(R.id.CAtegory3);
        CAtegory3.setTypeface(sb);
        CAtegory4=(TextView)findViewById(R.id.CAtegory4);
        CAtegory4.setTypeface(sb);
        CAtegory5=(TextView)findViewById(R.id.CAtegory5);
        CAtegory5.setTypeface(sb);
        CAtegory6=(TextView)findViewById(R.id.CAtegory6);
        CAtegory6.setTypeface(sb);
        CAtegory7=(TextView)findViewById(R.id.CAtegory7);
        CAtegory7.setTypeface(sb);
        CAtegory8=(TextView)findViewById(R.id.CAtegory8);
        CAtegory8.setTypeface(sb);
        CAtegory9=(TextView)findViewById(R.id.CAtegory9);
        CAtegory9.setTypeface(sb);
        CAtegory10=(TextView)findViewById(R.id.CAtegory10);
        CAtegory10.setTypeface(sb);
        Score1=(TextView)findViewById(R.id.Score1);
        Score2=(TextView)findViewById(R.id.Score2);
        Score3=(TextView)findViewById(R.id.Score3);
        Score4=(TextView)findViewById(R.id.Score4);
        Score5=(TextView)findViewById(R.id.Score5);
        Score6=(TextView)findViewById(R.id.Score6);
        Score7=(TextView)findViewById(R.id.Score7);
        Score8=(TextView)findViewById(R.id.Score8);
        Score9=(TextView)findViewById(R.id.Score9);
        Score10=(TextView)findViewById(R.id.Score10);

        DatabaseHelper helper = DatabaseHelper
                .getDbHelperSingletonInstance(getApplicationContext());
         list = helper.Getlist();
         list1=helper.GetlistCategory();

        String [] Scores = list.toArray(new String[list.size()]);
        String [] category = list1.toArray(new String[list1.size()]);

        if(list.size()>0) {
            Score1.setText(String.valueOf(Scores[0]));
            CAtegory1.setText(String.valueOf(category[0]));
        }
        if(list.size()>1) {
            Score2.setText(String.valueOf(Scores[1]));
            CAtegory2.setText(String.valueOf(category[1]));
        }
        if(list.size()>2) {
            Score3.setText(String.valueOf(Scores[2]));
            CAtegory3.setText(String.valueOf(category[2]));
        }
        if(list.size()>3) {

            Score4.setText(String.valueOf(Scores[3]));
            CAtegory4.setText(String.valueOf(category[3]));
        }
        if(list.size()>4) {
            Score5.setText(String.valueOf(Scores[4]));
            CAtegory5.setText(String.valueOf(category[4]));
        }
        if(list.size()>5) {

            Score6.setText(String.valueOf(Scores[5]));
            CAtegory6.setText(String.valueOf(category[5]));
        }
        if(list.size()>6) {
            Score7.setText(String.valueOf(Scores[6]));
            CAtegory7.setText(String.valueOf(category[6]));
        }
        if(list.size()>7) {

            Score8.setText(String.valueOf(Scores[7]));
            CAtegory8.setText(String.valueOf(category[7]));
        }
        if(list.size()>8) {

            Score9.setText(String.valueOf(Scores[8]));
            CAtegory9.setText(String.valueOf(category[8]));
        }
        if(list.size()>9) {

            Score10.setText(String.valueOf(Scores[9]));
            CAtegory10.setText(String.valueOf(category[9]));
        }

    }
}
