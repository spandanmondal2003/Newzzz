package industries.sdw.newzzz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private ArrayList<CategoryModel> categoryModels;
    private Context context;
    private CategorInterfaceClick categorClickInterface;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels, Context context, CategorInterfaceClick categorClickInterface) {
        this.categoryModels = categoryModels;
        this.context = context;
        this.categorClickInterface = categorClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    public interface CategorInterfaceClick{
        void oncategoryclick(int  position);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (categoryModels.isEmpty()){
            return;
        }
        CategoryModel categoryModel = categoryModels.get(position);
        holder.textView.setText(categoryModel.getCategory());
        Picasso.get().load(categoryModel.getImageUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorClickInterface.oncategoryclick(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.picture);


        }
    }
}
