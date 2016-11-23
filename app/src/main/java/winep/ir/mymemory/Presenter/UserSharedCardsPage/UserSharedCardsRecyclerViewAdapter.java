package winep.ir.mymemory.Presenter.UserSharedCardsPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.SharedFlashCard;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Dialogs;
import winep.ir.mymemory.Utility.RectangleView;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/19/2016.
 */
public class UserSharedCardsRecyclerViewAdapter extends RecyclerView.Adapter<UserSharedCardsRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SharedFlashCard> allShardFlashCards;

    public UserSharedCardsRecyclerViewAdapter(Context context, ArrayList<SharedFlashCard> sharedFlashCards){
        this.context=context;
        allShardFlashCards=sharedFlashCards;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.user_shared_cards_recycler_view_item,parent,false);
        return new MyViewHolder(v);     }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SharedFlashCard aSharedFlashCard=allShardFlashCards.get(position);
        holder.txtUsername.setText(aSharedFlashCard.getUserName());
        holder.txtSharedCardsTitle.setText(aSharedFlashCard.getFlashCard().getTitle());
        holder.txtSharedCardsSize.setText(aSharedFlashCard.getFlashCard().getDownloadSize());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getInstance().showSharedFlashCardsDescriptionDialog(context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allShardFlashCards.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtUsername;
        private TextView txtSharedCardsTitle;
        private TextView txtSharedCardsSize;
        private ImageView imgeSharedCards;
        private RectangleView colorSquare;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtUsername=(TextView)itemView.findViewById(R.id.txt_shared_cards_user_name);
            Utilities.getInstance().setFontTextView(context,txtUsername);
            txtSharedCardsTitle=(TextView)itemView.findViewById(R.id.txt_shared_cards_title);
            Utilities.getInstance().setFontTextView(context,txtSharedCardsTitle);
            txtSharedCardsSize=(TextView)itemView.findViewById(R.id.txt_shared_cards_size);
            Utilities.getInstance().setFontTextView(context,txtSharedCardsSize);
            imgeSharedCards=(ImageView)itemView.findViewById(R.id.image_shared_cards);
            colorSquare = (RectangleView) itemView.findViewById(R.id.colorSquare);
        }
    }
}
