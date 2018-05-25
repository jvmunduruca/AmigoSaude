package br.com.amigosaude.app.amigosaude;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Tel_Card extends RecyclerView.Adapter<Tel_Card.ViewHolder>{
    private Context mCtx;
    private List<Tel> telList;

    public Tel_Card(Context mCtx, List<Tel> telList) {
        this.mCtx = mCtx;
        this.telList = telList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.tel_card_layout,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tel tel = telList.get(position);
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(tel.getImage(),null));
        holder.textViewDescricao.setText(tel.getDescricao());
        holder.textViewTelefone.setText(tel.getTelefone());

    }

    @Override
    public int getItemCount() {
        return telList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewDescricao, textViewTelefone;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.icone);
            textViewDescricao = itemView.findViewById(R.id.tel_descricao);
            textViewTelefone = itemView.findViewById(R.id.tel_numero);
        }
    }

}
