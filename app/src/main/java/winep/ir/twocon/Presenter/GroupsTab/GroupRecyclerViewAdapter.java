package winep.ir.twocon.Presenter.GroupsTab;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Group;
import winep.ir.twocon.Presenter.LevelSettingPage.LevelSettingActivity;
import winep.ir.twocon.Presenter.StatisticsGroupPage.StatisticsGroupActivity;
import winep.ir.twocon.Presenter.SubGroupPage.SubGroupActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Font;
import winep.ir.twocon.Utility.RectangleView;
import winep.ir.twocon.Utility.Utilities;


/**
 * Created by ShaisteS on 6/14/2016.
 */
public class GroupRecyclerViewAdapter extends RecyclerView.Adapter<GroupRecyclerViewAdapter.MyViewHolder>
        implements DraggableItemAdapter<GroupRecyclerViewAdapter.MyViewHolder> {



    private ArrayList<Group> allGroups;
    private Context context;
    private Font font;
    private Drawable d;

    // NOTE: Make accessible with short name
    private interface Draggable extends DraggableItemConstants {
    }

    public GroupRecyclerViewAdapter(Context context,ArrayList<Group> groups) {
        setHasStableIds(true); // this is required for D&D feature.
        this.context=context;
        allGroups=groups;
        font=new Font();
    }

    @Override
    public long getItemId(int position) {
        return allGroups.get(position).id; // need to return stable (= not change even after reordered) value
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String firstCharecterOfGroupTitle=allGroups.get(position).getTitle().substring(0,1);
        holder.minGroupName.setText(firstCharecterOfGroupTitle);
        Utilities.getInstance().customView(holder.minGroupName, allGroups.get(position).getColor(),allGroups.get(position).getColor());
        //customView(holder.minGroupName, Color.GREEN,Color.GREEN);
        holder.groupName.setText(allGroups.get(position).getTitle());
        holder.groupDescription.setText(allGroups.get(position).getDescription());


        //Change color item when click
        if (allGroups.get(position).isSelected()) {
            Context c = holder.itemView.getContext();
            d = new ColorDrawable(ContextCompat.getColor(c, R.color.item_foreground_selected_color));
        } else {
            d = null;
        }
        ((FrameLayout) holder.colorSquare).setForeground(d);


        holder.groupSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.inflate(R.menu.menu_groups_item);
                setUnSelected(position);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals(context.getString(R.string.edit_name))){
                            boolean wrapInScrollView = true;
                            new MaterialDialog.Builder(context)
                                    .title(R.string.dialog_edit_group_title)
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
                                    .title(R.string.dialog_delete_group_title)
                                    .items(R.array.dialog_delete_group_item_choice)
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
                        else if(item.getTitle().equals(context.getString(R.string.information))){
                            Intent intent=new Intent(context, StatisticsGroupActivity.class);
                            intent.putExtra("groupTitle",allGroups.get(position).getTitle());
                            context.startActivity(intent);
                        }
                        else if (item.getTitle().equals(context.getString(R.string.set_the_level))){
                            Intent intent=new Intent(context, LevelSettingActivity.class);
                            context.startActivity(intent);
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
                Intent intent=new Intent(context, SubGroupActivity.class);
                intent.putExtra("groupName",allGroups.get(position).getTitle());
                context.startActivity(intent);
                setUnSelected(position);
            }
        });

        // set background item when drag
        final int dragState = holder.getDragStateFlags();
        if (((dragState & Draggable.STATE_FLAG_IS_UPDATED) != 0)) {

            if ((dragState & Draggable.STATE_FLAG_IS_ACTIVE) != 0) {
                Context c = holder.itemView.getContext();
                d = new ColorDrawable(ContextCompat.getColor(c, R.color.item_foreground_selected_color));

            } else if ((dragState & Draggable.STATE_FLAG_DRAGGING) != 0) {
                d =null;
            } else {
                d =null;
            }
            ((FrameLayout) holder.colorSquare).setForeground(d);
        }
    }

    public void setSelected(int pos) {
        try {
            allGroups.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUnSelected(int pos) {
        try {
            allGroups.get(pos).setSelected(false);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return allGroups.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        Group movedItem = allGroups.remove(fromPosition);
        allGroups.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }


    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    static class MyViewHolder extends AbstractDraggableItemViewHolder {
        TextView minGroupName;
        TextView groupName;
        TextView groupDescription;
        ImageButton groupSetting;
        RectangleView colorSquare;

        public MyViewHolder(View itemView) {
            super(itemView);
            colorSquare = (RectangleView) itemView.findViewById(R.id.colorSquare);
            minGroupName=(TextView)itemView.findViewById(R.id.min_group_name);
            groupName=(TextView)itemView.findViewById(R.id.group_name);
            groupDescription=(TextView)itemView.findViewById(R.id.group_description);
            groupSetting=(ImageButton)itemView.findViewById(R.id.group_settings);
        }
    }
}

