package mobile.game.scrachx.gfreud;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by scotscriven on 16/10/15.
 */
public class MyApplication extends Application {

    private static Context m_Context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        m_Context = this.getApplicationContext();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/BUBBCB__.TTF")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    public static Context getAppContext(){
        return m_Context;
    }

}
