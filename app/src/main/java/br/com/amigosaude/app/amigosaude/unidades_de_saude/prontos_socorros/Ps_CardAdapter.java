package br.com.amigosaude.app.amigosaude.unidades_de_saude.prontos_socorros;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

import br.com.amigosaude.app.amigosaude.R;

/**
 * Created by LAB on 05/06/2018.
 */

public class Ps_CardAdapter extends RecyclerView.Adapter<Ps_CardAdapter.ViewHolder>{
    private static final String TAG = "PS_CardAdapter";

    private Context mContext;
    private ArrayList<Ps> Postos;

    public Ps_CardAdapter(Context mContext, ArrayList<Ps> postos) {
        this.mContext = mContext;
        this.Postos = postos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ps_card_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: listando");
        Ps ps = Postos.get(position);
        holder.imagem.setImageDrawable(mContext.getResources().getDrawable(ps.getImage(),null));
        holder.nome.setText(ps.getNome());
        holder.endereco.setText(ps.getEndereco());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Click");
            }
        });


    }

    @Override
    public int getItemCount() {
        return Postos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagem;
        TextView nome, endereco;
        RelativeLayout parentLayout;



        public ViewHolder(View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.img);
            nome = itemView.findViewById(R.id.ps_nome);
            endereco = itemView.findViewById(R.id.ps_endereco);

        }
    }
}
