package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class savenote extends AppCompatActivity {
    RecyclerView recyclerView;
    Query mData;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savenote);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Activity").orderByChild("userid").equalTo(user.getUid());
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<card_save> options = new FirebaseRecyclerOptions.Builder<card_save>()
                .setQuery(mData,card_save.class)
                .build();
        FirebaseRecyclerAdapter<card_save,Card_View> adapter = new FirebaseRecyclerAdapter<card_save, Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Card_View holder, int position, @NonNull card_save model) {
                final String post_key = getRef(position).getKey();
                holder.setActivity(model.getDetail());
                holder.stack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(getApplicationContext(),holder.stack);
                        popupMenu.inflate(R.menu.menu_stack);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.edit_ac:
                                        Intent intent = new Intent(savenote.this,editPage.class);
                                        intent.putExtra("PostKey",post_key);
                                        startActivity(intent);
                                        break;
                                    case R.id.delete_ac:
                                        FirebaseDatabase.getInstance().getReference("Activity").child(post_key).removeValue();
                                        break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });
            }

            @NonNull
            @Override
            public Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_activity,viewGroup,false);
                return new Card_View(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public static class Card_View extends RecyclerView.ViewHolder{
        View mView;
        ImageView stack;
        public Card_View(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            stack = (ImageView) mView.findViewById(R.id.stack);

        }
        public void setActivity(String activity){
            TextView Activity = (TextView) mView.findViewById(R.id.text_detail_activity);
            Activity.setText(activity);
        }
    }
}
