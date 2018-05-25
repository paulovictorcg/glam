package glam.android.minexp.glam.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class OnSale extends CustomFragment {
    private ArrayList<Data> iList;
    public class CardViewHolder extends ViewHolder {
        protected ImageView img;
        protected TextView lbl1;
        protected TextView lbl2;
        protected TextView lbl3;

        public CardViewHolder(View v) {
            super(v);
            this.lbl1 = (TextView) v.findViewById(R.id.lbl1);
            this.lbl2 = (TextView) v.findViewById(R.id.lbl2);
            this.lbl3 = (TextView) v.findViewById(R.id.lbl3);
            this.img = (ImageView) v.findViewById(R.id.img);
        }
    }
    private class CardAdapter extends Adapter<CardViewHolder> {

        class C01531 implements OnClickListener {
            C01531() {
            }

            public void onClick(View v) {
                OnSale.this.startActivity(new Intent(OnSale.this.getActivity(), ProductDetail.class));
            }
        }



        private CardAdapter() {
        }

        public int getItemCount() {
            return OnSale.this.iList.size();
        }

        public void onBindViewHolder(CardViewHolder vh, int i) {
            Data d = (Data) OnSale.this.iList.get(i);
            vh.lbl1.setText(d.getTexts()[0]);
            vh.lbl2.setText(d.getTexts()[1]);
            vh.lbl3.setText(d.getTexts()[2]);
            vh.img.setImageResource(d.getResources()[0]);
        }

        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
            itemView.setOnClickListener(new C01531());
            return new CardViewHolder(itemView);
        }
    }

    @SuppressLint({"InflateParams", "InlinedApi"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.on_sale, null);
        ((MainActivity) getActivity()).toolbar.setTitle((CharSequence) "On Sale");
        ((MainActivity) getActivity()).toolbar.findViewById(R.id.spinner_toolbar).setVisibility(8);
        setHasOptionsMenu(true);
        setupView(v);
        return v;
    }

    public void onClick(View v) {
        super.onClick(v);
    }

    private void setupView(View v) {
        loadDummyData();
        RecyclerView recList = (RecyclerView) v.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        recList.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        recList.setAdapter(new CardAdapter());
    }

    private void loadDummyData() {
        ArrayList<Data> al = new ArrayList();
        al.add(new Data(new String[]{"Raven’s Wing Field Note", "$200-$400", "Gear Patrol"}, new int[]{R.drawable.on_sale_item1}));
        al.add(new Data(new String[]{"Y.M.C. Spray Tee (Grey)", "$67", "Oi Polloi"}, new int[]{R.drawable.on_sale_item2}));
        al.add(new Data(new String[]{"Ally Capellino", "$94", "Ally Capellino"}, new int[]{R.drawable.on_sale_item3}));
        al.add(new Data(new String[]{"Mid-Length Wool Cashmere Trench Coat", "$42", "Burberry US"}, new int[]{R.drawable.on_sale_item4}));
        this.iList = new ArrayList(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
        this.iList.addAll(al);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_exp, menu);
        menu.findItem(R.id.menu_grid).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
