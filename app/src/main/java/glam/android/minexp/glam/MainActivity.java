package glam.android.minexp.glam;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ActivityChooserView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import glam.android.minexp.glam.custom.CustomActivity;
import glam.android.minexp.glam.custom.CustomFragment;
import glam.android.minexp.glam.model.Data;
import glam.android.minexp.glam.ui.Checkout;
import glam.android.minexp.glam.ui.LeftNavAdapter;
import glam.android.minexp.glam.ui.MainFragment;
import glam.android.minexp.glam.ui.OnSale;
import glam.android.minexp.glam.ui.Settings;
import java.util.ArrayList;
import glam.android.minexp.glam.R;




@SuppressLint({"InlinedApi"})
public class MainActivity extends CustomActivity {
    private static final TypeEvaluator ARGB_EVALUATOR = new ArgbEvaluator();
    private DrawerLayout drawerLayout;
    private ListView drawerLeft;
    private ActionBarDrawerToggle drawerToggle;
    private boolean mActionBarAutoHideEnabled = true;
    private int mActionBarAutoHideMinY = 0;
    private int mActionBarAutoHideSensivity = 0;
    private int mActionBarAutoHideSignal = 0;
    private boolean mActionBarShown = true;
    private ObjectAnimator mStatusBarColorAnimator;
    public Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(this.toolbar);
        setupDrawer();
        setupContainer(1);
    }

    private void setupDrawer() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerLayout.setDrawerShadow((int) R.drawable.drawer_shadow, 8388611);
        this.drawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View drawerView) {
            }
        };
        this.drawerLayout.setDrawerListener(this.drawerToggle);
        this.drawerLayout.closeDrawers();
        setupLeftNavDrawer();
        this.drawerLayout.setDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                MainActivity.this.onNavDrawerStateChanged(true, false);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                MainActivity.this.onNavDrawerStateChanged(false, false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                MainActivity.this.onNavDrawerStateChanged(MainActivity.this.drawerLayout.isDrawerOpen(8388611), newState != 0);
            }
        });
    }

    private void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {
        if (this.mActionBarAutoHideEnabled && isOpen) {
            autoShowOrHideActionBar(true);
        }
    }

    private void autoShowOrHideActionBar(boolean show) {
        if (show != this.mActionBarShown) {
            this.mActionBarShown = show;
            onActionBarAutoShowOrHide(show);
        }
    }

    @SuppressLint({"NewApi"})
    private void onActionBarAutoShowOrHide(final boolean shown) {
        String str;
        int color;
        if (this.mStatusBarColorAnimator != null) {
            this.mStatusBarColorAnimator.cancel();
        }
        DrawerLayout drawerLayout = this.drawerLayout;
        if (this.drawerLayout != null) {
            str = "statusBarBackgroundColor";
        } else {
            str = "statusBarColor";
        }
        int[] iArr = new int[2];
        if (shown) {
            color = getResources().getColor(R.color.main_color_dk);
        } else {
            color = getResources().getColor(R.color.main_color);
        }
        iArr[0] = color;
        if (shown) {
            color = getResources().getColor(R.color.main_color);
        } else {
            color = getResources().getColor(R.color.main_color_dk);
        }
        iArr[1] = color;
        this.mStatusBarColorAnimator = ObjectAnimator.ofInt(drawerLayout, str, iArr).setDuration(250);
        if (this.drawerLayout != null) {
            this.mStatusBarColorAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ViewCompat.postInvalidateOnAnimation(MainActivity.this.drawerLayout);
                    if (shown) {
                        MainActivity.this.getSupportActionBar().show();
                        if (VERSION.SDK_INT >= 21) {
                            MainActivity.this.getWindow().setStatusBarColor(MainActivity.this.getResources().getColor(R.color.main_color_dk));
                            return;
                        }
                        return;
                    }
                    MainActivity.this.getSupportActionBar().hide();
                    if (VERSION.SDK_INT >= 21) {
                        MainActivity.this.getWindow().setStatusBarColor(MainActivity.this.getResources().getColor(R.color.main_color));
                    }
                }
            });
        }
        this.mStatusBarColorAnimator.setEvaluator(ARGB_EVALUATOR);
        this.mStatusBarColorAnimator.start();
    }

    public void enableActionBarAutoHide(RecyclerView recList) {
        initActionBarAutoHide();
        recList.setOnScrollListener(new OnScrollListener() {
            static final int ITEMS_THRESHOLD = 3;
            int lastFvi = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int i = 0;
                super.onScrolled(recyclerView, dx, dy);
                try {
                    int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    MainActivity mainActivity = MainActivity.this;
                    int i2 = firstVisibleItem <= 3 ? 0 : Integer.MAX_VALUE;
                    if (this.lastFvi - firstVisibleItem > 0) {
                        i = Integer.MIN_VALUE;
                    } else if (this.lastFvi != firstVisibleItem) {
                        i = Integer.MAX_VALUE;
                    }
                    mainActivity.onMainContentScrolled(i2, i);
                    this.lastFvi = firstVisibleItem;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void onMainContentScrolled(int currentY, int deltaY) {
        if (deltaY > this.mActionBarAutoHideSensivity) {
            deltaY = this.mActionBarAutoHideSensivity;
        } else if (deltaY < (-this.mActionBarAutoHideSensivity)) {
            deltaY = -this.mActionBarAutoHideSensivity;
        }
        if (Math.signum((float) deltaY) * Math.signum((float) this.mActionBarAutoHideSignal) < 0.0f) {
            this.mActionBarAutoHideSignal = deltaY;
        } else {
            this.mActionBarAutoHideSignal += deltaY;
        }
        boolean shouldShow = currentY < this.mActionBarAutoHideMinY || this.mActionBarAutoHideSignal <= (-this.mActionBarAutoHideSensivity);
        autoShowOrHideActionBar(shouldShow);
    }

    public void initActionBarAutoHide() {
        this.mActionBarAutoHideEnabled = true;
        this.mActionBarAutoHideMinY = getResources().getDimensionPixelSize(R.dimen.action_bar_auto_hide_min_y);
        this.mActionBarAutoHideSensivity = getResources().getDimensionPixelSize(R.dimen.action_bar_auto_hide_sensivity);
    }

    @SuppressLint({"InflateParams"})
    private void setupLeftNavDrawer() {
        this.drawerLeft = (ListView) findViewById(R.id.left_drawer);
        this.drawerLeft.addHeaderView(getLayoutInflater().inflate(R.layout.left_nav_header, null));
        ArrayList<Data> al = new ArrayList();
        al.add(new Data(new String[]{"Explore"}, new int[]{R.drawable.ic_nav1, R.drawable.ic_nav1_sel}));
        al.add(new Data(new String[]{"Favourites"}, new int[]{R.drawable.ic_nav2, R.drawable.ic_nav2_sel}));
        al.add(new Data(new String[]{"Cart"}, new int[]{R.drawable.ic_nav3, R.drawable.ic_nav3_sel}));
        al.add(new Data(new String[]{"Settings"}, new int[]{R.drawable.ic_nav4, R.drawable.ic_nav4_sel}));
        al.add(new Data(new String[]{"Logout"}, new int[]{R.drawable.ic_nav5, R.drawable.ic_nav5_sel}));
        final LeftNavAdapter adp = new LeftNavAdapter(this, al);
        this.drawerLeft.setAdapter(adp);
        this.drawerLeft.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View arg1, int pos, long arg3) {
                if (pos != 0) {
                    adp.setSelection(pos - 1);
                }
                MainActivity.this.drawerLayout.closeDrawers();
                MainActivity.this.setupContainer(pos);
            }
        });
    }

    private void setupContainer(int pos) {
        CustomFragment f = null;
        CharSequence title = null;
        if (pos != 0) {
            if (pos == 1) {
                f = new MainFragment();
            } else if (pos == 2) {
                f = new OnSale();
                title = "On Sale";
            } else if (pos == 3) {
                f = new Checkout();
                title = "Checkout";
            } else if (pos == 4) {
                f = new Settings();
                title = "Settings";
            } else if (pos == 5) {
                startActivity(new Intent(this, Login.class));
                finish();
            }
            if (f != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(title);
                }
            }
        }
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.drawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.drawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
