package com.ddm.iclean.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ddm.iclean.R;
import com.ddm.iclean.dto.DtoUser;
import com.ddm.iclean.helpers.UsuarioAdapter;
import com.ddm.iclean.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaUsuariosActivity extends AppCompatActivity {
    private static final String TAG = "ListaUsuariosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        buscaDados();
    }

    private void preencherRecyclerview(List<DtoUser> lista){
        RecyclerView mRecyclerView = findViewById(R.id.rv_todos_usuarios);
        UsuarioAdapter mAdapter = new UsuarioAdapter(this, lista);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void buscaDados(){
        SharedPreferences sp = getSharedPreferences("dados", 0);
        String token = sp.getString("token", null);

        RetrofitService.getServico(this).todosUsuarios(token).enqueue(new Callback<List<DtoUser>>() {
            @Override
            public void onResponse(Call<List<DtoUser>> call, Response<List<DtoUser>> response) {
                if(response.body() == null){
                    Toast.makeText(ListaUsuariosActivity.this,"Erro: Você ainda não logou",Toast.LENGTH_LONG).show();
                    return;
                }
                List<DtoUser> lista = response.body();
                preencherRecyclerview(lista);
            }

            @Override
            public void onFailure(Call<List<DtoUser>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
