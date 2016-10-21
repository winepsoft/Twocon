package winep.ir.twocon.Presenter.FlashTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winep.ir.twocon.R;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class Flashfragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.flash_fragment, container, false);
        return mainView;
    }
}
