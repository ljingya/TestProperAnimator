package com.example.shuiai.testproperanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyView.StartAnimtorListener {
    private Button javaAnimator, xmlAnimator, defineAnimator;
    private MyView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        javaAnimator = (Button) findViewById(R.id.javaAnimator);
        xmlAnimator = (Button) findViewById(R.id.xmlAnimator);
        defineAnimator = (Button) findViewById(R.id.defineAnimator);
        imageView = (MyView) findViewById(R.id.imgView);
        javaAnimator.setOnClickListener(this);
        xmlAnimator.setOnClickListener(this);
        defineAnimator.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.javaAnimator:
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 100f);
                ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
                AnimatorSet set = new AnimatorSet();
                set.setDuration(1000);
                set.play(rotation).after(animator);
                set.start();
                break;
            case R.id.xmlAnimator:
                Animator anim = AnimatorInflater.loadAnimator(this, R.animator.animset);
                anim.setDuration(1000);
                anim.setTarget(imageView);
                anim.start();
                break;
            case R.id.defineAnimator:
                imageView.setStartAnimatorListener(this);
                break;
        }
    }

    @Override
    public void startAnimation() {

    }
}
