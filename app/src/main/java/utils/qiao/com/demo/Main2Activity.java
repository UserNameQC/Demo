package utils.qiao.com.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String result = getIntent().getStringExtra("json");
        TextView textView = findViewById(R.id.text2);
        textView.setText(result);
    }
}
