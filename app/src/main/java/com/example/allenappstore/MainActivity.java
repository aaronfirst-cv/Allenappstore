package com.example.allenappstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String, String>> appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.app_list);

        // Sample data for demonstration
        appList = new ArrayList<>();
        addApp("Sample App 1", "https://example.com/sample-app1.apk");
        addApp("Sample App 2", "https://example.com/sample-app2.apk");
        addApp("Sample App 3", "https://example.com/sample-app3.apk");
        // Add more apps as needed

        CustomAdapter adapter = new CustomAdapter(
                this,
                appList,
                R.layout.list_item,
                new String[]{"name", "url"},
                new int[]{R.id.app_name, R.id.install_button}
        );

        listView.setAdapter(adapter);
    }

    private void addApp(String name, String url) {
        HashMap<String, String> app = new HashMap<>();
        app.put("name", name);
        app.put("url", url);
        appList.add(app);
    }

    public void installApp(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Installation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
