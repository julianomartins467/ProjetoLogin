package com.ddm.iclean.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddm.iclean.R;
import com.ddm.iclean.dto.DtoUser;
import com.ddm.iclean.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcluirUsuarioActivity extends AppCompatActivity {
   private Button btExcluir;
   private Button btBuscar;
   private EditText edtId;
   private EditText edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_usuario);

        edtId = findViewById(R.id.edt_Id);
        edtNome = findViewById(R.id.edt_Nome);
        btBuscar = findViewById(R.id.btn_Buscar);
        btExcluir = findViewById(R.id.btn_Deletar);


        btBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final int Id = Integer.parseInt(edtId.getText().toString());
                SharedPreferences sp = getSharedPreferences("dados", 0);
                String token = sp.getString("token", null);

                RetrofitService.getServico(v.getContext()).buscaUsuario(Id, token).enqueue(new Callback<DtoUser>() {
                    @Override
                    public void onResponse(Call<DtoUser> call, Response<DtoUser> response) {
                        if(response.body() == null){
                            Toast.makeText(ExcluirUsuarioActivity.this,"Erro: usuario não encontrado",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(ExcluirUsuarioActivity.this,"Usuário localizado!",Toast.LENGTH_LONG).show();
                        DtoUser usuario = response.body();
                        edtNome.setText(usuario.getNome());
                        btExcluir.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<DtoUser> call, Throwable t) {

                    }
                });
            }
        });
        btExcluir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final int Id = Integer.parseInt(edtId.getText().toString());
                SharedPreferences sp = getSharedPreferences("dados", 0);
                String token = sp.getString("token", null);

                RetrofitService.getServico(v.getContext()).excluirUsuario(Id, token).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.body() == null){
                            Toast.makeText(ExcluirUsuarioActivity.this,"Erro: Você ainda não logou",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(ExcluirUsuarioActivity.this,"Exclusão realizada com sucesso",Toast.LENGTH_LONG).show();
                        edtId.setText("");
                        edtNome.setText("");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }

        });
    }
}
