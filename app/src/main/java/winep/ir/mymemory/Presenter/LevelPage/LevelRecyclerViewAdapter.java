package winep.ir.mymemory.Presenter.LevelPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.Level;
import winep.ir.mymemory.Presenter.QuestionPage.QuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class LevelRecyclerViewAdapter extends RecyclerView.Adapter<LevelRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Level> allLevel;
    private Context context;
    private int lastPosition = -1;


    public LevelRecyclerViewAdapter(Context context, ArrayList<Level> levels){
        this.context=context;
        allLevel=levels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        setAnimation(holder.container, position);
        holder.levelNumber.setText(context.getString(R.string.level_number)+""+Integer.toString(allLevel.get(position).getLevelNumber()));
        if (allLevel.get(position).getLevelStatus()==0) {
            Utilities.getInstance().customView(holder.levelNumber, ContextCompat.getColor(context,R.color.green), ContextCompat.getColor(context,R.color.green));
            holder.levelStatus.setText(context.getString(R.string.level_status_ready));
        }

        else if (allLevel.get(position).getLevelStatus()==1) {
            Utilities.getInstance().customView(holder.levelNumber, Color.RED, Color.RED);
            holder.itemView.setClickable(false);
            holder.levelStatus.setText( "6"+
                    context.getString(R.string.level_status_days)+
                    " "+
                    context.getString(R.string.and)+
                    " "+
                    "12"+
                    context.getString(R.string.level_status_hours));

        }
        else if (allLevel.get(position).getLevelStatus()==2) {
            Utilities.getInstance().customView(holder.levelNumber, Color.GRAY, Color.GRAY);
            holder.itemView.setClickable(false);
        }

        holder.levelTotalQuestionNumber.setText(Integer.toString(allLevel.get(position).getLevelTotalQuestion()));
        holder.levelReadyQuestionNumber.setText(Integer.toString(allLevel.get(position).getLevelReadyQuestion()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allLevel.get(position).getLevelStatus()==0){
                    Intent intent=new Intent(context, QuestionActivity.class);
                    context.startActivity(intent);
                }
                else if (allLevel.get(position).getLevelStatus()==1) {
                    Toast.makeText(context,"6"+
                            context.getString(R.string.level_status_days)+
                            context.getString(R.string.and)+"12"+
                            context.getString(R.string.level_status_hours)+
                            " "+
                            context.getString(R.string.open),Toast.LENGTH_SHORT).show();

                }
                else if (allLevel.get(position).getLevelStatus()==2) {
                    Toast.makeText(context,context.getString(R.string.level_status_empty),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allLevel.size();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout container;
        private TextView levelNumber;
        private TextView levelReadyQuestionNumber;
        private TextView levelTotalQuestionNumber;
        private TextView levelStatus;
        public MyViewHolder(View itemView) {
            super(itemView);
            container=(FrameLayout)itemView.findViewById(R.id.level_item_container);
            levelNumber=(TextView)itemView.findViewById(R.id.txt_level_number);
            Utilities.getInstance().setFontTextView(context,levelNumber);
            levelReadyQuestionNumber=(TextView)itemView.findViewById(R.id.txt_ready);
            Utilities.getInstance().setFontTextView(context,levelReadyQuestionNumber);
           // levelReadyQuestionNumber.setPaintFlags(levelReadyQuestionNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            levelTotalQuestionNumber=(TextView)itemView.findViewById(R.id.txt_total);
            Utilities.getInstance().setFontTextView(context,levelTotalQuestionNumber);
            //levelTotalQuestionNumber.setPaintFlags(levelTotalQuestionNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            levelStatus=(TextView)itemView.findViewById(R.id.txt_level_status);
            Utilities.getInstance().setFontTextView(context,levelStatus);

        }
    }
}
