package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInforPage;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.presenter.EquipmentListPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Activity.LoginActivity;
import com.hy.onlinemonitor.view.Adapter.EquipmentRecyclerAdapter;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.Button;
import com.rey.material.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EquipmentListViewActivity extends BaseActivity implements LoadDataView {

    @Bind(R.id.rootView)
    RelativeLayout rootView;
    @Bind(R.id.equipment_toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    @Bind(R.id.error_message_tv)
    TextView errorMessageTv;
    @Bind(R.id.refresh_button)
    Button refreshButton;
    @Bind(R.id.error_message_ll)
    RelativeLayout errorMessageLl;

    private EquipmentRecyclerAdapter mAdapter;
    private EquipmentInforPage equipmentInforPage;
    private int selectedType;
    private int userId;
    private EquipmentListPresenter equipmentListPresenter;
    private AlertDialog loadingDialog;
    private LinearLayoutManager linearLayoutManager;
    private int pageNumber = 1;
    private int lastVisibleItem;
    private boolean isLoadingMore = false;
    private List<Line> lines = new ArrayList<>();
    private HashMap<String, Integer> lineSnHashMap = new HashMap<>();
    private RadioButton rbName, rbLine;
    private EditText etName;
    private Spinner spLine;

    private boolean scrollFlag = true;

    @Override
    protected Toolbar getToolbar() {
        toolbar.setTitle(R.string.equipment_list);
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_equipmentlist);
        ButterKnife.bind(this);
    }

    private void loadEquipmentList(int pageNumber) {
        this.equipmentListPresenter.initialize(userId, selectedType, pageNumber);
    }

    public void renderEquipmentList(EquipmentInforPage equipmentInforPage) {
        isLoadingMore = false;
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        if (equipmentInforPage != null) {
            if (equipmentInforPage.getList().size() != 0) {
                errorMessageLl.setVisibility(View.GONE);
                this.equipmentInforPage = equipmentInforPage;
                this.mAdapter.setEquipmentCollection(equipmentInforPage.getList());
            } else {
                showError(getResources().getString(R.string.not_data));
            }
        }
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancel();
    }

    @Override
    public void showError(String message) {
        if (message != null) {
            errorMessageLl.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
            errorMessageTv.setText(message);
            if (message.equals("请重新登录")) {
                refreshButton.setText("登录界面");
                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginActivity.goLoginView(EquipmentListViewActivity.this);
                    }
                });
            } else
                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initialize();
                    }
                });
        }
    }

    @Override
    public Context getContext() {
        return EquipmentListViewActivity.this;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void setupUI() {
        loadingDialog = GetLoading.getDialog(EquipmentListViewActivity.this, "加载数据中");
        selectedType = this.getUser().getSelectionType();
        userId = this.getUser().getUserId();
        initPresenter();
        linearLayoutManager = new LinearLayoutManager(this);
        rvRecyclerviewData.setLayoutManager(linearLayoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        equipmentInforPage = new EquipmentInforPage();

        mAdapter = new EquipmentRecyclerAdapter(selectedType, EquipmentListViewActivity.this, equipmentInforPage, getUser().getUserId());
        rvRecyclerviewData.setAdapter(mAdapter);

        /*下拉刷新更多*/
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.getEquipmentInformatics().clear();
                        pageNumber = 1;
                        scrollFlag = true;
                        EquipmentListViewActivity.this.loadEquipmentList(1);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });

        rvRecyclerviewData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() >= equipmentInforPage.getRowCount()) {
                    Log.e("hell", "到达底部");
                    ShowUtile.snackBarShow(getRootView(), "没有更多数据....");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (scrollFlag) {
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    int totalItemCount = mAdapter.getItemCount();
                    if (lastVisibleItem == totalItemCount - 1 && dy > 0 && equipmentInforPage.getRowCount() > totalItemCount) {
                        if (!isLoadingMore) {
                            isLoadingMore = true;
                            pageNumber++;
                            loadEquipmentList(pageNumber);//这里多线程也要手动控制isLoadingMore
                        }
                    }
                }
            }
        });
    }

    private void initPresenter() {
        this.equipmentListPresenter = new EquipmentListPresenter(this);
        this.equipmentListPresenter.setView(this);
    }

    @Override
    public void initialize() {
        this.pageNumber = 1;
        this.equipmentListPresenter.loadLineList(userId, selectedType, pageNumber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        equipmentListPresenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search://搜索界面
                MaterialDialog dialog = new MaterialDialog.Builder(EquipmentListViewActivity.this)
                        .title(R.string.equipment_search)
                        .customView(R.layout.dialog_equipment_search, false)
                        .positiveText(R.string.equipment_search)
                        .negativeText(R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                //触发的函数
                                super.onPositive(dialog);
                                scrollFlag = false;
                                mAdapter.getEquipmentInformatics().clear();
                                if (rbLine.isChecked()) {
                                    int lineSn = lineSnHashMap.get(spLine.getSelectedItem().toString());
                                    EquipmentListViewActivity.this.equipmentListPresenter.searchByLineSn(lineSn);
                                } else if (rbName.isChecked()) {
                                    String searchName = etName.getText().toString();
                                    EquipmentListViewActivity.this.equipmentListPresenter.searchByName(searchName);
                                }
                            }
                        })
                        .build();
                //companyNameList是一个list
                List<String> lineNameList = new ArrayList<>();
                for (Line line : lines) {
                    lineSnHashMap.put(line.getLineName(), line.getLineSn());
                    lineNameList.add(line.getLineName());
                }
                rbName = (RadioButton) dialog.getCustomView().findViewById(R.id.search_by_name);
                rbLine = (RadioButton) dialog.getCustomView().findViewById(R.id.search_by_line);
                etName = (EditText) dialog.getCustomView().findViewById(R.id.et_search_name);
                spLine = (Spinner) dialog.getCustomView().findViewById(R.id.spinner_search_line);
                spLine.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, lineNameList));

                rbName.setOnCheckedChangeListener(onCheckedChangeListener);
                rbLine.setOnCheckedChangeListener(onCheckedChangeListener);

                dialog.show();
                break;
        }
        return true;
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                rbName.setChecked(rbName == buttonView);
                rbLine.setChecked(rbLine == buttonView);

                if (rbName == buttonView) {
                    etName.setVisibility(View.VISIBLE);
                    spLine.setVisibility(View.GONE);
                } else if (rbLine == buttonView) {
                    etName.setVisibility(View.GONE);
                    spLine.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    public void setLines(List<Line> mList) {
        lineSnHashMap.clear();
        this.lines = mList;
    }
}
