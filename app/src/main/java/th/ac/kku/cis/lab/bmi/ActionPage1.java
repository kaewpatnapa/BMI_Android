package th.ac.kku.cis.lab.bmi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActionPage1 extends Fragment {
    TextView bici;
    TextView bicielec;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_action_page1,container,false);
        bici = (TextView) view.findViewById(R.id.text_detail_bi);
        bicielec = (TextView) view.findViewById(R.id.text_detail_bi_elec);

        bici.setText("หากผู้หญิงน้ำหนักเกณฑ์ปกติปั่นจักยานหนักๆโดยไม่หยุดพักเป็นเวลา 1 ชั่วโมง เผาผลาญได้ประมาณ 850 แคลอรี่");
        bicielec.setText("ปั่นจักรยานไฟฟ้า 1 ชั่วโมงช่วยเผาผลาญได้ถึง 600 แคลอรี่ และเพิ่มอัตราอีก 11% ถ้าปั่นถอยหลังแทนปั่นแบบปกติ");
        return view;
    }
}
