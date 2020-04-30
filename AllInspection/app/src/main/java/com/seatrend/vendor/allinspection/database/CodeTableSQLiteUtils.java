package com.seatrend.vendor.allinspection.database;


public class CodeTableSQLiteUtils {

//
//    //添加数据
//
//    public static void insert(List<CodeEntity.DataBean> data) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        for (CodeEntity.DataBean dataBean : data) {
//            cv.clear();
//            cv.put("xtlb", StringUtils.isNull(dataBean.getXtlb()));
//            cv.put("dmlb", StringUtils.isNull(dataBean.getDmlb()));
//            cv.put("dmz", StringUtils.isNull(dataBean.getDmz()));
//            cv.put("dmsm1", StringUtils.isNull(dataBean.getDmsm1()));
//            cv.put("mlmc", StringUtils.isNull(dataBean.getXtlb()));
//            cv.put("dmsm2", StringUtils.isNull(dataBean.getDmsm2()));
//            cv.put("dmsm3", StringUtils.isNull(dataBean.getDmsm3()));
//            cv.put("zt", StringUtils.isNull(dataBean.getZt()));
//            cv.put("dmsm4", StringUtils.isNull(dataBean.getDmsm4()));
//            db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME, null, cv);
//        }
//
//        db.close();
//    }
//
//    //查询数据
//    public static List<CodeEntity.DataBean> queryByDMLB(String DMLB) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        List<CodeEntity.DataBean> list = new ArrayList<>();
//
//        //db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME,null,cv);//"dmlb="+DMLB
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.TABLE_NAME, null, "dmlb=?", new String[]{DMLB}, null, null, null, null);
//        while (query.moveToNext()) {
//
//            String dmlb = query.getString(query.getColumnIndex("dmlb"));
//            String dmz = query.getString(query.getColumnIndex("dmz"));
//            String dmsm1 = query.getString(query.getColumnIndex("dmsm1"));
//            CodeEntity.DataBean dataBean = new CodeEntity.DataBean();
//            dataBean.setDmlb(dmlb);
//            dataBean.setDmz(dmz);
//            dataBean.setDmsm1(dmsm1);
//            list.add(dataBean);
//        }
//        db.close();
//        query.close();
//        return list;
//    }
//
//    //删除数据
//    public static void deleteAll(String tableName) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        String sql = String.format("delete from %s", tableName);
//        db.execSQL(sql);
//
//        db.close();
//
//    }
//
//    //更改数据
//    public static void updateByLshAndDmz(String lsh, String zplx, String id) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_ZPDZ(), id);
//        db.update(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, cv, "lsh=? and zpzl=?", new String[]{lsh, zplx});
//        db.close();
//    }
}
