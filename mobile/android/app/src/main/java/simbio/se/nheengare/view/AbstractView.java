package simbio.se.nheengare.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AbstractView implements OnClickListener {

    protected final View self;

    public AbstractView(Context context, int layoutId) {
        self = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId, null);
        self.setOnClickListener(this);
    }

    public View getView() {
        return self;
    }

    public EditText findEditTextById(int id) {
        return self.findViewById(id);
    }

    public TextView findTextViewById(int id) {
        return self.findViewById(id);
    }

    public ListView findListViewById(int id) {
        return self.findViewById(id);
    }

    public ImageView findImageViewById(int id) {
        return self.findViewById(id);
    }

    public ProgressBar findProgressBarById(int id) {
        return self.findViewById(id);
    }

    public LinearLayout findLinearLayoutById(int id) {
        return self.findViewById(id);
    }

    @Override
    public void onClick(View view) {
    }

}
