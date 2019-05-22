package th.ac.kku.cis.lab.bmi;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class action extends FragmentActivity {
    ImageButton page1;
    ImageButton page2;
    ImageButton page3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        page1 = (ImageButton) findViewById(R.id.image1_page);
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ActionPage1 oneFragment = new ActionPage1();
              FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
              transaction.replace(R.id.layout_fragment_container,oneFragment);
              transaction.commit();
            }
        });
        page2 = (ImageButton) findViewById(R.id.image2_page);
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionPage2 oneFragment = new ActionPage2();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_fragment_container,oneFragment);
                transaction.commit();
            }
        });
        page3 = (ImageButton) findViewById(R.id.image3_page);
        page3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionPage3 oneFragment = new ActionPage3();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_fragment_container,oneFragment);
                transaction.commit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActionPage1 oneFragment = new ActionPage1();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_fragment_container,oneFragment);
        transaction.commit();
    }
}
