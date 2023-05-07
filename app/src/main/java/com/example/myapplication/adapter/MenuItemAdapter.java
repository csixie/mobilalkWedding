package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.MenuActivity;
import com.example.myapplication.model.MenuItem;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> implements Filterable {


    private ArrayList<MenuItem> weddingMenuItemsData;
    private ArrayList<MenuItem> weddingMenuItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> itemsData) {


        this.weddingMenuItemsData = itemsData;
        this.weddingMenuItemsDataAll = itemsData;
        this.mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.element_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemAdapter.ViewHolder holder, int position) {


        MenuItem currentItem = weddingMenuItemsData.get(position);
        holder.bindTo(currentItem);

        if (holder.getAdapterPosition() > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();

        }

    }

    @Override
    public int getItemCount() {


        return weddingMenuItemsData.size();

    }

    @Override
    public Filter getFilter() {


        return menuFilter;

    }

    public Filter menuFilter = new Filter() {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<MenuItem> filteredList = new ArrayList<>();
            FilterResults filterResults = new FilterResults();

            if (constraint == null || constraint.length() == 0) {

                filterResults.count = weddingMenuItemsDataAll.size();
                filterResults.values = weddingMenuItemsDataAll;

            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();

                for (MenuItem item : weddingMenuItemsDataAll) {

                    if (item.getName().toLowerCase().contains(filterPattern)) {

                        filteredList.add(item);

                    }

                }

                filterResults.count = filteredList.size();
                filterResults.values = filteredList;

            }

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {


            weddingMenuItemsData = (ArrayList) results.values;
            notifyDataSetChanged();

        }

    };

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPlaceText;
        private ImageView mItemImage;
        private RatingBar mRatingBar;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);

             mTitleText = itemView.findViewById(R.id.itemTitle);
             mInfoText = itemView.findViewById(R.id.subTitle);
             mPlaceText = itemView.findViewById(R.id.itemPlace);
             mItemImage = itemView.findViewById(R.id.itemImage);
             mRatingBar = itemView.findViewById(R.id.itemRatingbar);

        }

        public void bindTo(MenuItem currentItem) {


            mTitleText.setText(currentItem.getName());
            mInfoText.setText(currentItem.getInfo());
            mPlaceText.setText(currentItem.getPlace());
            mRatingBar.setRating(currentItem.getRatedInfo());

            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);
            itemView.findViewById(R.id.favourite).setOnClickListener(view -> ((MenuActivity)mContext).updateAlertIcon(currentItem));
            itemView.findViewById(R.id.delete).setOnClickListener(view -> ((MenuActivity)mContext).deleteItem(currentItem));

        }
    }

}

