package com.example.allenappstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends SimpleAdapter {

    private ArrayList<HashMap<String, String>> data;
    private Context context;

    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        // Get the data item for this position
        HashMap<String, String> app = data.get(position);

        // Set the app name
        TextView appName = convertView.findViewById(R.id.app_name);
        appName.setText(app.get("name"));

        // Set the install button
        Button installButton = convertView.findViewById(R.id.install_button);
        installButton.setText("Install");
        installButton.setTag(position);
        installButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                String url = data.get(position).get("url");
                ((MainActivity) context).installApp(url);
            }
        });

        return convertView;
    }
}
