package com.example.artistrygallery;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private TypedArray images;
    private String[] titles;
    private String[] artist;
    private int[] year;
    private int rowLayout;
    private Context pContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic;
        public TextView title;
        public TextView time;
        public TextView num;

        public ViewHolder(View itemView){
            super(itemView);
            pic=(ImageView) itemView.findViewById(R.id.picture);
            title=(TextView) itemView.findViewById(R.id.titl);
            time=(TextView) itemView.findViewById(R.id.tim);
            num=(TextView) itemView.findViewById(R.id.number);
        }
    }

    public myAdapter(TypedArray images,String[] titles,String[] artist,int[] year,int rowLayout,Context context){
        this.images=images;
        this.titles=titles;
        this.artist=artist;
        this.year=year;
        this.rowLayout=rowLayout;
        this.pContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){

        viewHolder.pic.setImageResource(images.getResourceId(i,-1));
        viewHolder.title.setText(titles[i]);
        viewHolder.time.setText(artist[i]);
        viewHolder.num.setText(String.valueOf(year[i]));

        viewHolder.pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(pContext, DetailsArt.class);
                intent.putExtra("title", titles[i]);
                Log.d("TitleDebug", "Title sent: Chicken stroganoff");
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return images==null ? 0:images.length();
    }


}
