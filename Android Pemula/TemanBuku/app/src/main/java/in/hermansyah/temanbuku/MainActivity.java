package in.hermansyah.temanbuku;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBukuList;
    private ArrayList<Buku> listBuku = new ArrayList<>();
    final String STATE_LIST = "state_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewBukuList = findViewById(R.id.recycleViewBukuList);
        recyclerViewBukuList.setHasFixedSize(true);

        listBuku.addAll(BukuData.getListData());

        if(savedInstanceState == null){
            listBuku = BukuData.getListData();
            showRecyclerList();;
        }else {
            ArrayList<Buku> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            listBuku.addAll(stateList);
        }
    }

    private void showRecyclerList() {
        recyclerViewBukuList.setLayoutManager(new LinearLayoutManager(this));
        final ListBukuAdapter listBukuAdapter = new ListBukuAdapter(this);
        listBukuAdapter.setListBuku(listBuku);
        recyclerViewBukuList.setAdapter(listBukuAdapter);

        ItemClickSupport.addTo(recyclerViewBukuList).setOnItemClickListener(new ItemClickSupport.OnItemClickListerner() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                //Todo: Tampilkan Detail
                Buku buku = listBuku.get(position);
                Intent intentDetail = new Intent(MainActivity.this, DetailBukuActivity.class);
                intentDetail.putExtra(DetailBukuActivity.INTENT_BUKU, (Parcelable) buku);
                startActivity(intentDetail);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, listBuku);
    }
}
