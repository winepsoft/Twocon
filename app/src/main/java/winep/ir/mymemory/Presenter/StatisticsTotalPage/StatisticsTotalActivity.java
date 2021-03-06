package winep.ir.mymemory.Presenter.StatisticsTotalPage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.Presenter.PurchasedLessonPage.PurchasedLessonActivity;
import winep.ir.mymemory.Presenter.SharedLessonPage.SharedLessonActivity;
import winep.ir.mymemory.Presenter.StatisticsGroupPage.StatisticsGroupActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/1/2016.
 */
public class StatisticsTotalActivity extends AppCompatActivity {

    private LineChart chart1;
    private PieChart chart2;
    private PieChart chart3;
    private Context context;
    private TextView txtValuePurchased;
    private TextView txtTitlePurchased;
    private TextView txtValueSharedLesson;
    private TextView txtTitleSharedLesson;
    private TextView txtValueDownload;
    private TextView txtTitleDownload;
    private TextView txtValueGroup;
    private TextView txtTitleGroup;
    private TextView txtValueSubGroup;
    private TextView txtTitleSubGroup;
    private TextView txtValueLesson;
    private TextView txtTitleLesson;
    private TextView txtValueCard;
    private TextView txtTitleCard;
    private RelativeLayout layoutGroup;
    private RelativeLayout layoutSubGroup;
    private LinearLayout layoutCourse;
    private LinearLayout layoutPurchasedLesson;
    private RelativeLayout layoutSharedLesson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_total_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        forceRTLIfSupported();
        context = this;

        chart1 = (LineChart) findViewById(R.id.chart1);
        chart2 = (PieChart) findViewById(R.id.chart2);
        chart3 = (PieChart) findViewById(R.id.chart3);

        txtTitlePurchased=(TextView)findViewById(R.id.txt_title_flash_cart_right_top);
        txtTitlePurchased.setText(getString(R.string.statistics_total_purchased));
        txtValuePurchased=(TextView)findViewById(R.id.txt_value_flash_cart_right_top);
        Utilities.getInstance().setFontTextView(context,txtValuePurchased);
        txtValuePurchased.setText("12");

        txtTitleSharedLesson=(TextView)findViewById(R.id.txt_title_flash_cart_left_top);
        txtTitleSharedLesson.setText(getString(R.string.statistics_total_shared_lesson));
        txtValueSharedLesson=(TextView)findViewById(R.id.txt_value_flash_cart_left_top);
        Utilities.getInstance().setFontTextView(context,txtValueSharedLesson);
        txtValueSharedLesson.setText("28");

        txtTitleDownload=(TextView)findViewById(R.id.txt_title_flash_cart_left_bottom);
        txtTitleDownload.setText(getString(R.string.statistics_total_download));
        txtValueDownload=(TextView)findViewById(R.id.txt_value_flash_cart_left_bottom);
        Utilities.getInstance().setFontTextView(context,txtValueDownload);
        txtValueDownload.setText("32");


        txtTitleGroup =(TextView)findViewById(R.id.txt_title_left_top);
        txtTitleGroup.setText(getString(R.string.statistics_total_group));
        txtValueGroup =(TextView)findViewById(R.id.txt_value_left_top);
        Utilities.getInstance().setFontTextView(context, txtValueGroup);
        txtValueGroup.setText("3");

        txtTitleSubGroup =(TextView)findViewById(R.id.txt_title_left_bottom);
        txtTitleSubGroup.setText(getString(R.string.statistics_total_sub_group));
        txtValueSubGroup =(TextView)findViewById(R.id.txt_value_left_bottom);
        Utilities.getInstance().setFontTextView(context, txtValueSubGroup);
        txtValueSubGroup.setText("2");

        txtTitleLesson =(TextView)findViewById(R.id.txt_title_right_top);
        txtTitleLesson.setText(getString(R.string.statistics_total_lesson));
        txtValueLesson =(TextView)findViewById(R.id.txt_value_right_top);
        Utilities.getInstance().setFontTextView(context, txtValueLesson);
        txtValueLesson.setText("2");

        txtTitleCard =(TextView)findViewById(R.id.txt_title_right_bottom);
        txtTitleCard.setText(getString(R.string.statistics_total_card));
        txtValueCard =(TextView)findViewById(R.id.txt_value_right_bottom);
        Utilities.getInstance().setFontTextView(context, txtValueCard);
        txtValueCard.setText("100");

