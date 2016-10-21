package winep.ir.twocon.Presenter.MemoryBankTab;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.MemoryBankItem;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.RectangleView;

/**
 * Created by ShaisteS on 10/9/2016.
 */
public class MemoryBankRecyclerViewAdapter  extends DragSelectRecyclerViewAdapter<MemoryBankRecyclerViewAdapter.MainViewHolder> {


    public interface ClickListener {
        void onClick(int index);

        void onLongClick(int index);

        void onBackPressed();
    }

    private final ClickListener mCallback;
    private ArrayList<MemoryBankItem> allMemoryBankItems;
    private Context context;

    protected MemoryBankRecyclerViewAdapter(ClickListener callback,Context context,ArrayList<MemoryBankItem> memoryBankItems) {
        super();
        mCallback = callback;
        this.context=context;
        this.allMemoryBankItems=memoryBankItems;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_bank_recycler_view_item, parent, false);
        return new MainViewHolder(v, mCallback);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.groupInformation.setText(context.getString(R.string.m_group)+
                "-"+context.getString(R.string.m_sub_group)+"-"+context.getString(R.string.lesson));
        final Drawable d;
        final Context c = holder.itemView.getContext();

        if (isIndexSelected(position)) {
            d = new ColorDrawable(ContextCompat.getColor(c, R.color.item_foreground_selected_color));
        } else {
            d = null;
        }
        ((FrameLayout) holder.colorSquare).setForeground(d);
    }

    @Override
    public int getItemCount() {
        return allMemoryBankItems.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        public final RectangleView colorSquare;
        private final ClickListener mCallback;
        private TextView groupInformation;
        private TextView questionWrongValue;
        private TextView questionTrueValue;
        private TextView questionAvswerSeenValue;
        private TextView questionSeenValue;
        private TextView questionNumber;
        private TextView questionTitle;

        public MainViewHolder(View itemView, ClickListener callback) {
            super(itemView);
            mCallback = callback;
            colorSquare = (RectangleView) itemView.findViewById(R.id.colorSquare);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            groupInformation =(TextView)itemView.findViewById(R.id.txt_group_information);
            questionWrongValue=(TextView)itemView.findViewById(R.id.txt_question_wrong_value);
            questionTrueValue=(TextView)itemView.findViewById(R.id.txt_question_true_value);
            questionAvswerSeenValue=(TextView)itemView.findViewById(R.id.txt_question_answer_seen);
            questionSeenValue=(TextView)itemView.findViewById(R.id.txt_question_seen);
            questionNumber=(TextView)itemView.findViewById(R.id.txt_question_number);
            questionTitle=(TextView)itemView.findViewById(R.id.txt_question_title);
        }

        @Override
        public void onClick(View v) {
            if (mCallback != null)
                mCallback.onClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.inflate(R.menu.memory_bank_item_long_touch_menu);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getTitle().equals(itemView.getContext().getString(R.string.m_multi_selected))){
                        if (mCallback != null)
                            mCallback.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
            popup.show();
            return true;
        }
    }
}