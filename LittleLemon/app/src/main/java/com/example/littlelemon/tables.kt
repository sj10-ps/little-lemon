package com.example.littlelemon


import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.serialization.Serializable
import kotlinx.coroutines.flow.Flow


@Serializable
data class MenuRoom(
    val menu:List<MenuItemsRoom>
    )

@Serializable
@Entity(tableName = "menuitems")
data class MenuItemsRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)



@Dao
interface MenuItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(item: List<MenuItemsRoom>)

    @Query("SELECT * FROM menuitems")
    fun getAllMenuItems(): Flow<List<MenuItemsRoom>> // ✅ Correct Flow
}