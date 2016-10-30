package winep.ir.twocon.Presenter.LevelPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Level;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class LevelActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLevel;
    private Context context;
    private ArrayList<Level> allLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getIntent().getExtras().get("courseName").toString());
        context=this;
        allLevels=new ArrayList<Level>();

        recyclerViewLevel=(RecyclerView)findViewById(R.id.recycler_view_level);
        recyclerViewLevel.setLayoutManager(new LinearLayoutManager(context));
        //recyclerViewLevel.addItemDecoration(new DividerItemDecorationRecyclerView(10));
        LevelRecyclerViewAdapter adapter=new LevelRecyclerViewAdapter(context,createLevel());
        recyclerViewLevel.setAdapter(adapter);
    }

    private ArrayList<Level> createLevel(){
        Level level1=new Level();
        level1.setLevelNumber(1);
        level1.setLevelTotalQuestion(138);
        level1.setLevelReadyQuestion(138);
        level1.setLevelStatus(0);
        allLevels.add(level1);

        Level level2=new Level();
        level2.setLevelNumber(2);
        level2.setLevelTotalQuestion(54);
        level2.setLevelReadyQuestion(0);
        level2.setLevelStatus(1);
        allLevels.add(level2);

        Level level3=new Level();
        level3.setLevelNumber(3);
        level3.setLevelTotalQuestion(0);
        level3.setLevelReadyQuestion(0);
        level3.setLevelStatus(2);
        allLevels.add(level3);

        return allLevels;
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
