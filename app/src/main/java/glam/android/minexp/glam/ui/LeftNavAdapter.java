package glam.android.minexp.glam.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//import com.glam.C0148R;
import glam.android.minexp.glam.model.Data;
import java.util.ArrayList;
import glam.android.minexp.glam.R;

public class LeftNavAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Data> items;
    private int selection;

    public int isSelection() {
        return this.selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
        notifyDataSetChanged();
    }

    public LeftNavAdapter(Context context, ArrayList<Data> items) {
        this.context = context;
        this.items = items;
    }

    public int getCount() {
        return this.items.size();
    }

    public Data getItem(int arg0) {
        return (Data) this.items.get(arg0);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.left_nav_item, null);
        }
        Data f = getItem(position);
        TextView lbl = (TextView) convertView;
        lbl.setText(f.getTexts()[0]);
        if (this.selection == position) {
            lbl.setTextColor(-1);
            lbl.setCompoundDrawablesWithIntrinsicBounds(f.getResources()[1], 0, 0, 0);
            convertView.setBackgroundColor(this.context.getResources().getColor(R.color.main_grey_dk1));
        } else {
            convertView.setBackgroundResource(0);
            lbl.setTextColor(this.context.getResources().getColor(R.color.main_grey_dk1));
            lbl.setCompoundDrawablesWithIntrinsicBounds(f.getResources()[0], 0, 0, 0);
        }
        return convertView;
    }
}
