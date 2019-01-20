package com.nvmanh.haivl.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADING = 0;

    private Context mContext;
    private List<Post> mPosts;
    private OnLikeListener mOnLikeListener;

    public PostAdapter(Context context, List<Post> posts, OnLikeListener onLikeListener) {
        mContext = context;
        mPosts = posts;
        mOnLikeListener = onLikeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_post, parent, false);
            return new ViewHolderItem(view, mOnLikeListener);
        } else {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_loadmore_progress, parent, false);
            return new ViewHolderLoading(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ViewHolderItem) {
            ((ViewHolderItem) holder).bindView(mPosts.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mPosts.get(position) != null ? 1 : 0;
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.size() : 0;
    }

    public void addData(List<Post> posts) {
        int oldSize = mPosts.size();
        if(mPosts.size() != 0) {
            mPosts.remove(mPosts.size() - 1);
        }
        mPosts.addAll(posts);
        mPosts.add(null);
        notifyItemRangeInserted(oldSize, mPosts.size());

        Log.d("kiemtra", mPosts.size() + "");
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageUserAvatar;
        private TextView mTextDisplayName;
        private TextView mTextPostTime;
        private TextView mTextPostContent;
        private ImageView mImagePostImage;
        private CheckBox mCheckBoxLike;
        private TextView mTextNumberLike;
        private TextView mTextNumberComment;
        private OnLikeListener mOnLikeListener;

        public ViewHolderItem(View itemView, OnLikeListener onLikeListener) {
            super(itemView);
            mImageUserAvatar = itemView.findViewById(R.id.image_user_avatar);
            mTextDisplayName = itemView.findViewById(R.id.text_display_name);
            mTextPostTime = itemView.findViewById(R.id.text_post_time);
            mTextPostContent = itemView.findViewById(R.id.text_post_content);
            mImagePostImage = itemView.findViewById(R.id.image_post_image);
            mCheckBoxLike = itemView.findViewById(R.id.checkbox_like);
            mTextNumberLike = itemView.findViewById(R.id.text_likes);
            mTextNumberComment = itemView.findViewById(R.id.text_comments);
            mCheckBoxLike.setOnClickListener(this);
            mOnLikeListener = onLikeListener;
        }

        private void bindView(Post post) {
            if(!post.getOwner().getAvatar().equals("")) {
                Glide.with(mContext).load(post.getOwner().getAvatar()).into(mImageUserAvatar);
            }
            mTextDisplayName.setText(post.getOwner().getDisplayName());
            mTextPostTime.setText(post.getTime());
            mTextPostContent.setText(post.getContent());
            Glide.with(mContext).load(post.getImagePath()).into(mImagePostImage);
            if(post.isLike()) {
                mCheckBoxLike.setChecked(true);
            } else {
                mCheckBoxLike.setChecked(false);
            }
            mTextNumberLike.setText(String.valueOf(post.getNumberLike()));
            mTextNumberComment.setText(String.valueOf(post.getNumberComment()));
        }

        @Override
        public void onClick(View view) {
            boolean isLike = mCheckBoxLike.isChecked();
            mOnLikeListener.onLike(mPosts.get(getAdapterPosition()).getId(), isLike);
            mPosts.get(getAdapterPosition()).setLike(isLike);
            if(isLike) {
                mPosts.get(getAdapterPosition()).setNumberLike(mPosts.get(getAdapterPosition()).getNumberLike() + 1);
            } else {
                mPosts.get(getAdapterPosition()).setNumberLike(mPosts.get(getAdapterPosition()).getNumberLike() - 1);
            }
            mTextNumberLike.setText(String.valueOf(mPosts.get(getAdapterPosition()).getNumberLike()));
        }
    }

    public class ViewHolderLoading extends RecyclerView.ViewHolder {

        public ViewHolderLoading(View itemView) {
            super(itemView);
        }
    }

    public interface OnLikeListener {
        void onLike(int postId, boolean isLike);
    }
}
