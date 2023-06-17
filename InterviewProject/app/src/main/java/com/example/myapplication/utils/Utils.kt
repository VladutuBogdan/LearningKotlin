package com.example.myapplication.utils

class Utils {
    companion object {
        val LOG_D = "Interview Application"
        val BASE_URL = "https://pastebin.com/raw/"
    }

    enum class AppRoutes(val route: String) {
        HOME("home"),
        BOOK("book")
    }

    enum class AppStrings(val string: String) {
        DELETE("Delete book"),
        GO_BACK("Go back")
    }
}