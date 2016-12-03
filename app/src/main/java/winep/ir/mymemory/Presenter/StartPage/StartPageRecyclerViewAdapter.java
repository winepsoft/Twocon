package winep.ir.mymemory.Presenter.StartPage;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 12/3/2016.
 */
public class StartPageRecyclerViewAdapter extends RecyclerView.Adapter<StartPageRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<String> allLanguage;
    private Context context;

    public StartPageRecyclerViewAdapter(Context context,ArrayList<String> languageList){
        this.context=context;
        allLanguage=languageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.start_page_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txtLanguageName.setText(allLanguage.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.layoutlanguageItem.setBackgroundColor(ContextCompat.getColor(context,R.color.item_foreground_selected_color));

            }
        });
    }

    @Override
    public int getItemCount() {
        return allLanguage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLanguageName;
        private LinearLayout layoutlanguageItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(false);
            txtLanguageName=(TextView)itemView.findViewById(R.id.txt_start_page_item);
            layoutlanguageItem=(LinearLayout)itemView.findViewById(R.id.layout_start_page_language_item);
        }
    }
}
