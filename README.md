# AndroidDependencyLib

android依赖库，封装通用模块，包括以下模块：

- common：

  - 基类BaseActivity、BaseFragment、BaseRecyclerAdapter
  - 工具类：Toast、Log、FileUtils、SPUtils、ActivityController等等
  - MVP模板: BaseMVPActivity、BaseMVPFragment、BasePresenter
  - 常用控件：BaseRecyclerAdapter、BaseViewHolder

- permission：
  - 封装了AndPermission、通过PermissionManager和PermissionListener统一管理
  - 封装了原生请求权限PermissionCompat
  
- httpsglide:
  - 封装glide，解决加载https图片失败的问题
  
- imageloader:
  - 封装图片加载，使用ImageLoader加载，通过注入ImageLoaderProxy依赖实现加载逻辑

- 