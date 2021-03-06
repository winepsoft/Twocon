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
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.DataModel.Level;
import winep.ir.mymemory.MainPage;
import winep.ir.mymemory.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class LevelActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLevel;
    private LinearLayoutManager linearLayoutManagerRecycler;
    private Context context;
    private ArrayList<Level> allLevels;
    private FrameLayout frameLayout;
    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton fabAddQuestion;
    private FloatingActionButton fabAddFreeQuestions;
    private FloatingActionButton fabAddQuestionsFromExcel;
    private TextView txtSuccessLearnValue;
    private TextView txtReadyNumber;
    private TextView txtTotalNumber;
    private String courseTitle;
    private ScrollView scrollViewLevelPage;
    private LinearLayout layoutLevelSuccessLearn;




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
        txtReadyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,getString(R.string.level_ready_message)+"138",Toast.LENGTH_LONG).show();
            }
        });

        txtTotalNumber=(TextView)findViewById(R.id.txt_total);
        Utilities.getInstance().setFontTextView(context,txtTotalNumber);
        txtTotalNumber.setText(getString(R.string.level_total)+": "+"192");
        txtTotalNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,getString(R.string.level_total_message)+"192",Toast.LENGTH_LONG).show();

            }
        });

        layoutLevelSuccessLearn=(LinearLayout)findViewById(R.id.layout_level_success_learn);
        layoutLevelSuccessLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,getString(R.string.level_success_learn_message)+"897",Toast.LENGTH_LONG).show();
            }
        });

        recyclerViewLevel=(RecyclerView)findViewById(R.id.recycler_view_level);
        linearLayoutManagerRecycler=new LinearLayoutManager(context);
        recyclerViewLevel.setLayoutManager(linearLayoutManagerRecycler);
        //recyclerViewLevel.addItemDecoration(new DividerItemDecorationRecyclerView(10));
        LevelRecyclerViewAdapter adapter=new LevelRecyclerViewAdapter(context,createLevel());
        recyclerViewLevel.setAdapter(adapter);

        scrollViewLevelPage=(ScrollView)findViewById(R.id.scroll_view_level_page);
        floatingActionsMenu=(FloatingActionsMenu)findViewById(R.id.level_fab_add);
        fabAddQuestion=(FloatingActionButton)findViewById(R.id.level_fab_question);
        fabAddFreeQuestions=(FloatingActionButton)findViewById(R.id.level_fab_add_free_question);
        fabAddQuestionsFromExcel=(FloatingActionButton)findViewById(R.id.level_fab_add_question_from_excel);
        frameLayout=(FrameLayout)findViewById(R.id.containerFloatingActionMenu);
        frameLayout.setClickable(false);

        scrollViewLevelPage.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(scrollViewLevelPage.getScrollY()>0){
                    floatingActionsMenu.setVisibility(View.GONE);
                }
                else if(scrollViewLevelPage.getScrollY()==0){
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                }
            }
        });
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

        fabAddFreeQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFloatingActionMenu();
                Intent intent = new Intent(context, MainPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("show flash tab",true);
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

        Level level4=new Level();
        level4.setLevelNumber(4);
        level4.setLevelTotalQuestion(0);
        level4.setLevelReadyQuestion(0);
        level4.setLevelStatus(2);
        allLevels.add(level4);


        Level level5=new Level();
        level5.setLevelNumber(5);
        level5.setLevelTotalQuestion(0);
        level5.setLevelReadyQuestion(0);
        level5.setLevelStatus(2);
        allLevels.add(level5);

        Level level6=new Level();
        level6.setLevelNumber(6);
        level6.setLevelTotalQuestion(0);
        level6.setLevelReadyQuestion(0);
        level6.setLevelStatus(2);
        allLevels.add(level6);

        Level level7=new Level();
        level7.setLevelNumber(7);
        level7.setLevelTotalQuestion(0);
        level7.setLevelReadyQuestion(0);
        level7.setLevelStatus(2);
        allLevels.add(level7);

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
