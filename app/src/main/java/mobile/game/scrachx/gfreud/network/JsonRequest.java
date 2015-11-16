package mobile.game.scrachx.gfreud.network;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.List;

import mobile.game.scrachx.gfreud.MyApplication;
import mobile.game.scrachx.gfreud.R;
import mobile.game.scrachx.gfreud.helpers.Parser;
import mobile.game.scrachx.gfreud.views.fragments.PlayFragment;

public class JsonRequest extends Activity{

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String SEARCH_PATTERN = "http://www.google.com/complete/search?client=chrome";
    public static final String SERVER_QUESTIONS = "http://scrachx.hol.es/export/gsuggestjson/fr";

    public static void runApiSuggest(String query, String lang, final Activity activity) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String url = SEARCH_PATTERN + "&q=" + query + "&hl=" + lang;
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("JSON", "ERROR");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                List<String> l =  Parser.getSugestionsApi(json);
                PlayFragment.initRound(l, activity);

                Log.d("JSON API", json);
                Log.d("LIST", l.toString());
            }

        });
    }

    public static void runFetchQuestions() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(SERVER_QUESTIONS)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("JSON", "ERROR");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Log.d("JSON", json);
                Parser.getQuestions(json);
            }

        });
    }

}
