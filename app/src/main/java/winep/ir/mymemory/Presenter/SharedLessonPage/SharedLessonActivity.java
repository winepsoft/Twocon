package winep.ir.mymemory.Presenter.SharedLessonPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.DataModel.Course;
import winep.ir.mymemory.Presenter.CoursePage.CourseRecyclerViewAdapter;
import winep.ir.mymemory.Presenter.ServerConnectionHandler;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.MyApplication;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/21/2016.
 */
public class SharedLessonActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerViewCourses;
    private ArrayList<Course> allSharedLessons;
    private CourseRecyclerViewAdapter adapter;
    private MyApplication myApplication;
    private ServerConnectionHandler serverConnectionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);
        context = this;
        serverConnectionHandler=new ServerConnectionHandler(context);
        myApplication = new MyApplication();

        setTitle(getString(R.string.shared_lesson_page_title));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewCourses=(RecyclerView)findViewById(R.id.recycler_view_course);
        allSharedLessons=new ArrayList<Course>();
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(context));
        //recyclerViewCourses.addItemDecoration(new DividerItemDecorationRecyclerView(5));
        adapter=new CourseRecyclerViewAdapter(context,serverConnectionHandler.createCourse());
        recyclerViewCourses.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerViewCourses);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }
}
