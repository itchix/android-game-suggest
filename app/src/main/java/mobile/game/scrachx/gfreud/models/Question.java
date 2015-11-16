package mobile.game.scrachx.gfreud.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by scotscriven on 16/10/15.
 */
@Table(databaseName = GameDB.NAME)
@ModelContainer
public class Question extends BaseModel implements Parcelable {

    @Column
    @PrimaryKey
    private int id;

    @Column
    @Unique
    private String question_text;

    @Column
    private String category;

    @Column
    private String lang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static Question getRandomQuestion(String categ) {
        return new Select()
                .from(Question.class)
                .where(Condition.column(Question$Table.CATEGORY).is(categ))
                .orderBy("RANDOM()")
                .querySingle();
    }

    @Override
    public String toString() {
        return "(ID : " + id + ", QUESTION_TEXT : " + question_text + ", LANG : " + lang + ", CATEGORY : " + category + ")";
    }

    public Question() {

    }

    protected Question(Parcel in) {
        id = in.readInt();
        question_text = in.readString();
        lang = in.readString();
        category = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question_text);
        dest.writeString(lang);
        dest.writeString(category);
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
}
