      package winep.ir.mymemory.Presenter.QuestionPage;

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

      import java.util.ArrayList;

      import github.nisrulz.stackedhorizontalprogressbar.StackedHorizontalProgressBar;
      import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
      import winep.ir.mymemory.DataModel.Question;
      import winep.ir.mymemory.Presenter.ObserverPackage.setClickOnKnowButton;
      import winep.ir.mymemory.Presenter.ObserverPackage.setClickOnKnowButtonListener;
      import winep.ir.mymemory.Presenter.ServerConnectionHandler;
      import winep.ir.mymemory.R;
      import winep.ir.mymemory.Utility.Utilities;

      /**
 * Created by ShaisteS on 10/14/2016.
 */
public class QuestionActivity extends AppCompatActivity
              implements  Animator.AnimatorListener {

    private Context context;
    private ServerConnectionHandler serverConnectionHandler;
    private ArrayList<Question> allQuestions;
    private ViewPager questionViewPager;
    private boolean mIsInAnimation;
    private long mMotionBeginTime;
    private float mLastMotionX;
    private StackedHorizontalProgressBar progressBarAnswerStatus;
    private TextView txtAllQuestionNumber;
    private Button btnKnow;
    private Button btnNOKnow;
    private int knowNumber=0;
    private int noKnowNumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.question_activity_title));
        context=this;
        serverConnectionHandler=new ServerConnectionHandler(context);
        allQuestions=new ArrayList<>();
        allQuestions=serverConnectionHandler.createQuestion();
        questionViewPager = (ViewPager) findViewById(R.id.pager);
        questionViewPager.setAdapter(new QuestionPagerAdapter(allQuestions,this));
        progressBarAnswerStatus=(StackedHorizontalProgressBar)findViewById(R.id.progress_bar_question_process);
        progressBarAnswerStatus.setMax(allQuestions.size());
        progressBarAnswerStatus.setProgress(knowNumber);
        progressBarAnswerStatus.setSecondaryProgress(noKnowNumber);
        txtAllQuestionNumber=(TextView)findViewById(R.id.txt_all_question_number);
        Utilities.getInstance().setFontTextView(context,txtAllQuestionNumber);
        txtAllQuestionNumber.setText(Integer.toString(allQuestions.size()));
        btnKnow=(Button)findViewById(R.id.btn_know);
        btnKnow.setVisibility(View.INVISIBLE);
        btnNOKnow=(Button)findViewById(R.id.btn_no_know);
        btnNOKnow.setVisibility(View.INVISIBLE);
        setClickOnKnowButton.setClickOnKnowButtonListener(new setClickOnKnowButtonListener() {
            @Override
            public void clickOnKnowButton() {
                btnKnow.setVisibility(View.VISIBLE);
                btnNOKnow.setVisibility(View.VISIBLE);
            }
        });
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knowNumber++;
                progressBarAnswerStatus.setProgress(knowNumber);
                progressBarAnswerStatus.setSecondaryProgress(noKnowNumber);
                nextQuestion(true);
                btnNOKnow.setVisibility(View.INVISIBLE);
                btnKnow.setVisibility(View.INVISIBLE);

            }
        });

        btnNOKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noKnowNumber++;
                progressBarAnswerStatus.setProgress(knowNumber);
                progressBarAnswerStatus.setSecondaryProgress(noKnowNumber);
                nextQuestion(true);
                btnNOKnow.setVisibility(View.INVISIBLE);
                btnKnow.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
      super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
      Utilities.getInstance().setSettingLanguage(newBase);

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

    public void nextQuestion(boolean nextStatus) {
      if (mIsInAnimation) return;
      ObjectAnimator anim;

      if (!nextStatus ) {
          if (!hasPrevPage()) return;
          anim = ObjectAnimator.ofFloat(this, "motionX", 0, questionViewPager.getWidth());
      }
      else if (nextStatus) {
          if (!hasNextPage()) return;
          anim = ObjectAnimator.ofFloat(this, "motionX", 0, -questionViewPager.getWidth());
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

      questionViewPager.dispatchTouchEvent(ev);
    }

    private boolean hasPrevPage() {
      return questionViewPager.getCurrentItem() > 0;
    }

    private boolean hasNextPage() {
      return questionViewPager.getCurrentItem() + 1 < questionViewPager.getAdapter().getCount();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }
}

