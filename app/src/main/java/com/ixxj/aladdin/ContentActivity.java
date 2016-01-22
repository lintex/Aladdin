package com.ixxj.aladdin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContentActivity extends Activity {
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

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
}
