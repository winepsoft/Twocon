package winep.ir.twocon.Presenter.SettingsPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Reminder;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 11/6/2016.
 */
public class SettingReminderRecyclerViewAdapter extends RecyclerView.Adapter<SettingReminderRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Reminder> allReminder;
    private Context context;

    public SettingReminderRecyclerViewAdapter(Context context,ArrayList<Reminder> reminders){
        allReminder=reminders;
        this.context=context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_activity_reminder_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Reminder aRemind=new Reminder();
        aRemind=allReminder.get(position);
        holder.txtReminderTime.setText(aRemind.getDay()+", "+aRemind.getHour()+":"+aRemind.getMinute()+" "+aRemind.getTimeType());
        holder.btnReminderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeReminder(position);
            }
        });

    }

    public void removeReminder(int position){
        allReminder.remove(position);
        notifyDataSetChanged();
    }

    public void  addreminder(Reminder aReminder){
        allReminder.add(aReminder);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allReminder.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtReminderTime;
        private ImageButton btnReminderDelete;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtReminderTime=(TextView)itemView.findViewById(R.id.txt_reminder_date);
            Utilities.getInstance().setFontTextView(context,txtReminderTime);
            btnReminderDelete=(ImageButton)itemView.findViewById(R.id.ibtn_reminder_delete);
        }
    }
}
