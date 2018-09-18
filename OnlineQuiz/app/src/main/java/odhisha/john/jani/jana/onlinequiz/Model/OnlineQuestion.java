package odhisha.john.jani.jana.onlinequiz.Model;

/**
 * Created by janardan.d on 14-12-2017.
 */

public class OnlineQuestion {


    private String question;
    private String opta;
    private String optb;
    private String optc;
    private String optd;
    private String answer;

    public OnlineQuestion() {
    }

    public OnlineQuestion(String question, String opta, String optb, String optc, String optd, String answer) {
        this.question = question;
        this.opta = opta;
        this.optb = optb;
        this.optc = optc;
        this.optd = optd;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpta() {
        return opta;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public String getOptb() {
        return optb;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public String getOptc() {
        return optc;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public String getOptd() {
        return optd;
    }

    public void setOptd(String optd) {
        this.optd = optd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
