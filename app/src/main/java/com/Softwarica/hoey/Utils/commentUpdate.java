package com.Softwarica.hoey.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Comment;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.NowPlaying;
import com.google.android.exoplayer2.util.Log;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class commentUpdate extends AppCompatActivity {

    private static final String TAG = "commentUpdate";
    EditText etComment;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_update);
        final String comment;
        comment = getIntent().getStringExtra("comment");
        final String commentId = getIntent().getStringExtra("commentId");
        final String songId = NowPlaying.songId;
        etComment = findViewById(R.id.etCommentUpdate);
        btnUpdate = findViewById(R.id.btnMessageUpdate);
        if (getIntent().getStringExtra("comment") != null) {
            etComment.setText(comment);

        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Comment com = new Comment(etComment.getText().toString());
                    SongApi userapi = Url.getInstance().create(SongApi.class);
                    Call<Void> userCall = userapi.updateComment(Url.token, songId, commentId, com);
                    userCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            Snackbar snackbar = Snackbar.make(btnUpdate, "Sucessfully updated", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            Intent intent = new Intent(getApplicationContext(), NowPlaying.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Something is wrong with connection!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    Log.e(TAG, "onCreateView: :" + e.getLocalizedMessage(), e);
                }
            }

        });

    }
}
