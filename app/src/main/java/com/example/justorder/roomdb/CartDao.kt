package com.example.justorder.roomdb

import androidx.room.*

@Dao
interface CartDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cartItem: CartItem)

    @Query("SELECT * FROM CartItem WHERE id=:itemId")
    fun getItem(itemId: Long): CartItem

    @Query("UPDATE CartItem SET orderquantity=:newQuantity WHERE id=:itemId")
    fun update(itemId: Long, newQuantity : Int)

    @Query("DELETE from CartItem WHERE id=:itemId")
    fun delete(itemId: Long)

    @Query("SELECT * FROM CartItem")
    fun getallItem(): List<CartItem>

//    @Query("SELECT * FROM cartitem WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<CartItem>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): CartItem


//
//    @Insert
//    fun insertAll(vararg cartItems: CartItem)
//

//    @Delete
//    fun delete(cartItem: CartItem)
}