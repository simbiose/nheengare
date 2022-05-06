package simbio.se.nheengare;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simbio.se.nheengare.activities.AboutActivity;
import simbio.se.nheengare.activities.AbstractActivity;
import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.activities.configuration.ConfigurationsActivity;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.core.Options;
import simbio.se.nheengare.models.AbstractModel;
import simbio.se.nheengare.models.Language.LANGUAGE;
import simbio.se.nheengare.models.Word;

public class MainActivity extends AbstractActivity implements TextWatcher, Runnable, OnItemClickListener {

    private final ArrayList<String> adapterAux = new ArrayList<>();
    private final ArrayList<Word> words = new ArrayList<>();

    private ArrayAdapter<String> adapter;
    private EditText edtInput;
    private ListView listResults;
    private ProgressBar pbSearch;

    private boolean firstShow = true;
    private boolean searchLock = false;
    private boolean searchAgain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onOptionsChanged(null);
    }

    @Override
    protected void loadOnThread() {
        edtInput = findEditTextById(R.id.autoCompleteTextViewMain);
        edtInput.addTextChangedListener(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<>());

        listResults = findListViewById(R.id.listViewMain);
        listResults.setOnItemClickListener(this);

        pbSearch = findViewById(R.id.progressBarMainSearchWord);
    }

    @Override
    protected void loadOnUiThread() {
        listResults.setAdapter(adapter);
        refreshList();
        findViewById(R.id.autoCompleteTextViewMain).setVisibility(View.VISIBLE);
    }

    public void refreshList() {
        new Thread(this).start();
    }

    @Override
    public void afterTextChanged(Editable s) {
        refreshList();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        listResults.setSelectionAfterHeaderView();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void run() {
        if (searchLock) {
            setSearchAgain(true);
        } else {
            setSearchLock(true);
            AbstractModel.criteria = edtInput.getText().toString().toLowerCase();

            runOnUiThread(() -> pbSearch.setVisibility(View.VISIBLE));

            ArrayList<Word> words = new ArrayList<>(getBlackBoard().getWords());

            Options options = BlackBoard.getBlackBoard(getApplicationContext()).getOptions();

            if (options.filterSearchLanguages()) {
                ArrayList<LANGUAGE> langFilter = new ArrayList<>();
                if (!options.filterSearchShowNheengatu())
                    langFilter.add(LANGUAGE.NHEENGATU);
                if (!options.filterSearchShowPortuguese())
                    langFilter.add(LANGUAGE.PORTUGUESE);
                if (!options.filterSearchShowSpanish())
                    langFilter.add(LANGUAGE.SPANISH);
                if (!options.filterSearchShowEnglish())
                    langFilter.add(LANGUAGE.ENGLISH);
                if (!langFilter.isEmpty()) {
                    ArrayList<Word> wordsToRemove = new ArrayList<>();
                    for (Word w : words) {
                        if (langFilter.contains(w.getLanguage())) {
                            wordsToRemove.add(w);
                        }
                    }
                    words.removeAll(wordsToRemove);
                }
            }

            Collections.sort(words, Collections.reverseOrder());
            List<Word> listTemp = words.subList(0, (Math.min(100, words.size())));
            adapterAux.clear();
            for (Word w : listTemp) {
                adapterAux.addAll(w.getWrites());
            }
            this.words.clear();
            this.words.addAll(words);

            runOnUiThread(() -> {
                adapter.clear();
                adapter.addAll(adapterAux);
                adapter.notifyDataSetChanged();

                if (firstShow) {
                    firstShow = false;
                    show(new int[]{R.id.autoCompleteTextViewMain, R.id.listViewMain});
                    listResults.setEmptyView(findViewById(R.id.emptyViewMain));
                }

                setSearchLock(false);
                pbSearch.setVisibility(View.INVISIBLE);
                if (searchAgain) {
                    setSearchAgain(false);
                    new Thread(MainActivity.this).start();
                }
            });
        }
    }

    public synchronized void setSearchAgain(boolean searchAgain) {
        this.searchAgain = searchAgain;
    }

    public synchronized void setSearchLock(boolean searchLock) {
        this.searchLock = searchLock;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("Word", words.get(arg2).getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_config) {
            startActivity(new Intent(getApplicationContext(), ConfigurationsActivity.class));
            return true;
        }
        if (itemId == R.id.action_about) {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            return true;
        }
        if (itemId == R.id.action_speak) {
            sendEmail(getString(R.string.action_email_subject_main), getString(R.string.action_email_content_main));
            return true;
        }
        if (itemId == R.id.action_share) {
            share(getString(R.string.action_share_content_main));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsChanged(Options newOptions) {
        new Thread(this).start();
    }

}
