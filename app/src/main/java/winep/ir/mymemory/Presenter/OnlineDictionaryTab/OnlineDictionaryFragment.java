package winep.ir.mymemory.Presenter.OnlineDictionaryTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rey.material.widget.Spinner;

import winep.ir.mymemory.DataModel.Question;
import winep.ir.mymemory.Presenter.CreateQuestionPage.CreateQuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class OnlineDictionaryFragment extends Fragment {

    private Spinner spinnerFromLanguage;
    private Spinner spinnerToLanguage;
    private Button btnAddMemoryBank;
    private EditText editTextTranslateFrom;
    private TextView txtTranslateTo;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.online_dictionary_fragment, container, false);
        context=getContext();
        spinnerFromLanguage=(Spinner)mainView.findViewById(R.id.spinner_translation_page_from);
        spinnerToLanguage=(Spinner)mainView.findViewById(R.id.spinner_translation_page_to);
        ArrayAdapter<String> a = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Utilities.getInstance().getAllLanguageTitle(context));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromLanguage.setAdapter(a);
        spinnerToLanguage.setAdapter(a);

        txtTranslateTo=(TextView)mainView.findViewById(R.id.txt_translation_page_to_text);
        btnAddMemoryBank=(Button)mainView.findViewById(R.id.btn_translation_page_add_memory_bank);
        btnAddMemoryBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question aQuestion=new Question();
                Intent intent=new Intent();
                intent.setClass(context, CreateQuestionActivity.class);
                context.startActivity(intent);
            }
        });


        return mainView;
    }
}
