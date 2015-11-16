package mobile.game.scrachx.gfreud.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wang.avi.AVLoadingIndicatorView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mobile.game.scrachx.gfreud.R;
import mobile.game.scrachx.gfreud.models.Game;
import mobile.game.scrachx.gfreud.models.Question;
import mobile.game.scrachx.gfreud.network.JsonRequest;

public class PlayFragment extends Fragment {

    private static View m_rootView = null;
    private static Game m_game;
    private static TextView m_title;
    private static TextView m_points;
    private static TextView m_try;
    private static EditText m_ans;
    private static List<String> m_list;
    private static List<String> list_cut = new ArrayList<>();
    private Button m_check;
    private Button m_but_ans;

    public PlayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        m_rootView = inflater.inflate(R.layout.fragment_play, container, false);

        startProgress();

        m_title = (TextView) m_rootView.findViewById(R.id.textTitleQuestion);
        m_points = (TextView) m_rootView.findViewById(R.id.textPoint);
        m_ans = (EditText) m_rootView.findViewById(R.id.editTextAns);
        m_check = (Button) m_rootView.findViewById(R.id.buttonCheck);
        m_try = (TextView) m_rootView.findViewById(R.id.textTry);
        m_but_ans = (Button) m_rootView.findViewById(R.id.buttonAns);

        m_game = getArguments().getParcelable("Game");

        if (m_game != null) {
            updateTry();
        }

        getAnswersFirstTime();

