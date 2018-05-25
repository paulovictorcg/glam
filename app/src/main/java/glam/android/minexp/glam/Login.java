package glam.android.minexp.glam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import  glam.android.minexp.glam.custom.CustomActivity;

public class Login extends CustomActivity {
    private ViewPager pager;
    private LinearLayout vDots;

    private class PageAdapter extends PagerAdapter {
        private PageAdapter() {
        }

        public int getCount() {
            return 5;
        }

        public Object instantiateItem(ViewGroup container, int arg0) {
            ImageView img = (ImageView) Login.this.getLayoutInflater().inflate(R.layout.img, null);
            img.setImageResource(R.drawable.img_signin);
            container.addView(img, -1, -1);
            return img;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.login);
        setupView();
    }

    private void setupView() {
        ((Button) setTouchNClick(R.id.btnReg)).setText(Html.fromHtml(getString(R.string.sign_up)));
        setTouchNClick(R.id.btnLogin);
        setTouchNClick(R.id.btnForget);
        setTouchNClick(R.id.btnFb);
        initPager();
    }

    private void initPager() {
        this.pager = (ViewPager) findViewById(R.id.pager);
        this.pager.setPageMargin(10);
        this.pager.setOnPageChangeListener((new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                if (Login.this.vDots != null && Login.this.vDots.getTag() != null) {
                    ((ImageView) Login.this.vDots.getTag()).setImageResource(R.drawable.dot_gray);
                    ((ImageView) Login.this.vDots.getChildAt(pos)).setImageResource(R.drawable.dot_blue);
                    Login.this.vDots.setTag(Login.this.vDots.getChildAt(pos));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        }));
        this.vDots = (LinearLayout) findViewById(R.id.vDots);
        this.pager.setAdapter(new PageAdapter());
        setupDotbar();
    }

    private void setupDotbar() {
        LayoutParams param = new LayoutParams(-2, -2);
        param.setMargins(10, 0, 0, 0);
        this.vDots.removeAllViews();
        for (int i = 0; i < 5; i++) {
            int i2;
            ImageView img = new ImageView(this);
            if (i == 0) {
                i2 = R.drawable.dot_blue;
            } else {
                i2 = R.drawable.dot_gray;
            }
            img.setImageResource(i2);
            this.vDots.addView(img, param);
            if (i == 0) {
                this.vDots.setTag(img);
            }
        }
    }

    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btnLogin || v.getId() == R.id.btnFb) {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }
}
