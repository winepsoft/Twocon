package winep.ir.mymemory.Presenter.OnlineDictionaryTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rey.material.widget.Spinner;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.DataModel.Question;
import winep.ir.mymemory.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class OnlineDictionaryActivity extends AppCompatActivity {

    private Spinner spinnerFromLanguage;
    private Spinner spinnerToLanguage;
    private Button btnAddMemoryBank;
    private EditText editTextTranslateFrom;
    private TextView txtTranslateTo;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_dictionary_activity);
        context=this;

        setTitle(getString(R.string.dictionary));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerFromLanguage=(Spinner)findViewById(R.id.spinner_translation_page_from);
        spinnerToLanguage=(Spinner)findViewById(R.id.spinner_translation_page_to);
        ArrayAdapter<String> a = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Utilities.getInstance().getAllLanguageTitle(context));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromLanguage.setAdapter(a);
        spinnerToLanguage.setAdapter(a);

        txtTranslateTo=(TextView)findViewById(R.id.txt_translation_page_to_text);
        btnAddMemoryBank=(Button)findViewById(R.id.btn_translation_page_add_memory_bank);
        btnAddMemoryBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question aQuestion=new Question();
                Intent intent=new Intent();
                intent.setClass(context, CreateQuestionActivity.class);
                context.startActivity(intent);
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
