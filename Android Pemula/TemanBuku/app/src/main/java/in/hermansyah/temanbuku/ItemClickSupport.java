package in.hermansyah.temanbuku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemClickSupport {
    private final RecyclerView recyclerView;
    private OnItemClickListerner onItemClickListener;
    private OnItemLongClickListerner onItemLongClickListener;

    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    private View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null){
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                return onItemLongClickListener.onItemLongClicked(recyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            if (onItemClickListener != null){
                view.setOnClickListener(onClickListener);
            }
            if (onItemLongClickListener != null){
                view.setOnLongClickListener(onLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {

        }
    };

    public ItemClickSupport(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setTag(R.id.item_click_support, this);
        this.recyclerView.addOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
    }

    public static ItemClickSupport addTo (RecyclerView recyclerView) {
        ItemClickSupport support = (ItemClickSupport) recyclerView.getTag(R.id.item_click_support);
        if (support == null){
            support = new ItemClickSupport(recyclerView);
        }
        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView recyclerView){
        ItemClickSupport support = (ItemClickSupport) recyclerView.getTag(R.id.item_click_support);
        if (support !=null){
            support.detach(recyclerView);
        }
        return support;
    }


    public void setOnItemClickListener(OnItemClickListerner onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListerner onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListerner {
        void onItemClicked(RecyclerView recyclerView, int position, View view);
    }

    public interface OnItemLongClickListerner {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View view);
    }

    private void detach (RecyclerView recyclerView) {
        recyclerView.removeOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
        recyclerView.setTag(R.id.item_click_support, null);
    }
}
