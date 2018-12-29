package utils.qiao.com.demo;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import utils.qiao.com.demo.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        TextView textView = findViewById(R.id.textview_button);
        final EditText text = findViewById(R.id.editText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = text.getText().toString();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("json", s);
                startActivity(intent);
            }
        });
        binding.textviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tencentSdkWebview.loadUrl("http://www.baidu.com");
            }
        });

    }
}
