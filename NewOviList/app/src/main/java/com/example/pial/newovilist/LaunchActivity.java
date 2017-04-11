package com.example.pial.newovilist;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final int DELAY = 100;
    private RelativeLayout bgViewGroup,check;
    private TextView body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

        bgViewGroup = (RelativeLayout) findViewById(R.id.reveal_root);
        body = ((TextView) findViewById(R.id.sample_body));
        check= (RelativeLayout) findViewById(R.id.check);
        final View view=View.inflate(this,R.layout.activity_launch,null);

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this,LoginActivity.class));
                finish();
            }
        };

        Timer t=new Timer();
        t.schedule(timerTask,5000);

        try {
            view.post(new Runnable() {
                @Override
                public void run() {

                    float finalRadius = (float) Math.hypot(bgViewGroup.getWidth(), bgViewGroup.getHeight());

                    Animator anim = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        anim = ViewAnimationUtils.createCircularReveal(bgViewGroup, check.getRight(), check.getBottom(), 0, finalRadius);
                    }
                    bgViewGroup.setBackgroundColor(ContextCompat.getColor(LaunchActivity.this, R.color.colorPrimary));
                    anim.setDuration(1500);
                    anim.setInterpolator(new AccelerateDecelerateInterpolator());
                    anim.start();


                }
            });

        }catch (Exception e)
        {
            Log.e("Error",""+e.getMessage());
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (view.getId() == R.id.square_yellow) {
                revealYellow(motionEvent.getRawX(), motionEvent.getRawY());
                Toast.makeText(this, ""+motionEvent.getX(), Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    private void revealYellow(float x, float y) {
        animateRevealColorFromCoordinates(bgViewGroup, R.color.colorPrimary, (int) x, (int) y);
        body.setText("");
        body.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(bgViewGroup.getWidth(), bgViewGroup.getHeight());

        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(bgViewGroup, check.getRight(), check.getBottom(), 0, finalRadius);
        }
        bgViewGroup.setBackgroundColor(ContextCompat.getColor(LaunchActivity.this, R.color.colorPrimary));
        anim.setDuration(1000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }
}
