package com.example.homework5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;
    ArrayList<Integer> colors;
    Stack<Path> lines;
    int color;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        colors = new ArrayList<>();
        lines = new Stack<>();
        color = Color.BLUE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = 0;
        for(Path path: activePaths.values()){
            canvas.drawPath(path, newPaint());
        }
        for(Path path: lines){
            canvas.drawPath(path, oldPaint(count));
            count++;
        }
        super.onDraw(canvas);
    }

    private Paint oldPaint(int count) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colors.get(count));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        return paint;
    }

    private Paint newPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        return paint;
    }

    public void addPath(int id, MotionEvent event) {
        Path path = new Path();
        path.moveTo(event.getX(), event.getY());
        activePaths.put(id, path);

        invalidate();
    }

    public void updatePath(int id, MotionEvent event) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(event.getX(), event.getY());
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            colors.add(color);
            lines.add(activePaths.remove(id));
        }
        invalidate();
    }

    public void green(){ color = Color.GREEN; }

    public void blue(){ color = Color.BLUE; }

    public void red(){ color = Color.RED; }

    public void undo() {
        if (!lines.empty()) {
            colors.remove(colors.size() - 1);
            lines.pop();
        }
        invalidate();
    }

    public void clear() {
        while (!lines.empty()){
            undo();
        }
    }
}