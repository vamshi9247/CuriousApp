package com.example.vamshi.homwayprojectchallenge;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.vamshi.homwayprojectchallenge.Retrofit.QueryConstants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Placesinfo> listItems;
    private Context context;
    private ContractPresenterView.PresenterMainWork mainPresenter;
    private String preferenceKey;

    public PlacesAdapter(List<Placesinfo> listItems, Context context, ContractPresenterView.PresenterMainWork mainPresenter) {
        this.listItems = listItems;
        this.context = context;
        this.mainPresenter = mainPresenter;
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);
                return new ViewHolder1(v);



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

          final Placesinfo placesinfo = listItems.get(position);

          String iconUrl = placesinfo.getPrefixicon()+ QueryConstants.IMAGE_SIZE+ placesinfo.getSuffixicon();
          preferenceKey= placesinfo.getPlaceId();

            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.textViewName.setText(placesinfo.getName());
            holder1.textViewCategory.setText(placesinfo.getFormattedAddress());
            holder1.Distance.setText(placesinfo.getDistance()+"");
            Picasso.get().load(iconUrl).into(holder1.imageView);

            if(mainPresenter.isFavourite(preferenceKey)){
                holder1.favourite.setChecked(true);
            }else {
                holder1.favourite.setChecked(false);
            }

            holder1.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        mainPresenter.addFavourite(preferenceKey);
                    }else{
                        mainPresenter.removeFavouite(preferenceKey);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {

        if (listItems == null){
            return 0;}
        else{
            return listItems.size();}
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.textName)
        TextView textViewName;
        @BindView(R.id.textCategory)
        TextView textViewCategory;
        @BindView(R.id.distance)
        TextView Distance;
        @BindView(R.id.favourite)
        ToggleButton favourite;



        public ViewHolder1(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openPlaceDeatails = new Intent(context,PlaceDetails.class);
                    context.startActivity(openPlaceDeatails);
                }
            });
           ButterKnife.bind(this,itemView);



        }


    }




}
