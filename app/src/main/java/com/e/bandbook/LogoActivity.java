package com.e.bandbook;

import androidx.annotation.Nullable;

import android.Manifest;
import android.content.*;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.bandbook.Helpers.DexterClient;
import com.e.bandbook.Listeners.PermissionsListener;
import com.karumi.dexter.Dexter;

public class LogoActivity extends AppCompatActivity {

    @BindView(R.id.logo)
    ImageView logoImageView;
    @BindView(R.id.logoText)
    TextView logoTextView;

    private final long time = 5000;
    private CountDownTimer countDownTimer;
    private AlphaAnimation animation;
    PermissionsListener permissionsListener;
    private DexterClient dexter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        dexter = new DexterClient(this);
        startAlphaAnimation();
    }

    void nextActivity() {
        startActivity(new Intent(LogoActivity.this, MenuActivity.class));
        finish();
    }

    void startAlphaAnimation() {
        animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(4000);
        logoImageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (dexter.checkPermission()) {
                    nextActivity();
                } else {
                    startAlphaAnimation();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
