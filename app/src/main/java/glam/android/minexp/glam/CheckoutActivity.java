package glam.android.minexp.glam;

import android.os.Bundle;
import glam.android.minexp.glam.custom.CustomActivity;

public class CheckoutActivity extends CustomActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.checkout_act);
        getSupportActionBar().setTitle((CharSequence) "Checkout");
    }
}
