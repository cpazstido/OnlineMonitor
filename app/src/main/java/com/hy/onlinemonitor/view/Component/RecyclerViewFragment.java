package com.hy.onlinemonitor.view.Component;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.presenter.AlarmPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.Function.AlarmInformationActivity;
import com.hy.onlinemonitor.view.Adapter.AlarmRecyclerAdapter;
import com.hy.onlinemonitor.view.AlarmListView;
import com.rey.material.widget.Button;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewFragment extends Fragment implements AlarmListView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.error_message_tv)
    TextView errorMessageTv;
    @Bind(R.id.refresh_button)
    Button refreshButton;
    @Bind(R.id.error_message_ll)
    RelativeLayout errorMessageLl;

    private static String curProject;
    private AlarmRecyclerAdapter mAdapter;
    private RecyclerView.Adapter RcAdapter;
    private static List<String> alarmTitles;
    private static int userId;
    private int showType = 1;
    private int dvrTypes;
    private AlarmPage alarmPage;
    private AlarmPresenter alarmPresenter;
    private int status = -1;
    private String queryAlarmType;
    private static Context mContext;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    private boolean isLoadingMore = false;
    private int pageNum = 1;
    private boolean isHaveData = false;

    public static RecyclerViewFragment newInstance(Context mcontext, List<String> alarmTitle, int postions, int user, String curProjectStr) {
        alarmTitles = alarmTitle;
        userId = user;
        mContext = mcontext;
        Bundle bundle = new Bundle();
        bundle.putInt("postion", postions);
        curProject = curProjectStr;
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("RecyclerViewFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        int postion = bundle.getInt("postion");
        switch (alarmTitles.get(postion)) {
            case "山火新报警":
                queryAlarmType = "fire";
                dvrTypes = 1;
                status = 0;
                break;
            case "山火历史报警":
                queryAlarmType = "fire";
                dvrTypes = 1;
                status = 1;
                break;
            case "传感器新报警":
                queryAlarmType = "sensor";
                status = 0;
                showType = 0;
                break;
            case "传感器历史报警":
                queryAlarmType = "sensor";
                status = 1;
                showType = 1;
                break;
            case "外破新报警":
                queryAlarmType = "break";
                dvrTypes = 2;
                status = 0;
                break;
            case "外破历史报警":
                queryAlarmType = "break";
                dvrTypes = 2;
                status = 1;
                break;
        }

        this.setupUI();
        this.initialize();
        pageNum = 1;
        this.loadAlarmList(userId, curProject, queryAlarmType, status, pageNum);
    }

    private void setupUI() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        alarmPage = new AlarmPage();
        mAdapter = new AlarmRecyclerAdapter(alarmPage, mContext, showType, queryAlarmType, status);
        RcAdapter = new RecyclerViewMaterialAdapter(mAdapter);
        recyclerView.setAdapter(RcAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (lastVisibleItem == mAdapter.getItemCount() && isHaveData) {
                            if (pageNum < alarmPage.getTotalPage() && !isLoadingMore) {
                                isLoadingMore = true;
                                pageNum++;
                                loadAlarmList(userId, curProject, queryAlarmType, status, pageNum);
                            } else {
                                ShowUtile.toastShow(mContext, "无更多数据...");
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initialize() {
        alarmPresenter = new AlarmPresenter(mContext);
        this.alarmPresenter.setView(this);
    }

    private void loadAlarmList(int userId, String curProject, String queryAlarmType, int status, int pageNumber) {
        this.alarmPresenter.initialize(userId, queryAlarmType, status, pageNumber, curProject);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showLoading() {
        ((AlarmInformationActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((AlarmInformationActivity) getActivity()).hideLoading();
    }

    @Override
    public void showError(String message) {
        errorMessageLl.setVisibility(View.VISIBLE);
        errorMessageTv.setText(message);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                RecyclerViewFragment.this.loadAlarmList(userId, curProject, queryAlarmType, status, pageNum);
            }
        });
    }

    @Override
    public void renderAlarmList(AlarmPage alarmPage, final String queryAlarmType) {
        mAdapter.setQueryAlarmType(queryAlarmType);
        isLoadingMore = false;
        this.alarmPage = alarmPage;
        if (alarmPage.getRowCount() == 0) {
            isHaveData = false;
            errorMessageLl.setVisibility(View.VISIBLE);
            errorMessageTv.setText(R.string.not_data);
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecyclerViewFragment.this.loadAlarmList(userId, curProject, queryAlarmType, status, 1);
                }
            });
        } else {
            isHaveData = true;
            pageNum = alarmPage.getPageNum();
            recyclerView.setVisibility(View.VISIBLE);
            errorMessageLl.setVisibility(View.GONE);
            this.mAdapter.setAlarmCollection(alarmPage.getList());
            RcAdapter.notifyDataSetChanged();
        }
    }

}
