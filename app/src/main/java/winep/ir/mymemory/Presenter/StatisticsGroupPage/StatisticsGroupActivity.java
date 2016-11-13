package winep.ir.mymemory.Presenter.StatisticsGroupPage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/8/2016.
 */
public class StatisticsGroupActivity extends AppCompatActivity {

    private LineChart chart1;
    private PieChart chart2;
    private PieChart chart3;
    private String groupTitle;
    private TextView txtLeftTopValue;
    private TextView txtLeftBottomValue;
    private TextView txtRightTopValue;
    private TextView txtRightBottomValue;
    private TextView txtLeftTopTitle;
    private TextView txtLeftBottomTitle;
    private TextView txtRightTopTitle;
    private TextView txtRightBottomTitle;
    private Context context;
    private Boolean pieChartsShowStatus;
    private LinearLayout layoutPieCharts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupTitle=getIntent().getExtras().getString("groupTitle");
        pieChartsShowStatus=getIntent().getExtras().getBoolean("pieChartStatus");
        setContentView(R.layout.statistics_group_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        forceRTLIfSupported();
        context=this;

        chart1=(LineChart)findViewById(R.id.chart1);
        chart2=(PieChart)findViewById(R.id.chart2);
        chart3=(PieChart)findViewById(R.id.chart3);
        layoutPieCharts=(LinearLayout)findViewById(R.id.layoutPieCharts);
        if(!pieChartsShowStatus){
            layoutPieCharts.setVisibility(View.GONE);
        }

        //Title
        txtLeftTopTitle =(TextView)findViewById(R.id.txt_title_left_top);
        txtLeftTopTitle.setText(getString(R.string.create_date));

        txtLeftBottomTitle =(TextView)findViewById(R.id.txt_title_left_bottom);
        txtLeftBottomTitle.setText(getString(R.string.last_seen));

        txtRightTopTitle =(TextView)findViewById(R.id.txt_title_right_top);
        txtRightTopTitle.setText(getString(R.string.all_cart));

        txtRightBottomTitle =(TextView)findViewById(R.id.txt_title_right_bottom);
        txtRightBottomTitle.setText(getString(R.string.ready_to_learn));


        //Value
        txtLeftTopValue =(TextView)findViewById(R.id.txt_value_left_top);
        Utilities.getInstance().setFontTextView(context, txtLeftTopValue);
        txtLeftTopValue.setText("95/07/07");
        txtLeftTopValue.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


        txtLeftBottomValue =(TextView)findViewById(R.id.txt_value_left_bottom);
        Utilities.getInstance().setFontTextView(context, txtLeftBottomValue);
        txtLeftBottomValue.setText("95/08/10");
        txtLeftBottomValue.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


        txtRightTopValue =(TextView)findViewById(R.id.txt_value_right_top);
        Utilities.getInstance().setFontTextView(context, txtRightTopValue);
        txtRightTopValue.setText("200");

        txtRightBottomValue =(TextView)findViewById(R.id.txt_value_right_bottom);
        Utilities.getInstance().setFontTextView(context, txtRightBottomValue);
        txtRightBottomValue.setText("1000");





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
            getSupportActionBar().setTitle("آمار"+" "+groupTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            getSupportActionBar().setTitle("Statistics of"+" "+groupTitle);
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
