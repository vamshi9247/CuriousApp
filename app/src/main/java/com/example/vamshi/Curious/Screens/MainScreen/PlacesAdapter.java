package com.example.vamshi.Curious.Screens.MainScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.vamshi.Curious.Model.Retrofit.QueryConstants;
import com.example.vamshi.Curious.PlacesEntity;
import com.example.vamshi.Curious.R;
import com.example.vamshi.Curious.Screens.PlacedetailsScreen.PlaceDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlacesEntity> listItems;
    private Context context;
    private MainContractor.PresenterMainWork mainPresenter;
    private String preferenceKey;

    public PlacesAdapter(List<PlacesEntity> listItems,
                         Context context,
                         MainContractor.PresenterMainWork mainPresenter) {
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

        PlacesEntity placeEntity = listItems.get(position);

        String iconUrl = placeEntity.getPrefixicon() +
                QueryConstants.IMAGE_SIZE +
                placeEntity.getSuffixicon();

        preferenceKey = placeEntity.getPlaceId();
        ViewHolder1 holder1 = (ViewHolder1) holder;

        holder1.textViewName.setText(placeEntity.getName());
        holder1.textViewCategory.setText(placeEntity.getFormattedAddress());
        holder1.Distance.setText(placeEntity.getDistance() + "");
        holder1.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlaceDetails.class);
            intent.putExtra(QueryConstants.MAIN_INTENT_DETAIL, placeEntity);
            context.startActivity(intent);
        });

        Picasso.get().load(iconUrl).into(holder1.imageView);

        if (mainPresenter.isFavourite(preferenceKey)) {
            holder1.favourite.setChecked(true);
        } else {
            holder1.favourite.setChecked(false);
        }

        holder1.favourite.
                setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
                    if (isChecked) {
                        mainPresenter.addFavourite(preferenceKey);
                    } else {
                        mainPresenter.removeFavourite(preferenceKey);
                    }
                });
    }

    @Override
    public int getItemCount() {
        if (listItems == null) {
            return 0;
        } else {
            return listItems.size();
        }
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

        View itemView;

        public ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
