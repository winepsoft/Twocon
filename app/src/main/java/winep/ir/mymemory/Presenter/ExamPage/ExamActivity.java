package winep.ir.mymemory.Presenter.ExamPage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.rey.material.widget.Button;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.DataModel.Exam;
import winep.ir.mymemory.DataModel.Question;
import winep.ir.mymemory.Presenter.ObserverPackage.setClickOnAnswerOfQuestionExam;
import winep.ir.mymemory.Presenter.ObserverPackage.setClickOnAnswerOfQuestionExamListener;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/14/2016.
 */
public class ExamActivity extends AppCompatActivity
        implements View.OnClickListener, Animator.AnimatorListener{

    private ArrayList<Exam> exam;
    private Context context;
    //private Button btnExamEnd;
    private Button btnExamNext;
    private Button btnExamPreview;
    private TextView txtExamProcess;
    private ProgressView progressBarExamProcess;
    private int previewPage=0;
    private int allQuestionTest=0;
    private int currentQuestion=0;
    private boolean swipePager =false;
    private double percent;
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
        //examViewPager.setOffscreenPageLimit(1);
        examViewPager.setAdapter(new ExamPagerAdapter(createExam(allQuestionTest),this));


        //btnExamEnd=(Button)findViewById(R.id.exam_end);
        btnExamNext=(Button)findViewById(R.id.exam_next);
        btnExamPreview=(Button)findViewById(R.id.exam_preview);
        progressBarExamProcess =(ProgressView)findViewById(R.id.progress_bar_exam_process);
        txtExamProcess=(TextView)findViewById(R.id.txt_exam_process);
        Utilities.getInstance().setFontTextView(context,txtExamProcess);


        if (allQuestionTest!=0) {
            percent = (double) 1 / (double) allQuestionTest;
            btnExamPreview.setVisibility(View.GONE);
            currentQuestion=1;
        }
        if (allQuestionTest==1){
            percent = (double) 1 / (double) allQuestionTest;
            btnExamNext.setVisibility(View.GONE);
            btnExamPreview.setVisibility(View.GONE);
        }
        if (allQuestionTest==0){
            btnExamNext.setVisibility(View.GONE);
            btnExamPreview.setVisibility(View.GONE);
            percent=0;


        }

        progressBarExamProcess.setProgress((float)percent);
        progressBarExamProcess.setSecondaryProgress(allQuestionTest);
        txtExamProcess.setText(Integer.toString(currentQuestion)+"/"+Integer.toString(allQuestionTest));

        examViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (swipePager){
                    if(previewPage<position)
                        nextQuestion();
                    else if(previewPage>position)
                        previewQuestion();
                    swipePager =false;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state== ViewPager.SCROLL_STATE_DRAGGING) {
                    previewPage = examViewPager.getCurrentItem();
                    swipePager =true;
                    //Toast.makeText(context,"Preview:"+Integer.toString(previewPage),Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnExamNext.setOnClickListener(this);
        btnExamPreview.setOnClickListener(this);
        /*btnExamNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examViewPager.setCurrentItem(examViewPager.getCurrentItem()+1);
                nextQuestion();

            }
        });

        btnExamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examViewPager.setCurrentItem(examViewPager.getCurrentItem()-1);
                previewQuestion();

            }
        });*/

        setClickOnAnswerOfQuestionExam.setClickOnAnswerOfQuestionExamListener(new setClickOnAnswerOfQuestionExamListener() {
            @Override
            public void clickOn() {
                //Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
                onClick(btnExamNext);
            }
        });


    }

    private ArrayList<Exam> createExam(int examQuestionNumber){
        for (int i=0;i<examQuestionNumber;i++){
            Question question=new Question();
            Exam aQuestion=new Exam();
            aQuestion.setQuestion(question);
            aQuestion.setAnswerOne("Answer1");
            aQuestion.setAnswerTwo("Answer2");
            aQuestion.setAnswerThree("Answer3");
            aQuestion.setAnswerFour("Answer4");
            aQuestion.setUserSelectAnswer(0);
            exam.add(aQuestion);
        }
        return exam;

    }

    private void nextQuestion(){
        if (currentQuestion<allQuestionTest){
            currentQuestion=currentQuestion+1;
            txtExamProcess.setText(Integer.toString(currentQuestion)+"/"+Integer.toString(allQuestionTest));
            progressBarExamProcess.setProgress(progressBarExamProcess.getProgress()+(float) percent);
            if (currentQuestion==2)
                btnExamPreview.setVisibility(View.VISIBLE);
            if (currentQuestion==allQuestionTest)
                btnExamNext.setVisibility(View.GONE);
        }
        else
            btnExamNext.setVisibility(View.GONE);

    }

    private void previewQuestion(){
        if (currentQuestion>1){
            currentQuestion=currentQuestion-1;
            txtExamProcess.setText(currentQuestion+"/"+allQuestionTest);
            progressBarExamProcess.setProgress(progressBarExamProcess.getProgress()-(float) percent);
            if (currentQuestion<allQuestionTest)
                btnExamNext.setVisibility(View.VISIBLE);
            if(currentQuestion==1)
                btnExamPreview.setVisibility(View.GONE);
        }
        else
            btnExamPreview.setVisibility(View.GONE);
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


    private boolean mIsInAnimation;
    private long mMotionBeginTime;
    private float mLastMotionX;

    @Override
    public void onClick(View view) {
        if (mIsInAnimation) return;
        ObjectAnimator anim;

        if (view == btnExamPreview) {
            if (!hasPrevPage()) return;
            anim = ObjectAnimator.ofFloat(this, "motionX", 0, examViewPager.getWidth());
        }
        else if (view == btnExamNext) {
            if (!hasNextPage()) return;
            anim = ObjectAnimator.ofFloat(this, "motionX", 0, -examViewPager.getWidth());
        }
        else return;

        anim.setInterpolator(new LinearInterpolator());
        anim.addListener(this);
        anim.setDuration(300);
        anim.start();

    }

    public void setMotionX(float motionX) {
        if (!mIsInAnimation) return;
        mLastMotionX = motionX;
        final long time = SystemClock.uptimeMillis();
        simulate(MotionEvent.ACTION_MOVE, mMotionBeginTime, time);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mIsInAnimation = false;
        final long time = SystemClock.uptimeMillis();
        simulate(MotionEvent.ACTION_UP, mMotionBeginTime, time);
    }

    @Override
    public void onAnimationStart(Animator animation) {
        mLastMotionX = 0;
        mIsInAnimation = true;
        final long time = SystemClock.uptimeMillis();
        simulate(MotionEvent.ACTION_DOWN, time, time);
        mMotionBeginTime = time;
    }

    // method from http://stackoverflow.com/a/11599282/1294681
    private void simulate(int action, long startTime, long endTime) {
        // specify the property for the two touch points
        MotionEvent.PointerProperties[] properties = new MotionEvent.PointerProperties[1];
        MotionEvent.PointerProperties pp = new MotionEvent.PointerProperties();
        pp.id = 0;
        pp.toolType = MotionEvent.TOOL_TYPE_FINGER;

        properties[0] = pp;

        // specify the coordinations of the two touch points
        // NOTE: you MUST set the pressure and size value, or it doesn't work
        MotionEvent.PointerCoords[] pointerCoords = new MotionEvent.PointerCoords[1];
        MotionEvent.PointerCoords pc = new MotionEvent.PointerCoords();
        pc.x = mLastMotionX;
        pc.pressure = 1;
        pc.size = 1;
        pointerCoords[0] = pc;

        final MotionEvent ev = MotionEvent.obtain(
                startTime, endTime, action, 1, properties,
                pointerCoords, 0,  0, 1, 1, 0, 0, 0, 0);

        examViewPager.dispatchTouchEvent(ev);
    }

    private boolean hasPrevPage() {
        return examViewPager.getCurrentItem() > 0;
    }

    private boolean hasNextPage() {
        return examViewPager.getCurrentItem() + 1 < examViewPager.getAdapter().getCount();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }
}
