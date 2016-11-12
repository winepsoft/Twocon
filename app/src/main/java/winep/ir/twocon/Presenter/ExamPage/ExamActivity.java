package winep.ir.twocon.Presenter.ExamPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rey.material.widget.Button;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Exam;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/14/2016.
 */
public class ExamActivity extends AppCompatActivity {

    private ArrayList<Exam> exam;
    private Context context;
    private Button btnExamEnd;
    private Button btnExamNext;
    private Button btnExamPreview;
    private TextView txtExamProcess;
    private ProgressView progressBarExamProcess;
    private int allQuestionTest=0;
    private int currentQuestion=0;
    private ViewPager examViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        allQuestionTest=getIntent().getExtras().getInt("examQuestionNumber");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.exam_activity_title));
        context=this;

        exam=new ArrayList<>();
        examViewPager=(ViewPager)findViewById(R.id.exam_pager);
        examViewPager.setAdapter(new ExamPagerAdapter(createExam(allQuestionTest),this));


        btnExamEnd=(Button)findViewById(R.id.exam_end);
        btnExamNext=(Button)findViewById(R.id.exam_next);
        btnExamPreview=(Button)findViewById(R.id.exam_preview);
        progressBarExamProcess =(ProgressView)findViewById(R.id.progress_bar_exam_process);
        txtExamProcess=(TextView)findViewById(R.id.txt_exam_process);
        Utilities.getInstance().setFontTextView(context,txtExamProcess);

        final double perecent;
        if (allQuestionTest!=0) {
            perecent = (double) 1 / (double) allQuestionTest;
            btnExamPreview.setVisibility(View.GONE);
        }
        else
            perecent=0;

        progressBarExamProcess.setProgress(0);
        progressBarExamProcess.setSecondaryProgress(allQuestionTest);
        txtExamProcess.setText(Integer.toString(currentQuestion)+"/"+Integer.toString(allQuestionTest));


        btnExamNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestion<allQuestionTest){
                    currentQuestion=currentQuestion+1;
                    txtExamProcess.setText(Integer.toString(currentQuestion)+"/"+Integer.toString(allQuestionTest));
                    progressBarExamProcess.setProgress(progressBarExamProcess.getProgress()+(float) perecent);
                    if (currentQuestion==1)
                        btnExamPreview.setVisibility(View.VISIBLE);
                }
                else
                    btnExamNext.setVisibility(View.GONE);

            }
        });

        btnExamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestion>0){
                    currentQuestion=currentQuestion-1;
                    txtExamProcess.setText(currentQuestion+"/"+allQuestionTest);
                    progressBarExamProcess.setProgress(progressBarExamProcess.getProgress()-(float) perecent);
                    if (currentQuestion<allQuestionTest)
                        btnExamNext.setVisibility(View.VISIBLE);

                }
                else
                    btnExamPreview.setVisibility(View.GONE);

            }
        });

    }

    private ArrayList<Exam> createExam(int examQuestionNumber){
        Exam aQuestion=new Exam();
        aQuestion.setQuestion("Question");
        aQuestion.setAnswerOne("Answer1");
        aQuestion.setAnswerTwo("Answer2");
        aQuestion.setAnswerThree("Answer3");
        aQuestion.setAnswerFour("Answer4");
        for (int i=0;i<examQuestionNumber;i++)
            exam.add(i,aQuestion);
        return exam;
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
