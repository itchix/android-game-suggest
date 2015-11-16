package mobile.game.scrachx.gfreud.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

    public static int MAXPOINTS = 10000;

    private int m_numberTry = 3;
    private int m_numberAnswer = 5;
    private int m_numberMaxTry = 3;
    private int m_points = 0;
    private Categories m_categ;
    private Question m_question;

    public enum Categories {
        SPORT,
        PEOPLE,
        CULTURE,
        NAMES,
        QUESTIONS
    }

    public Game() {

    }

    public int getM_numberTry() {
        return m_numberTry;
    }

    public void setM_numberTry(int m_numberTry) {
        this.m_numberTry = m_numberTry;
    }

    public int getM_numberAnswer() {
        return m_numberAnswer;
    }

    public void setM_numberAnswer(int m_numberAnswer) {
        this.m_numberAnswer = m_numberAnswer;
    }

    public int getM_numberMaxTry() {
        return m_numberMaxTry;
    }

    public void setM_numberMaxTry(int m_numberMaxTry) {
        this.m_numberMaxTry = m_numberMaxTry;
    }

    public Question getM_question() {
        return m_question;
    }

    public void setM_question(Question m_question) {
        this.m_question = m_question;
    }

    public int getM_points() {
        return m_points;
    }

    public void setM_points(int m_points) {
        this.m_points = m_points;
    }

    public Categories getM_categ() {
        return m_categ;
    }

    public void setM_categ(Categories m_categ) {
        this.m_categ = m_categ;
    }

    protected Game(Parcel in) {
        m_numberTry = in.readInt();
        m_numberAnswer = in.readInt();
        m_numberMaxTry = in.readInt();
        m_points = in.readInt();
        m_categ = (Categories) in.readValue(Categories.class.getClassLoader());
        m_question = (Question) in.readValue(Question.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(m_numberTry);
        dest.writeInt(m_numberAnswer);
        dest.writeInt(m_numberMaxTry);
        dest.writeInt(m_points);
        dest.writeValue(m_categ);
        dest.writeValue(m_question);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public String toString() {
        return "CATEGORY : " + m_categ + ", TRY : " + m_numberTry + ", MAXTRY : " + m_numberMaxTry + ", ANSWER : " + m_numberAnswer + ", POINTS : " + m_points + ", QUESTION : " + m_question.toString();
    }
}
