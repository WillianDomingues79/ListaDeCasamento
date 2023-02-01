package com.example.vemprocasorio.repository

import android.content.ContentValues
import android.content.Context
import com.example.vemprocasorio.constants.DataBaseConstants
import com.example.vemprocasorio.model.GuestModel

class GuestRepository private constructor(context: Context){

    private val guestDataBase = GuestDataBase(context)

    //SINGLETON - Faz o controle do acesso as instancias da classe, para não haver inconsistencia de gravação e acesso
    companion object {
        private lateinit var repository: GuestRepository

        //Função para deixar assincrono e não ser inicializado 2 ou mais vezes
        fun getInstance(context: Context): GuestRepository {
            if(!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }


    fun insert(guest: GuestModel): Boolean{
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception){
            false
        }
    }

    fun update(guest : GuestModel): Boolean{
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)

            true
        }catch (e: Exception){
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            true
        } catch (e: Exception){
            false
        }
    }

    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guest = (GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        }catch (e: Exception){
            return guest
        }

        return guest
    }

    fun getAll(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        }catch (e: Exception){
            return list
        }

        return list
    }

    fun getPresent(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        }catch (e: Exception){
            return list
        }


        return list

    }

    fun getAbsent(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        }catch (e: Exception){
            return list
        }

        return list
    }

}