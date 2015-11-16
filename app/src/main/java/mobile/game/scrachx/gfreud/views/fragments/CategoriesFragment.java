package mobile.game.scrachx.gfreud.views.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import mobile.game.scrachx.gfreud.R;
import mobile.game.scrachx.gfreud.models.Game;

public class CategoriesFragment extends Fragment {

    public CategoriesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        final Game game = getArguments().getParcelable("Game");
        final Bundle extras = new Bundle();

        Button buttonQuestions = (Button) rootView.findViewById(R.id.buttonQuestions);
        Button buttonCulture = (Button) rootView.findViewById(R.id.buttonCulture);
        Button buttonPeople = (Button) rootView.findViewById(R.id.buttonPeople);
        Button buttonSport = (Button) rootView.findViewById(R.id.buttonSport);
        Button buttonNames = (Button) rootView.findViewById(R.id.buttonNames);

        buttonQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game != null) {
                    game.setM_categ(Game.Categories.QUESTIONS);
                    extras.putParcelable("Game", game);
                    PlayFragment playFragment = new PlayFragment();
                    playFragment.setArguments(extras);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, playFragment).addToBackStack(null).commit();
                }
            }
        });

        buttonCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game != null) {
                    game.setM_categ(Game.Categories.CULTURE);
                    extras.putParcelable("Game", game);
                    PlayFragment playFragment = new PlayFragment();
                    playFragment.setArguments(extras);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, playFragment).addToBackStack(null).commit();
                }
            }
        });

        buttonPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game != null) {
                    game.setM_categ(Game.Categories.PEOPLE);
                    extras.putParcelable("Game", game);
                    PlayFragment playFragment = new PlayFragment();
                    playFragment.setArguments(extras);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, playFragment).addToBackStack(null).commit();
                }
            }
        });

        buttonSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game != null) {
                    game.setM_categ(Game.Categories.SPORT);
                    extras.putParcelable("Game", game);
                    PlayFragment playFragment = new PlayFragment();
                    playFragment.setArguments(extras);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, playFragment).addToBackStack(null).commit();
                }
            }
        });

        buttonNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game != null) {
                    game.setM_categ(Game.Categories.NAMES);
                    extras.putParcelable("Game", game);
                    PlayFragment playFragment = new PlayFragment();
                    playFragment.setArguments(extras);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, playFragment).addToBackStack(null).commit();
                }
            }
        });

        return rootView;
    }

}
