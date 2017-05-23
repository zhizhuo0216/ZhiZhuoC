package zhizhuoc.zhizhuo.com.zhizhuoc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 52829 on 2017/5/24.
 */

public class ContemnFragment extends Fragment {
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_content,container,false);
        textView=(TextView)view.findViewById(R.id.textView);

        String textString =getArguments().getString("text");
        textView.setText(textString);
        return view;
    }
}
