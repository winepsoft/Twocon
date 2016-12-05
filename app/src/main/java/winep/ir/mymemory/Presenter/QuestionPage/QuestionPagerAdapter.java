package winep.ir.mymemory.Presenter.QuestionPage;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.Question;
import winep.ir.mymemory.Presenter.ObserverPackage.setClickOnKnowButton;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

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
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewLayout = inflater.inflate(R.layout.question_activity_pager, container, false);

        final CardView cardQuestion=(CardView)viewLayout.findViewById(R.id.question_cart_layout);
        final CardView cardFront=(CardView)viewLayout.findViewById(R.id.front_card);
        final CardView cardBack=(CardView) viewLayout.findViewById(R.id.back_card);
        //final RelativeLayout back_layout=(RelativeLayout)viewLayout.findViewById(R.id.back_layout);
        cardBack.setVisibility(View.GONE);
        final TextView txtQuestionNumber=(TextView)viewLayout.findViewById(R.id.txt_question_number);
        Utilities.getInstance().setFontTextView(activity,txtQuestionNumber);
        final LinearLayout layoutQuestionDescription=(LinearLayout)viewLayout.findViewById(R.id.layout_description);
        layoutQuestionDescription.setVisibility(View.GONE);
        final ImageButton btnAnswerVoice=(ImageButton) viewLayout.findViewById(R.id.answer_voice);
        //fabAnswerVoice.setSize(FloatingActionButton.SIZE_MINI);
        final ImageButton btnAnswerPicture=(ImageButton)viewLayout.findViewById(R.id.answer_picture);
        final ImageButton btnQuestionMark=(ImageButton) viewLayout.findViewById(R.id.question_mark);
        //fabQuestionMark.setSize(FloatingActionButton.SIZE_MINI);
        final ImageButton btnAnswerDescription=(ImageButton)viewLayout.findViewById(R.id.answer_description);
        //btnAnswerDescription.setSize(FloatingActionButton.SIZE_MINI);
        final ImageButton btnQuestionPlayVoice=(ImageButton)viewLayout.findViewById(R.id.question_play_voice);
        //fabQuestionPlayVoice.setSize(FloatingActionButton.SIZE_MINI);
        //final Button btnKnowNumber=(Button)viewLayout.findViewById(R.id.btn_know_number);
        //Utilities.getInstance().setFontButtonView(activity,btnKnowNumber);
        //final Button btnNoKnowNumber=(Button)viewLayout.findViewById(R.id.btn_no_know_number);
        //Utilities.getInstance().setFontButtonView(activity,btnNoKnowNumber);
        //final StackedHorizontalProgressBar progressBarAnswerStatus=(StackedHorizontalProgressBar)viewLayout.findViewById(R.id.progress_bar_question_process);
        //final Button btnKnow=(Button)viewLayout.findViewById(R.id.btn_know);
        //final Button btnNOKnow=(Button)viewLayout.findViewById(R.id.btn_no_know);
        //final LinearLayout layoutButtonKnowStatus=(LinearLayout)viewLayout.findViewById(R.id.layout_button_know_status);
        //layoutButtonKnowStatus.setVisibility(View.GONE);
        final boolean[] showAnswerCartStatus = {false};
        final ScrollView scrollView=(ScrollView)viewLayout.findViewById(R.id.scroll_view_question_activity);


        final TextView txtPleaseTouch=(TextView)viewLayout.findViewById(R.id.txt_please_touch);
        Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(700); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        txtPleaseTouch.setAnimation(animation);


        /*progressBarAnswerStatus.setMax(allQuestion.size());
        progressBarAnswerStatus.setProgress(knowNumber);
        progressBarAnswerStatus.setSecondaryProgress(noKnowNumber);*/

        cardQuestion.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putParcelable("Question",allQuestion.get(position));
                intent.putExtras(bundle);
                intent.setClass(activity, EditQuestionActivity.class);
                activity.startActivity(intent);
                return false;
            }
        });

        /*btnKnowNumber.setOnClickListener(new View.OnClickListener() {
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
        });*/

       /* btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickOnKnowButton.setClickOnAnswerStatus(true);
                knowNumber++;
            }
        });

        btnNOKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickOnKnowButton.setClickOnAnswerStatus(true);
                noKnowNumber++;
            }
        });*/

        cardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!showAnswerCartStatus[0]){
                    flipOut(cardFront,cardBack);
                    //layoutButtonKnowStatus.setVisibility(View.VISIBLE);
                    setClickOnKnowButton.setClickOnAnswerStatus(true);
                    showAnswerCartStatus[0] =true;
                }
                /*YoYo.with(Techniques.FlipOutX)
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                cardFront.setVisibility(View.GONE);
                                cardBack.setVisibility(View.VISIBLE);
                                YoYo.with(Techniques.FlipInX)
                                        .duration(200)
                                        .playOn(cardBack);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .duration(200)
                        .playOn(cardFront);*/
            }
        });
       /*back_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               flipOut(cardBack,cardFront);

               /*YoYo.with(Techniques.FlipOutX)
                   .withListener(new Animator.AnimatorListener() {
                       @Override
                       public void onAnimationStart(Animator animation) {

                       }

                       @Override
                       public void onAnimationEnd(Animator animation) {
                           cardBack.setVisibility(View.GONE);
                           cardFront.setVisibility(View.VISIBLE);
                           YoYo.with(Techniques.FlipInX)
                                   .duration(200)
                                   .playOn(cardFront);
                       }

                       @Override
                       public void onAnimationCancel(Animator animation) {

                       }

                       @Override
                       public void onAnimationRepeat(Animator animation) {

                       }
                   })
                   .duration(200)
                   .playOn(cardBack);
           }
       });*/


        final boolean[] questionDescriptionShowStatus = {false};
        btnAnswerDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionDescriptionShowStatus[0]){
                    Utilities.getInstance().animateFAB(activity, false, btnAnswerDescription);
                    layoutQuestionDescription.setVisibility(View.VISIBLE);
                    questionDescriptionShowStatus[0] =!questionDescriptionShowStatus[0];

                }
                else if (questionDescriptionShowStatus[0]){
                    Utilities.getInstance().animateFAB(activity, true, btnAnswerDescription);
                    layoutQuestionDescription.setVisibility(View.GONE);
                    questionDescriptionShowStatus[0] =!questionDescriptionShowStatus[0];
                }
            }
        });

        btnAnswerPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,ShowImageActivity.class);
                activity.startActivity(intent);

            }
        });

        container.addView(viewLayout);
        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);

    }

    private void flipOut(final View viewFront, final View viewBack){
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(activity, R.animator.flip_out);
        anim.setTarget(viewFront);
        anim.setDuration(200);
        anim.addListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animator) {

            }

            @Override
            public void onAnimationEnd(android.animation.Animator animator) {
                viewBack.setVisibility(View.VISIBLE);
                flipIn(viewBack);


            }

            @Override
            public void onAnimationCancel(android.animation.Animator animator) {

            }

            @Override
            public void onAnimationRepeat(android.animation.Animator animator) {

            }
        });
        anim.start();
    }
    private void flipIn(View view){
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(activity, R.animator.flip_in);
        anim.setTarget(view);
        anim.setDuration(200);
        anim.start();

    }

}


