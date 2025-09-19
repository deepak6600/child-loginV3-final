package com.safe.setting.app.di.component

import com.safe.setting.app.app.Hom
import com.safe.setting.app.data.rxFirebase.InterfaceFirebase
import com.safe.setting.app.di.module.AppModule
import com.safe.setting.app.di.module.FirebaseModule
import com.safe.setting.app.services.accessibilityData.AccessibilityDataService
import com.safe.setting.app.services.notificationService.NotificationService
import com.safe.setting.app.workers.UploadWorker
import dagger.Component
import javax.inject.Singleton
import com.safe.setting.app.services.health.HealthCheckService // <-- यह import जोड़ें


@Singleton
@Component(modules = [AppModule::class, FirebaseModule::class])
interface AppComponent {

    fun inject(app: Hom)
    fun inject(accessibilityDataService: AccessibilityDataService)
    fun inject(notificationService: NotificationService)
    fun getInterfaceFirebase(): InterfaceFirebase

    fun inject(worker: UploadWorker) // <<< --- YEH FUNCTION AB SAHI SE KAAM KAREGA --- >>>

    fun inject(service: HealthCheckService)

}