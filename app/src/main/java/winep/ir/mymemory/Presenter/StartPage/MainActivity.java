package winep.ir.mymemory.Presenter.StartPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rey.material.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 12/3/2016.
 */
public class MainActivity extends AppCompatActivity {

    //private RecyclerView recyclerViewSelectLanguage;
    //private StartPageRecyclerViewAdapter adapter;
    private Spinner spinnerSelectLanguage;
    private Button btnGo;
    private Context context;
    private String languge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page_activity);
        context=this;

         /*recyclerViewSelectLanguage =(RecyclerView)findViewById(R.id.recycler_view_start_page_select_language);
        recyclerViewSelectLanguage.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewSelectLanguage.addItemDecoration(new ItemDecorationWithLineRecyclerView(context, LinearLayoutManager.VERTICAL));
        adapter=new StartPageRecyclerViewAdapter(context,Utilities.getInstance().getAllLanguageTitleList(context));
        recyclerViewSelectLanguage.setAdapter(adapter);*/

        spinnerSelectLanguage=(Spinner)findViewById(R.id.spinner_start_page_select_language);
        ArrayAdapter<String> a = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Utilities.getInstance().getAllLanguageTitleListByTitle(context));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectLanguage.setAdapter(a);
        spinnerSelectLanguage.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getSelectedItem().toString().equals(getString(R.string.persian))) {
                    Utilities.getInstance().setLocale(context,"fa");
                }
                else if(adapterView.getSelectedItem().toString().equals(getString(R.string.english))){
                    Utilities.getInstance().setLocale(context,"en");
                }
                else if(adapterView.getSelectedItem().toString().equals(getString(R.string.arabic))){
                    Utilities.getInstance().setLocale(context,"ar");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        btnGo=(Button)findViewById(R.id.btn_start_page_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,TourActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);
    }
}
