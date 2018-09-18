package odhisha.john.jani.jana.onlinequiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

import odhisha.john.jani.jana.onlinequiz.Model.Common;
import odhisha.john.jani.jana.onlinequiz.Model.OnlineQuestion;

public class StartActivity extends AppCompatActivity {
Button playGame;
    FirebaseDatabase database;
    DatabaseReference question;
    String instance;
    TextView tQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        playGame=(Button)findViewById(R.id.playGame);
        tQ=(TextView)findViewById(R.id.tQ);
        database=FirebaseDatabase.getInstance();
        Bundle b=getIntent().getExtras();
        instance=b.getString("Name").trim();
        Common.categoryName=instance;
        tQ.setText(instance);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        tQ.setTypeface(typeface);

        question=database.getReference(instance);
        loadQustion();
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadQustion() {

        if(Common.questionlist.size()>0)
            Common.questionlist.clear();
        question.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                    OnlineQuestion question = postSnapshot.getValue(OnlineQuestion.class);
                    Common.questionlist.add(question);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Collections.shuffle(Common.questionlist);

    }
}
