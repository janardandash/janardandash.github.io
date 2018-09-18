package odhisha.john.jani.jana.onlinequiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import odhisha.john.jani.jana.onlinequiz.Interface.ItemClickListner;
import odhisha.john.jani.jana.onlinequiz.Model.Category;
import odhisha.john.jani.jana.onlinequiz.Model.Common;
import odhisha.john.jani.jana.onlinequiz.ViewHolder.MenuViewHolder;

public class ListCategory extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference category;
    TextView categorytext;
    StorageReference storageRef;
    FirebaseStorage storage;
    TextView txtfullname;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progress;
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        categorytext=(TextView)findViewById(R.id.categorytext);
        Typeface tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        Typeface sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        categorytext.setTypeface(sb);

        //firebase connection

        database=FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        category=database.getReference("Category");
        recyclerView=(RecyclerView)findViewById(R.id.recyler_menu);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.relative_layout);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            loadmenu();
        }
        else{

            linearLayout.setBackgroundResource(R.color.white);
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Please Connect to intenet", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Your Device has no internet connection !!");
            builder.setIcon(R.drawable.ic_error_black_24dp);
            builder.show();
        }
    }

    private void loadmenu() {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        adapter=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
               // Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imgMenu);

                Glide.with(getApplicationContext())
                        .using(new FirebaseImageLoader())
                        .load(storage.getReferenceFromUrl(model.getImage()))
                        .into(viewHolder.imgMenu);

                final Category clickitem=model;
                viewHolder.setItemClickListner(new ItemClickListner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //Toast.makeText(Home.this, ""+clickitem.getName(), Toast.LENGTH_SHORT).show();

                        //Get categoryid and send to next activity
                        Intent intent=new Intent(ListCategory.this,StartActivity.class);
                        //Common.categoryid=adapter.getRef(position).getKey();
                        intent.putExtra("Name",model.getName());
                        startActivity(intent);
                        finish();

                    }
                });
                progress.dismiss();
            }
        };
        recyclerView.setAdapter(adapter);
    }

}
