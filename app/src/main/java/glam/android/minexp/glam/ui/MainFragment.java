package glam.android.minexp.glam.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import glam.android.minexp.glam.R;
import glam.android.minexp.glam.MainActivity;
import glam.android.minexp.glam.ProductDetail;
import glam.android.minexp.glam.custom.CustomFragment;
import glam.android.minexp.glam.model.Data;

import java.util.ArrayList;

public class MainFragment extends CustomFragment {

    private ArrayList<Data> iList;

    private class CardAdapter extends Adapter<CardViewHolder> {

        private CardAdapter() {
        }

        public int getItemCount() {
            return MainFragment.this.iList.size();
        }

        public void onBindViewHolder(CardViewHolder vh, int i) {
            int i2;
            Data d = (Data) MainFragment.this.iList.get(i);
            vh.lbl1.setText(d.getTexts()[0]);
            TextView textView = vh.lbl1;
            if (d.getTexts()[0] == null) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            textView.setVisibility(i2);
            vh.lbl2.setText(d.getTexts()[1]);
            vh.lbl3.setText(d.getTexts()[2]);
            vh.lbl4.setText(d.getTexts()[3]);
            vh.img.setImageResource(d.getResources()[0]);
        }

        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainFragment.this.startActivity(new Intent(MainFragment.this.getActivity(), ProductDetail.class));
                }
            });
            return new CardViewHolder(itemView);
        }
    }
    private class PageAdapter extends PagerAdapter {
        private PageAdapter() {
        }

        public int getCount() {
            return 5;
        }

        public Object instantiateItem(ViewGroup container, int pos) {
            View v = MainFragment.this.getLayoutInflater(null).inflate(R.layout.pager_card_view, null);
            RecyclerView recList = (RecyclerView) v.findViewById(R.id.cardList);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(MainFragment.this.getActivity());
            llm.setOrientation(1);
            recList.setLayoutManager(llm);
            recList.setAdapter(new CardAdapter());
            ((MainActivity) MainFragment.this.getActivity()).enableActionBarAutoHide(recList);
            container.addView(v, -1, -1);
            return v;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "MEN";
            }
            if (position == 1) {
                return "WOMEN";
            }
            if (position == 2) {
                return "KIDS";
            }
            if (position == 3) {
                return "TRADITIONAL";
            }
            if (position == 4) {
                return "SPECIAL";
            }
            return "Page-" + position;
        }
    }

    @SuppressLint({"InflateParams"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_container, null);
        ((MainActivity) getActivity()).toolbar.findViewById(R.id.spinner_toolbar).setVisibility(0);
        setHasOptionsMenu(true);
        setupView(v);
        return v;
    }

    public void onClick(View v) {
        super.onClick(v);
    }

    private void setupView(View v) {
        loadDummyData();
        initPager(v);
    }

    private void initPager(View v) {
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setPageMargin(10);
        pager.setAdapter(new PageAdapter());
    }

    private void loadDummyData() {
        ArrayList<Data> al = new ArrayList();
        String[] strArr = new String[4];
        strArr[1] = "Ally Capellino Frank Brown - Fott Shop 2014";
        strArr[2] = "$200-$400";
        strArr[3] = "Shop.fott.com";
        al.add(new Data(strArr, new int[]{R.drawable.popularity_img1}));
        al.add(new Data(new String[]{"50%\nOFF", "Tap & DYE Legacy", "$67", "Tapanddye"}, new int[]{R.drawable.popularity_img2}));
        strArr = new String[4];
        strArr[1] = "Piper Felt Hat by Brixton";
        strArr[2] = "$94";
        strArr[3] = "Tapanddye";
        al.add(new Data(strArr, new int[]{R.drawable.popularity_img3}));
        strArr = new String[4];
        strArr[1] = "HIKE Abysss Stone";
        strArr[2] = "$42";
        strArr[3] = "Handwers";
        al.add(new Data(strArr, new int[]{R.drawable.popularity_img4}));
        al.add(new Data(new String[]{"20%\nOFF", "Lenovo Leather Belt", "$12", "Lenovo"}, new int[]{R.drawable.popularity_img5}));
        this.iList = new ArrayList(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_exp, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    public class CardViewHolder extends ViewHolder {
        protected ImageView img;
        protected TextView lbl1;
        protected TextView lbl2;
        protected TextView lbl3;
        protected TextView lbl4;

        public CardViewHolder(View v) {
            super(v);
            this.lbl1 = (TextView) v.findViewById(R.id.lbl1);
            this.lbl2 = (TextView) v.findViewById(R.id.lbl2);
            this.lbl3 = (TextView) v.findViewById(R.id.lbl3);
            this.lbl4 = (TextView) v.findViewById(R.id.lbl4);
            this.img = (ImageView) v.findViewById(R.id.img);
        }
    }
}
