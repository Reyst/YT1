package gsihome.reyst.y1t;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ImageGalleryAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private TextView mTextViewValueCreated;
    private TextView mTextViewValueRegistered;
    private TextView mTextViewValueDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.task_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTextViewValueCreated = (TextView) findViewById(R.id.tvValueCreated);
        mTextViewValueRegistered = (TextView) findViewById(R.id.tvValueRegistered);
        mTextViewValueDeadline = (TextView) findViewById(R.id.tvValueDeadline);

        initRecyclerView();
        initDates(Calendar.getInstance());

        ViewGroup vg = (ViewGroup) findViewById(R.id.mainContainer);
        int chCount = vg.getChildCount();

        for (int i = 0; i < chCount; i++) {

            View childView = vg.getChildAt(i);
                childView.setOnClickListener(this);
        }
    }

    private void initDates(Calendar date) {

        DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(getApplicationContext());

        mTextViewValueRegistered.setText(dateFormat.format(date.getTime()));

        date.add(Calendar.DAY_OF_YEAR, -2);
        mTextViewValueCreated.setText(dateFormat.format(date.getTime()));

        date.add(Calendar.MONTH, 2);
        mTextViewValueDeadline.setText(dateFormat.format(date.getTime()));
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rvImages);

        ViewGroup.LayoutParams lp = mRecyclerView.getLayoutParams();

        lp.height = getResources().getDisplayMetrics().widthPixels / 2;
        mRecyclerView.setLayoutParams(lp);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ImageGalleryAdapter(this, new String[]{
                "http://img4.st.klumba-ua.com/img/used/2014/11/08/6260/l/6260139_9.jpg",
                "http://img4.st.klumba-ua.com/img/used/2014/11/08/6260/l/6260139_8.jpg",
                "http://img3.st.klumba-ua.com/img/used/2015/02/17/6802/l/6802488_3.jpg",
                "http://img2.st.klumba-ua.com/img/used/2015/09/07/8037/l/8037167_4.jpg"

        }, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, v.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
