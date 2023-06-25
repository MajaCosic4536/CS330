package com.example.cs330_p01.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.InvalidationTracker
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteOpenHelper

class DBCategorySQLLite(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

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

//@Entity(tableName = "categories")
//data class CategoryRoom(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "categoryPrimaryKey")
//    val key: Int = 0,
//
//    @ColumnInfo(name = "categoryName")
//    val name: String
//)
//
//@Dao
//interface CategoryDao {
//    @Insert
//    fun insertCategory(category: CategoryRoom)
//
//    @Query("SELECT * FROM categories")
//    fun getCategories(): List<CategoryRoom>
//
//    @Query("SELECT * FROM categories WHERE categoryName = :name LIMIT 1")
//    fun getCategoryByName(name: String): CategoryRoom
//
//    @Query("DELETE FROM categories WHERE categoryPrimaryKey = :key")
//    fun deleteCategory(key: Int)
//
//    @Update
//    fun updateCategory(category: CategoryRoom)
//}
//
//abstract class DBCategory(context: Context?) : RoomDatabase() {
//    private val db: DBCategoryDatabase
//
//    companion object {
//        private const val DB_NAME = "categoryDB"
//        private var instance: DBCategory? = null
//
//        fun getInstance(context: Context): DBCategory {
//            if (instance == null) {
//                synchronized(DBCategory::class) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        DBCategory::class.java,
//                        DB_NAME
//                    ).build()
//                }
//            }
//            return instance!!
//        }
//    }
//
//    init {
//        db = Room.databaseBuilder(
//            context!!.applicationContext,
//            DBCategoryDatabase::class.java,
//            DB_NAME
//        ).build()
//    }
//
//    fun getCategoryDao(): CategoryDao {
//        return db.categoryDao()
//    }
//
//}
//
//@Database(entities = [CategoryRoom::class], version = 1, exportSchema = false)
//abstract class DBCategoryDatabase : RoomDatabase() {
//    abstract fun categoryDao(): CategoryDao
//}

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

class DBClothes(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "clothesDB"
        private const val CLOTHES_TABLE = "clothes"
        private const val CODE = "code"
        private const val NAME_KEY = "name"
        private const val ITEMTYPE_KEY = "itemtype"
        private const val CATEGORY_KEY = "category"
        private const val ONEORMORE_KEY = "oneOrMore"
        private const val COLOR_KEY = "color"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + CLOTHES_TABLE + "("
                + CODE + " TEXT PRIMARY KEY,"
                + NAME_KEY + " TEXT,"
                + ITEMTYPE_KEY + " TEXT,"
                + CATEGORY_KEY + " TEXT,"
                + ONEORMORE_KEY + " TEXT,"
                + COLOR_KEY + " TEXT )")
        p0!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + CLOTHES_TABLE)
        onCreate(p0)
    }

    fun insertClothingItem(clothingItem: ClothingItem) {
        val db = this.writableDatabase

        val cValues = ContentValues()
        cValues.put(CODE, clothingItem.code)
        cValues.put(NAME_KEY, clothingItem.name)
        cValues.put(ITEMTYPE_KEY, clothingItem.itemType)
        cValues.put(CATEGORY_KEY, clothingItem.category)
        cValues.put(ONEORMORE_KEY, clothingItem.oneOrMoreColors)
        cValues.put(COLOR_KEY, clothingItem.color)

        val newRowId = db.insert(CLOTHES_TABLE, null, cValues)
        db.close()
    }

    fun getClothingItems(): ArrayList<ClothingItem> {
        val db = this.writableDatabase

        val ciList = ArrayList<ClothingItem>()

        val query = "SELECT code, name, itemtype, category, oneOrMore, color FROM " + CLOTHES_TABLE
        val cursor = db.rawQuery(query, null)


        while (cursor.moveToNext()) {
            val code = cursor.getColumnIndex(CODE)
            val name = cursor.getColumnIndex(NAME_KEY)
            val itemtype = cursor.getColumnIndex(ITEMTYPE_KEY)
            val category = cursor.getColumnIndex(CATEGORY_KEY)
            val oneormore = cursor.getColumnIndex(ONEORMORE_KEY)
            val color = cursor.getColumnIndex(COLOR_KEY)

            ciList.add(
                ClothingItem(
                    cursor.getString(code),
                    cursor.getString(name),
                    cursor.getString(itemtype),
                    cursor.getString(category),
                    cursor.getString(oneormore).toBoolean(),
                    cursor.getString(color)
                )
            )
        }
        return ciList
    }

    fun getClothingItem(code: String): ClothingItem {
        val db = this.writableDatabase
        var ci = ClothingItem("", "", "", "", false, "")
        val query =
            "SELECT code, name, itemtype, category, oneOrMore, color FROM " + CLOTHES_TABLE
        val cursor = db.query(
            CLOTHES_TABLE,
            arrayOf(
                NAME_KEY, ITEMTYPE_KEY,
                CATEGORY_KEY,
                ONEORMORE_KEY,
                COLOR_KEY
            ),
            CODE + "=?",
            arrayOf(code),
            null,
            null,
            null,
            null
        )

        if (cursor.moveToNext()) {
            val code = cursor.getColumnIndex(CODE)
            val name = cursor.getColumnIndex(NAME_KEY)
            val itemtype = cursor.getColumnIndex(ITEMTYPE_KEY)
            val category = cursor.getColumnIndex(CATEGORY_KEY)
            val oneormore = cursor.getColumnIndex(ONEORMORE_KEY)
            val color = cursor.getColumnIndex(COLOR_KEY)

            ci = ClothingItem(
                cursor.getString(code),
                cursor.getString(name),
                cursor.getString(itemtype),
                cursor.getString(category),
                cursor.getString(oneormore).toBoolean(),
                cursor.getString(color)
            )
        }
        return ci
    }

    fun deleteClothingItem(key: String?) {
        val db = this.writableDatabase
        db.delete(CLOTHES_TABLE, CODE + " = ?", arrayOf(key))
        db.close()
    }

    fun updateClothingItem(clothingItem: ClothingItem): Int {
        val db = this.writableDatabase
        val cVals = ContentValues()
        cVals.put(NAME_KEY, clothingItem.name)
        cVals.put(ITEMTYPE_KEY, clothingItem.itemType)
        cVals.put(CATEGORY_KEY, clothingItem.category)
        cVals.put(ONEORMORE_KEY, clothingItem.oneOrMoreColors)
        cVals.put(COLOR_KEY, clothingItem.color)
        return db.update(
            CLOTHES_TABLE,
            cVals,
            "${CODE} = ?",
            arrayOf(clothingItem.code)
        )
    }
}