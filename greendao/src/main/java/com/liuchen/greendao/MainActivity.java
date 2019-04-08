package com.liuchen.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.liuchen.greendao.adapter.MainAdapter;
import com.liuchen.greendao.entity.DaoSession;
import com.liuchen.greendao.entity.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.deleteBtn)
    Button deleteBtn;
    @BindView(R.id.upDataBtn)
    Button upDataBtn;
    @BindView(R.id.selectBtn)
    Button selectBtn;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DaoSession daoSession;
    private MainAdapter mainAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        daoSession = ((MyApplication) getApplication()).getDaoSession();
        studentList = new ArrayList<>();
        mainAdapter = new MainAdapter(R.layout.item_main, studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);
    }


    @OnClick({R.id.addBtn, R.id.deleteBtn, R.id.upDataBtn, R.id.selectBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addBtn:
                insertData();
                break;
            case R.id.deleteBtn:
                deleteAll();
                break;
            case R.id.upDataBtn:

                break;
            case R.id.selectBtn:
                mainAdapter.replaceData(queryAll());
                break;
        }
    }


    /**
     * insert() 插入数据
     */
    public void insertData() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setStudentNo(i);
            student.setAge(i);
            student.setTelPhone("手机号" + i);
            student.setName("姓名" + i);
            student.setSex("男" + i);
            student.setAddress("地址" + i);
            student.setGrade("六年级" + i);
            student.setSchoolName("宝安中学" + i);
            //daoSession.insert(student);//直接插入 studentNo是唯一的 如果插入已存在的studentNo会抛出异常
            daoSession.insertOrReplace(student);//插入或替换
        }
    }

    /**
     * 删除单个数据
     *
     * @param s 要删除的对象
     */
    public void deleteData(Student s) {
        daoSession.delete(s);
    }


    /**
     * 删除所有数据
     */
    public void deleteAll() {
        daoSession.deleteAll(Student.class);
    }

    /**
     * 修改数据
     *
     * @param s 要修改的对象
     */
    public void updataData(Student s) {
        daoSession.update(s);
    }

    /**
     * 查询所有数据
     *
     * @return 查询结果
     */
    public List<Student> queryAll() {
        return daoSession.loadAll(Student.class);
    }


    /**
     * 根据条件查询
     *
     * @param s 查询条件
     * @return 查询结果
     */
    public List<Student> queryData(String s) {
        return daoSession.queryRaw(Student.class, " where id = ?", s);
    }
}
