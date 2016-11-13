package winep.ir.twocon.Presenter.ExamPage;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Exam;
import winep.ir.twocon.Presenter.QuestionPage.EditQuestionActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 11/12/2016.
 */
public class ExamPagerAdapter  extends PagerAdapter {

    private Activity activity;
    private ArrayList<Exam> examList;
    private View viewLayout;

    public ExamPagerAdapter(ArrayList<Exam> exam,Activity activity) {
        this.activity = activity;
        this.examList=exam;
    }


    @Override
    public int getCount() {
        return examList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewLayout = inflater.inflate(R.layout.exam_pager, container, false);

        CardView cardViewExamQuestion=(CardView)viewLayout.findViewById(R.id.cart_view_exam_question);
        final LinearLayout layoutAnswerOne=(LinearLayout)viewLayout.findViewById(R.id.layout_exam_answer_one);
        final LinearLayout layoutExamAnswerTwo=(LinearLayout) viewLayout.findViewById(R.id.layout_exam_answer_two);
        final LinearLayout layoutExamAnswerThree=(LinearLayout) viewLayout.findViewById(R.id.layout_exam_answer_three);
        final LinearLayout layoutExamAnswerFour=(LinearLayout)viewLayout.findViewById(R.id.layout_exam_answer_four);


        TextView txtExamQuestionNumber=(TextView)viewLayout.findViewById(R.id.txt_exam_question_number);
        Utilities.getInstance().setFontTextView(activity,txtExamQuestionNumber);
        TextView txtExamQuestionTitle=(TextView)viewLayout.findViewById(R.id.txt_exam_question_title);
        Utilities.getInstance().setFontTextView(activity,txtExamQuestionTitle);
        TextView txtAnswerNumberOne=(TextView)viewLayout.findViewById(R.id.txt_exam_number_one);
        Utilities.getInstance().setFontTextView(activity,txtAnswerNumberOne);
        TextView txtAnswerValueOne=(TextView)viewLayout.findViewById(R.id.txt_exam_answer_one);
        Utilities.getInstance().setFontTextView(activity,txtAnswerValueOne);
        TextView txtAnswerNumberTwo=(TextView)viewLayout.findViewById(R.id.txt_exam_number_two);
        Utilities.getInstance().setFontTextView(activity,txtAnswerNumberTwo);
        TextView txtAnswerValueTwo=(TextView)viewLayout.findViewById(R.id.txt_exam_answer_two);
        Utilities.getInstance().setFontTextView(activity,txtAnswerValueTwo);
        TextView txtAnswerNumberThree=(TextView)viewLayout.findViewById(R.id.txt_exam_number_three);
        Utilities.getInstance().setFontTextView(activity,txtAnswerNumberThree);
        TextView txtAnswerValueThree=(TextView)viewLayout.findViewById(R.id.txt_exam_answer_three);
        Utilities.getInstance().setFontTextView(activity,txtAnswerValueThree);
        TextView txtAnswerNumberFour=(TextView)viewLayout.findViewById(R.id.txt_exam_number_four);
        Utilities.getInstance().setFontTextView(activity,txtAnswerNumberFour);
        TextView txtAnswerValueFour=(TextView)viewLayout.findViewById(R.id.txt_exam_answer_four);
        Utilities.getInstance().setFontTextView(activity,txtAnswerValueFour);


        if (examList.get(position).getUserSelectAnswer()==1)
            layoutAnswerOne.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
        else if (examList.get(position).getUserSelectAnswer()==2)
            layoutExamAnswerTwo.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
        else if (examList.get(position).getUserSelectAnswer()==3)
            layoutExamAnswerThree.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
        else if (examList.get(position).getUserSelectAnswer()==4)
            layoutExamAnswerFour.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));


        cardViewExamQuestion.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putParcelable("Question",examList.get(position).getQuestion());
                intent.putExtras(bundle);
                intent.setClass(activity, EditQuestionActivity.class);
                activity.startActivity(intent);
                return false;
            }
        });

        layoutAnswerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examList.get(position).setUserSelectAnswer(1);
                layoutAnswerOne.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
                layoutExamAnswerTwo.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerThree.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerFour.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));

            }
        });

        layoutExamAnswerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examList.get(position).setUserSelectAnswer(2);
                layoutAnswerOne.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerTwo.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
                layoutExamAnswerThree.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerFour.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
            }
        });

        layoutExamAnswerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examList.get(position).setUserSelectAnswer(3);
                layoutAnswerOne.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerTwo.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerThree.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
                layoutExamAnswerFour.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
            }
        });

        layoutExamAnswerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examList.get(position).setUserSelectAnswer(4);
                layoutAnswerOne.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerTwo.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerThree.setBackgroundColor(ContextCompat.getColor(activity,R.color.white));
                layoutExamAnswerFour.setBackgroundColor(ContextCompat.getColor(activity,R.color.item_foreground_selected_color));
            }
        });

        container.addView(viewLayout);
        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ScrollView) object);

    }
}



