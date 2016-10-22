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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rey.material.widget.Button;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Question;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

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
        final TextView txtQuestionNumber=(TextView)viewLayout.findViewById(R.id.txt_question_number);
        Utilities.getInstance().setFontTextView(activity,txtQuestionNumber);
        final TextView txtQuestionDescription=(TextView)viewLayout.findViewById(R.id.txt_description);
        txtQuestionDescription.setVisibility(View.GONE);
        final FloatingActionButton fabAnswerVoice=(FloatingActionButton)viewLayout.findViewById(R.id.answer_voice);
        fabAnswerVoice.setSize(FloatingActionButton.SIZE_MINI);
        final FloatingActionButton fabQuestionMark=(FloatingActionButton)viewLayout.findViewById(R.id.question_mark);
        fabQuestionMark.setSize(FloatingActionButton.SIZE_MINI);
        final FloatingActionButton fabAnswerDescription=(FloatingActionButton)viewLayout.findViewById(R.id.answer_description);
        fabAnswerDescription.setSize(FloatingActionButton.SIZE_MINI);
        final Button btnKnowNumber=(Button)viewLayout.findViewById(R.id.btn_know_number);
        final Button btnNoKnowNumber=(Button)viewLayout.findViewById(R.id.btn_no_know_number);
        final Button btnKnow=(Button)viewLayout.findViewById(R.id.btn_know);
        final Button btnoNOKnow=(Button)viewLayout.findViewById(R.id.btn_no_know);


        btnKnowNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,activity.getString(R.string.know_number_question_title)+"2",Toast.LENGTH_SHORT).show();
            }
        });

        btnNoKnowNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,activity.getString(R.string.no_Know_question_title)+"12",Toast.LENGTH_SHORT).show();
            }
        });




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


        final boolean[] questionDescriptionShowStatus = {false};
        fabAnswerDescription.setOnClickListener(new View.OnClickListener() {
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


