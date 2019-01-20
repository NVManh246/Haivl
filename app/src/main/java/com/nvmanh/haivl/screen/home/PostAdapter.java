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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHodler> {

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
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_post, parent, false);
        return new ViewHodler(view, mOnLikeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.bindView(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.size() : 0;
    }

    public void addData(List<Post> posts) {
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    public PostAdapter getIntance() {
        return this;
    }

    public class ViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageUserAvatar;
        private TextView mTextDisplayName;
        private TextView mTextPostTime;
        private TextView mTextPostContent;
        private ImageView mImagePostImage;
        private CheckBox mCheckBoxLike;
        private TextView mTextNumberLike;
        private TextView mTextNumberComment;
        private OnLikeListener mOnLikeListener;

        public ViewHodler(View itemView, OnLikeListener onLikeListener) {
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

    public interface OnLikeListener {
        void onLike(int postId, boolean isLike);
    }
}
