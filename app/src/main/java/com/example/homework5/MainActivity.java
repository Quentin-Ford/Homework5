package com.example.homework5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MyCanvas myCanvas;
    TouchListener touchListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);

        Bitmap thumbnail = (Bitmap) getIntent().getExtras().get("data");
        myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap() {

    }

    public void onLongPress() {

    }

    public void pressGreen(View view) { myCanvas.green();}

    public void pressBlue(View view) { myCanvas.blue();}

    public void pressRed(View view) { myCanvas.red();}
}