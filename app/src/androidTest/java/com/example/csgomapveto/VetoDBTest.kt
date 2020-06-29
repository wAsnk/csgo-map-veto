package com.example.csgomapveto

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.csgomapveto.data.VetoDatabase
import com.example.csgomapveto.data.VetoDatabaseDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VetoDBTest {

    private lateinit var vetoDao: VetoDatabaseDao
    private lateinit var database: VetoDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(context, VetoDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        vetoDao = database.vetoDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun getAllMapVetos() {
        val allMapVetos = vetoDao.getAllMapVetos()

        Assert.assertNotNull(allMapVetos)
    }

    @Test
    @Throws(Exception::class)
    fun getAllTeams() {
        val allTeams = vetoDao.getAllTeams()

        Assert.assertNotNull(allTeams)
    }

}