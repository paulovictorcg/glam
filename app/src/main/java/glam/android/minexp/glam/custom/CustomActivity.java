package glam.android.minexp.glam.custom;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import glam.android.minexp.glam.ui.TouchEffect;

public class CustomActivity extends AppCompatActivity implements OnClickListener {
    public static final TouchEffect TOUCH = new TouchEffect();

    @TargetApi(21)
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setupActionBar();
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(null);
        }
    }

    public void onClick(View v) {
    }

    public View setTouchNClick(int id) {
        View v = setClick(id);
        v.setOnTouchListener(TOUCH);
        return v;
    }

    public View setClick(int id) {
        View v = findViewById(id);
        v.setOnClickListener(this);
        return v;
    }
}
