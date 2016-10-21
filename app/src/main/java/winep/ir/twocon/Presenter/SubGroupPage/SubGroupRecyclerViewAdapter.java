package winep.ir.twocon.Presenter.SubGroupPage;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Group;
import winep.ir.twocon.Presenter.CoursePage.CourseActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Font;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/4/2016.
 */
public class SubGroupRecyclerViewAdapter extends RecyclerView.Adapter<SubGroupRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Group> allSubGroups;
    private Context context;
    private Font font;

    public SubGroupRecyclerViewAdapter(Context context,ArrayList<Group> subGroups){
        this.context=context;
        allSubGroups=subGroups;
        font=new Font();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_group_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String firstCharecterOfSubGroupTitle=allSubGroups.get(position).getTitle().substring(0,1);
        holder.minSubGroupName.setText(firstCharecterOfSubGroupTitle);
        Utilities.getInstance().customView(holder.minSubGroupName, allSubGroups.get(position).getColor(),allSubGroups.get(position).getColor());
        holder.subGroupName.setText(allSubGroups.get(position).getTitle());
        holder.subGroupDescription.setText(allSubGroups.get(position).getDescription());
        holder.subGroupSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.inflate(R.menu.menu_sub_group_item);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals(context.getString(R.string.edit_name))){
                            boolean wrapInScrollView = true;
                            new MaterialDialog.Builder(context)
                                    .title(R.string.dialog_edit_sub_group_title)
                                    .customView(R.layout.dialog_edit_group, wrapInScrollView)
                                    .positiveText(R.string.save)
                                    .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                                    .negativeText(R.string.cancel)
                                    .negativeColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                                    .typeface(font.getRTLFontNameForDialog(),null)
                                    .show();
                        }
                        else if(item.getTitle().equals(context.getString(R.string.delete))){
                            new MaterialDialog.Builder(context)
                                    .title(R.string.dialog_delete_sub_group_title)
                                    .items(R.array.dialog_delete_sub_group_item_choice)
                                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            /**
                                             * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                             * returning false here won't allow the newly selected radio button to actually be selected.
                                             **/
                                            return true;
                                        }
                                    })
                                    .positiveText(R.string.save)
                                    .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                                    .negativeText(R.string.cancel)
                                    .neutralColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                                    .typeface(font.getRTLFontNameForDialog(),null)
                                    .show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CourseActivity.class);
                intent.putExtra("subGroupName", allSubGroups.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allSubGroups.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView minSubGroupName;
        ImageButton subGroupFavourite;
        TextView subGroupName;
        TextView subGroupDescription;
        ImageButton subGroupSetting;

        public MyViewHolder(View itemView) {
            super(itemView);
            minSubGroupName=(TextView)itemView.findViewById(R.id.min_sub_group_name);
            subGroupFavourite=(ImageButton)itemView.findViewById(R.id.sub_group_favorite);
            subGroupName=(TextView)itemView.findViewById(R.id.sub_group_name);
            subGroupDescription=(TextView)itemView.findViewById(R.id.sub_group_description);
            subGroupSetting=(ImageButton)itemView.findViewById(R.id.sub_group_settings);
        }
    }
}
