package com.nvmanh.haivl.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHodler> {

    private Context mContext;
    private List<Post> mPosts;

    public PostAdapter(Context context, List<Post> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_post, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.bindView(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.size() : 0;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        private ImageView mImageUserAvatar;
        private TextView mTextDisplayName;
        private TextView mTextPostTime;
        private TextView mTextPostContent;
        private ImageView mImagePostImage;
        private ImageView mImageLike;
        private TextView mTextNumberLike;
        private TextView mTextNumberComment;

        public ViewHodler(View itemView) {
            super(itemView);
            mImageUserAvatar = itemView.findViewById(R.id.image_user_avatar);
            mTextDisplayName = itemView.findViewById(R.id.text_display_name);
            mTextPostTime = itemView.findViewById(R.id.text_post_time);
            mTextPostContent = itemView.findViewById(R.id.text_post_content);
            mImagePostImage = itemView.findViewById(R.id.image_post_image);
            mImageLike = itemView.findViewById(R.id.image_like);
            mTextNumberLike = itemView.findViewById(R.id.text_likes);
            mTextNumberComment = itemView.findViewById(R.id.text_comments);
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
                mImageLike.setImageResource(R.drawable.ic_favorite_red_24dp);
            } else {
                mImageLike.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
            mTextNumberLike.setText(String.valueOf(post.getNumberLike()));
            mTextNumberComment.setText(String.valueOf(post.getNumberComment()));
        }
    }
}
