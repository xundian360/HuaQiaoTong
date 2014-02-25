package com.xundian360.huaqiaotong.common.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作类的父类
 * 
 * 1.建表 2.提供一些简单通用的方法
 * 
 * @author TengTeng
 * 
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String dbName = "huaqiaotong_db";
	public static final int version = 1;

	Context context;

	public MySQLiteOpenHelper(Context context) {
		super(context, dbName, null, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// 创建站点表SQL
		String createBusStationSql = "create table bus_station( "
				+ "_id integer not null primary key, " + // ID
				"busStopId integer not null, " + // 公交站点ID
				"busStopName text not null, " + // 公交名称
				"longitude text not null, " + // 经度
				"latitude text not null, " + // 纬度
				"isSave text not null, " + // 是否收藏（0：收藏， 其他：非收藏）
				"direction text )"; // 方向

		// 创建线路表SQL
		String createBusRouteSql = "create table bus_route( "
				+ "_id integer not null primary key, " + // ID
				"routeId integer not null, " + // 线路ID
				"routeName text not null, " + // 线路名称
				"routeKey text not null, " + // 线路Key（222， 228）
				"isSave text not null, " + // 是否收藏（0：收藏， 其他：非收藏）
				"direction text not null)"; // 方向（0:上行，1:下行）

		// 创建站牌表SQL
		String createBusStopSignSql = "create table bus_stop_sign( "
				+ "_id integer not null primary key, " + // ID
				"routeId integer not null, " + // 线路ID
				"routeStopNo integer not null, " + // 站牌编号
				"busStopId integer not null)"; // 站牌Id

		// 创建收藏表SQL
		String createSavaBusDataSql = "create table sava_bus_data( "
				+ "_id integer not null primary key, " + // ID
				"routeOrStationId integer not null, " + // 线路或站台ID
				"routeOrStationName text not null, " + // 线路或站台名称
				"routeOrStationFlag integer not null)"; // 线路或站台区分（0：站点，1：线路）

		// 创建公交相关表
		db.execSQL(createBusStationSql);
		db.execSQL(createBusRouteSql);
		db.execSQL(createBusStopSignSql);
		// db.execSQL(createSavaBusDataSql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */

	/**
	 * 更新表
	 * 
	 * @param table
	 * @param cv
	 * @return
	 */
	public long insertRecode(String table, ContentValues cv) {
		SQLiteDatabase db = getWritableDatabase();

		return db.insert(table, null, cv);
	}

	/**
	 * 查询数据
	 * 
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public Cursor selectRecode(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursorr = db.query(table, columns, selection, selectionArgs,
				groupBy, having, orderBy);
		return cursorr;
	}

	/**
	 * 删除数据
	 * 
	 * @param table
	 * @param where
	 * @param whereArgs
	 * @return
	 */
	public int deleteRecode(String table, String where, String[] whereArgs) {
		SQLiteDatabase db = getWritableDatabase();
		int result = db.delete(table, where, whereArgs);
		db.close();
		return result;
	}

	/**
	 * 删除所有数据
	 * 
	 * @param table
	 * @return
	 */
	public int deleteAllRecode(String table) {
		SQLiteDatabase db = getWritableDatabase();
		int result = db.delete(table, null, null);
		return result;
	}

	/**
	 * 更新数据
	 * 
	 * @param table
	 * @param cv
	 * @param where
	 * @param whereArgs
	 * @return
	 */
	public int updateRecode(String table, ContentValues cv, String where,
			String[] whereArgs) {
		SQLiteDatabase db = getWritableDatabase();
		int result = db.update(table, cv, where, whereArgs);
		return result;
	}

	/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */

	/**
	 * 执行文件里的SQL
	 * 
	 * @param db
	 * @param FileName
	 */
	public void excuteSqlFromFile(SQLiteDatabase db, String FileName) {

		InputStream shopOf4SIn = null;
		InputStreamReader shopOf4SReaderTemp = null;
		BufferedReader shopOf4SReader = null;

		try {
			shopOf4SIn = context.getAssets().open(FileName);

			shopOf4SReaderTemp = new InputStreamReader(shopOf4SIn);

			shopOf4SReader = new BufferedReader(shopOf4SReaderTemp);

			String lineSql = null;

			while ((lineSql = shopOf4SReader.readLine()) != null) {
				db.execSQL(lineSql);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (shopOf4SReader != null) {
					shopOf4SReader.close();
				}

				if (shopOf4SReaderTemp != null) {
					shopOf4SReaderTemp.close();
				}

				if (shopOf4SIn != null) {
					shopOf4SIn.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
