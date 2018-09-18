package odhisha.john.jani.jana.onlinequiz.Model;

/**
 * Created by janardan.d on 20-12-2017.
 */

public class Scorelist {
    String Categoryid;
    int score;


    public Scorelist() {
    }

    public Scorelist(String categoryid, int score) {
        Categoryid = categoryid;
        this.score = score;
    }

    public String getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(String categoryid) {
        Categoryid = categoryid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
