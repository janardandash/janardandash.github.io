package odhisha.john.jani.jana.onlinequiz;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import info.hoang8f.widget.FButton;
import odhisha.john.jani.jana.onlinequiz.Database.DatabaseHelper;
import odhisha.john.jani.jana.onlinequiz.Model.Common;
import odhisha.john.jani.jana.onlinequiz.Model.OnlineQuestion;

public class MainGameActivity extends AppCompatActivity {
    Button buttonA, buttonB, buttonC, buttonD;
    TextView questionText, onlineQuestion, timeText, resultText, coinText;
    OnlineQuestion currentQuestion;
    List<OnlineQuestion> list;
    int qid = 0;
    int timeValue = 20;
    int coinValue = 0;
    int errorCount=0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;

  MediaPlayer mp,cmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        //Initializing variables
        questionText = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        onlineQuestion = (TextView) findViewById(R.id.triviaQuizText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultText = (TextView) findViewById(R.id.resultText);
        coinText = (TextView) findViewById(R.id.coinText);
         mp = MediaPlayer.create(this, R.raw.wrongbuzzer);
        cmp = MediaPlayer.create(this, R.raw.correctsound);




        //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        onlineQuestion.setTypeface(sb);
        questionText.setTypeface(tb);
      buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);
        Collections.shuffle(Common.questionlist);

        //currentQuestion will hold the que, 4 option and ans for particular id
        //currentQuestion = Common.questionlist.get(qid);

        //countDownTimer
        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                timeText.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    //Since user is out of time setText as time up
                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                mp.start();
                timeUp();
            }
        }.start();

        //This method will set the que and four options
        if(Common.questionlist.size()!=0) {
            updateQueAndOptions();
        }
        else{
            Intent intent =new Intent (MainGameActivity.this,ListCategory.class);
            startActivity(intent);
        }

    }


    public void updateQueAndOptions() {

        //This method will setText for que and options
        questionText.setText(Common.questionlist.get(qid).getQuestion());
        buttonA.setText(Common.questionlist.get(qid).getOpta());
        buttonB.setText(Common.questionlist.get(qid).getOptb());
        buttonC.setText(Common.questionlist.get(qid).getOptc());
        buttonD.setText(Common.questionlist.get(qid).getOptd());


        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        //set the value of coin text
        coinText.setText(String.valueOf(coinValue));
        //Now since user has ans correct increment the coinvalue
        coinValue++;

    }

    public void buttonA(View view) {
        //compare the option with the ans if yes then make button color green


        if (Common.questionlist.get(qid).getOpta().equals(Common.questionlist.get(qid).getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //Check if user has not exceeds the que limit
            if (qid < Common.questionlist.size() - 1) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();

                //Show the dialog that ans is correct
                cmp.start();
                correctDialog();
            }
            //If user has exceeds the que limit just navigate him to GameWon activity
            else {
                cmp.start();
                gameWon();

            }
        }
        //User ans is wrong then just navigate him to the PlayAgain activity
        else {
            if(errorCount<2){
                mp.start();
                incorrectDialog();
            }
            else
            gameLostPlayAgain();

        }
    }
    public void buttonB(View view) {

        if (Common.questionlist.get(qid).getOptb().equals(Common.questionlist.get(qid).getAnswer())) {
            buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < Common.questionlist.size() - 1) {
                disableButton();
                cmp.start();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            if(errorCount<2){
                mp.start();
                incorrectDialog();
            }
            else
                gameLostPlayAgain();
        }

    }

    public void buttonC(View view) {

        if (Common.questionlist.get(qid).getOptc().equals(Common.questionlist.get(qid).getAnswer())) {
            buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < Common.questionlist.size() - 1) {
                disableButton();
                cmp.start();
                correctDialog();
            } else {
                gameWon();
            }
        } else {

            if(errorCount<2){
                mp.start();
                incorrectDialog();
            }
            else
                gameLostPlayAgain();
        }

    }

    public void buttonD(View view) {
        if (Common.questionlist.get(qid).getOptd().equals(Common.questionlist.get(qid).getAnswer())) {
            buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < Common.questionlist.size() - 1) {
                disableButton();
                cmp.start();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            if(errorCount<2){
                mp.start();
                incorrectDialog();
            }
            else
                gameLostPlayAgain();
        }

    }

    //This method will navigate from current activity to GameWon
    public void gameWon() {
        Common.Totalscore=coinValue;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("CategoryName",Common.categoryName );
        params.put("Score", Common.Totalscore);
        if (DatabaseHelper.getDbHelperSingletonInstance(
                getApplicationContext()).insert("HHT_SCORE",
                params))
            Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }

    //This method is called when user ans is wrong
    //this method will navigate user to the activity PlayAgain
    public void gameLostPlayAgain() {
        Common.Totalscore=coinValue-1;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("CategoryName",Common.categoryName );
        params.put("Score", Common.Totalscore);
        if (DatabaseHelper.getDbHelperSingletonInstance(
                getApplicationContext()).insert("HHT_SCORE",
                params))
            Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();

    }

    //This method is called when time is up
    //this method will navigate user to the activity Time_Up
    public void timeUp() {
        Common.Totalscore=coinValue-1;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("CategoryName",Common.categoryName );
        params.put("Score", Common.Totalscore);
        if (DatabaseHelper.getDbHelperSingletonInstance(
                getApplicationContext()).insert("HHT_SCORE",
                params))
            Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Time_Up.class);
        startActivity(intent);
        finish();
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();

    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();

    }

    //This will pause the time
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();

    }


    //This dialog is show to the user after he ans correct
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);

        //Setting type faces
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
               // currentQuestion = Common.questionlist.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }
    public void incorrectDialog() {

        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.errorchance);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        TextView wrongt= (TextView) dialogCorrect.findViewById(R.id.wrongt);
        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);

        //Setting type faces
        correctText.setTypeface(sb);
        wrongt.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                errorCount++;
                coinValue--;
                //get the que and 4 option and store in the currentQuestion
                // currentQuestion = Common.questionlist.get(qid);
                //Now this method will set the new que and 4 options
                if(qid < Common.questionlist.size() - 1)
                updateQueAndOptions();
                if(qid == Common.questionlist.size())
                    gameWon();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }


    //This method will make button color white again since our one button color was turned green
    public void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
    }

    //This method will disable all the option button
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }
}
