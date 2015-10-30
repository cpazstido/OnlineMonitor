package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.SMLinePresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.ViewHolder.LineViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Administrator on 2015/7/24.
 */
public class SMLineRecyclerAdapter extends RecyclerSwipeAdapter<LineViewHolder> {
    private Context mContext;
    private List<Line> lines;
    private SMLinePresenter smLinePresenter;
    private List<Company> companyList;
    public LinkedHashSet<Line> linkedHashSet = new LinkedHashSet<>();

    public SMLineRecyclerAdapter(Context mContext, List<Line> lines) {
        this.mContext = mContext;
        this.lines = lines;
    }

    @Override
    public LineViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) { //创建viewHolder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_line, viewGroup, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LineViewHolder lineViewHolder, final int i) { //给viewholder绑定数据
        lineViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown); //设置swipe的模式
        lineViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) { //添加监听
                //设置动画
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_delete));
            }
        });

        lineViewHolder.lineName.setText(lines.get(i).getLineName());
        lineViewHolder.lineStart.setText(lines.get(i).getLineStart());
        lineViewHolder.lineFinish.setText(lines.get(i).getLineFinish());
        lineViewHolder.lineTrend.setText(lines.get(i).getLineTrend());
        lineViewHolder.voltageLevel.setText(lines.get(i).getVoltageLevel());

        //设置删除的触发函数
        lineViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(30)) {//拥有的权限
                    new MaterialDialog.Builder(mContext)
                            .content(R.string.delete_information)
                            .positiveText(R.string.yes)
                            .negativeText(R.string.no)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    //这里进行网络操作
                                    mItemManger.removeShownLayouts(lineViewHolder.swipeLayout);
                                    smLinePresenter.deleteLine(lines.get(i).getLineSn());
                                    lines.remove(i);
                                    notifyItemRemoved(i);
                                    notifyItemRangeChanged(i, lines.size());
                                    mItemManger.closeAllItems();
                                    super.onPositive(dialog);
                                }
                            })
                            .show();
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });
        //设置按钮设置监听
        lineViewHolder.ActionConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(31)) {//拥有的权限
                    MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                            .title(R.string.line_change)
                            .customView(R.layout.dialog_line_change, true)
                            .positiveText(R.string.submit)
                            .negativeText(R.string.cancel)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    String newName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_name)).getText().toString();
                                    String newTrend = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_trend)).getText().toString();
                                    String newFinish = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_finish)).getText().toString();
                                    String newStart = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_start)).getText().toString();
                                    String newVoltageLevel = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_voltage_level)).getText().toString();
                                    int newCompanySn = companyList.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_line_company)).getSelectedItemPosition()).getSn();
                                    smLinePresenter.changeLine(lines.get(i).getLineSn(), newCompanySn, newName, newStart, newFinish, newTrend, newVoltageLevel);
                                    super.onPositive(dialog);
                                }

                                @Override
                                public void onNegative(MaterialDialog dialog) {
                                    super.onNegative(dialog);
                                }
                            })
                            .build();

                    EditText newName = (EditText) dialog.getCustomView().findViewById(R.id.dialog_line_name);
                    EditText newTrend = (EditText) dialog.getCustomView().findViewById(R.id.dialog_line_trend);
                    EditText newFinish = (EditText) dialog.getCustomView().findViewById(R.id.dialog_line_finish);
                    EditText newStart = (EditText) dialog.getCustomView().findViewById(R.id.dialog_line_start);
                    EditText newVoltageLevelr = (EditText) dialog.getCustomView().findViewById(R.id.dialog_line_voltage_level);
                    List<String> companyName = new ArrayList<>();
                    for (Company company : companyList) {
                        companyName.add(company.getCompanyName());
                    }

                    Spinner spinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_line_company);
                    spinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, companyName));

                    newName.setText(lineViewHolder.lineName.getText());
                    newTrend.setText(lineViewHolder.lineTrend.getText());
                    newFinish.setText(lineViewHolder.lineFinish.getText());
                    newStart.setText(lineViewHolder.lineStart.getText());
                    newVoltageLevelr.setText(lineViewHolder.voltageLevel.getText());
                    dialog.show();
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });
        mItemManger.bindView(lineViewHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return (this.lines != null) ? this.lines.size() : 0;
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    public void setLinePage(Collection<Line> roleCollection) { //设置数据
        this.validateLineCollection(roleCollection);
        linkedHashSet.addAll(roleCollection);
        Iterator it = linkedHashSet.iterator();
        List<Line> list1 = new ArrayList<>();
        while (it.hasNext()) {
            Line line = (Line) it.next();
            list1.add(line);
        }
        lines = list1;
        this.notifyDataSetChanged();
    }

    //检查数据是否为空
    private void validateLineCollection(Collection<Line> roleCollection) {
        if (roleCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setPresenter(SMLinePresenter smLinePresenter) {
        this.smLinePresenter = smLinePresenter;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public void cleanList() {
        lines.clear();
    }
}
