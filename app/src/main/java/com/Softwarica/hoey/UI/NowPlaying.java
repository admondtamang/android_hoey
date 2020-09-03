package com.Softwarica.hoey.UI;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Adapter.CommentAdapter;
import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Api.UserApi;
import com.Softwarica.hoey.Utils.CommentActivity;
import com.Softwarica.hoey.Model.Comment;
import com.Softwarica.hoey.R;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlaying extends AppCompatActivity {

    private static final String TAG = "NowPlaying";
    private static final String PLAYBACK_CHANNEL_ID = "s";
    private static final int PLAYBACK_NOTIFICATION_ID = 2;
    SimpleExoPlayer player;
    PlayerNotificationManager playerNotificationManager;

    ImageView albumCover;
    private TextView songName, artistName, playedSong;
    ImageButton btnFavourite;
    Bundle bundle = null;
    public static String songId = "";
    Button btnComment;
    RecyclerView recyclerComment;
    List<Comment> listComments;
    CommentAdapter adaptComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        songName = findViewById(R.id.tvSongName);
        artistName = findViewById(R.id.tvArtistName);
        albumCover = findViewById(R.id.imgAlbum);
        btnFavourite = findViewById(R.id.favourite);
        playedSong = findViewById(R.id.tvPlayedSong);
        btnComment = findViewById(R.id.btnAddComment);

        recyclerComment = findViewById(R.id.recycleComment);
        final Bundle bundle = getIntent().getExtras();
        if(bundle.getString("played")!=null)
            playedSong.setText(bundle.getString("played"));
        songId = bundle.getString("songId");

        loadJsonComments();
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                intent.putExtra("songId", songId);
                startActivity(intent);
            }
        });

        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String userId = MainActivity.globalUser.get_id();

                UserApi userapi = Url.getInstance().create(UserApi.class);
                Call<Void> userCall = userapi.addToFavourite(Url.token, songId);

                userCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                            Snackbar snackbar = Snackbar.make(v, "Added to Favourite Playlist", Snackbar.LENGTH_SHORT);
                            snackbar.show();
//                        Snackbar snackbar = Snackbar.make(btnComment, bundle.getString("snackbarShowTeamAdded"), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null);
//
//                        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.design_default_color_on_primary));
//                        snackbar.show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        songName.setText(bundle.getString("songTitle"));
        artistName.setText(bundle.getString("artistName"));
        Glide.with(getApplicationContext()).load(Url.uploads + bundle.getString("albumCover")).centerCrop().placeholder(R.drawable.album).into(albumCover);
        String music = Url.uploads + bundle.getString("link");
        initializePlayer(music);
        increaseCounter();
    }

    private void increaseCounter() {
        // Counter
        SongApi songCounterApi = Url.getInstance().create(SongApi.class);
        Call<Void> counterCall = songCounterApi.incrementPlayCounter(songId);
        counterCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(NowPlaying.this, "counted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(NowPlaying.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadJsonComments() {

        SongApi songApi = Url.getInstance().create(SongApi.class);
        Call<List<Comment>> listCallGetComment = songApi.showAllComments(songId);
        listCallGetComment.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(NowPlaying.this, "Error", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onResponse: " + response.errorBody());
                    return;
                }

                listComments = response.body();
                adaptComment = new CommentAdapter(listComments, NowPlaying.this);
                recyclerComment.setAdapter(adaptComment);
                recyclerComment.setLayoutManager(new LinearLayoutManager(NowPlaying.this));
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void initializePlayer(String music) {

        final Context context = this;
        player = new SimpleExoPlayer.Builder(context).build();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Hoey"));

//        ConcatenatingMediaSource concatenatingMediaSource=new ConcatenatingMediaSource();
        MediaSource mediaSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(music));


        final PlayerControlView playerView = findViewById(R.id.controlView);

        playerView.setPlayer(player);
        playerView.setShowTimeoutMs(0);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);


        playerNotificationManager = PlayerNotificationManager.createWithNotificationChannel(
                this,
                PLAYBACK_CHANNEL_ID,
                R.string.playback_channel_name,
                PLAYBACK_NOTIFICATION_ID,
                new PlayerNotificationManager.MediaDescriptionAdapter() {
                    @Override
                    public String getCurrentContentTitle(Player player) {
                        return getIntent().getStringExtra("songTitle");
                    }

                    @Nullable
                    @Override
                    public PendingIntent createCurrentContentIntent(Player player) {
                        Intent intent = new Intent(context, NowPlaying.class);
                        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    }

                    @Nullable
                    @Override
                    public String getCurrentContentText(Player player) {
                        return getIntent().getStringExtra("songTitle");
                    }

                    @Nullable
                    @Override
                    public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
                        return null;
                    }
                }
        );

        playerNotificationManager.setFastForwardIncrementMs(0);
        playerNotificationManager.setRewindIncrementMs(0);
        playerNotificationManager.setPlayer(player);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (player != null) {
            player.release();
            player = null;
            playerNotificationManager.setPlayer(null);

        }
    }
}
