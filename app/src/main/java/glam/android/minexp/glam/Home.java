package glam.android.minexp.glam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import glam.android.minexp.glam.custom.CustomActivity;

public class Home extends CustomActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.home);
        setupView();
    }

    private void setupView() {
        setTouchNClick(R.id.btnReg);
        setTouchNClick(R.id.btnLogin);
    }

    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btnLogin) {
//            Intent i = new Intent(this, Login.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(i);
            finish();
        }
    }
}
