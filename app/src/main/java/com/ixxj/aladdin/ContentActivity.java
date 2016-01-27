package com.ixxj.aladdin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContentActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private ImageButton btn_back;
    GestureDetector mGestureDetector;
    private int verticalMinDistance = 20;
    private int minVelocity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new common().openImmerseStatasBarMode(this);
        setContentView(R.layout.activity_content);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
        LinearLayout viewSnsLayout = (LinearLayout) findViewById(R.id.id_content);
        viewSnsLayout.setOnTouchListener(this);
        viewSnsLayout.setLongClickable(true);

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        Bundle bundle = this.getIntent().getExtras();

        textView.setText(bundle.getString("title"));
        textView2.setText(bundle.getString("content"));
        btn_back = (ImageButton) findViewById(R.id.id_item_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentActivity.this.finish();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        // scroll.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            ContentActivity.this.finish();
            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
