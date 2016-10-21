package winep.ir.twocon.Presenter.QuestionPage;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Question;
import winep.ir.twocon.R;

/**
 * Created by ShaisteS on 10/17/2016.
 */
public class QuestionPagerAdapter  extends PagerAdapter {

    private ArrayList<Question> allQuestion;
    private Activity activity;
    private View viewLayout;

    public QuestionPagerAdapter(ArrayList<Question> questions,Activity activity) {
        allQuestion = questions;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return allQuestion.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewLayout = inflater.inflate(R.layout.question_activity_pager, container, false);

        final CardView cardView=(CardView)viewLayout.findViewById(R.id.front_card);
        final FrameLayout cardBack=(FrameLayout) viewLayout.findViewById(R.id.back_card);
        cardBack.setVisibility(View.GONE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardView.setVisibility(View.GONE);
                cardBack.setVisibility(View.VISIBLE);
            }
        });
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView.setVisibility(View.VISIBLE);
                cardBack.setVisibility(View.GONE);
            }
        });

        final TextView txtQuestionDescription=(TextView)viewLayout.findViewById(R.id.txt_description);
        txtQuestionDescription.setVisibility(View.GONE);
        ImageButton btnQuestionDescriptionShow=(ImageButton)viewLayout.findViewById(R.id.question_description);
        final boolean[] questionDescriptionShowStatus = {false};
        btnQuestionDescriptionShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionDescriptionShowStatus[0]){
                    txtQuestionDescription.setVisibility(View.VISIBLE);
                    questionDescriptionShowStatus[0] =!questionDescriptionShowStatus[0];
                }
                else if (questionDescriptionShowStatus[0]){
                    txtQuestionDescription.setVisibility(View.GONE);
                    questionDescriptionShowStatus[0] =!questionDescriptionShowStatus[0];
                }
            }
        });

        container.addView(viewLayout);
        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

    }

    public void slideToRight(View view){
        TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
        animate.setDuration(5000);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

}


