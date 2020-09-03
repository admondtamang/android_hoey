package com.Softwarica.hoey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Comment;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.NowPlaying;
import com.Softwarica.hoey.Utils.commentUpdate;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> comments;
    private Context con;

    public CommentAdapter(List<Comment> comments, Context con) {
        this.comments = comments;
        this.con = con;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        final Comment com = comments.get(position);
        holder.comment.setText(com.getComment());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongApi comment = Url.getInstance().create(SongApi.class);
                Call<Void> advertiseCallDelete = comment.deleteComment(Url.token, NowPlaying.songId,com.get_id());

                advertiseCallDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(con, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(con, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(con, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(con, commentUpdate.class);
                intent.putExtra("commentId",com.get_id());
                intent.putExtra("comment",com.getComment());
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView comment;
        CircleImageView image;
        ImageView btnUpdate, btnDelete;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgProfileComment);
            comment = itemView.findViewById(R.id.tvMessageComment);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
