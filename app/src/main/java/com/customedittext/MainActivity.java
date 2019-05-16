package com.customedittext;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout openCountryCodeDialog;
    private EditText mobileNumber;
    private String uNumber;
    private Button nextPage;
    private Toolbar toolbar;
    private Toast toast;
    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView recycleCountrys;
    private LinearLayout linearLayoutBSheet;
    private List<StudentInfoModal> listItem;
    private StudentInfoAdapter studentInfoAdapter;
    private Boolean isCountryCodeDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInItId();
        setToolbar();
        setDataInList();
        setUpRecyclerView();
    }

    private void setDataInList() {
        for (int i = 0; i < 20; i++) {
            StudentInfoModal studentInfoModalList = new StudentInfoModal("harsh" + i, "21");
            listItem.add(studentInfoModalList);
        }
    }

    private void setUpRecyclerView() {
        recycleCountrys.setLayoutManager(new LinearLayoutManager(this));
        studentInfoAdapter = new StudentInfoAdapter(listItem);
        recycleCountrys.setAdapter(studentInfoAdapter);
    }

    private void setInItId() {
        openCountryCodeDialog = findViewById(R.id.rl_open_dialog);
        mobileNumber = findViewById(R.id.et_number);
        nextPage = findViewById(R.id.btn_next_page);
        toolbar = findViewById(R.id.customToolBar);
        linearLayoutBSheet = findViewById(R.id.ll_bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBSheet);
        recycleCountrys = findViewById(R.id.bottomSheet_recycler);
        nextPage.setOnClickListener(this);
        openCountryCodeDialog.setOnClickListener(this);
        listItem = new ArrayList<>();
    }

    private void setToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_open_dialog:
               /* toast = Toast.makeText(this, "BottomSheetDialog", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 250);
                toast.show();*/
               setUpBottomSheet();
                /* Toast.makeText(this, "BottomSheetDialog", Toast.LENGTH_SHORT).show();*/
                break;
            case R.id.btn_next_page:
                closeKeyboard();
                uNumber = mobileNumber.getText().toString();
                toast = Toast.makeText(this, "MobileNumber:" + uNumber, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 250);
                toast.show();
                break;
            default:
                break;
        }
    }

    private void setUpBottomSheet() {
        if(isCountryCodeDialog)
        {
            linearLayoutBSheet.setVisibility(View.VISIBLE);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            isCountryCodeDialog = false;
        }
        else {
            linearLayoutBSheet.setVisibility(View.VISIBLE);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            isCountryCodeDialog = true;
        }

    bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View view, int i) {

        }

        @Override
        public void onSlide(@NonNull View view, float v) {

        }
    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
