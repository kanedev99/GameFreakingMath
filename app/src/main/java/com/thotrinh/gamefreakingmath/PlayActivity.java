package com.thotrinh.gamefreakingmath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtFormular, txtResult, txtScore;

    private Timer mTimer;
    private TimerTask mTimerTask;

    private int score = 0;

    private LevelModel mLevelModel;
    private Random mRandom;

    public PlayActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.play_activity);

        txtFormular = findViewById(R.id.txt_formular);
        txtResult = findViewById(R.id.txt_result);
        txtScore = findViewById(R.id.txt_score);

        ImageView imgTrue = findViewById(R.id.img_true);
        ImageView imgFalse = findViewById(R.id.img_false);

        mLevelModel = GenerateLevel.generateLevel(0);
        display(mLevelModel);

        imgTrue.setOnClickListener(this);
        imgFalse.setOnClickListener(this);

        createTimeTask();
    }

    private void createTimeTask() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameOver();
                    }
                });
            }
        };
        int TIME_PLAY_EACH_LEVEL = 2000;
        mTimer.schedule(mTimerTask, TIME_PLAY_EACH_LEVEL);
    }

    private void display(LevelModel levelModel) {
        txtFormular.setText(mLevelModel.strOperator);
        txtResult.setText(mLevelModel.strResult);
        txtScore.setText(String.valueOf(score));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if ((id == R.id.img_true && mLevelModel.correct) || (id == R.id.img_false && !mLevelModel.correct)){
            score ++;
            nextLevel(score);
        }
        else{
            showGameOver();
        }
    }

    private void showGameOver() {
//        mTimer.cancel();
//        mTimerTask.cancel();
        cancelSchedule();
        new AlertDialog.Builder(this)
                .setTitle("Game Over!")
                .setMessage("Your score: " + txtScore.getText())
                .setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        txtScore.setText("0");
                        PlayActivity.this.score = 0;
                        PlayActivity.this.nextLevel(score);
                    }
                })
                .setNegativeButton(R.string.home, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PlayActivity.this.finish();
                    }
                })
                .setIcon(R.mipmap.ic_gameover)
                .setCancelable(false)
                .show();
    }

    private void cancelSchedule() {
        mTimer.cancel();
        mTimerTask.cancel();
    }

    private void nextLevel(int score) {
        mLevelModel = GenerateLevel.generateLevel(score);
        display(mLevelModel);
        cancelSchedule();
        createTimeTask();
    }
}