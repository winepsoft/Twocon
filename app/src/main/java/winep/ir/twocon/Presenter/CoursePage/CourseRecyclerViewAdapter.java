package winep.ir.twocon.Presenter.CoursePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Course;
import winep.ir.twocon.Presenter.ExamPage.ExamActivity;
import winep.ir.twocon.Presenter.LevelPage.LevelActivity;
import winep.ir.twocon.Presenter.StatisticsGroupPage.StatisticsGroupActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Font;
import winep.ir.twocon.Utility.RectangleView;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.MyViewHolder> implements DraggableItemAdapter<CourseRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Course> allCourses;
    private Context context;
    private Font font;
    private Drawable d;

    // NOTE: Make accessible with short name
    private interface Draggable extends DraggableItemConstants {
    }


    public CourseRecyclerViewAdapter(Context context,ArrayList<Course> courses) {
        setHasStableIds(true); // this is required for D&D feature.
        this.context=context;
        allCourses =courses;
        font=new Font();
    }

    @Override
    public long getItemId(int position) {
        return allCourses.get(position).id; // need to return stable (= not change even after reordered) value
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String firstCharecterOfGroupTitle= allCourses.get(position).getTitle().substring(0,1);
        holder.minCourseName.setText(firstCharecterOfGroupTitle);
        Utilities.getInstance().customView(holder.minCourseName, allCourses.get(position).getColor(), allCourses.get(position).getColor());
        holder.courseName.setText(allCourses.get(position).getTitle());
        holder.courseDescription.setText(allCourses.get(position).getDescription());

        holder.courseSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.inflate(R.menu.menu_course_item);
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
                            intent.putExtra("groupTitle",allCourses.get(position).getTitle());
                            intent.putExtra("pieChartStatus",false);
                            context.startActivity(intent);
                        }
                        else if(item.getItemId()==R.id.color){
                            Utilities.getInstance().showColorPickerDialog(context);
                        }
                        else if(item.getItemId()==R.id.action_course_exam){
                            boolean wrapInScrollView = true;
                            new MaterialDialog.Builder(context)
                                    .customView(R.layout.exam_dialog, wrapInScrollView)
                                    .positiveText(R.string.save)
                                    .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                                    .negativeText(R.string.cancel)
                                    .negativeColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                                    .typeface(font.getRTLFontNameForDialog(),null)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            EditText editTextExamNumber=(EditText)dialog.findViewById(R.id.edit_text_exam_number);
                                            int examNumber;
                                            if (editTextExamNumber.getText().toString().equals(""))
                                                examNumber=0;
                                            else
                                                examNumber =Integer.parseInt(editTextExamNumber.getText().toString());
                                            Intent intent=new Intent(context, ExamActivity.class);
                                            intent.putExtra("examQuestionNumber",examNumber);
                                            context.startActivity(intent);
                                        }
                                    })
                                    .show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        //Change color item when click
        if (allCourses.get(position).isSelected()) {
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
                Intent intent=new Intent(context, LevelActivity.class);
                intent.putExtra("courseName", allCourses.get(position).getTitle());
                context.startActivity(intent);
                setUnSelected(position);

            }
        });

        holder.courseShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected(position);
                holder.courseShare.setImageResource(R.mipmap.share_green);
            }
        });

        holder.courseFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected(position);
                holder.courseFavourite.setImageResource(R.mipmap.favorite_ok);
            }
        });
    }

    public void setSelected(int pos) {
        try {
            allCourses.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUnSelected(int pos) {
        try {
            allCourses.get(pos).setSelected(false);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return allCourses.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        Course movedItem = allCourses.remove(fromPosition);
        allCourses.add(toPosition, movedItem);
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
        TextView minCourseName;
        TextView courseName;
        TextView courseDescription;
        ImageButton courseSetting;
        ImageButton courseShare;
        ImageButton courseFavourite;
        RectangleView colorSquare;
        public MyViewHolder(View itemView) {
            super(itemView);
            colorSquare = (RectangleView) itemView.findViewById(R.id.courseColorSquare);
            minCourseName =(TextView)itemView.findViewById(R.id.min_course_name);
            courseName =(TextView)itemView.findViewById(R.id.course_name);
            courseDescription =(TextView)itemView.findViewById(R.id.course_description);
            courseSetting =(ImageButton)itemView.findViewById(R.id.course_settings);
            courseFavourite=(ImageButton)itemView.findViewById(R.id.course_favorite);
            courseShare=(ImageButton)itemView.findViewById(R.id.course_share);
        }
    }
}


