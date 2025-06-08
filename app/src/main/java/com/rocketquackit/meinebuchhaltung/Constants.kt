package com.rocketquackit.meinebuchhaltung

import android.content.Context
import android.content.pm.PackageManager

object Constants {

    /**
     * Gibt die aktuelle App-Version (VersionName) zurück.
     * @param context Context der aufrufenden Komponente (z.B. Activity, Fragment)
     * @return String mit der App-Version, z.B. "1.0.0"
     */
    fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                context.packageManager.getPackageInfo(context.packageName, 0)
            }
            packageInfo.versionName ?: "Unbekannt"
        } catch (e: PackageManager.NameNotFoundException) {
            "Unbekannt"
        }
    }

}