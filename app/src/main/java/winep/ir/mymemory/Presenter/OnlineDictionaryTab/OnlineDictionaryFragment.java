package winep.ir.mymemory.Presenter.OnlineDictionaryTab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rey.material.widget.Spinner;

import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class OnlineDictionaryFragment extends Fragment {

    private Spinner spinnerFromLanguage;
    private Spinner spinnerToLanguage;
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


        return mainView;
    }
}
