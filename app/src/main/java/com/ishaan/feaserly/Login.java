package com.ishaan.feaserly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.ishaan.feaserly.Api.JsonplaceHolderforactivity1;
import com.ishaan.feaserly.Files.AsteriskPasswordTransformationMethod;
import com.ishaan.feaserly.Files.PostforViewpager;
import com.ishaan.feaserly.SharedPreferences.Savesharedpreference;
import com.ishaan.feaserly.SharedPreferences.WelcomePage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    ImageButton b1,b2;
    Button button;
    TextInputEditText ed1,ed2;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        getSupportActionBar().hide();
        TextInputEditText edittext = (TextInputEditText) findViewById(R.id.Password);
        edittext.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        b1 = (ImageButton)findViewById(R.id.imageButton2);
        ed1 = (TextInputEditText) findViewById(R.id.Username1);
        ed2 = (TextInputEditText) findViewById(R.id.Password);
        button=(Button) findViewById(R.id.textView6);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://13.56.153.188:3000/v6/guest/")
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
                    Savesharedpreference.setLoggedIn(getApplicationContext(), true);
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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") && ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(Login.this, WelcomePage.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup();
            }

            private void Signup() {
                startActivity( new Intent(Login.this, Signup.class));
            }
        });
    }
}
