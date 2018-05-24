package glam.android.minexp.glam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import glam.android.minexp.glam.custom.CustomActivity;

public class ProductDetail extends CustomActivity {
    private ViewPager pager;
    private LinearLayout vDots;

    class C01961 implements OnPageChangeListener {
        C01961() {
        }

        public void onPageSelected(int pos) {
            if (ProductDetail.this.vDots != null && ProductDetail.this.vDots.getTag() != null) {
                ((ImageView) ProductDetail.this.vDots.getTag()).setImageResource(R.drawable.dot_gray);
                ((ImageView) ProductDetail.this.vDots.getChildAt(pos)).setImageResource(R.drawable.dot_blue);
                ProductDetail.this.vDots.setTag(ProductDetail.this.vDots.getChildAt(pos));
            }
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    }

    private class PageAdapter extends PagerAdapter {
        private PageAdapter() {
        }

        public int getCount() {
            return 5;
        }

        public Object instantiateItem(ViewGroup container, int arg0) {
            ImageView img = (ImageView) ProductDetail.this.getLayoutInflater().inflate(R.layout.img, null);
            img.setImageResource(R.drawable.product_detail_bottom_banner);
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
        setContentView((int) R.layout.pr_detail);
        setupView();
    }

    private void setupView() {
        setTouchNClick(R.id.fabCart);
        setTouchNClick(R.id.btnLike);
        setTouchNClick(R.id.btnComment);
        setTouchNClick(R.id.btnMore);
        getSupportActionBar().setTitle((CharSequence) "Product Detail");
        initPager();
    }

    private void initPager() {
        this.pager = (ViewPager) findViewById(R.id.pager);
        this.pager.setPageMargin(10);
        this.pager.setOnPageChangeListener(new C01961());
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cart) {
            startActivity(new Intent(this, CheckoutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
