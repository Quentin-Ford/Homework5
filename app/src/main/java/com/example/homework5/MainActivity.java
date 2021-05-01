package com.example.homework5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MyCanvas myCanvas;
    TouchListener touchListener;
    ArrayList<ImageView> icons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);
        icons = new ArrayList<>();

        Bitmap thumbnail = (Bitmap) getIntent().getExtras().get("data");
        myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
    }

    public void addPath(int id, MotionEvent event) {
        myCanvas.addPath(id, event);
    }

    public void updatePath(int id, MotionEvent event) {
        myCanvas.updatePath(id, event);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap(float x, float y) {
        RelativeLayout rl = findViewById(R.id.activity_main);
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.hokie);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.leftMargin = (int) x - 100;
        params.topMargin = (int) y - 100;
        rl.addView(iv, params);
        icons.add(iv);
    }

    public void onLongPress(float x, float y) {
        RelativeLayout rl = findViewById(R.id.activity_main);
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.star);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.leftMargin = (int) x - 100;
        params.topMargin = (int) y - 100;
        rl.addView(iv, params);
        icons.add(iv);
    }

    public void pressGreen(View view) { myCanvas.green(); }

    public void pressBlue(View view) { myCanvas.blue(); }

    public void pressRed(View view) { myCanvas.red(); }

    public void pressDone(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void pressUndo(View view){ myCanvas.undo(); }

    public void pressClear(View view){
        myCanvas.clear();
        while (!icons.isEmpty()){
            icons.remove(icons.size() - 1).setVisibility(View.GONE);
        }
    }

}