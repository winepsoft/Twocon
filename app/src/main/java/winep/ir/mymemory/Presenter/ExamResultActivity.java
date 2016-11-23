package winep.ir.mymemory.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.rey.material.widget.Button;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Dialogs;
import winep.ir.mymemory.Utility.StaticParameters;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/17/2016.
 */
public class ExamResultActivity extends AppCompatActivity {

    private Context context;
    private PieChart resultChart;
    private TextView txtAllQuestionValue;
    private TextView txtCorrectAnswerValue;
    private TextView txtIncorrectAnswerValue;
    private TextView txtNoAnswerValue;
    private Button btnExamReview;
    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resualt_exam_activity);
        StaticParameters.getInstance().examResultContext=this;
        context = this;
        setTitle(getString(R.string.exam_result_page_title));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultChart=(PieChart)findViewById(R.id.result_chart);
        txtAllQuestionValue=(TextView)findViewById(R.id.txt_result_exam_all_question_value);
        Utilities.getInstance().setFontTextView(context,txtAllQuestionValue);
        txtCorrectAnswerValue=(TextView)findViewById(R.id.txt_result_exam_correct_answer_value);
        Utilities.getInstance().setFontTextView(context,txtCorrectAnswerValue);
        txtIncorrectAnswerValue=(TextView)findViewById(R.id.txt_result_exam_incorrect_answer_value);
        Utilities.getInstance().setFontTextView(context,txtIncorrectAnswerValue);
        txtNoAnswerValue =(TextView)findViewById(R.id.txt_result_exam_no_answer_value);
        Utilities.getInstance().setFontTextView(context, txtNoAnswerValue);
        btnExamReview=(Button)findViewById(R.id.btn_result_exam_review);
        btnRetry=(Button)findViewById(R.id.result_exam_retry);

        //Pie chart
        ArrayList<String> x=new ArrayList<>();
        x.add(getString(R.string.result_exam_no_answer));
        x.add(getString(R.string.result_exam_incorrect_answer));
        x.add(getString(R.string.result_exam_correct_answer));
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(3,0));
        entries.add(new Entry(5, 1));
        entries.add(new Entry(12, 1));
        PieDataSet set = new PieDataSet(entries,"");
        set.setColors(new int[]{R.color.gray_light,R.color.red,R.color.green_light},context);
        PieData data = new PieData(x,set);
        resultChart.setData(data);
        resultChart.setCenterText(getString(R.string.exam_result_page_title));
        resultChart.setDrawXValues(false);
        resultChart.setDescription("");

        //retry
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getInstance().showExamDialog(context);
            }
        });
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
