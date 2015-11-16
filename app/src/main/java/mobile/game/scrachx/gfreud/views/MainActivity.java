package mobile.game.scrachx.gfreud.views;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import mobile.game.scrachx.gfreud.R;
import mobile.game.scrachx.gfreud.views.fragments.CategoriesFragment;
import mobile.game.scrachx.gfreud.views.fragments.MainActivityFragment;
import mobile.game.scrachx.gfreud.views.fragments.PlayFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        MainActivityFragment mainFragment = new MainActivityFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.contentFragment, mainFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed()
    {
        Fragment f = getFragmentManager().findFragmentById(R.id.contentFragment);

        if (f instanceof MainActivityFragment) {

            MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                    .title(R.string.Dialog_Title_Quit)
                    .content(R.string.Dialog_Content_Quit)
                    .positiveText(R.string.Dialog_Button_Yes)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            dialog.getOwnerActivity().finish();
                        }
                    })
                    .negativeText(R.string.Dialog_Button_No);

            MaterialDialog dialog = builder.build();
            dialog.setOwnerActivity(this);
            dialog.show();

        } else if (f instanceof CategoriesFragment) {

            MainActivityFragment mainFragment = new MainActivityFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, mainFragment).addToBackStack(null).commit();

        } else if (f instanceof PlayFragment) {

            MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                    .title(R.string.Dialog_Title_Exit)
                    .content(R.string.Dialog_Content_Leave_Game)
                    .positiveText(R.string.Dialog_Button_Yes)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            MainActivityFragment mainFragment = new MainActivityFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, mainFragment).addToBackStack(null).commit();
                        }
                    })
                    .negativeText(R.string.Dialog_Button_No);

            MaterialDialog dialog = builder.build();
            dialog.setOwnerActivity(this);
            dialog.show();

        }

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
