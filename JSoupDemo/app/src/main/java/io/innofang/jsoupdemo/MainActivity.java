package io.innofang.jsoupdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://innofang.github.io";

    private ImageView mIconImageView;
    private RecyclerView mArticleRecyclerView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIconImageView = (ImageView) findViewById(R.id.icon_image_view);
        mArticleRecyclerView = (RecyclerView) findViewById(R.id.article_recycler_view);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        onHandleParseHtml();
    }

    public void onHandleParseHtml() {
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<ArticleItem> list = new ArrayList<>();
                final StringBuilder builder = new StringBuilder();
                final StringBuilder iconUrl = new StringBuilder();
                try {
                    final Document doc = Jsoup.connect(BASE_URL).get();
                    Elements img = doc.getElementsByClass("site-author-image");
                    String title = doc.title();
                    iconUrl.append(img.attr("src"));

                    builder.append(title).append("\n");

                    Elements links = doc.getElementsByClass("post-title-link");
                    for (Element link : links) {
                        String linkHref = link.attr("href");
                        String linkText = link.text();
                        builder.append(BASE_URL)
                                .append(linkHref)
                                .append("    ")
                                .append(linkText)
                                .append("\n");
                        list.add(new ArticleItem(linkText, BASE_URL+linkHref));

                        Log.i("tag", "run: " + BASE_URL+linkHref);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(iconUrl.toString())) {
                            Glide.with(MainActivity.this)
                                    .load(iconUrl.toString())
                                    .into(mIconImageView);
                        }
                        Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT)
                                .show();
                        showList(list);
                        closeProgressDialog();
                    }
                });
            }
        }).start();

    }

    private void showList(List<ArticleItem> list) {
        if (list.isEmpty())
            return;

        Log.i("tag", "showList: " + list.size());

        mArticleRecyclerView.setAdapter(new BaseSimpleAdapter<ArticleItem>(
                MainActivity.this, android.R.layout.simple_list_item_1, list) {
            @Override
            protected void bindViewHolder(BaseSimpleViewHolder viewHolder,
                                          final ArticleItem articleItem, int position) {
                viewHolder.setText(android.R.id.text1, articleItem.title);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, articleItem.moreLink,
                                Toast.LENGTH_SHORT)
                                .show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(articleItem.moreLink));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void showProgressDialog() {
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    public void closeProgressDialog() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }
    }
}
