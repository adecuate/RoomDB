package com.example.appforhour;

import android.app.Application;

import com.example.appforhour.db.AppDatabase;

import androidx.room.Room;

public class App extends Application {

    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // инициализ всех глобальных обж
    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room
                .databaseBuilder(this, AppDatabase.class, "app-database")
                .build();
    }
}
