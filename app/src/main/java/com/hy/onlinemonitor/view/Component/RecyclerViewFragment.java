package com.hy.onlinemonitor.view.Component;

import android.app.Activity;
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

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.presenter.AlarmPresenter;
import com.hy.onlinemonitor.view.Adapter.AlarmRecyclerAdapter;
import com.hy.onlinemonitor.view.AlarmListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wsw on 2015/7/9.
 */
public class RecyclerViewFragment extends Fragment implements AlarmListView {

    public interface AlarmListListener {
        void onAlarmClicked(final AlarmInformation alarmInformation);
    }

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private boolean isNoInit = true;
    private AlarmRecyclerAdapter mAdapter;
    private RecyclerView.Adapter RcAdapter;
    private static List<String> alarmTitles;
    private static String userName;
    private int showType = 1;
    private Context mContext;
    private AlarmPresenter alarmPresenter;
    private AlarmListListener alarmListListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AlarmListListener) {
            this.alarmListListener = (AlarmListListener) activity;
        }
    }

    public static RecyclerViewFragment newInstance(List<String> alarmTitle, int postion, String name) {
        Log.e("newInstance","newInstance");
        alarmTitles = alarmTitle;
        userName = name;
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
        mAdapter = new AlarmRecyclerAdapter(new ArrayList<AlarmInformation>(), mContext, showType);
        this.mAdapter.setOnItemClickListener(onItemClickListener);
        RcAdapter = new RecyclerViewMaterialAdapter(mAdapter);
        recyclerView.setAdapter(RcAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
    }

    private void initialize() {
        alarmPresenter = new AlarmPresenter(RecyclerViewFragment.this.getContext());
        this.alarmPresenter.setView(this);
    }

    private void loadAlarmList(String title, String userName) {
        this.alarmPresenter.initialize(title, userName);
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
    public void renderAlarmList(Collection<AlarmInformation> alarmInformationCollection) {
        Log.e("renderAlarmList",alarmInformationCollection.toString());
        this.mAdapter.setAlarmCollection(alarmInformationCollection);
        RcAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewAlarm(AlarmInformation alarmInformation) {
        if (this.alarmListListener != null) {
            this.alarmListListener.onAlarmClicked(alarmInformation);
        }
    }

    private AlarmRecyclerAdapter.OnItemClickListener onItemClickListener =
            new AlarmRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onAlarmItemClicked(AlarmInformation alarmInformation) {
                    if (RecyclerViewFragment.this.alarmPresenter != null && alarmInformation != null) {
                        RecyclerViewFragment.this.alarmPresenter.onAlarmClicked(alarmInformation);
                    }
                }
            };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("setUserVisibleHint","setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isNoInit) {
            Bundle bundle = getArguments();
            int postion = bundle.getInt("postion");
            Log.e("showData", "SHowData -- here");
            isNoInit = false;
            Log.e("a", "newInstance->" + alarmTitles.get(postion) + "---" + userName);
            this.initialize();
            this.loadAlarmList(alarmTitles.get(postion), userName);
        }
    }
}
