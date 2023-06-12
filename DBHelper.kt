package com.example.cs330_p01.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBCategory(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "categoryDB"
        private const val CATEGORY_TABLE = "categories"
        private const val CATEGORY_PRIMARYKEY = "categoryPrimaryKey"
        private const val KEY_CATEGORYNAME = "categoryName"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + CATEGORY_TABLE + "("
                + CATEGORY_PRIMARYKEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CATEGORYNAME + " TEXT )")
        p0!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE)
        onCreate(p0)
    }

    fun insertCategory(category: String) {
        val db = this.writableDatabase

        val cValues = ContentValues()
        // cValues.put(CATEGORY_PRIMARYKEY, category.key)
        cValues.put(KEY_CATEGORYNAME, category)

        val newRowId = db.insert(CATEGORY_TABLE, null, cValues)
        db.close()
    }

    fun getCategories(): ArrayList<Category> {
        val db = this.writableDatabase

        val categoryList = ArrayList<Category>()

        val query = "SELECT categoryPrimaryKey, categoryName FROM " + CATEGORY_TABLE
        val cursor = db.rawQuery(query, null)


        while (cursor.moveToNext()) {
            val key = cursor.getColumnIndex(CATEGORY_PRIMARYKEY)
            val name = cursor.getColumnIndex(KEY_CATEGORYNAME)

            categoryList.add(
                Category(
                    cursor.getInt(key),
                    cursor.getString(name)
                )
            )
        }
        return categoryList
    }

    fun getCategoryByName(name: String): Category {
        val db = this.writableDatabase
        var cat = Category(0, "")
        val query = "SELECT categoryPrimaryKey, categoryName FROM " + CATEGORY_TABLE
        val cursor = db.query(
            CATEGORY_TABLE,
            arrayOf(KEY_CATEGORYNAME),
            CATEGORY_PRIMARYKEY + "=?",
            arrayOf(name),
            null,
            null,
            null,
            null
        )

        if (cursor.moveToNext()) {
            val key = cursor.getColumnIndex(CATEGORY_PRIMARYKEY)
            val name = cursor.getColumnIndex(KEY_CATEGORYNAME)

            cat = Category(cursor.getInt(key), cursor.getString(name))
        }
        return cat
    }

    fun deleteCategory(key: Int?) {
        val db = this.writableDatabase
        db.delete(CATEGORY_TABLE, CATEGORY_PRIMARYKEY + " = ?", arrayOf(key.toString()))
        db.close()
    }

    fun updateCategory(category: Category): Int {
        val db = this.writableDatabase
        val cVals = ContentValues()
        cVals.put(KEY_CATEGORYNAME, category.name)
        return db.update(
            CATEGORY_TABLE,
            cVals,
            "$CATEGORY_PRIMARYKEY = ?",
            arrayOf(category.key.toString())
        )
    }
}

class DBWeatherCode(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "weatherCodeDB"
        private const val WC_TABLE = "weatherCodes"
        private const val CODE = "code"
        private const val DESCRIPTION = "description"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + WC_TABLE + "("
                + CODE + " INTEGER PRIMARY KEY,"
                + DESCRIPTION + " TEXT )")
        p0!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + WC_TABLE)
        onCreate(p0)
    }

    fun insertWeatherCode(weatherCode: WeatherCode) {
        val db = this.writableDatabase

        val cValues = ContentValues()
         cValues.put(CODE, weatherCode.code)
        cValues.put(DESCRIPTION, weatherCode.descripton)

        val newRowId = db.insert(WC_TABLE, null, cValues)
        db.close()
    }

    fun getWeatherCodes(): ArrayList<WeatherCode> {
        val db = this.writableDatabase

        val wcList = ArrayList<WeatherCode>()

        val query = "SELECT code, description FROM " + WC_TABLE
        val cursor = db.rawQuery(query, null)


        while (cursor.moveToNext()) {
            val code = cursor.getColumnIndex(CODE)
            val desc = cursor.getColumnIndex(DESCRIPTION)

            wcList.add(
                WeatherCode(
                    cursor.getInt(code),
                    cursor.getString(desc)
                )
            )
        }
        return wcList
    }

    fun getWeatherCode(code: Int): WeatherCode {
        val db = this.writableDatabase
        var wc = WeatherCode(0, "")
        val query = "SELECT code, description FROM " + WC_TABLE
        val cursor = db.query(
            WC_TABLE,
            arrayOf(DESCRIPTION),
            CODE + "=?",
            arrayOf(code.toString()),
            null,
            null,
            null,
            null
        )

        if (cursor.moveToNext()) {
            val code = cursor.getColumnIndex(CODE)
            val desc = cursor.getColumnIndex(DESCRIPTION)

            wc = WeatherCode(cursor.getInt(code), cursor.getString(desc))
        }
        return wc
    }

    fun deleteWeatherCode(key: Int?) {
        val db = this.writableDatabase
        db.delete(WC_TABLE, CODE + " = ?", arrayOf(key.toString()))
        db.close()
    }

    fun updateWeatherCode(weatherCode: WeatherCode): Int {
        val db = this.writableDatabase
        val cVals = ContentValues()
        cVals.put(DESCRIPTION, weatherCode.descripton)
        return db.update(
            WC_TABLE,
            cVals,
            "$CODE = ?",
            arrayOf(weatherCode.code.toString())
        )
    }
}