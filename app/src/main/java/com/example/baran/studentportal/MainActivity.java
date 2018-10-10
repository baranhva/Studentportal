package com.example.baran.studentportal;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener {

//Local variables
    private List<Portal> mPortals;
    private PortalAdapter mAdapter;
    private RecyclerView mRecyclerView;

    //Constants used when calling the update activity
    public static final String EXTRA_PORTAL = "Portal";
    public static final int REQUESTCODE = 1234;
//    private int mModifyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize the local variables
        mPortals = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        FloatingActionButton fab = findViewById(R.id.fab_new);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
              startActivityForResult(intent,REQUESTCODE);
            }
        });

/*
Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
and uses callbacks to signal when a user is performing these actions.
*/
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        mPortals.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

updateUI();
    }


    public void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalAdapter(mPortals, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void portalOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, portalWebview.class);
        intent.putExtra(EXTRA_PORTAL,  mPortals.get(i));
        startActivityForResult(intent, REQUESTCODE);
    }

@Override

public void onActivityResult(int requestCode, int resultCode, Intent data) {


   if (requestCode == REQUESTCODE) {
       if (resultCode == RESULT_OK) {
           Portal updatedReminder = data.getParcelableExtra(MainActivity.EXTRA_PORTAL);
           // New timestamp: timestamp of update
           mPortals.add(updatedReminder);
           updateUI();
       }
   }
}




}
