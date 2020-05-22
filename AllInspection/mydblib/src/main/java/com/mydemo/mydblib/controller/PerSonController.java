package com.mydemo.mydblib.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.mydemo.mydblib.entity.DaoMaster;
import com.mydemo.mydblib.entity.DaoSession;
import com.mydemo.mydblib.entity.PersonInfor;
import com.mydemo.mydblib.entity.PersonInforDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ly on 2020/5/21 14:41
 */
public class PerSonController {

    /**
     * 数据库名字
     */
    private String DB_NAME = "person.db";  //数据库名字

    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private PersonInforDao personInforDao;

    private static PerSonController mDbController;

    public PerSonController(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        personInforDao = mDaoSession.getPersonInforDao();
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    /**
     * 获取单例（context 最好用application的context  防止内存泄漏）
     */
    public static PerSonController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (PerSonController.class) {
                if (mDbController == null) {
                    mDbController = new PerSonController(context);
                }
            }
        }
        return mDbController;
    }

    /**
     * 会自动判定是插入还是替换
     *
     * @param personInfor
     */
    public void insertOrReplace(PersonInfor personInfor) {
        personInforDao.insertOrReplace(personInfor);
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param personInfor
     */
    public long insert(PersonInfor personInfor) {
        return personInforDao.insert(personInfor);
    }

    /**
     * 更新数据
     *
     * @param personInfor
     */
    public void update(PersonInfor personInfor) {
        PersonInfor mOldPersonInfor = personInforDao.queryBuilder().where(PersonInforDao.Properties.PerNo.eq(personInfor.getPerNo())).build().unique();//拿到之前的记录
        if (mOldPersonInfor != null) {
            mOldPersonInfor.setPerNo(personInfor.getPerNo());
            mOldPersonInfor.setName(personInfor.getName());
            mOldPersonInfor.setSex(personInfor.getSex());
            personInforDao.update(mOldPersonInfor);
        }
    }

    /**
     * 按条件查询数据
     */
    public List<PersonInfor> searchByWhere(String wherecluse) {
        List<PersonInfor> personInfors = (List<PersonInfor>) personInforDao.queryBuilder().where(PersonInforDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfors;
    }

    /**
     * 查询所有数据
     */
    public List<PersonInfor> searchAll() {
        List<PersonInfor> personInfors = personInforDao.queryBuilder().list();
        return personInfors;
    }

    /**
     * 查询条件数据
     */
    public List<PersonInfor> query(String wherecluse) {
        List<PersonInfor> personInfors = (List<PersonInfor>) personInforDao.queryBuilder().where(PersonInforDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfors;
    }


    /**
     * 查询条件数据
     */
    public List<PersonInfor> query(PersonInfor personinfor) {
        QueryBuilder<PersonInfor> builder = personInforDao.queryBuilder();

        //可以利用反射 获取成员变量的值 然后遍历加进去
        if (!TextUtils.isEmpty(personinfor.getPerNo()) && personinfor.getPerNo() != null && !"/".equals(personinfor.getPerNo())) {  //编号
            builder.where(PersonInforDao.Properties.PerNo.eq(personinfor.getPerNo()));
        }

        if (!TextUtils.isEmpty(personinfor.getName()) && personinfor.getName() != null && !"/".equals(personinfor.getName())) {  //名字
            builder.where(PersonInforDao.Properties.Name.eq(personinfor.getName()));
        }

        if (!TextUtils.isEmpty(personinfor.getSex()) && personinfor.getSex() != null && !"/".equals(personinfor.getSex())) {  //性别
            builder.where(PersonInforDao.Properties.Sex.eq(personinfor.getSex()));
        }

        return builder.list();
    }

    /**
     * 删除数据(尝试删除对象)
     */
    public void delete(PersonInfor personinfor) {

        QueryBuilder<PersonInfor> builder = personInforDao.queryBuilder();

        //可以利用反射 获取成员变量的值 然后遍历加进去
        if (!TextUtils.isEmpty(personinfor.getPerNo()) && personinfor.getPerNo() != null && !"/".equals(personinfor.getPerNo())) {  //编号
            builder.where(PersonInforDao.Properties.PerNo.eq(personinfor.getPerNo()));
        }

        if (!TextUtils.isEmpty(personinfor.getName()) && personinfor.getName() != null && !"/".equals(personinfor.getName())) {  //名字
            builder.where(PersonInforDao.Properties.Name.eq(personinfor.getName()));
        }

        if (!TextUtils.isEmpty(personinfor.getSex()) && personinfor.getSex() != null && !"/".equals(personinfor.getSex())) {  //性别
            builder.where(PersonInforDao.Properties.Sex.eq(personinfor.getSex()));
        }

        builder.buildDelete().executeDeleteWithoutDetachingEntities();

    }

    /**
     * delete特定项
     * @param wherecluse
     */
    public void delete(String wherecluse) {
        personInforDao.queryBuilder().where(PersonInforDao.Properties.PerNo.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除所有数据
     */
    public void deleteAll(){
        personInforDao.deleteAll();
    }
}
