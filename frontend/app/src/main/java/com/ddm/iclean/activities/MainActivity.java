package com.ddm.iclean.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ddm.iclean.R;
import com.ddm.iclean.entity.ResponseEntitity;
import com.ddm.iclean.services.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(item.getItemId()){
            /*case R.id.action_alteracao_usuario:
                startActivity(new Intent(this, AlteracaoUsuarioActivity.class));
                return true;*/
            case R.id.action_cadastro_de_usuario:
                startActivity(new Intent(this, CadastroDeUsuarioActivity.class));
                return true;
            case R.id. action_excluir_usuario:
                startActivity(new Intent(this, ExcluirUsuarioActivity.class));
                return true;
            case R.id.action_lista_usuarios:
                startActivity(new Intent(this, ListaUsuariosActivity.class));
                return true;
            case R.id.action_login:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
