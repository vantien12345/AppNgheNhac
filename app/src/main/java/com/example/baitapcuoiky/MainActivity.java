package com.example.baitapcuoiky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView lvSong;
    ImageButton btnPrew, btnPlay,btnNext,imgTron;
    TextView tvTimeSong, tvTotal, tvPlaying;
    SeekBar seekBar;
    ArrayList<ListSong> listSongs;
    ListSongAdapter adapter;
    int position=0;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Addsong();
        KhoiTaoMedia();
        SetTotal();
        adapter= new ListSongAdapter(this, R.layout.list_song,listSongs);
        lvSong.setAdapter(adapter);
        mediaPlayer= MediaPlayer.create(MainActivity.this, listSongs.get(position).getFile());
        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaPlayer.stop();
                mediaPlayer= MediaPlayer.create(MainActivity.this, listSongs.get(position).getFile());
                tvPlaying.setText(listSongs.get(position).getSong());
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pausees);
                SetTotal();
                UpdateTime();
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.player);
                }else
                {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pausees);
                }
                SetTotal();
                UpdateTime();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position>listSongs.size()-1){
                    position=0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }

                KhoiTaoMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pausees);
                SetTotal();
                UpdateTime();
            }
        });
        imgTron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                position=random.nextInt(listSongs.size()-1);

                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                KhoiTaoMedia();
                mediaPlayer.start();
            }
        });
        btnPrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position<0){
                    position=listSongs.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pausees);
                SetTotal();
                UpdateTime();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }

        });
    }
    private void UpdateTime(){
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio= new SimpleDateFormat("mm:ss");
                tvTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                // update seebar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position> listSongs.size()-1){
                            position=0;
                        }if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMedia();
                        mediaPlayer.start();
                        SetTotal();
                        UpdateTime();
                    }
                });
            }
        },100);
    }
    private void SetTotal() {
        SimpleDateFormat dinhDangGio= new SimpleDateFormat("mm:ss");
        tvTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        // g??n max c???a seebar = (mediaPlayer.getDuration());
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void KhoiTaoMedia() {
        mediaPlayer= MediaPlayer.create(MainActivity.this, listSongs.get(position).getFile());
        tvPlaying.setText(listSongs.get(position).getSong());
    }
    private void Addsong() {
        listSongs = new ArrayList<>();
        listSongs.add(new ListSong("N??i em thu???c v???","H?? Anh",R.drawable.haanh,R.raw.noiemthuocve));
        listSongs.add(new ListSong("S??i G??n ??au L??ng Qu??","H???a Kim Tuy???n-Ho??ng Duy??n", R.drawable.saigondaulong,R.raw.saigondaulongqua));
        listSongs.add(new ListSong("3107","DuongG - N??u",R.drawable.bamotkobay,R.raw.bamotobat));
        listSongs.add(new ListSong("Ch???ng Th??? T??m ???????c Em","PhucXp ft. Freak D",R.drawable.changthetim,R.raw.changthetimdcem));
        listSongs.add(new ListSong("Ch??ng Ta C???a Sau N??y","T.R.I x Freak D",R.drawable.chungtacuasau,R.raw.chungtacuasaunay));
        listSongs.add(new ListSong("C?? Ai ??? ????y Kh??ng","14 Casper & Bon",R.drawable.coaiodaykhong,R.raw.coaiodaykhong));
        listSongs.add(new ListSong("Gi?? Nh?? Em Nh??n L???i","JSOL x VIRUSS",R.drawable.gianhuemnhin,R.raw.gianhuemnhinlai));
        listSongs.add(new ListSong("L???i Xin L???i V???ng V???", "Qu??n A.P",R.drawable.loixinloivungve,R.raw.loixinloivungve));
        listSongs.add(new ListSong("N??? Ai ????? L???i Xin L???i","Bozitt x Freak D",R.drawable.noaidoloixinloi,R.raw.noaidoloixinloi));
        listSongs.add(new ListSong("Ph???i Ch??ng Em ???? Y??u","JUKY SAN ",R.drawable.phaichangemda,R.raw.phaichangemdayeu));
        listSongs.add(new ListSong("Ph??? C?? C??n Anh","Quinn Ft. Chilly",R.drawable.phocuconanh,R.raw.phocuconanh));
    }
    private void Anhxa() {
        lvSong =(ListView) findViewById(R.id.lvSong);
        btnPrew=(ImageButton) findViewById(R.id.btnPrev);
        btnPlay=(ImageButton) findViewById(R.id.btnPlay);
        btnNext=(ImageButton) findViewById(R.id.btnNext);
        tvTimeSong=(TextView)findViewById(R.id.tvTimeSong);
        tvTotal=(TextView) findViewById(R.id.tvTotal);
        tvPlaying=(TextView) findViewById(R.id.tvPlaying);
        seekBar=(SeekBar) findViewById(R.id.SeekBar);
        imgTron=(ImageButton) findViewById((R.id.imgTron));
    }
}