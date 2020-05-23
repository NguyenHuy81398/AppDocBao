package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listSearch;
    ArrayList<ItemTinTuc> itemTinTucs;
    ListSearch_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        searchView = findViewById(R.id.searchView);
        listSearch = findViewById(R.id.lvSeach);

        new ReadRssSeach().execute();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }

    private class ReadRssSeach extends AsyncTask<String, Void, Void>{
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(TimKiemActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
                String url = "https://vnexpress.net/rss/tin-moi-nhat.rss";
                ArrayList<ItemTinTuc> itemTinTucs = new ArrayList<>();
                try {
                    org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
                    Elements elements = doc.select("item");
                    for (int j=0; j<=10; j++){
                        Element item = elements.get(j);
                        String title = item.select("title").text();
                        String link = item.select("link").text();
                        String pubDate = item.select("pubDate").text();
                        String description = item.select("description").text();

                        Document docIMG = Jsoup.parse(description);
                        String img = "";
                        if(docIMG.select("img").isEmpty()){
                            img = "https://i-vnexpress.vnecdn.net/2020/04/30/settop72871588223888-158822416-4317-2483-1588224181_180x108.jpg";
                        }else {
                            img = docIMG.select("img").get(0).attr("src");
                        }
                        itemTinTucs.add(new ItemTinTuc(title, img, link, pubDate));
                    }

                    adapter = new ListSearch_Adapter(getBaseContext(), itemTinTucs);
                    listSearch.setAdapter(adapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
}
