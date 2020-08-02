# 华为

## 使用刘海区域配置

方案一： 在Application或Activity中配置

```
<meta-data
    android:name="android.notch_support"
    android:value="true" />
```

方案二： 通过代码设置(官方代码)

```
    /**
     * 设置应用窗口在华为刘海屏手机使用刘海区
     *
     * 通过添加窗口FLAG的方式设置页面使用刘海区显示
     *
     * @param window 应用页面window对象
     */
    fun displayInNotch(window: Window?) {
        window ?: return
        val layoutParams = window.attributes
        try {
            val layoutParamsExCls =
                Class.forName("com.huawei.android.view.LayoutParamsEx")
            val constructor =
                layoutParamsExCls.getConstructor(WindowManager.LayoutParams::class.java)
            val layoutParamsExObj = constructor.newInstance(layoutParams)
            val method =
                layoutParamsExCls.getMethod("addHwFlags", Int::class.java)
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT)
        } catch (e: Exception) {
            e.printStackTrace()
            log("hw add notch screen flag api error")
        }
    }
```

# 小米适配

## 使用刘海区配置

方案一：在application中配置

```
<meta-data
    android:name="notch.config"
    android:value="portrait|landscape"/>

"none" 横竖屏都不绘制耳朵区
"portrait" 竖屏绘制到耳朵区
"landscape" 横屏绘制到耳朵区
"portrait|landscape" 横竖屏都绘制到耳朵区
这里的耳朵区指的就是刘海区两侧的状态栏区域
```

方案二： 代码配置

```
    /**
     * 设置应用窗口在刘海屏手机使用刘海区
     *
     * 通过添加窗口FLAG的方式设置页面使用刘海区显示
     *
     * @param window 应用页面window对象
     */
    fun displayInNotch(window: Window?) {
        window ?: return
        // 竖屏绘制到耳朵区
        val flag = FLAG_NOTCH_SUPPORT or FLAG_NOTCH_PORTRAIT
        try {
            val method = Window::class.java.getMethod(
                "addExtraFlags",
                Int::class.java
            )
            method.invoke(window, flag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
```

# Oppo/Vivo...

这类没有指定方式使用刘海区域的
可以使用沉浸式状态栏的方式使用刘海区域，使用全屏模式

# 适配流程

开发中需要我们适配刘海屏的情况并不多，只有两种情况需要我们进行考虑：
1.沉浸式状态栏，窗口布局延伸到了状态栏中，是否会遮挡必要的内容或控件（处在危险区域）。
适配方案就是将窗口布局下移，预留出状态栏的空间。
2.全屏显示模式，不做适配的话状态栏会呈现一条黑边。适配方案是首先判断系统版本，是Android P
及以上就按照官方的API来适配，否则根据手机厂商的适配方案进行适配。鉴于目前市面上Android P还没有普及，
为了带来更好的用户体验，我们还是需要多花一些精力来适配各个手机厂商的刘海屏手机。

