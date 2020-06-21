package in.hermansyah.temanbuku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailBukuActivity extends AppCompatActivity {
    public static final String INTENT_BUKU = "intent_buku";
    private Buku dataBuku;
    TextView textViewNama, textViewAuthor, textViewSinopsis;
    ImageView imageViewGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        textViewNama = findViewById(R.id.textViewDetailNama);
        textViewSinopsis = findViewById(R.id.textViewDetailSinopsis);
        textViewAuthor = findViewById(R.id.textViewDetailAuthor);
        imageViewGambar = findViewById(R.id.imageViewDetailGambar);

        dataBuku = (Buku) getIntent().getParcelableExtra(INTENT_BUKU);

        textViewNama.setText(dataBuku.getNama());
        textViewAuthor.setText(dataBuku.getAuthor());
        textViewSinopsis.setText(dataBuku.getSinopsis());

        Glide.with(this)
                .load(dataBuku.getGambar())
                .into(imageViewGambar);
    }
}
