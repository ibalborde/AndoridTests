package com.example.rosariorescue.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rosariorescue.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class    SocialLoginActivity extends AppCompatActivity {

    private static final String TAG = SocialLoginActivity.class.getSimpleName();

    private LoginButton loginButtonFacebook;
    private CircleImageView circleImageViewFacebook;
    private TextView textNameFacebook, textEmailFacebook;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private static final String PUBLIC_PROFILE = "public_profile";
    private int animal_position;
    private String animal_types;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //toolbar
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.baseline_keyboard_arrow_left_white_36);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginButtonFacebook = findViewById(R.id.login_button_facebook);
        textEmailFacebook = findViewById(R.id.profile_email);
        textNameFacebook = findViewById(R.id.profile_name);
        circleImageViewFacebook = findViewById(R.id.profile_pic);

        callbackManager = CallbackManager.Factory.create();
        loginButtonFacebook.setPermissions(Arrays.asList(EMAIL, PUBLIC_PROFILE));
        checkLoginStatus();

        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);

        goToShare();

    }

    public void goToShare(){
        if (getIntent().hasExtra("animal_type") && getIntent().hasExtra("animal_position")) {
            animal_types = getIntent().getStringExtra("animal_type");
            animal_position = getIntent().getIntExtra("animal_position", 0);
        }

        Log.d(TAG, "type and pos: " + animal_types + " " + animal_position );

        Intent myIntent = new Intent(this, SharePhotosActivity.class);
        myIntent.putExtra("animal_position", animal_position);
        myIntent.putExtra("animal_type", animal_types);
        startActivity(myIntent);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            if(currentAccessToken==null)
            {
                textNameFacebook.setText("");
                textEmailFacebook.setText("");
                circleImageViewFacebook.setImageResource(0);
                Toast.makeText(SocialLoginActivity.this,"User Logged out", Toast.LENGTH_LONG).show();
            }
            else
                loadUserProfile(currentAccessToken);
        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                Log.d("Facebook info: ", String.valueOf(response));
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                    textEmailFacebook.setText(email);
                    textNameFacebook.setText(first_name +" "+last_name);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(SocialLoginActivity.this).load(image_url).into(circleImageViewFacebook);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus() {
        if(AccessToken.getCurrentAccessToken() != null) {
            loadUserProfile(AccessToken.getCurrentAccessToken());
            goToShare();
        }
    }

}
