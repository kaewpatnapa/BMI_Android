package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class User_Page extends AppCompatActivity {
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__page);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("User");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<datauser> options = new FirebaseRecyclerOptions.Builder<datauser>()
                .setQuery(mData,datauser.class)
                .build();

        FirebaseRecyclerAdapter<datauser,Card_View> adapter = new FirebaseRecyclerAdapter<datauser, Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Card_View holder, int position, @NonNull datauser model) {
                final String post_key = getRef(position).getKey();
                holder.setUser(model.getName());
                holder.userl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),UserDetail.class);
                        intent.putExtra("PostKey",post_key);
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_user,viewGroup,false);
                return new Card_View(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public static class Card_View extends RecyclerView.ViewHolder{
        View mView;
        CardView userl;
        public Card_View(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            userl = (CardView) mView.findViewById(R.id.card_view_user);
        }
        public void setUser(String name){
            TextView textView_name = (TextView) mView.findViewById(R.id.user_name);
            textView_name.setText(name);
        }
    }
}
