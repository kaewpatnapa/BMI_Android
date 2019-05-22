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

public class ActionPage3 extends Fragment {
    TextView airobi;
    TextView yokadance;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_action_page3,container,false);
        airobi = (TextView) view.findViewById(R.id.text_detail_air);
        yokadance = (TextView) view.findViewById(R.id.text_detail_yoka);
        airobi.setText("เต้นแอโรบิกเป็นการออกกำลังกายไปพร้อมเสียงเพลงเต้นตามจังหวะสนุกสนานทำให้การออกกำลังกายของเราไม่น่าเบื่อเต้นสนุกๆขำๆแค่ชั่วโมงเดียว สามารถเผาผลาญได้ถึง 500 แคลอรี่");
        yokadance.setText("ปกติโยคะ เราจะเล่นเพื่อเสริมสุขภาพร่างกายและสุขภาพจิตใจให้แข็งแรงผลพลอยได้ก็คือน้ำหนักที่ลดลงยิ่งเป็นโยคะที่เล่นในห้องที่มีุณหภูมิประมาณ 37 องศาเซลเซียส ซึ่งเป็นอุณหภูมิที่ใกล้เคียงกับอุณหภูมิของร่างกายแบบนี้ซักชั่วโมงนึงเบิร์นได้เกิน 500 แคลอรี่่");
        return view;
    }
}
