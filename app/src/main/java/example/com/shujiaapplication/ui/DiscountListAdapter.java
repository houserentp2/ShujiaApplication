package example.com.shujiaapplication.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.com.shujiaapplication.R;

public class DiscountListAdapter extends RecyclerView.Adapter<DiscountListAdapter.ViewHolder> implements LeftSlideView.IonSlidingButtonListener{

    private Context mContext;
    private List<DiscountListData> mdiscountList;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private LeftSlideView mMenu = null;

    public DiscountListAdapter(List<DiscountListData> d,Context context){
        mdiscountList = d;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.discount_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mdiscountList.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (LeftSlideView)view;
    }

    @Override
    public void onBindViewHolder(@NonNull final DiscountListAdapter.ViewHolder viewHolder, int i) {
        DiscountListData discount = mdiscountList.get(i);
        viewHolder.discountMoney.setText("¥"+discount.getReduce());
        viewHolder.discountStore.setText(discount.getDescription());
        viewHolder.discountDate.setText(discount.getOutdate());
        viewHolder.discountImage.setImageResource(R.drawable.discount);

        viewHolder.layout_content.getLayoutParams().width = Utils.getScreenWidth(mContext);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = viewHolder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }

            }
        });

        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int n = viewHolder.getLayoutPosition();
                mIDeleteBtnClickListener.onItemLongClick(v,n);
                return true;
            }
        });

        viewHolder.delete_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = viewHolder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(view, n);
            }
        });
    }

    @Override
    public void onDownOrMove(LeftSlideView leftSlideView) {
        if (menuIsOpen()) {
            if (mMenu != leftSlideView) {
                closeMenu();
            }
        }
    }



    public void removeData(int position) {
        mdiscountList.remove(position);
        notifyItemRemoved(position);
    }

    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);//点击item正文
        void onDeleteBtnCilck(View view, int position);//点击“删除”
        void onItemLongClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView discountMoney;
        TextView discountDate;
        TextView discountStore;
        ImageView discountImage;
        CardView cardView;
        public TextView delete_discount;
        ViewGroup layout_content;

        public ViewHolder(View view){
            super(view);
            discountMoney = (TextView)view.findViewById(R.id.discountMoney);
            discountDate = (TextView)view.findViewById(R.id.discountDate);
            discountStore = (TextView)view.findViewById(R.id.discountStore);
            discountImage = (ImageView) view.findViewById(R.id.discountImage);
            delete_discount = (TextView)view.findViewById (R.id.delete_discount);
            layout_content = (ViewGroup)view.findViewById(R.id.layout_content);
            cardView = (CardView)view.findViewById(R.id.layout_content);
            ((LeftSlideView)view).setSlidingButtonListener(DiscountListAdapter.this);
        }
    }
}
