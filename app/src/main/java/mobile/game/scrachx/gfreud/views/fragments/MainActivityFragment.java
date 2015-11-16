package mobile.game.scrachx.gfreud.views.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import mobile.game.scrachx.gfreud.R;
import mobile.game.scrachx.gfreud.models.Game;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button buttonPlay = (Button) rootView.findViewById(R.id.buttonPlay);
        Button buttonSettings = (Button) rootView.findViewById(R.id.buttonSettings);
        Button buttonRank = (Button) rootView.findViewById(R.id.buttonRank);
        Button buttonExit = (Button) rootView.findViewById(R.id.buttonExit);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = new Game();
                Bundle extras = new Bundle();
                extras.putParcelable("Game", game);
                CategoriesFragment categoriesFragment = new CategoriesFragment();
                categoriesFragment.setArguments(extras);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, categoriesFragment).addToBackStack(null).commit();
            }
        });

        buttonExit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
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
                        dialog.setOwnerActivity(getActivity());
                        dialog.show();
                    }
                }
        );

        return  rootView;
    }
}
