package winep.ir.twocon.Presenter.LevelSettingPage;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rey.material.widget.EditText;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.LevelSetting;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

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
        holder.txtLevelNumber.setText(Integer.toString(position+1));
        Utilities.getInstance().customView(holder.txtLevelNumber, ContextCompat.getColor(context,R.color.level_settings_label),
                ContextCompat.getColor(context,R.color.level_settings_label));
        holder.editTextTimeNumber.setText("1");
    }

    @Override
    public int getItemCount() {
        return allLevelInformation.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLevelNumber;
        private EditText editTextTimeNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtLevelNumber=(TextView)itemView.findViewById(R.id.txt_level_number);
            Utilities.getInstance().setFontTextView(context,txtLevelNumber);
            editTextTimeNumber=(EditText) itemView.findViewById(R.id.editText_select_time_type);
            Utilities.getInstance().setFontEditTextView(context,editTextTimeNumber);
        }
    }

    private ArrayAdapter<String> setSpinnerTimeType(){
        String[] timeType = {context.getString(R.string.level_setting_select_time_type_month),
                context.getString(R.string.level_setting_select_time_type_day)};
        ArrayAdapter<String> a =new ArrayAdapter<>(context,android.R.layout.simple_spinner_item, timeType);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return a;
    }

    private ArrayAdapter<String> setSpinnerTime(){
        String[] spinnerTime=new String[20] ;
        for (int i=0;i<20;i++)
            spinnerTime[i]=Integer.toString(i);
        ArrayAdapter<String> a =new ArrayAdapter<>(context,android.R.layout.simple_spinner_item, spinnerTime);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return a;
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
