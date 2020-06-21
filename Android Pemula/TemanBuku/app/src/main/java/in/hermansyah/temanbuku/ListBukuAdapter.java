package in.hermansyah.temanbuku;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListBukuAdapter extends RecyclerView.Adapter<ListBukuAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Buku> listBuku;

    public ListBukuAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Buku> getListBuku() {
        return listBuku;
    }

    public void setListBuku(ArrayList<Buku> listBuku) {
        this.listBuku = listBuku;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_buku,
                viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.textViewNama.setText(getListBuku().get(i).getNama());
        categoryViewHolder.textViewAuthor.setText(getListBuku().get(i).getAuthor());

        Glide.with(context)
                .load(getListBuku().get(i).getGambar())
                .into(categoryViewHolder.circleImageViewGambar);
    }

    @Override
    public int getItemCount() {
        return getListBuku().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama, textViewAuthor;
        CircleImageView circleImageViewGambar;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.textViewItemNama);
            textViewAuthor = itemView.findViewById(R.id.textViewItemAuthor);
            circleImageViewGambar = itemView.findViewById(R.id.circleImageViewGambar);
        }
    }
}
