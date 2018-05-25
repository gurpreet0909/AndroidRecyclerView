package com.example.gurpreetsingh.retro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {


    //APITime[] items;
    Context context;
    private List<APITime> contactList;
    private List<APITime> contactListFiltered;
    private APITimesAdapterListener listener;

    public MyAdapter(Context context,List<APITime> contactList,APITimesAdapterListener listener){

        this.context = context;
        this.contactList= contactList;
        this.contactListFiltered=contactList;
        this.listener=listener;

      //  this.items = items;


    }



    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_layout,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

       // APITime myview = items[position];

        final APITime apiTime = contactListFiltered.get(position);

        Picasso.with(holder.imageView.getContext()).load(apiTime.getAvatarUrl()).into(holder.imageView);
        holder.textView.setText(apiTime.getLogin());

       // Picasso.with(holder.imageView.getContext()).load(apiTime.getAvatarUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("url",apiTime.getHtmlUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<APITime> filteredList = new ArrayList<>();
                    for (APITime row : contactList) {


                        if (row.getLogin().toLowerCase().contains(charString.toLowerCase()) || row.getId().toString().contains(charSequence)){
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<APITime>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface APITimesAdapterListener {
        void onItemSSelected(APITime contact);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;


        public MyViewHolder(View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
