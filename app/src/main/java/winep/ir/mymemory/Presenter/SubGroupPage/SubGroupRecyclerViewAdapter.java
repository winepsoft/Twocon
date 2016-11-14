package winep.ir.mymemory.Presenter.SubGroupPage;

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

import winep.ir.mymemory.DataModel.Group;
import winep.ir.mymemory.Presenter.CoursePage.CourseActivity;
import winep.ir.mymemory.Presenter.StatisticsGroupPage.StatisticsGroupActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.MyApplication;
import winep.ir.mymemory.Utility.RectangleView;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/4/2016.
 */
public class SubGroupRecyclerViewAdapter extends RecyclerView.Adapter<SubGroupRecyclerViewAdapter.MyViewHolder>
        implements DraggableItemAdapter<SubGroupRecyclerViewAdapter.MyViewHolder> {


    private ArrayList<Group> allSubGroups;
    private Context context;
    private MyApplication myApplication;
    private Drawable d;

    // NOTE: Make accessible with short name
    private interface Draggable extends DraggableItemConstants {
    }

    public SubGroupRecyclerViewAdapter(Context context, ArrayList<Group> subGroups) {
        setHasStableIds(true); // this is required for D&D feature.
        this.context = context;
        allSubGroups = subGroups;
        myApplication = new MyApplication();
    }

    @Override
    public long getItemId(int position) {
        return allSubGroups.get(position).id; // need to return stable (= not change even after reordered) value
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_group_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
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
                setUnSelected(position);
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
                                    .typeface(myApplication.getRTLFontNameForDialog(),null)
                                    .show();
                        }
                        else if(item.getTitle().equals(context.getString(R.string.delete))){
                            new MaterialDialog.Builder(context)
                                    .title(R.string.dialog_delete_sub_group_title)
                                    .items(R.array.dialog_delete_sub_group_item_choice)
                                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            return true;
                                        }
                                    })
                                    .positiveText(R.string.save)
                                    .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                                    .negativeText(R.string.cancel)
                                    .neutralColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                                    .typeface(myApplication.getRTLFontNameForDialog(),null)
                                    .show();
                        }
                        else if(item.getTitle().equals(context.getString(R.string.information))){
                            Intent intent=new Intent(context, StatisticsGroupActivity.class);
                            intent.putExtra("groupTitle",allSubGroups.get(position).getTitle());
                            intent.putExtra("pieChartStatus",false);
                            context.startActivity(intent);
                        }
                        else if(item.getItemId()==R.id.color){
                            Utilities.getInstance().showColorPickerDialog(context);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        //Change color item when click
        if (allSubGroups.get(position).isSelected()) {
            Context c = holder.itemView.getContext();
            d = new ColorDrawable(ContextCompat.getColor(c, R.color.item_foreground_selected_color));
        } else {
            d = null;
        }
        ((FrameLayout) holder.colorSquare).setForeground(d);

        // set background item when drag
        final int dragState = holder.getDragStateFlags();
        if (((dragState & Draggable.STATE_FLAG_IS_UPDATED) != 0)) {

            if ((dragState & Draggable.STATE_FLAG_IS_ACTIVE) != 0) {
                Context c = holder.itemView.getContext();
                d = new ColorDrawable(ContextCompat.getColor(c, R.color.item_foreground_selected_color));

            } else if ((dragState & Draggable.STATE_FLAG_DRAGGING) != 0) {
                d = null;
            } else {
                d = null;
            }
            ((FrameLayout) holder.colorSquare).setForeground(d);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CourseActivity.class);
                intent.putExtra("subGroupName", allSubGroups.get(position).getTitle());
                context.startActivity(intent);
                setUnSelected(position);
            }
        });

        holder.subGroupFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected(position);
                holder.subGroupFavourite.setImageResource(R.mipmap.favorite_ok);
            }
        });
    }

    public void setSelected(int pos) {
        try {
            allSubGroups.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUnSelected(int pos) {
        try {
            allSubGroups.get(pos).setSelected(false);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return allSubGroups.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        Group movedItem = allSubGroups.remove(fromPosition);
        allSubGroups.add(toPosition, movedItem);
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
        TextView minSubGroupName;
        ImageButton subGroupFavourite;
        TextView subGroupName;
        TextView subGroupDescription;
        ImageButton subGroupSetting;
        RectangleView colorSquare;

        public MyViewHolder(View itemView) {
            super(itemView);
            colorSquare = (RectangleView) itemView.findViewById(R.id.subColorSquare);
            minSubGroupName = (TextView) itemView.findViewById(R.id.min_sub_group_name);
            subGroupFavourite = (ImageButton) itemView.findViewById(R.id.sub_group_favorite);
            subGroupName = (TextView) itemView.findViewById(R.id.sub_group_name);
            subGroupDescription = (TextView) itemView.findViewById(R.id.sub_group_description);
            subGroupSetting = (ImageButton) itemView.findViewById(R.id.sub_group_settings);
        }
    }
}
/*extends RecyclerView.Adapter<SubGroupRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Group> allSubGroups;
    private Context context;
    private MyApplication myApplication;

    public SubGroupRecyclerViewAdapter(Context context,ArrayList<Group> subGroups){
        this.context=context;
        allSubGroups=subGroups;
        myApplication=new MyApplication();
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
}*/
