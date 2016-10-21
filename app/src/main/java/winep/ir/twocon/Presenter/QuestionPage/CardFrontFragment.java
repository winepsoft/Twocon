package winep.ir.twocon.Presenter.QuestionPage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winep.ir.twocon.R;

/**
 * Created by ShaisteS on 10/17/2016.
 */
public class CardFrontFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_activity_card_front, container, false);
    }
}
