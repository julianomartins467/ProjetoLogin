package com.ddm.iclean.helpers;

import android.content.Context;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.iclean.R;

import com.ddm.iclean.activities.ListaUsuariosActivity;
import com.ddm.iclean.dto.DtoUser;
import com.ddm.iclean.services.RetrofitService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioAdapter extends RecyclerView.Adapter <UsuarioAdapter.UsuarioHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<DtoUser> lista;

    //swipe
    private DtoUser mRecentlyDeletedItem;//elemento excluído da lista
    private int mRecentlyDeletedItemPosition;//posição do elemento excluído da lista

    public UsuarioAdapter(Context context, List<DtoUser> lista) {
        this.context = context;
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UsuarioAdapter.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerview_layout_item_todos_usuarios, parent, false);
        return new UsuarioHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioHolder holder, int position) {
        String nome = lista.get(position).getId() + "-" + lista.get(position).getNome();
        holder.nome.setText(nome);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {
        final UsuarioAdapter usuarioAdapter;
        public final TextView nome;
        private Button deleteButton;

        public UsuarioHolder(@NonNull View itemView, UsuarioAdapter usuarioAdapter) {
            super(itemView);
            this.usuarioAdapter = usuarioAdapter;
            nome = itemView.findViewById(R.id.tv_recyclerview_nome_usuario);

        }

               /*@Override
        public void onClick(View v) {
            String valor = lista.get(getLayoutPosition()).getId() + "";
        }*/
    }
}