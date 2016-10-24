package winep.ir.twocon.Presenter.CoursePage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.lzyzsd.randomcolor.RandomColor;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Course;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.DividerItemDecorationRecyclerView;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class CourseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Context context;
    private RecyclerView recyclerViewCourses;
    private ArrayList<Course> allCourse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);
        context = this;
        setTitle(getIntent().getExtras().get("subGroupName").toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerViewCourses=(RecyclerView)findViewById(R.id.recycler_view_course);
        allCourse=new ArrayList<Course>();
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewCourses.addItemDecoration(new DividerItemDecorationRecyclerView(5));
        CourseRecyclerViewAdapter adapter=new CourseRecyclerViewAdapter(context,createCourse());
        recyclerViewCourses.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerViewCourses);
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
        if(Utilities.getInstance().isRTL())
            menu.getItem(0).setIcon(R.mipmap.back_fa);
        else
            menu.getItem(0).setIcon(R.mipmap.back_en);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
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
