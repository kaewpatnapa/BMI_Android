package th.ac.kku.cis.lab.bmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class save extends AppCompatActivity {
    ImageButton save_btn;
    RecyclerView recyclerView;
    Query mData;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        save_btn = (ImageButton) findViewById(R.id.imageButton_save);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(save.this,note.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycle_save);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("BMI").orderByChild("userid").equalTo(user.getUid());
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<card_bmi> options = new FirebaseRecyclerOptions.Builder<card_bmi>()
                .setQuery(mData,card_bmi.class)
                .build();
        FirebaseRecyclerAdapter<card_bmi,Card_View> adapter = new FirebaseRecyclerAdapter<card_bmi, Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Card_View holder, final int position, @NonNull card_bmi model) {
                final String post_key =  getRef(position).getKey();
                  holder.setHiegh(model.getHeigh());
                  holder.setWCard(model.getWeight());
                  holder.cardView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          PopupMenu popupMenu = new PopupMenu(getApplicationContext(),holder.cardView);
                          popupMenu.inflate(R.menu.menu_edit);
                          popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                              @Override
                              public boolean onMenuItemClick(MenuItem item) {
                                  switch (item.getItemId()){
                                      case R.id.delete_menu:
                                          FirebaseDatabase.getInstance().getReference("BMI").child(post_key).removeValue();
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
                        .inflate(R.layout.content_h_w,viewGroup,false);
                return new Card_View(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    public static class Card_View extends RecyclerView.ViewHolder{
        View mView;
        CardView cardView;
        public Card_View(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            cardView = (CardView) mView.findViewById(R.id.card_view_hw);
        }
        public void setWCard(String wieght){
            TextView wecard = (TextView) mView.findViewById(R.id.wieght_card);
            wecard.setText(wieght);
        }
        public void setHiegh(String hieght){
            TextView hcard = (TextView) mView.findViewById(R.id.hiegh_card);
            hcard.setText(hieght);
        }
    }
}
