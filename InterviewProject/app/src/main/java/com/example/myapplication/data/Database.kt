package com.example.myapplication.data

class Database private constructor() {
    val bookDao = BookDao();

    companion object {
        @Volatile private var istance: Database? = null

        fun getIstance() = istance ?: synchronized(this) {
            istance ?: Database().also {
                istance = it
            }
        }
    }
}