package br.com.amigosaude.app.amigosaude;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Tel_Card extends RecyclerView.Adapter<Tel_Card.ViewHolder>{

    private static final String TAG = "Tel_Card";

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Tel tel = telList.get(position);
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(tel.getImage(),null));
        holder.textViewDescricao.setText(tel.getDescricao());
        holder.textViewTelefone.setText(tel.getTelefone());

        holder.telOpcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: Clique no " + telList.get(position).getTelefone());
                String numero = telList.get(position).getTelefone();
                ligar(this, numero);

            }
        });

    }

    @Override
    public int getItemCount() {
        return telList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewDescricao, textViewTelefone;
        RelativeLayout telOpcao;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.icone);
            textViewDescricao = itemView.findViewById(R.id.tel_descricao);
            textViewTelefone = itemView.findViewById(R.id.tel_numero);
            telOpcao = itemView.findViewById(R.id.Tel_opcao);
        }

    }

    public void ligar(View.OnClickListener view, String n) {
        String uri = "tel: " + n; // Ver forma de pegar numero do item da lista
        Log.d(TAG,"ligar() " + n);
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse(uri));
        Log.d(TAG,"ligar: " + uri);
        if (ActivityCompat.checkSelfPermission(mCtx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else{
            mCtx.startActivity(intentLigar);
        }

    }






}
