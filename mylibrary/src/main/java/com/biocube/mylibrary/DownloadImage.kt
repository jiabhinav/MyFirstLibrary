package com.biocube.mylibrary

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class DownloadImage(val context: Context,val filename:String) {

    fun storeDownload(image: ImageView) {
        try {
            val u = URL("https://cdn.searchenginejournal.com/wp-content/uploads/2019/08/c573bf41-6a7c-4927-845c-4ca0260aad6b-1520x800.jpeg")
            val out = u.openStream()
            val dis = DataInputStream(out)
            val buffer = ByteArray(1024)
            var length: Int
            val fos: FileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
            while (dis.read(buffer).also { length = it } > 0) {
                fos.write(buffer, 0, length)
            }

            val bitmap = BitmapFactory.decodeFile(getFilePath())
            image.setImageBitmap(bitmap)
            Log.d("TAG", "storeDownload: "+getFilePath())

        } catch (mue: MalformedURLException) {
            Log.e("SYNC getUpdate", "malformed url error", mue)
        } catch (ioe: IOException) {
            Log.e("SYNC getUpdate", "io error", ioe)
        } catch (se: SecurityException) {
            Log.e("SYNC getUpdate", "security error", se)
        }
    }

    private fun getFilePath(): String {
        val mFileName = filename
        val inputStream  =context.getFileStreamPath(mFileName)
        val filePath: String = inputStream.getAbsolutePath()
        return filePath
    }
}