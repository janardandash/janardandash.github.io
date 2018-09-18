package odhisha.john.jani.jana.onlinequiz.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import odhisha.john.jani.jana.onlinequiz.Interface.ItemClickListner;
import odhisha.john.jani.jana.onlinequiz.R;

/**
 * Created by janardan.d on 14-12-2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imgMenu;
    private ItemClickListner itemClickListner;

    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName=(TextView)itemView.findViewById(R.id.menu_name);
        imgMenu=(ImageView)itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);
    }
}
