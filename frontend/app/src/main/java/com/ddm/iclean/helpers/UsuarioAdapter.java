package com.ddm.iclean.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.iclean.R;
import com.ddm.iclean.activities.AlteracaoUsuarioActivity;
import com.ddm.iclean.dto.DtoUser;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter <UsuarioAdapter.UsuarioHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<DtoUser> lista;
    //swipe
    private DtoUser mRecentlyDeletedItem;//elemento excluído da lista
    private int mRecentlyDeletedItemPosition;//posição do elemento excluído da lista

    public UsuarioAdapter(Context context, List<DtoUser> lista){
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
        String nome = lista.get(position).getNome();
        holder.nome.setText(nome);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder{
        final UsuarioAdapter usuarioAdapter;
        public final TextView nome;

        public UsuarioHolder (@NonNull View itemView, UsuarioAdapter usuarioAdapter){
            super(itemView);
            this.usuarioAdapter = usuarioAdapter;
            nome = itemView.findViewById(R.id.tv_recyclerview_nome_usuario);
        }
    }
}
