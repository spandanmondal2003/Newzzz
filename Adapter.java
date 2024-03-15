package industries.sdw.newzzz;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Article> articles;

    public Adapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news,parent,false);
        return  new ViewHolder(view);
    }

    void updatedata(List<Article> data){
        articles.clear();
        articles.addAll(data);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            Article article = articles.get(position);
            holder.main.setText(article.getTitle());
            holder.summary.setText(article.getDescription());
            Picasso.get().load(article.getUrlToImage()).into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),NewsDetail.class);
                    intent.putExtra("Title",article.getTitle());
                    intent.putExtra("desc",article.getDescription());
                    intent.putExtra("Content",article.getContent());
                    intent.putExtra("Image",article.getUrlToImage());
                    intent.putExtra("Url",article.getUrl());
                    v.getContext().startActivity(intent);
                }
            });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView main,summary;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main = itemView.findViewById(R.id.Main);
            summary = itemView.findViewById(R.id.summary);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
