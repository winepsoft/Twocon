package winep.ir.mymemory.Presenter.CoursePage;

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
import winep.ir.mymemory.DataModel.Course;
import winep.ir.mymemory.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.ClickListener;
import winep.ir.mymemory.Utility.Font;
import winep.ir.mymemory.Utility.RecyclerTouchListener;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class CourseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Context context;
    private RecyclerView recyclerViewCourses;
    private ArrayList<Course> allCourse;
    private CourseRecyclerViewAdapter adapter;
    private FrameLayout frameLayout;
    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton fabAddCourse;
    private FloatingActionButton fabAddQuestion;
    private Font font;
    private String subGroupTitle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);
        context = this;
        font=new Font();
        subGroupTitle=getIntent().getExtras().get("subGroupName").toString();

        setTitle(getString(R.string.sub_group)+ " "+subGroupTitle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        recyclerViewCourses=(RecyclerView)findViewById(R.id.recycler_view_course);
        allCourse=new ArrayList<Course>();
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(context));
        //recyclerViewCourses.addItemDecoration(new DividerItemDecorationRecyclerView(5));
        adapter=new CourseRecyclerViewAdapter(context,createCourse());
        recyclerViewCourses.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerViewCourses);
        recyclerViewCourses.addOnItemTouchListener(new RecyclerTouchListener(context,recyclerViewCourses, new ClickListener() {
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

        floatingActionsMenu=(FloatingActionsMenu)findViewById(R.id.course_fab_add);
        fabAddCourse=(FloatingActionButton)findViewById(R.id.course_fab_course);
        fabAddQuestion=(FloatingActionButton)findViewById(R.id.course_fab_question);
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

        fabAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(context)
                        .title(R.string.dialog_course_add_new_course_title)
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

    private ArrayList<Course> createCourse(){

        RandomColor rnd1 = new RandomColor();
        int color1 = rnd1.randomColor();
        Course course1=new Course(0);
        course1.setTitle("زبان");
        course1.setDescription("توضیحاتی در مورد این بخش");
        course1.setColor(color1);
        allCourse.add(course1);

        RandomColor rnd2 = new RandomColor();
        int color2 = rnd2.randomColor();
        Course course2=new Course(1);
        course2.setTitle("دندان جلو");
        course2.setDescription("توضیحاتی در مورد این بخش");
        course2.setColor(color2);
        allCourse.add(course2);
        return allCourse;

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

}
