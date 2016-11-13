package winep.ir.mymemory.Presenter.LevelPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.DataModel.Level;
import winep.ir.mymemory.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class LevelActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLevel;
    private Context context;
    private ArrayList<Level> allLevels;
    private FrameLayout frameLayout;
    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton fabAddQuestion;
    private TextView txtSuccessLearnValue;
    private TextView txtReadyNumber;
    private TextView txtTotalNumber;
    private String courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        courseTitle=getIntent().getExtras().get("courseName").toString();
        setTitle(getString(R.string.level_title_page)+" "+courseTitle);
        context=this;
        allLevels=new ArrayList<Level>();

        txtSuccessLearnValue=(TextView)findViewById(R.id.txt_success_learn_value);
        Utilities.getInstance().setFontTextView(context,txtSuccessLearnValue);

        txtReadyNumber=(TextView)findViewById(R.id.txt_ready);
        Utilities.getInstance().setFontTextView(context,txtReadyNumber);
        txtReadyNumber.setText(getString(R.string.level_ready)+ ": "+"138");

        txtTotalNumber=(TextView)findViewById(R.id.txt_total);
        Utilities.getInstance().setFontTextView(context,txtTotalNumber);
        txtTotalNumber.setText(getString(R.string.level_total)+": "+"192");

        recyclerViewLevel=(RecyclerView)findViewById(R.id.recycler_view_level);
        recyclerViewLevel.setLayoutManager(new LinearLayoutManager(context));
        //recyclerViewLevel.addItemDecoration(new DividerItemDecorationRecyclerView(10));
        LevelRecyclerViewAdapter adapter=new LevelRecyclerViewAdapter(context,createLevel());
        recyclerViewLevel.setAdapter(adapter);

        floatingActionsMenu=(FloatingActionsMenu)findViewById(R.id.level_fab_add);
        fabAddQuestion=(FloatingActionButton)findViewById(R.id.level_fab_question);
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
        getMenuInflater().inflate(R.menu.level_page_menu, menu);
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
