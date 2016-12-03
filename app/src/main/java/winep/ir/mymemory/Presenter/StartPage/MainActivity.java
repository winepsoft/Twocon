package winep.ir.mymemory.Presenter.StartPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rey.material.widget.Button;

import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 12/3/2016.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSelectLanguage;
    private StartPageRecyclerViewAdapter adapter;
    private Button btnGo;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page_activity);
        context=this;

        recyclerViewSelectLanguage =(RecyclerView)findViewById(R.id.recycler_view_start_page_select_language);
        btnGo=(Button)findViewById(R.id.btn_start_page_go);

        recyclerViewSelectLanguage.setLayoutManager(new LinearLayoutManager(context));
        adapter=new StartPageRecyclerViewAdapter(context,Utilities.getInstance().getAllLanguageTitleList(context));
        recyclerViewSelectLanguage.setAdapter(adapter);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,TourActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
