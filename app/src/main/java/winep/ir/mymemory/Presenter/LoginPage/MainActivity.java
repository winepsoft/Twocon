package winep.ir.mymemory.Presenter.LoginPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import winep.ir.mymemory.MainPage;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;


/**
 * Created by ShaisteS on 11/12/2016.
 */
public class MainActivity extends AppCompatActivity {


    private Button btnTakeTour;
    private Spinner spinnerSelectLanguage;
    private Context context;
    private CallbackManager mFacebookCallbackManager;
    private LoginButton mFacebookSignInButton;
    private TwitterLoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.log_in_activity);
        context=this;

        btnTakeTour=(Button)findViewById(R.id.btn_log_in_tour);
        spinnerSelectLanguage=(Spinner)findViewById(R.id.spinner_log_in_select_language);
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Utilities.getInstance().getAllLanguageTitle(context));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectLanguage.setAdapter(a);

        btnTakeTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MainPage.class);
                startActivity(intent);
            }
        });


        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


       mFacebookSignInButton = (LoginButton)findViewById(R.id.log_in_facebook);
        mFacebookSignInButton.registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        //TODO: Use the Profile class to get information about the current user.
                        /*handleSignInResult(new Callable<Void>() {
                            @Override
                            public Void call() throws Exception {
                                LoginManager.getInstance().logOut();
                                return null;
                            }
                        });*/
                    }

                    @Override
                    public void onCancel() {
                        //handleSignInResult(null);
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(MainActivity.class.getCanonicalName(), error.getMessage());
                        //handleSignInResult(null);
                    }
                }
        );
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}