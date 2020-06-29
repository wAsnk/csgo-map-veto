package com.example.csgomapveto.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MapVeto::class, Team::class], version = 30, exportSchema = false)
abstract class VetoDatabase : RoomDatabase() {

    abstract val vetoDatabaseDao: VetoDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: VetoDatabase? = null
        fun getInstance(context: Context): VetoDatabase {
            Log.i("VetoDatabase", "get instance entry")
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VetoDatabase::class.java,
                        "VetoDB"
                    )
                        .addCallback(seedDatabaseCallback(context))
                        .fallbackToDestructiveMigration()
                        .build()
                    Log.i("VetoDatabase", "build")
                    INSTANCE = instance
                }
                Log.i("VetoDatabase", "return instance")
                return instance
            }
        }

        private fun seedDatabaseCallback(context: Context): RoomDatabase.Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.i("VetoDatabase", "Seed onCreate")
                    Thread(Runnable {
                        var vetoDao= getInstance(context).vetoDatabaseDao

                        var teams = getTeams()
                        for (f in teams){
                            vetoDao.insertTeam(f)
                            Log.i("Team added: ", f.Name)
                        }

                        for (f in getMapVetos(vetoDao.getAllTeamsAsList())){
                            vetoDao.insertMapVeto(f)
                            Log.i("Veto added: ", f.id.toString())
                        }
                    }).start()
                }
            }
        }

    }
}