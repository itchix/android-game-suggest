package mobile.game.scrachx.gfreud.helpers;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.container.JSONArrayModel;
import com.raizlabs.android.dbflow.structure.container.JSONModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import mobile.game.scrachx.gfreud.models.Question;

/**
 * Created by scotscriven on 16/10/15.
 */
public class Parser {

    public static List<String> getSugestionsApi(String json) {
        List<String> list = new ArrayList<>();

        try {
            JSONTokener jsonTokener = new JSONTokener(json);
            Object value = jsonTokener.nextValue();
            if (value instanceof JSONArray) {
                JSONArray main = (JSONArray) value;
                JSONArray questionAll = main.getJSONArray(1);
                for(int i = 0; i < questionAll.length(); i++) {
                    String suggest = questionAll.getString(i);
                    list.add(suggest);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void getQuestions(String json) {
        try {
            JSONArray jsonO = new JSONArray(json);
            JSONArrayModel<Question> jsonModel = new JSONArrayModel<>(jsonO, Question.class);
            jsonModel.save();

            List<Question> questionsFromDB = new Select().from(Question.class).queryList();
            Log.d("DATABASE", questionsFromDB.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
