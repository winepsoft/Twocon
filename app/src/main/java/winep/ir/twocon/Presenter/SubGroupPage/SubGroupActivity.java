package winep.ir.twocon.Presenter.SubGroupPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Group;
import winep.ir.twocon.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.ClickListener;
import winep.ir.twocon.Utility.Font;
import winep.ir.twocon.Utility.RecyclerTouchListener;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/4/2016.
 */
public class SubGroupActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private NavigationView navigationView;
    private RecyclerView recyclerVieSubGroups;
    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton fabAddSubGroup;
    private FloatingActionButton fabAddCourse;
    private FloatingActionButton fabAddQuestion;
    private FrameLayout frameLayout;
    private Context context;
    private  SubGroupRecyclerViewAdapter adapter;
    private Font font;
    private String groupTitle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_group_activity);
        context=this;
        font=new Font();
        groupTitle=getIntent().getExtras().get("groupName").toString();
        setTitle(getString(R.string.group)+ " "+groupTitle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        recyclerVieSubGroups =(RecyclerView)findViewById(R.id.recycler_view_sub_groups);
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerVieSubGroups.setLayoutManager(new LinearLayoutManager(context));
        //recyclerVieSubGroups.addItemDecoration(new DividerItemDecorationRecyclerView(10));
        adapter=new SubGroupRecyclerViewAdapter(context,createSubGroup());
        recyclerVieSubGroups.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerVieSubGroups);
        //for change color of item when click
        recyclerVieSubGroups.addOnItemTouchListener(new RecyclerTouchListener(context,recyclerVieSubGroups, new ClickListener() {
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

        floatingActionsMenu=(FloatingActionsMenu)findViewById(R.id.sub_group_fab_add);
        fabAddSubGroup=(FloatingActionButton)findViewById(R.id.sub_fab_sub_group);
        fabAddCourse=(FloatingActionButton)findViewById(R.id.sub_fab_course);
        fabAddQuestion=(FloatingActionButton)findViewById(R.id.sub_fab_question);
        frameLayout=(FrameLayout)findViewById(R.id.containerFloatingActionMenu);
        frameLayout.setClickable(false);
        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.background_after_click_FAM));
                frameLayout.setClickable(true);
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        floatingActionsMenu.collapse();
                    }
                });
            }

            @Override
            public void onMenuCollapsed() {
                frameLayout.setBackgroundColor(Color.TRANSPARENT);
                frameLayout.setClickable(false);
            }
        });
        fabAddSubGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(context)
                        .title(R.string.dialog_sub_group_add_new_sub_group_title)
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

        fabAddCourse.setOnClickListener(new View.OnClickListener() {
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

        fabAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFloatingActionMenu();
                Intent intent=new Intent(context, CreateQuestionActivity.class);
                startActivity(intent);
            }
        });

    }

    private void closeFloatingActionMenu(){
        frameLayout.setBackgroundColor(Color.TRANSPARENT);
        floatingActionsMenu.collapse();
    }

    private String[] createListOfSubGroupsOfAGroup(){
        String[] subGroupTitle=new String[2];
        subGroupTitle[0]="فیزیولوژی";
        subGroupTitle[1]="دهان و دندان";
        return subGroupTitle;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_back_menu, menu);
        if(Utilities.getInstance().isRTL()) {
            menu.getItem(0).setIcon(R.mipmap.back_fa);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else {
            menu.getItem(0).setVisible(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==android.R.id.home)
            finish();
        if (id==R.id.action_back)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }


    private ArrayList<Group> createSubGroup(){
        ArrayList<Group> groups=new ArrayList<>();
        //group:1
        RandomColor rnd1 = new RandomColor();
        int color1 = rnd1.randomColor();
        Group group1=new Group(0);
        group1.setTitle("فیزیولوژی");
        group1.setDescription("توضیحاتی در مورد این بخش");
        group1.setColor(color1);
        groups.add(group1);
        //group:2
        RandomColor rnd2 = new RandomColor();
        int color2 = rnd2.randomColor();
        Group group2=new Group(1);
        group2.setTitle("دهان و دندان");
        group2.setDescription("توضیحاتی در مورد این بخش");
        group2.setColor(color2);
        groups.add(group2);
        return groups;
    }
}
