package com.serenidad;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class ChartAdaptor extends RecyclerView.Adapter<ChartAdaptor.InvoiceViewHolder> {

    private ArrayList<Acitvity> acts;
    private FragmentManager fragmentManager;

    ChartAdaptor( ArrayList<Acitvity> acts,FragmentManager fragmentManager) {
        this.acts = acts;
        this.fragmentManager = fragmentManager;
    }
    View v;
    ViewGroup k;
    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chart_adaptor, parent, false);
        k = parent;
        v = view;
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InvoiceViewHolder holder, int position) {

        holder.name.setText(acts.get(position).getActTitle());
        holder.txt_thought_date.setText(acts.get(position).getActDate());

        String uri = "@drawable/"+acts.get(position).getActImage();  // where myresource (without the extension) is the file
        int imageResource = v.getResources().getIdentifier(uri, null, k.getContext().getPackageName());
        holder.img.setImageResource(imageResource);
        holder.text.setText(acts.get(position).getActResult());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if (acts.get(holder.getAdapterPosition()).getActType().equalsIgnoreCase("H")) {
//                    Intent intent = new Intent(v.getContext(),WaterEntryActivity.class);
//                    intent.putExtra("id",acts.get(holder.getAdapterPosition()).getId());
//                    v.getContext().startActivity(intent);
                } else if(acts.get(holder.getAdapterPosition()).getActType().equalsIgnoreCase("D")){
                    Intent intent = new Intent(v.getContext(),WaterEntryActivity.class);
                    intent.putExtra("id",acts.get(holder.getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
                else{

                    Intent intent = new Intent(v.getContext(),ProgressThoughtLogActivity.class);
                    intent.putExtra("id",acts.get(holder.getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return acts.size();
    }

    class InvoiceViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView txt_thought_date;
        private TextView text;
        private ImageView img;

        InvoiceViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            txt_thought_date = itemView.findViewById(R.id.txt_thought_date);
            text = itemView.findViewById(R.id.text);
            img = itemView.findViewById(R.id.imgId);

        }
    }
}
