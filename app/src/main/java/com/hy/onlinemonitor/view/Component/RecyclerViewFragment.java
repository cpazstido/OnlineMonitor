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
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.presenter.AlarmPresenter;
import com.hy.onlinemonitor.view.Adapter.AlarmRecyclerAdapter;
import com.hy.onlinemonitor.view.AlarmListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewFragment extends Fragment implements AlarmListView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.show_no_data)
    TextView showNoData;
    private boolean isNoInit = true;
    private AlarmRecyclerAdapter mAdapter;
    private RecyclerView.Adapter RcAdapter;
    private static List<String> alarmTitles;
    private static int userId;
    private int showType = 1;
    private Context mContext;
    private AlarmPage alarmPage;
    private AlarmPresenter alarmPresenter;
    private int status = -1;
    private String queryAlarmType;

    public static RecyclerViewFragment newInstance(List<String> alarmTitle, int postion, int user) {
        Log.e("newInstance", "newInstance");
        alarmTitles = alarmTitle;
        userId = user;
        Bundle bundle = new Bundle();
        bundle.putInt("postion", postion);
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("RecyclerViewFragment", "onCreateView");
        mContext = container != null ? container.getContext() : null;
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        alarmPage = new AlarmPage();
        mAdapter = new AlarmRecyclerAdapter(alarmPage, mContext, showType, queryAlarmType, status);
        RcAdapter = new RecyclerViewMaterialAdapter(mAdapter);
        recyclerView.setAdapter(RcAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
    }

    private void initialize() {
        alarmPresenter = new AlarmPresenter(RecyclerViewFragment.this.getContext());
        this.alarmPresenter.setView(this);
    }

    private void loadAlarmList(int userId, String queryAlarmType, int status, int pageNumber) {
        this.alarmPresenter.initialize(userId, queryAlarmType, status, pageNumber);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return getActivity().getBaseContext();
    }

    @Override
    public void renderAlarmList(AlarmPage alarmPage) {
        Log.e("alarmPage",alarmPage.toString());
        if (alarmPage.getRowCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            showNoData.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            showNoData.setVisibility(View.GONE);
            this.mAdapter.setAlarmCollection(alarmPage.getList());
            RcAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isNoInit) {
            Bundle bundle = getArguments();
            int postion = bundle.getInt("postion");
            isNoInit = false;
            this.initialize();

            switch (alarmTitles.get(postion)) {
                case "山火新报警":
                    queryAlarmType = "fire";
                    status = 0;
                    break;
                case "山火历史报警":
                    queryAlarmType = "fire";
                    status = 1;
                    break;
                case "传感器新报警":
                    queryAlarmType = "sensor";
                    status = 0;
                    break;
                case "传感器历史报警":
                    queryAlarmType = "sensor";
                    status = 1;
                    break;
                case "外破新报警":
                    queryAlarmType = "break";
                    status = 0;
                    break;
                case "外破历史报警":
                    queryAlarmType = "break";
                    status = 1;
                    break;
            }

            this.loadAlarmList(userId, queryAlarmType, status, 1);
        }
    }
}
