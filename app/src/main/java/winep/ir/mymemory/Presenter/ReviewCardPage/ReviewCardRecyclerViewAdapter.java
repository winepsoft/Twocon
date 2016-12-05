package winep.ir.mymemory.Presenter.ReviewCardPage;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.Question;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 12/5/2016.
 */
public class ReviewCardRecyclerViewAdapter extends RecyclerView.Adapter<ReviewCardRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Question> allQuestion;

    public ReviewCardRecyclerViewAdapter(Context context,ArrayList<Question> questions){
        this.context=context;
        allQuestion=questions;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_card_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtQuestionNumber.setText(Integer.toString(position));
        holder.txtQuestionTitle.setText(allQuestion.get(position).getQuestionTitle());
        holder.txtAnswerNumber.setText(Integer.toString(position));
        holder.txtAnswer.setText(allQuestion.get(position).getQuestionAnswer());
    }

    @Override
    public int getItemCount() {
        return allQuestion.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtQuestionNumber;
        private TextView txtQuestionTitle;
        private TextView txtAnswerNumber;
        private TextView txtAnswer;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtQuestionNumber=(TextView)itemView.findViewById(R.id.txt_review_cart_question_number);
            Utilities.getInstance().setFontTextView(context,txtQuestionNumber);
            txtQuestionTitle=(TextView)itemView.findViewById(R.id.txt_review_cart_question_title);
            Utilities.getInstance().setFontTextView(context,txtQuestionTitle);
            txtAnswerNumber=(TextView)itemView.findViewById(R.id.txt_review_cart_question_number_in_answer);
            Utilities.getInstance().setFontTextView(context,txtAnswerNumber);
            txtAnswer=(TextView)itemView.findViewById(R.id.txt_review_cart_answer);
            Utilities.getInstance().setFontTextView(context,txtAnswer);
        }
    }
}
