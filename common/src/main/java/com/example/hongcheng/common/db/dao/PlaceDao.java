package com.example.hongcheng.common.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.hongcheng.common.model.Place;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * DAO for table "PLACE".
*/
public class PlaceDao extends AbstractDao<Place, Long> {

    public static final String TABLENAME = "PLACE";

    /**
     * Properties of entity Place.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Label = new Property(1, String.class, "label", false, "LABEL");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Pinyin = new Property(3, String.class, "pinyin", false, "PINYIN");
        public final static Property Province = new Property(4, String.class, "province", false, "PROVINCE");
    };


    public PlaceDao(DaoConfig config) {
        super(config);
    }
    
    public PlaceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLACE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LABEL\" TEXT," + // 1: label
                "\"NAME\" TEXT," + // 2: name
                "\"PINYIN\" TEXT," + // 3: pinyin
                "\"PROVINCE\" TEXT);"); // 4: province
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLACE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Place entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(2, label);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String pinyin = entity.getPinyin();
        if (pinyin != null) {
            stmt.bindString(4, pinyin);
        }
 
        String province = entity.getProvince();
        if (province != null) {
            stmt.bindString(5, province);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Place readEntity(Cursor cursor, int offset) {
        Place entity = new Place( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // label
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // pinyin
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // province
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Place entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLabel(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPinyin(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProvince(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Place entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Place entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
