package com.example.shophiki;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.ViewHolder> {

    List<Review> reviews;
    int z = 0;

    public AdapterReview(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public AdapterReview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_review, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterReview.ViewHolder holder, int position) {
        Review review = reviews.get(position);

        holder.nameUser.setText(review.getUser());

        if(review.getImgUser() != null)
            Picasso.get().load(review.getImgUser()).into(holder.imgUser);

        holder.voteUser.setRating(Float.parseFloat(String.valueOf(review.getVote())));

        holder.date.setText(review.getDate());

        holder.msg.setText(review.getMsg());


        if(review.getEmojis() == null)
            holder.emojies.setVisibility(View.GONE);
        else {
            int amount = review.getEmojis().length;
            holder.emojies.setVisibility(View.VISIBLE);
            if(amount == 1){
                holder.emoji3.setImageResource(imgEmoji(review.getEmojis()[0]));
                holder.emoji1.setVisibility(View.GONE);
                holder.emoji2.setVisibility(View.GONE);
            }
            else if (amount == 2){
                sortByAmountEmoji(review.getEmojis());
                holder.emoji1.setVisibility(View.GONE);
                holder.emoji2.setVisibility(View.VISIBLE);
                holder.emoji2.setImageResource(imgEmoji(review.getEmojis()[0]));
                holder.emoji3.setImageResource(imgEmoji(review.getEmojis()[1]));
            }
            else {
                sortByAmountEmoji(review.getEmojis());
                holder.emoji1.setVisibility(View.VISIBLE);
                holder.emoji2.setVisibility(View.VISIBLE);
                holder.emoji1.setImageResource(imgEmoji(review.getEmojis()[0]));
                holder.emoji2.setImageResource(imgEmoji(review.getEmojis()[1]));
                holder.emoji3.setImageResource(imgEmoji(review.getEmojis()[2]));
            }
        }

        removeAllSelectedEmoji(holder);
        holder.allEmoji.setVisibility(View.GONE);
        holder.btnEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(z == 1){
                    holder.allEmoji.setVisibility(View.GONE);
                    z = 0;
                }
                else {
                    holder.allEmoji.setVisibility(View.VISIBLE);
                    holder.btnLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnLike, holder);
                            holder.statusEmoji.setText(R.string.emoji_like);
                        }
                    });
                    holder.btnLove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnLove, holder);
                            holder.statusEmoji.setText(R.string.emoji_love);
                        }
                    });
                    holder.btnLol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnLol, holder);
                            holder.statusEmoji.setText(R.string.emoji_lol);
                        }
                    });
                    holder.btnWonder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnWonder, holder);
                            holder.statusEmoji.setText(R.string.emoji_wonder);
                        }
                    });
                    holder.btnWow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnWow, holder);
                            holder.statusEmoji.setText(R.string.emoji_wow);
                        }
                    });
                    holder.btnBad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnBad, holder);
                            holder.statusEmoji.setText(R.string.emoji_bad);
                        }
                    });
                    holder.btnSad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnSad, holder);
                            holder.statusEmoji.setText(R.string.emoji_sad);
                        }
                    });
                    holder.btnAngry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            addBorderEmoji(holder.btnAngry, holder);
                            holder.statusEmoji.setText(R.string.emoji_angry);
                            holder.statusEmoji.setTextColor(Color.parseColor("#FF0000"));
                        }
                    });
                    z = 1;
                }
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.allEmoji.setVisibility(View.GONE);
                z = 0;
            }
        });

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        TextView nameUser;
        ImageView imgUser;
        RatingBar voteUser;
        TextView date;
        TextView msg;
        View btnEmoji;
        TextView statusEmoji;
        View btnReply;
        View emojies;
        ImageView emoji1;
        ImageView emoji2;
        ImageView emoji3;

        View allEmoji;

        ImageView btnLike;
        ImageView btnLove;
        ImageView btnLol;
        ImageView btnWonder;
        ImageView btnWow;
        ImageView btnBad;
        ImageView btnSad;
        ImageView btnAngry;

        Button btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_review);
            nameUser = itemView.findViewById(R.id.tv_name_user);
            imgUser = itemView.findViewById(R.id.img_user);
            voteUser = itemView.findViewById(R.id.vote_user);
            date = itemView.findViewById(R.id.tv_date);
            msg = itemView.findViewById(R.id.tv_msg);
            btnEmoji = itemView.findViewById(R.id.btn_emoji);
            statusEmoji = itemView.findViewById(R.id.tv_status_emoji);
            btnReply = itemView.findViewById(R.id.btn_reply);
            emojies = itemView.findViewById(R.id.tv_emoji);
            emoji1 = itemView.findViewById(R.id.img_emoji_top1);
            emoji2 = itemView.findViewById(R.id.img_emoji_top2);
            emoji3 = itemView.findViewById(R.id.img_emoji_top3);
            allEmoji = itemView.findViewById(R.id.tv_all_emoji);

            btnLike = itemView.findViewById(R.id.btn_like);
            btnLove = itemView.findViewById(R.id.btn_love);
            btnLol = itemView.findViewById(R.id.btn_lol);
            btnWonder = itemView.findViewById(R.id.btn_wonder);
            btnWow = itemView.findViewById(R.id.btn_wow);
            btnBad = itemView.findViewById(R.id.btn_bad);
            btnSad = itemView.findViewById(R.id.btn_sad);
            btnAngry = itemView.findViewById(R.id.btn_angry);
        }
    }

    public void sortByAmountEmoji(Emoji[] emojis){
        for(int i = 0; i < emojis.length; i++)
            for(int j = i+1; j < emojis.length; j++)
                if(emojis[i].getAmount() < emojis[j].getAmount()){
                    Emoji tg = emojis[i]; emojis[i] = emojis[j]; emojis[j] = tg;
                }
    }

    public int imgEmoji(Emoji emoji){
        int img = 0;
        switch (emoji.getId()){
            case 1: img = R.drawable.ic_like; break;
            case 2: img = R.drawable.ic_love; break;
            case 3: img = R.drawable.ic_lol; break;
            case 4: img = R.drawable.ic_wonder; break;
            case 5: img = R.drawable.ic_great; break;
            case 6: img = R.drawable.ic_bad; break;
            case 7: img = R.drawable.ic_sad; break;
            case 8: img = R.drawable.ic_angry; break;
        }

        return img;
    }

    public void removeAllSelectedEmoji(AdapterReview.ViewHolder holder){
        holder.btnLike.setBackgroundResource(R.color.white);
        holder.btnLove.setBackgroundResource(R.color.white);
        holder.btnLol.setBackgroundResource(R.color.white);
        holder.btnWonder.setBackgroundResource(R.color.white);
        holder.btnWow.setBackgroundResource(R.color.white);
        holder.btnBad.setBackgroundResource(R.color.white);
        holder.btnSad.setBackgroundResource(R.color.white);
        holder.btnAngry.setBackgroundResource(R.color.white);
    }

    public void addBorderEmoji(View emoji, AdapterReview.ViewHolder holder){
        removeAllSelectedEmoji(holder);
        emoji.setBackgroundResource(R.drawable.border_emoji);
        holder.allEmoji.setVisibility(View.GONE);
        z = 0;
        holder.statusEmoji.setTextColor(Color.parseColor("#065900"));
    }
}