        layoutGroup=(RelativeLayout)findViewById(R.id.layoutLeftTop);
        layoutGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(R.string.statistics_total_group_dialog)
                        .items(getAllCategory())
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Intent intent=new Intent(context, StatisticsGroupActivity.class);
                                intent.putExtra("groupTitle",text);
                                startActivity(intent);

                            }
                        })
                        .show();
            }

        });

        layoutSubGroup=(RelativeLayout)findViewById(R.id.layoutLeftBottom);
        layoutSubGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(R.string.statistics_total_sub_group_dialog)
                        .items(getAllSubCategoris())
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Intent intent=new Intent(context, StatisticsGroupActivity.class);
                                intent.putExtra("groupTitle",text);
                                startActivity(intent);

                            }
                        })
                        .show();
            }

        });

        layoutCourse=(LinearLayout) findViewById(R.id.layoutRightTop);
        layoutCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(R.string.statistics_total_lesson_dialog)
                        .items(getAllCourse())
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Intent intent=new Intent(context, StatisticsGroupActivity.class);
                                intent.putExtra("groupTitle",text);
                                startActivity(intent);

                            }
                        })
                        .show();
            }

        });

        layoutPurchasedLesson=(LinearLayout)findViewById(R.id.layout_purchased_lesson);
        layoutPurchasedLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PurchasedLessonActivity.class);
                startActivity(intent);
            }
        });

        layoutSharedLesson=(RelativeLayout)findViewById(R.id.layout_shared_lessons);
        layoutSharedLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SharedLessonActivity.class);
                startActivity(intent);
            }
        });


        //LineChart Set Data
        ArrayList<String> xLine=new ArrayList<>();
        xLine.add("پزشکی");
        xLine.add("مهندسی پزشکی");
        xLine.add("englishTitle");
        ArrayList<Entry> yLine=new ArrayList<>();
        yLine.add(new Entry(20,0));
        yLine.add(new Entry(12,1));
        yLine.add(new Entry(10,2));
        LineDataSet datset=new LineDataSet(yLine,"LineChart");
        datset.setColors(ColorTemplate.COLORFUL_COLORS);
        LineData lineData=new LineData(xLine,datset);
        chart1.setData(lineData);
        chart1.setDescription("");


        //PieChart Set Data
        ArrayList<String> x=new ArrayList<>();
        x.add("فیزیولوژی");
        x.add("دهان و دندان");
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(65,0));
        entries.add(new Entry(35, 1));
        PieDataSet set = new PieDataSet(entries,"Election Results");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(x,set);
        chart2.setData(data);
        chart2.setCenterText(getString(R.string.sub_group));
        chart2.setDrawXValues(false);
        chart2.setDescription("");


        ArrayList<String> x1=new ArrayList<>();
        x1.add("زبان");
        x1.add("دندان جلو");
        ArrayList<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(85,0));
        entries1.add(new Entry(15, 1));
        PieDataSet set1 = new PieDataSet(entries1,"Election Results");
        set1.setColors(ColorTemplate.PASTEL_COLORS);
        PieData data1 = new PieData(x1,set1);
        chart3.setData(data1);
        chart3.setCenterText(getString(R.string.course));
        chart3.setDrawXValues(false);
        chart3.setDescription("");
    }

    private String[] getAllCategory(){
        String[] allCategories=new String[3];
        allCategories[0]="پزشکی";
        allCategories[1]="مهندسی پزشکی";
        allCategories[2]="English Title";
        return allCategories;
    }

    private String[] getAllSubCategoris(){
        String[] allSubcategories=new String[2];
        allSubcategories[0]="فیزیولوژی";
        allSubcategories[1]="دهان و دندان";
        return allSubcategories;
    }

    private String[] getAllCourse(){
        String[] allCourses=new String[2];
        allCourses[0]="زبان";
        allCourses[1]="دندان جلو";
        return allCourses;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(Utilities.getInstance().isRTL()) {
            getSupportActionBar().setTitle(getString(R.string.statistics_total_title));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            getSupportActionBar().setTitle(getString(R.string.statistics_total_title));
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
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);
    }
}
