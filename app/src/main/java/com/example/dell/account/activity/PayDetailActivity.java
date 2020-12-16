package com.example.dell.account.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dell.account.db.MyDBHelper;
import com.example.dell.account.other.OutpayBean;
import com.example.dell.account.R;
import com.example.dell.account.adapter.OutpayAdapter;

import java.util.ArrayList;
import java.util.List;

public class PayDetailActivity extends AppCompatActivity {
    //定义对象
    RecyclerView recy_view;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    List<OutpayBean> arr1=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detail);
        //绑定控件
        initView();
        initData();
        //创建适配器
        OutpayAdapter adapter=new OutpayAdapter(PayDetailActivity.this,arr1);

        //将适配器和布局管理器加载到控件当中
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1);
        recy_view.setLayoutManager(st);
        recy_view.setAdapter(adapter);
    }
    //绑定控件
    private void initView() {
        recy_view=findViewById(R.id.recy_view_outdetail);
        mhelper=new MyDBHelper(PayDetailActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //准备数据
    private void initData() {
        //从数据库查询所有的新增收入信息,取出数据
        Cursor cursor=db.rawQuery("select * from pay_out",null);
        while(cursor.moveToNext()){
            int myid=cursor.getInt(cursor.getColumnIndex("id"));
            double mymoney=cursor.getDouble(cursor.getColumnIndex("outmoney"));
            String mytime=cursor.getString(cursor.getColumnIndex("outtime"));
            String mytype=cursor.getString(cursor.getColumnIndex("outtype"));
            String mypayer=cursor.getString(cursor.getColumnIndex("outpayee"));
            String myremark=cursor.getString(cursor.getColumnIndex("outremark"));
            OutpayBean outpayBean=new OutpayBean( myid,mymoney,mytime,mytype,mypayer,myremark);
            arr1.add(outpayBean);
        }

    }
}
