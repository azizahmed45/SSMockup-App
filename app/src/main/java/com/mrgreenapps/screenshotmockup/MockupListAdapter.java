package com.mrgreenapps.screenshotmockup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.mrgreenapps.screenshotmockup.api.models.Mockup;

import java.util.ArrayList;
import java.util.List;

public class MockupListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int VIEW_TYPE_MOCKUP = 0;
    private int VIEW_TYPE_LOADING = 1;

    private boolean isLoading = false;

    private OnMockupClickListener onMockupClickListener;

    private Context context;
    private List<Mockup> mockupList = new ArrayList<>();

    public MockupListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_MOCKUP) {
            View view = LayoutInflater.from(context).inflate( R.layout.mockup_item, parent, false);
            return new MockupViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate( R.layout.mockup_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_MOCKUP) {
            MockupViewHolder mockupViewHolder = (MockupViewHolder) holder;

            mockupViewHolder.mockupTitleView.setText(mockupList.get(position).name);

            if(mockupList.get(position).mockupFiles.size() > 0)
                Glide.with(context).load(mockupList.get(position).mockupFiles.get(0).url).into(mockupViewHolder.mockupImageView);

        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.shimmerFrameLayout.startShimmerAnimation();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == mockupList.size() - 1 && isLoading ? VIEW_TYPE_LOADING : VIEW_TYPE_MOCKUP;
    }

    @Override
    public int getItemCount() {
        return mockupList.size();
    }

    public void setLoading(boolean isLoading) {
        if (this.isLoading != isLoading) {
            this.isLoading = isLoading;
            if (isLoading) {
                mockupList.add(null);
                notifyItemInserted(mockupList.size() - 1);
            } else {
                mockupList.remove(mockupList.size() - 1);
                notifyItemRemoved(mockupList.size());
            }
        }
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void addMockupList(List<Mockup> mockupList) {
        this.mockupList.addAll(mockupList);
        notifyItemRangeInserted(this.mockupList.size(), mockupList.size());
    }

    public void setOnMockupClickListener(OnMockupClickListener onMockupClickListener) {
        this.onMockupClickListener = onMockupClickListener;
    }

    class MockupViewHolder extends RecyclerView.ViewHolder {

        ImageView mockupImageView;
        TextView mockupTitleView;

        public MockupViewHolder(@NonNull View itemView) {
            super(itemView);
            mockupImageView = itemView.findViewById(R.id.mockup_image);
            mockupTitleView = itemView.findViewById(R.id.mockup_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onMockupClickListener != null)
                        onMockupClickListener.onMockupClick(mockupList.get(getAdapterPosition()));
                }
            });
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {

        ShimmerFrameLayout shimmerFrameLayout;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer_view_container);
        }
    }

    interface OnMockupClickListener {
        void onMockupClick(Mockup mockup);
    }
}