        m_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                m_ans.setText("");
            }
        });

        /* TODO button ans */
        m_but_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                        .title("Answers list")
                        .content(list_cut.toString())
                        .neutralText(R.string.Dialog_Button_Ok);

                MaterialDialog dialog = builder.build();
                dialog.setOwnerActivity(getActivity());
                dialog.show();
            }
        });

        return m_rootView;
    }


    public void getAnswersFirstTime() {
        try {
            String categ;
            Question quest = null;

            if (m_game != null) {
                categ = m_game.getM_categ().name().substring(0,1) + m_game.getM_categ().name().substring(1).toLowerCase();
                quest = Question.getRandomQuestion(categ);
                m_game.setM_question(quest);
            }
            if (quest != null) {
                m_title.setText(quest.getQuestion_text());
                JsonRequest.runApiSuggest(quest.getQuestion_text(), quest.getLang(), getActivity());
            } else {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                        .title(R.string.Dialog_Error_Title)
                        .content("Cannot found a question for this category. Please contact the developer.")
                        .positiveText(R.string.Dialog_Button_Ok)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MainActivityFragment mainFragment = new MainActivityFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.contentFragment, mainFragment).addToBackStack(null).commit();
                            }
                        });

                MaterialDialog dialog = builder.build();
                dialog.setOwnerActivity(getActivity());
                dialog.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAnswers(Activity activity) {
        try {
            String categ;
            Question quest = null;
            if (m_game != null) {
                categ = m_game.getM_categ().name().substring(0,1) + m_game.getM_categ().name().substring(1).toLowerCase();
                quest = Question.getRandomQuestion(categ);
                m_title.setText(quest.getQuestion_text());
                m_game.setM_question(quest);
                JsonRequest.runApiSuggest(quest.getQuestion_text(), quest.getLang(), activity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initRound(final List<String> list, Activity activity) {
        m_list = list;
        list_cut.clear();
        for(int i = 0; i < 5; i++) {
            list_cut.add(m_list.get(i));
        }
        if (m_list.size() < 5) {
            m_game.setM_numberAnswer(list.size());
        } else {
            m_game.setM_numberAnswer(5);
        }
        m_game.setM_numberTry(3);
        m_game.setM_numberMaxTry(3);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stopProgress();
                updateTitle();
                updatePoints();
                updateTry();
            }
        });
    }

    public static void startProgress() {
        AVLoadingIndicatorView progress = (AVLoadingIndicatorView) m_rootView.findViewById(R.id.avloadingIndicatorView);
        LinearLayout layout = (LinearLayout) m_rootView.findViewById(R.id.layoutPlay);
        RelativeLayout rLayout = (RelativeLayout) m_rootView.findViewById(R.id.rlayout);
        RelativeLayout rbLayout = (RelativeLayout) m_rootView.findViewById(R.id.rblayout);
        progress.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);
        rLayout.setVisibility(View.INVISIBLE);
        rbLayout.setVisibility(View.INVISIBLE);
    }

    public static void stopProgress(){
        AVLoadingIndicatorView progress = (AVLoadingIndicatorView) m_rootView.findViewById(R.id.avloadingIndicatorView);
        LinearLayout layout = (LinearLayout) m_rootView.findViewById(R.id.layoutPlay);
        RelativeLayout rLayout = (RelativeLayout) m_rootView.findViewById(R.id.rlayout);
        RelativeLayout rbLayout = (RelativeLayout) m_rootView.findViewById(R.id.rblayout);
        progress.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);
        rLayout.setVisibility(View.VISIBLE);
        rbLayout.setVisibility(View.VISIBLE);
    }

    public static void updateTitle() {
        m_title.setText(m_game.getM_question().getQuestion_text());
    }

    public static void updatePoints() {
        m_points.setText(String.valueOf(m_game.getM_points()));
    }

    public static void updateTry() {
        String txt = String.valueOf(m_game.getM_numberTry()) + " / " + String.valueOf(m_game.getM_numberMaxTry());
        m_try.setText(txt);
    }

    public void checkAnswer() {
        String ansS = m_game.getM_question().getQuestion_text().toLowerCase() + " " +  m_ans.getText().toString();
        boolean isEquals = false;
        for(int i = 0; i < m_game.getM_numberAnswer(); i++) {
            if(ansS.trim().equals(m_list.get(i).trim())) {
                m_game.setM_points(m_game.getM_points() + Game.MAXPOINTS - i*1000);
                //appendToLayoutAnswers(ansS);
                updatePoints();
                isEquals = true;
            }
        }

        if(!isEquals) {
            m_game.setM_numberTry(m_game.getM_numberTry() - 1);
            if(m_game.getM_numberTry() > 0) {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                        .title(R.string.Dialog_Answer_No)
                        .content("Wrong answer! Try left : " + m_game.getM_numberTry())
                        .neutralText(R.string.Dialog_Button_Ok)
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                updateTry();
                            }
                        });

                MaterialDialog dialog = builder.build();
                dialog.setOwnerActivity(getActivity());
                dialog.show();
            } else {
                PlayFragment.startProgress();
                m_game.setM_points(0);
                getAnswers(getActivity());
                MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                        .title(R.string.Dialog_Answer_No)
                        .content("You loose! Try again!")
                        .neutralText(R.string.Dialog_Button_Ok);

                MaterialDialog dialog = builder.build();
                dialog.setOwnerActivity(getActivity());
                dialog.show();
            }

        } else {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                    .title(R.string.Dialog_Correct_Answer)
                    .content("You're right! The answer is '" + ansS + "'.")
                    .neutralText(R.string.Dialog_Button_Ok)
                    .onNeutral(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            updateTry();
                            updatePoints();
                        }
                    });

            MaterialDialog dialog = builder.build();
            dialog.setOwnerActivity(getActivity());
            dialog.show();
        }
    }

    public void appendToLayoutAnswers(String answer) {
        TextView ansText = new TextView(m_rootView.getContext());
        ansText.setTextSize(20);
        ansText.setTextColor(getResources().getColor(R.color.grey_400));
        ansText.setBackground(getResources().getDrawable(R.drawable.textview_full));
        ansText.setText(answer);
        LinearLayout layout = (LinearLayout) m_rootView.findViewById(R.id.layoutPlay);
        layout.addView(ansText);
    }


}
