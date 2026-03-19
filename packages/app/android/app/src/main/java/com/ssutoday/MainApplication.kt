package com.ssutoday

import android.app.Application
import android.content.Context
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeApplicationEntryPoint.loadReactNative
import com.facebook.react.defaults.DefaultReactHost
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.soloader.SoLoader
import java.lang.reflect.InvocationTargetException

class MainApplication : Application(), ReactApplication {

    override val reactHost: ReactHost by lazy {
        DefaultReactHost.getDefaultReactHost(context = applicationContext,
            packageList = PackageList(this).packages)
    }

    override fun onCreate() {
        super.onCreate()
        loadReactNative(this)
        SoLoader.init(this,  /* native exopackage */false)
        initializeFlipper(this, reactNativeHost.getReactInstanceManager())
    }

    companion object {
        /**
         * Loads Flipper in React Native templates. Call this in the onCreate method with something like
         * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
         * 
         * @param context
         * @param reactInstanceManager
         */
        private fun initializeFlipper(
            context: Context?, reactInstanceManager: ReactInstanceManager?
        ) {
            if (BuildConfig.DEBUG) {
                try {
                    val aClass = Class.forName("com.ssutoday.ReactNativeFlipper")
                    aClass
                        .getMethod(
                            "initializeFlipper",
                            Context::class.java,
                            ReactInstanceManager::class.java
                        )
                        .invoke(null, context, reactInstanceManager)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
