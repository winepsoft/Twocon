package winep.ir.twocon.Presenter.StatisticsPage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/8/2016.
 */
public class StatisticsActivity extends AppCompatActivity {

    private LineChart chart1;
    private PieChart chart2;
    private PieChart chart3;
    private String groupTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupTitle=getIntent().getExtras().getString("groupTitle");
        setContentView(R.layout.statistics_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        forceRTLIfSupported();

        chart1=(LineChart)findViewById(R.id.chart1);
        chart2=(PieChart)findViewById(R.id.chart2);
        chart3=(PieChart)findViewById(R.id.chart3);

        //LineChart Set Data
        ArrayList<String> xLine=new ArrayList<>();
        xLine.add("0");
        xLine.add("1");
        xLine.add("2");
        xLine.add("3");
        xLine.add("4");
        xLine.add("5");
        xLine.add("6");
        xLine.add("7");
        xLine.add("8");
        xLine.add("9");
        xLine.add("10");
        xLine.add("11");
        xLine.add("12");
        xLine.add("13");
        xLine.add("14");
        ArrayList<Entry> yLine=new ArrayList<>();
        yLine.add(new Entry(15f,0));
        yLine.add(new Entry(5f,1));
        yLine.add(new Entry(10f,2));
        yLine.add(new Entry(30f,3));
        yLine.add(new Entry(21f,4));
        yLine.add(new Entry(80f,5));
        yLine.add(new Entry(5f,6));
        yLine.add(new Entry(15f,7));
        yLine.add(new Entry(20f,8));
        yLine.add(new Entry(0f,9));
        yLine.add(new Entry(15f,10));
        yLine.add(new Entry(10f,11));
        yLine.add(new Entry(21f,12));
        yLine.add(new Entry(25f,13));
        yLine.add(new Entry(7f,14));
        LineDataSet datset=new LineDataSet(yLine,"LineChart");
        datset.setColors(ColorTemplate.COLORFUL_COLORS);
        LineData lineData=new LineData(xLine,datset);
        chart1.setData(lineData);


        //PieChart Set Data
        ArrayList<String> x=new ArrayList<>();
        x.add("data0");
        x.add("data1");
        x.add("data2");
        x.add("data3");
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(18.5f,0));
        entries.add(new Entry(26.7f, 1));
        entries.add(new Entry(24.0f,2));
        entries.add(new Entry(30.8f,3));
        PieDataSet set = new PieDataSet(entries,"Election Results");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(x,set);
        chart2.setData(data);
        chart3.setData(data);

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
            getSupportActionBar().setTitle("آمار گروه"+" "+groupTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            getSupportActionBar().setTitle("Statistics Group of"+" "+groupTitle);
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
