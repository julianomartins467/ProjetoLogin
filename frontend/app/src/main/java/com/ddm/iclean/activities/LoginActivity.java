package com.ddm.iclean.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ddm.iclean.R;
import com.ddm.iclean.dto.DtoLogin;
import com.ddm.iclean.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void logar(View view) {
        String email = ((EditText)findViewById(R.id.et_login_email)).getText().toString();
        String senha = ((EditText)findViewById(R.id.et_login_senha)).getText().toString();

        DtoLogin dtoLogin = new DtoLogin();
        dtoLogin.setEmail(email);
        dtoLogin.setSenha(senha);

        RetrofitService.getServico(this).login(dtoLogin).enqueue(new Callback<DtoLogin>() {
            @Override
            public void onResponse(Call<DtoLogin> call, Response<DtoLogin> response) {
                if(response.body() == null){
                    Toast.makeText(LoginActivity.this,"Usuario e/ou senha incorreta",Toast.LENGTH_LONG).show();
                    return;
                }
                Long id = response.body().getId();
                String token = response.body().getToken();
                Toast.makeText(LoginActivity.this, "Usuário logado", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, id.toString(), Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getSharedPreferences("dados", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putLong("id", id);
                editor.putString("token", "Bearer "+token);
                editor.apply();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<DtoLogin> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
