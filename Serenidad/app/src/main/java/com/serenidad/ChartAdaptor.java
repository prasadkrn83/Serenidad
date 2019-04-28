package com.serenidad;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChartAdaptor extends RecyclerView.Adapter<ChartAdaptor.InvoiceViewHolder> {

    private String dates;
    private String glasses;
    private ArrayList<String> names;
    private FragmentManager fragmentManager;

    ChartAdaptor(String dates, ArrayList<String> names, String glasses,
                FragmentManager fragmentManager) {
        this.names = names;
        this.dates = dates;
        this.glasses = glasses;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chart_adaptor, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InvoiceViewHolder holder, int position) {

        holder.name.setText(names.get(position));
        holder.txt_thought_date.setText(dates);

        if (names.get(position).contains("Thought")) {
            holder.text.setText("Awesome");
        } else {
            holder.text.setText(glasses);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//                if (names.get(holder.getAdapterPosition()).contains("Thought")) {
//                    Fragment fragment = new ThoughtFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("date", dates);
//                    fragment.setArguments(bundle);
//
//                    transaction.replace(R.id.frameLayout, fragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                } else {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("date", dates);
//                    bundle.putString("glasses", glasses);
//                    MessagesFragment fragment2 = new MessagesFragment();
//                    fragment2.setArguments(bundle);
//
//                    transaction.replace(R.id.frameLayout, fragment2);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class InvoiceViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView txt_thought_date;
        private TextView text;

        InvoiceViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            txt_thought_date = itemView.findViewById(R.id.txt_thought_date);
            text = itemView.findViewById(R.id.text);
        }
    }
}
