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

import org.w3c.dom.Text;

public class ActionPage2 extends Fragment {
    TextView ji;
    TextView water;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_action_page2,container,false);
        ji = (TextView) view.findViewById(R.id.text_detail_jump);
        water = (TextView) view.findViewById(R.id.text_detail_swing);

        ji.setText("กระโดดเชือก 1 ชั่วโมง ร่างกายสามารถเผาผลาญพลังงานได้ถึง 500 แคลอรี่ ช่วยกระชับช่วงล่างถ้าใครไม่เคยออกกำลังกายนานๆเริ่มต้นแนะนำให้กระโดด 10 - 20 นาทีก่อนค่อยเป็นค่อยไป");
        water.setText("การว่ายน้ำเหมาะมากสำหรับคนที่ไม่ชอบเหงื่อออกเยอะๆหรืออากาศ้อนๆ ที่ทำให้ตัวเหนียวเหนอะหนะการว่ายน้ำถือเป็นการออกกำลังกายี่ได้ออกแรงกล้ามเนื้อทุกส่วนในร่างกาย ");
        return view;
    }
}
