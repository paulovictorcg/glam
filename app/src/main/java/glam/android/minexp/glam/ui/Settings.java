package glam.android.minexp.glam.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import glam.android.minexp.glam.R;
import glam.android.minexp.glam.MainActivity;
import glam.android.minexp.glam.custom.CustomFragment;
import glam.android.minexp.glam.model.Data;
import java.util.ArrayList;

public class Settings extends CustomFragment {
    private ArrayList<Data> iList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings, null);
        ((MainActivity) getActivity()).toolbar.setTitle((CharSequence) "Settings");
        ((MainActivity) getActivity()).toolbar.findViewById(R.id.spinner_toolbar).setVisibility(8);
        return v;
    }

    public void onClick(View v) {
        super.onClick(v);
    }
}
