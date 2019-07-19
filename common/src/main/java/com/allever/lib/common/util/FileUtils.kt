package com.allever.lib.common.util

import android.text.TextUtils
import com.allever.lib.common.app.App
import java.io.*

object FileUtils {

    fun readTextFile(path: String): String {
        var result = ""

        if (TextUtils.isEmpty(path)) return result

        try {
            result = readTextFileFromInputStream(FileInputStream(path))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun readAssetsTextFile(path: String): String {
        var result = ""

        if (TextUtils.isEmpty(path)) return result

        try {
            val assetManager = App.context.assets
            result = readTextFileFromInputStream(assetManager.open(path))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun readTextFileFromInputStream(inputStream: InputStream): String {
        var result = ""
        val bufferedInputStream = BufferedInputStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        try {
            var len = -1
            val buffer = ByteArray(1024)
            while (bufferedInputStream.read(buffer).also { len = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, len)
            }
            val dataArray = byteArrayOutputStream.toByteArray()
            result = String(dataArray)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            bufferedInputStream.close()
            byteArrayOutputStream.close()
            inputStream.close()
        }

        return result
    }
}