package winep.ir.twocon.Presenter.LevelSettingPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rey.material.widget.Slider;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.LevelSetting;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.DividerItemDecorationRecyclerView;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/17/2016.
 */
public class LevelSettingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLevelSetting;
    private Slider sliderLevel;
    private ArrayList<LevelSetting> allLevelsInformation;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_setting_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.level_setting_activity_title));

        context=this;
        allLevelsInformation=new ArrayList<>();

        recyclerViewLevelSetting=(RecyclerView)findViewById(R.id.recycler_view_level_setting);
        sliderLevel=(Slider)findViewById(R.id.slider_level);

        recyclerViewLevelSetting.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewLevelSetting.addItemDecoration(new DividerItemDecorationRecyclerView(5));
        final LevelSettingRecyclerViewAdapter adapter=new LevelSettingRecyclerViewAdapter(context,createLevel());
        recyclerViewLevelSetting.setAdapter(adapter);

        sliderLevel.setValue(8,true);
        sliderLevel.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider slider, boolean b, float v, float v1, int i, int i1) {
                if(i1>i){
                    for(int j=0;j<i1-i;j++){
                        LevelSetting newLevel=new LevelSetting(adapter.getItemCount()+2,0,0);
                        adapter.addNewItem(newLevel);
                    }

                }
                 else if(i1<i){
                    int counter=i-i1;
                    int size=adapter.getItemCount()-1;
                    for(int j=0;j<counter;j++){
                        adapter.removeItem(size);
                        size=size-1;
                    }
                }

            }
        });




    }

    private ArrayList<LevelSetting> createLevel(){
        LevelSetting levelSetting1=new LevelSetting(1,0,2);
        allLevelsInformation.add(levelSetting1);
        LevelSetting levelSetting2=new LevelSetting(2,0,2);
        allLevelsInformation.add(levelSetting2);
        LevelSetting levelSetting3=new LevelSetting(3,0,2);
        allLevelsInformation.add(levelSetting3);
        LevelSetting levelSetting4=new LevelSetting(4,0,2);
        allLevelsInformation.add(levelSetting4);
        LevelSetting levelSetting5=new LevelSetting(5,0,2);
        allLevelsInformation.add(levelSetting5);
        LevelSetting levelSetting6=new LevelSetting(6,0,2);
        allLevelsInformation.add(levelSetting6);
        LevelSetting levelSetting7=new LevelSetting(7,0,2);
        allLevelsInformation.add(levelSetting7);
        LevelSetting levelSetting8=new LevelSetting(8,0,2);
        allLevelsInformation.add(levelSetting8);
        return allLevelsInformation;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }

}
