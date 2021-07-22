package com.hua.testhook

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.appcompat.app.AppCompatActivity
import com.hua.permissionmonitor.PermissionMonitor


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionMonitor.start(false)
    }

    override fun onResume() {
        super.onResume()
        val host = Host()
        host.fuck()
        host.fuck(621)
        packageManager.getInstalledPackages(0)
        val phoneCursor: Cursor? = contentResolver.query(
            Phone.CONTENT_URI,
            arrayOf(
                Phone.DISPLAY_NAME, Phone.NUMBER, ContactsContract.Contacts.Photo.PHOTO_ID, Phone.CONTACT_ID
            ), null, null, null
        )
        phoneCursor?.moveToNext()
    }


}