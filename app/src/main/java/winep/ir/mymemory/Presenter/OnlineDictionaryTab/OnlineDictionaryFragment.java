package winep.ir.mymemory.Presenter.OnlineDictionaryTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class OnlineDictionaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.online_dictionary_fragment, container, false);
        return mainView;
    }
}
