package glam.android.minexp.glam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import glam.android.minexp.glam.custom.CustomActivity;

public class MainActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
