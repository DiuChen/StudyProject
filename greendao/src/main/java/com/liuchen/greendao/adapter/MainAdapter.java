package com.liuchen.greendao.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuchen.greendao.R;
import com.liuchen.greendao.entity.Student;

import java.util.List;

/**
 * Author: 刘晨
 * Date: 2019/4/8 13:59
 */
public class MainAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
    public MainAdapter(int layoutResId, @Nullable List<Student> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.idTv,String.valueOf(item.getId()))
                .setText(R.id.studentNoTv,String.valueOf(item.getStudentNo()))
                .setText(R.id.ageTv,String.valueOf(item.getAge()))
                .setText(R.id.telPhoneTv,item.getTelPhone())
                .setText(R.id.sexTv,item.getSex())
                .setText(R.id.nameTv,item.getName())
                .setText(R.id.addressTv,item.getAddress())
                .setText(R.id.schoolNameTv,item.getSchoolName())
                .setText(R.id.gradeTv,item.getGrade());
    }
}
