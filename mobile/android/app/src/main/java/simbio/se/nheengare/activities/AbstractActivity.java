package simbio.se.nheengare.activities;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;

import simbio.se.nheengare.MainActivity;
import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.core.IOptionsChangedListener;
import simbio.se.nheengare.core.Options;

public class AbstractActivity extends Activity implements IOptionsChangedListener {

    private boolean dataHasLoaded = false;

    protected BlackBoard getBlackBoard() {
        return BlackBoard.getBlackBoard(getApplicationContext());
    }

    protected void show(@NonNull int[] viewsIds) {
        for (int i : viewsIds) {
            findViewById(i).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.loadViews).setVisibility(View.INVISIBLE);
        clearTemp();
    }

    protected void loadOnUiThread() {
    }

    protected void loadOnThread() {
    }

    protected void clearTemp() {
    }

    protected void share(String content) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(intent, getString(R.string.action_share_with)));
    }

    protected void sendEmail(String subject, String content) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.action_email_address), null));
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(content));
        startActivity(Intent.createChooser(emailIntent, getString(R.string.action_email_with)));
    }

    protected void backToHome() {
        Intent upIntent = new Intent(this, MainActivity.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("isFromHome", true)) {
            NavUtils.navigateUpTo(this, upIntent);
        } else {
            TaskStackBuilder.create(this).addNextIntent(upIntent).startActivities();
            finish();
        }
    }

    public EditText findEditTextById(int id) {
        return super.findViewById(id);
    }

    public TextView findTextViewById(int id) {
        return super.findViewById(id);
    }

    public ListView findListViewById(int id) {
        return super.findViewById(id);
    }

    public ImageView findImageViewById(int id) {
        return super.findViewById(id);
    }

    public LinearLayout findLinearLayoutById(int id) {
        return super.findViewById(id);
    }

    public RelativeLayout findRelativeLayoutById(int id) {
        return super.findViewById(id);
    }

    public ScrollView findScrollViewById(int id) {
        return super.findViewById(id);
    }

    public ToggleButton findToggleButtonViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    public void onOptionsChanged(Options newOptions) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        Options.addOptionsChangeListener(this);
        if (!dataHasLoaded) {
            new Thread(() -> {
                loadOnThread();
                runOnUiThread(this::loadOnUiThread);
            }).start();
            dataHasLoaded = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Options.removeOptionsChangeListener(this);
    }

}
