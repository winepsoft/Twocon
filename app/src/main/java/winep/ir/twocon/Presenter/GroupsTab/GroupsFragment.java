package winep.ir.twocon.Presenter.GroupsTab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Group;
import winep.ir.twocon.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.ClickListener;
import winep.ir.twocon.Utility.DividerItemDecorationRecyclerView;
import winep.ir.twocon.Utility.Font;
import winep.ir.twocon.Utility.RecyclerTouchListener;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class GroupsFragment extends Fragment {

    private RecyclerView recyclerViewGroups;
    private Context context;
    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton fabAddNewGroup;
    private FloatingActionButton fabAddNewSubGroup;
    private FloatingActionButton fabAddNewCourse;
    private FloatingActionButton fabAddNewQuestion;
    private FrameLayout frameLayout;
    private ArrayList<Group> groups;
    private Font font;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.groups_fragment, container, false);
        context=getContext();
        font=new Font();
        groups=new ArrayList<>();
        recyclerViewGroups=(RecyclerView)mainView.findViewById(R.id.recycler_view_groups);
        floatingActionsMenu=(FloatingActionsMenu)mainView.findViewById(R.id.group_fab_add);
        fabAddNewGroup=(FloatingActionButton)mainView.findViewById(R.id.fab_add_new_group);
        fabAddNewSubGroup=(FloatingActionButton)mainView.findViewById(R.id.fab_add_new_sub_group);
        fabAddNewCourse=(FloatingActionButton)mainView.findViewById(R.id.fab_add_new_course);
        fabAddNewQuestion=(FloatingActionButton)mainView.findViewById(R.id.fab_add_new_question);
        frameLayout=(FrameLayout)mainView.findViewById(R.id.containerFloatingActionMenu);
        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.background_after_click_FAM));
            }

            @Override
            public void onMenuCollapsed() {
                frameLayout.setBackgroundColor(Color.TRANSPARENT);

            }
        });
        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewGroups.addItemDecoration(new DividerItemDecorationRecyclerView(5));
        final GroupRecyclerViewAdapter adapter=new GroupRecyclerViewAdapter(context,createGroup());
        recyclerViewGroups.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerViewGroups);


        //for change color of item when click
        recyclerViewGroups.addOnItemTouchListener(new RecyclerTouchListener(context,recyclerViewGroups, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                adapter.setSelected(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                /*adapter.setSelected(position);
                dragMgr.setInitiateOnMove(true);*/

            }
        }));

        fabAddNewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(context)
                        .title(R.string.new_group_dialog_title)
                        .customView(R.layout.dialog_edit_group, wrapInScrollView)
                        .positiveText(R.string.save)
                        .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                        .negativeText(R.string.cancel)
                        .negativeColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                        .typeface(font.getRTLFontNameForDialog(),null)
                        .show();
                closeFloatingActionMenu();
            }
        });


        fabAddNewSubGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(R.string.new_sub_group_dialog_title)
                        .items(createListOfGroupsTitle())
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                boolean wrapInScrollView = true;
                                new MaterialDialog.Builder(context)
                                        .title(context.getString(R.string.add_a_sub_group_to)+" "+groups.get(which).getTitle())
                                        .customView(R.layout.dialog_edit_group, wrapInScrollView)
                                        .positiveText(R.string.save)
                                        .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                                        .negativeText(R.string.cancel)
                                        .negativeColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                                        .typeface(font.getRTLFontNameForDialog(),null)
                                        .show();
                            }
                        })
                        .show();
                closeFloatingActionMenu();
            }
        });

        fabAddNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(R.string.new_course_dialog_title)
                        .items(createListOfSubGroupsOfAGroup())
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                boolean wrapInScrollView = true;
                                new MaterialDialog.Builder(context)
                                        .title(context.getString(R.string.add_a_course_to)+" "+text)
                                        .customView(R.layout.dialog_edit_group, wrapInScrollView)
                                        .positiveText(R.string.save)
                                        .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                                        .negativeText(R.string.cancel)
                                        .negativeColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                                        .typeface(font.getRTLFontNameForDialog(),null)
                                        .show();
                            }
                        })
                        .show();
                closeFloatingActionMenu();

            }
        });

        fabAddNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFloatingActionMenu();
                Intent intent=new Intent(context, CreateQuestionActivity.class);
                startActivity(intent);
            }
        });

        return mainView;
    }

    private void closeFloatingActionMenu(){
        frameLayout.setBackgroundColor(Color.TRANSPARENT);
        floatingActionsMenu.collapse();
    }

    private String[] createListOfGroupsTitle(){
        String[] groupsTitle=new String[groups.size()];
        for (int i=0;i<groups.size();i++)
            groupsTitle[i]=groups.get(i).getTitle();
        return groupsTitle;
    }

    private String[] createListOfSubGroupsOfAGroup(){
        String[] subGroupTitle=new String[2];
        subGroupTitle[0]="فیزیولوژی";
        subGroupTitle[1]="دهان و دندان";
        return subGroupTitle;
    }

    private ArrayList<Group> createGroup(){

        //group:1
        RandomColor rnd1 = new RandomColor();
        int color1 = rnd1.randomColor();
        Group group1=new Group(0);
        group1.setTitle("پزشکی");
        group1.setDescription("توضیحاتی در مورد این بخش");
        group1.setColor(color1);
        groups.add(group1);
        //group:2
        RandomColor rnd2 = new RandomColor();
        int color2 = rnd2.randomColor();
        Group group2=new Group(1);
        group2.setTitle("مهندسی پزشکی");
        group2.setDescription("توضیحاتی در مورد این بخش");
        group2.setColor(color2);
        groups.add(group2);
        //group:3
        RandomColor rnd3 = new RandomColor();
        int color3 = rnd3.randomColor();
        Group group3=new Group(2);
        group3.setTitle("English title");
        group3.setDescription("توضیحاتی در مورد این بخش");
        group3.setColor(color3);
        groups.add(group3);
        return groups;
    }
}
