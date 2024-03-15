package industries.sdw.newzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {


    String titlee,desc,img,content,url;
    private ImageView imageView;
    private TextView Title,Summary,contentt;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        imageView = findViewById(R.id.picture);
        Title = findViewById(R.id.Title);
        Summary = findViewById(R.id.Summary);
        contentt = findViewById(R.id.conntent);
        btn = findViewById(R.id.button);

        titlee = getIntent().getStringExtra("Title");
        desc = getIntent().getStringExtra("desc");
        img = getIntent().getStringExtra("Image");
        content = getIntent().getStringExtra("Content");
        url = getIntent().getStringExtra("Url");


        Title.setText(titlee);
        Summary.setText(desc);
        contentt.setText(content);
        Picasso.get().load(img).into(imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),NewsPage.class);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });


    }


}