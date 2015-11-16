package mobile.game.scrachx.gfreud.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

import mobile.game.scrachx.gfreud.R;
import mobile.game.scrachx.gfreud.network.JsonRequest;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        try {
            JsonRequest.runFetchQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
