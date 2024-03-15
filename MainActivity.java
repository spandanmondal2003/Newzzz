package industries.sdw.newzzz;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategorInterfaceClick{


    private RecyclerView category,news;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private ProgressBar progressBar;
    private ArrayList<CategoryModel> categoryModel;
    private CategoryAdapter categoryAdapter;
    SearchView searchView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryModel = new ArrayList<>();
        category = findViewById(R.id.Categgories);
        news = findViewById(R.id.News);
        progressBar = findViewById(R.id.progress);
        categoryModel = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryModel,this,this::oncategoryclick);
        category.setAdapter(categoryAdapter);
        searchView = findViewById(R.id.search);
        toolbar = findViewById(R.id.Toolbar);
        getCategories();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("GENERAL",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        news.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(articles);
        news.setAdapter(adapter);
        getNews("General",null);


    }



    void change(boolean show){
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);

    }

    void getNews(String category,String query){
        change(true);
        NewsApiClient apiClient = new NewsApiClient("dd5c6050458c4a85bd19656e880f29a5");
        apiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .category(category)
                        .q(query)
                        .build(),

                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(()->
                                change(false));
                                articles = response.getArticles();
                                adapter.updatedata(articles);
                                adapter.notifyDataSetChanged();
                        
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("GOT FAILURE", "onFailure: ");

                    }
                }
        );


    }

    private void getCategories(){
        //categoryModel.add(new CategoryModel("All","https://plus.unsplash.com/premium_photo-1667579006452-30de09c775ce?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8b3BlbiUyMHJvYWR8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("General","https://images.unsplash.com/photo-1512314889357-e157c22f938d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Z2VuZXJhbHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("Science","https://images.unsplash.com/photo-1564325724739-bae0bd08762c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8c2NpZW5jZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("Sports","https://images.unsplash.com/photo-1517649763962-0c623066013b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8c3BvcnRzfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("Business","https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dHJhZGluZ3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("Entertainment","https://plus.unsplash.com/premium_photo-1682125771198-f7cbed7cb868?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8ZmlsbXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("Health","https://plus.unsplash.com/premium_photo-1682309570054-e2fdcbb2c682?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHJlZCUyMGNyb3NzfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        categoryModel.add(new CategoryModel("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoryAdapter.notifyDataSetChanged();
    }


    @Override
    public void oncategoryclick(int position) {
        String category = categoryModel.get(position).getCategory();
        getNews(category,null);

    }
}