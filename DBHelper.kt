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

class DBColor(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "colorDB"
        private const val COLOR_TABLE = "colors"
        private const val COLOR_PRIMARYKEY = "colorPrimaryKey"
        private const val KEY_COLORNAME = "colorName"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + COLOR_TABLE + "("
                + COLOR_PRIMARYKEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_COLORNAME + " TEXT )")
        p0!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + COLOR_TABLE)
        onCreate(p0)
    }

    fun insertColor(color: String) {
        val db = this.writableDatabase

        val cValues = ContentValues()
        cValues.put(KEY_COLORNAME, color)

        val newRowId = db.insert(COLOR_TABLE, null, cValues)
        db.close()
    }

    fun getColors(): ArrayList<MyColor> {
        val db = this.writableDatabase

        val colorList = ArrayList<MyColor>()

        val query = "SELECT colorPrimaryKey, colorName FROM " + COLOR_TABLE
        val cursor = db.rawQuery(query, null)


        while (cursor.moveToNext()) {
            val key = cursor.getColumnIndex(COLOR_PRIMARYKEY)
            val name = cursor.getColumnIndex(KEY_COLORNAME)

            colorList.add(
                MyColor(
                    cursor.getInt(key),
                    cursor.getString(name)
                )
            )
        }
        return colorList
    }

    fun getCategoryByName(color: String): MyColor {
        val db = this.writableDatabase
        var col = MyColor(0, "")
        val query = "SELECT colorPrimaryKey, colorName FROM " + COLOR_TABLE
        val cursor = db.query(
            COLOR_TABLE,
            arrayOf(KEY_COLORNAME),
            COLOR_PRIMARYKEY + "=?",
            arrayOf(color),
            null,
            null,
            null,
            null
        )

        if (cursor.moveToNext()) {
            val key = cursor.getColumnIndex(COLOR_PRIMARYKEY)
            val name = cursor.getColumnIndex(KEY_COLORNAME)

            col = MyColor(cursor.getInt(key), cursor.getString(name))
        }
        return col
    }

    fun deleteColor(key: Int?) {
        val db = this.writableDatabase
        db.delete(COLOR_TABLE, COLOR_PRIMARYKEY + " = ?", arrayOf(key.toString()))
        db.close()
    }

    fun updateColor(color: MyColor): Int {
        val db = this.writableDatabase
        val cVals = ContentValues()
        cVals.put(KEY_COLORNAME, color.name)
        return db.update(
            COLOR_TABLE,
            cVals,
            "$COLOR_PRIMARYKEY = ?",
            arrayOf(color.key.toString())
        )
    }
}