package com.allever.lib.common.util

import android.text.TextUtils
import com.allever.lib.common.app.App
import java.io.*

object FileUtils {

    /**
     * 检查文件是否存在
     *
     * @param path 文件的路径
     * @return 文件是否存在
     */
    fun checkExist(path: String?): Boolean {
        if (TextUtils.isEmpty(path)) {
            return false
        }

        return try {
            val file = File(path)
            file.exists()
        } catch (e: Exception) {
            false
        }
    }

    /***
     * 读取文本文件
     * @param path 路径
     * @return 文本内容
     */
    fun readTextFile(path: String?): String {
        var result = ""

        if (!checkExist(path)) return result

        try {
            result = readTextFileFromInputStream(FileInputStream(path))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    /***
     * 读取assets目录的文本文件
     * @param path 路径
     * @return 文本内容
     */
    fun readAssetsTextFile(path: String?): String {
        var result = ""

        if (checkExist(path)) return result

        try {
            val assetManager = App.context.assets
            //path不可能为null
            result = readTextFileFromInputStream(assetManager.open(path!!))
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