package com.example.dell.account.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dell.account.other.IncomeBean;
import com.example.dell.account.db.MyDBHelper;
import com.example.dell.account.R;
import com.example.dell.account.adapter.IncomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class InComeDetailActivity extends AppCompatActivity {
    //定义对象
    RecyclerView recy_view;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    List<IncomeBean> arr1=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_come_detail);
        //绑定控件
        initView();
        initData();
        //定义适配器： 数据和子布局关联起来（桥梁的作用）
        //在IncomeAdapter.java中实现
        IncomeAdapter adapter=new IncomeAdapter(InComeDetailActivity.this,arr1);

        //将适配器和布局管理器加载到控件当中（这里用网格布局）
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1);
        recy_view.setLayoutManager(st);
        recy_view.setAdapter(adapter);
    }
    //绑定控件
    private void initView() {
        recy_view=findViewById(R.id.recy_view_indetail);
        mhelper=new MyDBHelper(InComeDetailActivity.this);
        db=mhelper.getWritableDatabase();
    }

    private void initData() {
        //从数据库查询所有的新增收入信息,取出数据
        Cursor cursor=db.rawQuery("select * from in_come",null);
        while(cursor.moveToNext()){
            int myid=cursor.getInt(cursor.getColumnIndex("id"));
            double mymoney=cursor.getDouble(cursor.getColumnIndex("inmoney"));
            String mytime=cursor.getString(cursor.getColumnIndex("intime"));
            String mytype=cursor.getString(cursor.getColumnIndex("intype"));
            String mypayer=cursor.getString(cursor.getColumnIndex("inpayer"));
            String myremark=cursor.getString(cursor.getColumnIndex("inremark"));
            IncomeBean incomeBean=new IncomeBean( myid,mymoney,mytime,mytype,mypayer,myremark);
            arr1.add(incomeBean);
        }
    }
}
