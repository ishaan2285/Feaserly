package com.ishaan.feaserly;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ishaan.feaserly.Api.JsonplaceHolderforactivity1;
import com.ishaan.feaserly.Files.AsteriskPasswordTransformationMethod;
import com.ishaan.feaserly.Files.PostforViewpager;
import com.ishaan.feaserly.SharedPreferences.Savesharedpreference;
import com.ishaan.feaserly.SharedPreferences.WelcomePage;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {
    ImageButton b1;
    private Button button;
    String Token;
    private TextInputLayout edittext3;
    private TextInputEditText firstname,lastname,email,contactnumber,password,confirmpassword;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        getSupportActionBar().hide();
        TextInputEditText edittext = (TextInputEditText) findViewById(R.id.Password1);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        retrofit = new Retrofit.Builder()
                .baseUrl("http://13.56.153.188:3000/v6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonplaceHolderforactivity1 jsonplaceHolderforactivity1 = retrofit.create(JsonplaceHolderforactivity1.class);
        Call<PostforViewpager> call =jsonplaceHolderforactivity1.getViewpager("");
        call.enqueue(new Callback<PostforViewpager>() {
            @Override
            public void onResponse(Call<PostforViewpager> call, Response<PostforViewpager> response) {
                if (!response.isSuccessful()) {
                    Log.i("Code:", "" + response.code());
                } else {
                    PostforViewpager posts = response.body();
                    Log.i("data", "" + posts.getData());
                    Log.i("category", "" + posts.getData().getAccessToken());
                    String token = posts.getData().getAccessToken();
                    Token=token;
                    //Savesharedpreference.setLoggedIn(getApplicationContext(), true);
                    Savesharedpreference.setToken(getApplicationContext(), token);
                    /*SharedPreferences shrd= getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor=shrd.edit();

                    editor.putString("str", token);
                    editor.apply();
                    editor.commit();*/
                }
            }
            @Override
            public void onFailure(Call<PostforViewpager> call, Throwable t) {
                t.getMessage();
            }
        });

        TextInputEditText edittext1 = (TextInputEditText) findViewById(R.id.confirmPassword1);
        edittext1.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        edittext3= findViewById(R.id.Email);
        firstname = findViewById(R.id.Firstname1);
        lastname = findViewById(R.id.Lastname1);
        lastname = findViewById(R.id.Lastname1);
        contactnumber = findViewById(R.id.phone_number1);
        password = findViewById(R.id.Password1);
        email = findViewById(R.id.Email1);
        confirmpassword = findViewById(R.id.confirmPassword1);
        b1 = (ImageButton)findViewById(R.id.imageButton2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstname.getText().toString().trim().equalsIgnoreCase("")) {
                    edittext3.setError("Invalid Email Address");

                }else{
                    startActivity( new Intent(Signup.this, Otpverification.class));
                    String Firstname=firstname.getText().toString();
                    String Lastname=lastname.getText().toString();
                    String Email=email.getText().toString();
                    String Contactnumber=contactnumber.getText().toString();
                    String Password=password.getText().toString();
                    String Confirmpassword=confirmpassword.getText().toString();
                    Log.i("First", ""+Firstname);
                    Log.i("Last", ""+Lastname);
                    Log.i("Email", ""+Email);
                    Log.i("Contact", ""+Contactnumber);
                    Log.i("confirmpassword", ""+confirmpassword);

                    RequestBody body = null;
                    Log.i("body", ""+ body);
                    try {
                        JSONObject jsonObject3 = new JSONObject();
                        Log.i("jsonobject", ""+ jsonObject3);
                        jsonObject3.put("email", Email); // true
                        jsonObject3.put("name", "$name $lastName"); // true
                        jsonObject3.put("first_name",Firstname); // true
                        jsonObject3.put("last_name", Lastname); // true
                        jsonObject3.put("password", Confirmpassword); // true
                        jsonObject3.put("country_code", "+91");//false
                        jsonObject3.put("mobile_number", Contactnumber); // false
                        jsonObject3.put("address", ""); // false
                        jsonObject3.put("user_type", 1); // true
                        jsonObject3.put("latitude", ""); // false
                        jsonObject3.put("longitude", ""); // false
                        jsonObject3.put(
                                "device_id", "test_id"
                        );  // true
                        jsonObject3.put(
                                "device_token", "test_token"
                        );  // true
                        jsonObject3.put("device_type", "android");
                        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(jsonObject3.toString()));
                    }
                    catch (Exception e){

                    }
                    Call<PostforViewpager> call =jsonplaceHolderforactivity1.signup(Savesharedpreference.getToken(Signup.this), body);
                    call.enqueue(new Callback<PostforViewpager>() {
                        @Override
                        public void onResponse(Call<PostforViewpager> call, Response<PostforViewpager> response) {
                            if (!response.isSuccessful()) {
                                Log.i("Code:", "" + response.code());
                            } else {
                                PostforViewpager posts = response.body();
                                Log.i("data", "" + posts.getData());
                                Log.i("category", "" + posts.getData().getAccessToken());
                                String token = posts.getData().getAccessToken();
                                //Savesharedpreference.setLoggedIn(getApplicationContext(), true);
                    /*SharedPreferences shrd= getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor=shrd.edit();

                    editor.putString("str", token);
                    editor.apply();
                    editor.commit();*/
                            }
                        }
                        @Override
                        public void onFailure(Call<PostforViewpager> call, Throwable t) {
                            t.getMessage();
                            Log.i("fail", ""+t.getMessage().toString());
                            Log.i("fail1", ""+call.clone());
                        }
                    });
                }
            }
        });
    }
}
