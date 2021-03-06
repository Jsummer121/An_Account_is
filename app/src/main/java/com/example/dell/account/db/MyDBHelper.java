package com.example.dell.account.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 2020/6/6.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static  final String DBNAME="financial.db";
    private static final int VERSION=1;
    public MyDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        db.execSQL("create table tb_userinfo(id integer primary key autoincrement,name varchar(10),pwd varchar(15),email varchar(50),phone varchar(11))");

        //创建收入表
        db.execSQL("create table in_come(id integer primary key autoincrement,inmoney double,intime varchar(20),intype varchar(30),inpayer varchar(100),inremark varchar(500))");
        //创建支出表
        db.execSQL("create table pay_out(id integer primary key autoincrement,outmoney double,outtime varchar(20),outtype varchar(30),outpayee varchar(100),outremark varchar(500))");

    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
