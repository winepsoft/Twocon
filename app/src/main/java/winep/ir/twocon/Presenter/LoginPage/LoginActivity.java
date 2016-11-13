package winep.ir.twocon.Presenter.LoginPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import winep.ir.twocon.R;
import com.facebook.FacebookSdk;


/**
 * Created by ShaisteS on 11/12/2016.
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.log_in_activity);
    }
}