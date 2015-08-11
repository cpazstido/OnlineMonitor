package com.hy.onlinemonitor.view.Component;

import android.support.v4.app.Fragment;

/**
 * Created by wsw on 2015/7/9.
 */
public class RecyclerViewFragment extends Fragment {
//
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private int position;
//    private static List<String> alarmTitle;
//
//    public static RecyclerViewFragment newInstance(int a, List<String> b) {
//
//        alarmTitle = b;
//        RecyclerViewFragment fragment = new RecyclerViewFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("postion", a);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    public RecyclerViewFragment() {
//        super();
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.e("fragment","fragment");
//
//        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        /**
//         * 这里取出放入的viewpager的当前选中的页数
//         */
//        Bundle bundle = getArguments();
//        position = bundle.getInt("postion");
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setHasFixedSize(true);
//        List<AlarmInformation> mContentItems = new ArrayList<>();
//
//        /**
//         * 这里获取数据,在没有获取数据前,出现一个等待选框.......
//         */
//        int showType = 1;
//        switch (alarmTitle.get(position)) {
//            case "传感器历史报警":
//                for (int i = 0; i < 2; ++i) {
//                    showType = 0;
//                    mContentItems.add(new AlarmInformation("地球电力", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 0));
//                }
//                break;
//            case "传感器新报警":
//                for (int i = 0; i < 3; ++i) {
//                    showType = 0;
//                    mContentItems.add(new AlarmInformation("火星电力", "http://p2.so.qhimg.com/bdr/_240_/t01cfc4b3d8f4a4f1b7.jpg", "http://p1.so.qhimg.com/bdr/_240_/t01c67d191fbf4589f6.jpg", "是", "紧急的报警", "已处理", 1));
//                }
//                break;
//            case "山火历史报警":
//                for (int i = 0; i < 4; ++i) {
//                    mContentItems.add(new AlarmInformation("水星电力", "http://p2.so.qhimg.com/bdr/_240_/t01cfc4b3d8f4a4f1b7.jpg", "http://p1.so.qhimg.com/bdr/_240_/t01c67d191fbf4589f6.jpg", "是", "紧急的报警", "已处理", 2));
//                }
//                break;
//            case "山火新报警":
//                for (int i = 0; i < 5; ++i) {
//                    mContentItems.add(new AlarmInformation("木星电力", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://p1.so.qhimg.com/bdr/_240_/t01c67d191fbf4589f6.jpg", "是", "紧急的报警", "已处理", 3));
//                }
//                break;
//            case "外破历史报警":
//                for (int i = 0; i < 6; ++i) {
//                    mContentItems.add(new AlarmInformation("太阳电力", "http://p2.so.qhimg.com/bdr/_240_/t01cfc4b3d8f4a4f1b7.jpg", "http://p1.so.qhimg.com/bdr/_240_/t01c67d191fbf4589f6.jpg", "是", "紧急的报警", "已处理", 4));
//                }
//                break;
//            case "外破新报警":
//                for (int i = 0; i < 7; ++i) {
//                    mContentItems.add(new AlarmInformation("海王星电力", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://p1.so.qhimg.com/bdr/_240_/t01c67d191fbf4589f6.jpg", "是", "紧急的报警", "已处理", 5));
//                }
//                break;
//        }
//
//        mAdapter = new RecyclerViewMaterialAdapter(new AlarmRecyclerAdapter(mContentItems, view.getContext(), showType));
//        mRecyclerView.setAdapter(mAdapter);
//
//        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
//    }
}
