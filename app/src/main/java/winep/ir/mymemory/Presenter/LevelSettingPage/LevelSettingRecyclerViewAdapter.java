package winep.ir.mymemory.Presenter.LevelSettingPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.LevelSetting;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/19/2016.
 */
public class LevelSettingRecyclerViewAdapter extends RecyclerView.Adapter<LevelSettingRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<LevelSetting> allLevelInformation;

    public LevelSettingRecyclerViewAdapter(Context context,ArrayList<LevelSetting> levelsInformationInfo){
        this.context=context;
        allLevelInformation=levelsInformationInfo;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.level_setting_recycler_view_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtLevelNumber.setText(context.getString(R.string.level_number)+" "+Integer.toString(position+1));
        holder.editTextSetDay.setText(Integer.toString(allLevelInformation.get(position).getLevelDay()));
        holder.editTextSetHour.setText(Integer.toString(allLevelInformation.get(position).getLevelHour()));
    }

    @Override
    public int getItemCount() {
        return allLevelInformation.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLevelNumber;
        private EditText editTextSetDay;
        private EditText editTextSetHour;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtLevelNumber=(TextView)itemView.findViewById(R.id.txt_level_number);
            Utilities.getInstance().setFontTextView(context,txtLevelNumber);
            editTextSetDay =(EditText) itemView.findViewById(R.id.editText_set_day);
            Utilities.getInstance().setFontEditTextView(context, editTextSetDay);
            editTextSetHour=(EditText)itemView.findViewById(R.id.editText_set_hour);
            Utilities.getInstance().setFontEditTextView(context,editTextSetHour);
        }
    }

    public void addNewItem(LevelSetting newLevel){
        allLevelInformation.add(newLevel);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        allLevelInformation.remove(position);
        notifyDataSetChanged();
    }
}
