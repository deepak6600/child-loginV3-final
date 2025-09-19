package com.safe.setting.app.app
import android.app.Application
import androidx.multidex.MultiDex
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.safe.setting.app.di.component.AppComponent
import com.safe.setting.app.di.component.DaggerAppComponent
import com.safe.setting.app.di.module.AppModule
import com.safe.setting.app.di.module.FirebaseModule
import com.safe.setting.app.utils.Consts.SIZE_CACHE_FIREBASE
import com.safe.setting.app.utils.supabase.SupabaseManager


class Hom : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        // ===============================================================
        // बदला हुआ कोड
        // लॉजिक: CloudinaryManager.initialize(this) को हटा दिया गया है
        // और उसकी जगह SupabaseManager.initialize() को कॉल किया गया है।
        // यह सुनिश्चित करता है कि ऐप शुरू होते ही Cloudinary की जगह
        // Supabase से कनेक्ट हो।
        // ===============================================================
        SupabaseManager.initialize()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).firebaseModule(FirebaseModule()).build()
        appComponent.inject(this)


        if (FirebaseApp.getApps(this).isNotEmpty()) {
            val database = FirebaseDatabase.getInstance()
            database.setPersistenceEnabled(true)
            database.setPersistenceCacheSizeBytes(SIZE_CACHE_FIREBASE)
        }

    }

}

