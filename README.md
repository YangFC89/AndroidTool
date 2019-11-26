# AndroidTool       
# 引入
project's build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```
dependencies {
	        implementation 'com.github.q7938182:AndroidTool:v1.4'
	}
```
[自定义控件]: /widgets.md
# [自定义控件]
第三方UI推荐库 https://github.com/lavor-zl/UILibs

[网络请求]: /Http.md
# [网络请求]
Android okHttp网络请求之Retrofit+Okhttp+RxJava组合  https://www.cnblogs.com/whoislcj/p/5539239.html

使用了Lambda表达式 所以项目中需要 https://www.cnblogs.com/rainboy2010/p/6476076.html

# StringHelper类
处理基本字符串

# DensityHelper类
单位转换的辅助类

# EditHelper类
输入帮助类

# SharePreferencesHelper类
SharedPreferences 帮助类

# 四级联动地址选择
使用：
```
 x.view().inject(MainActivity.this);
 SelectAddressPop pop = new SelectAddressPop();
 pop.setAddressName(省, 市, 区, 街);
 pop.show(getFragmentManager(), "address");
 pop.setOnSelectAddress(new SelectAddressPop.SelectAddressFinish() {
   @Override
   public void finish(String provinceCode, String cityCode, String areaCode, String townCode) {
      String[] strings = AddressManager.newInstance(MainActivity.this).getAddressName(provinceCode, cityCode, areaCode, townCode);
                        省 = strings[0];
                        市 = strings[1];
                        区 = strings[2];
                        街 = strings[3];
                    }
                });
```
# 卡片布局框架
https://blog.csdn.net/javacainiao931121/article/details/51720807 使用方法 （已集成）
# 64K问题解决
https://www.jianshu.com/p/dd90d7e7c691 使用方法（已集成）
# 屏幕适配方案
https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md 使用方法（已集成）

请在 AndroidManifest 中填写全局设计图尺寸 (单位 dp)，如果使用副单位，则可以直接填写像素尺寸，不需要再将像素转化为 dp，详情请查看 demo-subunits
```
<manifest>
    <application>            
        <meta-data
            android:name="design_width_in_dp"
            android:value="360"/>
        <meta-data
            android:name="design_height_in_dp"
            android:value="640"/>           
     </application>           
</manifest>
```
# RecyclerView 代替listView等控件的适配性 （已集成）
控件简介 https://www.jianshu.com/p/4f9591291365

简单使用 https://blog.csdn.net/qq_34801506/article/details/80538944

# SmartRefreshLayout下拉刷新、上拉加载 （未集成）
控件简介使用 https://github.com/scwang90/SmartRefreshLayout

# 插件ButterKnife （未集成）
https://github.com/JakeWharton/butterknife

暂时使用下面版本的 最新版本有冲突未解决
```
    implementation 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
```   
# 图片处理 （未集成）
https://github.com/jeasonlzy/ImagePicker
# 侵入式布局 （未集成）
https://github.com/gyf-dev/ImmersionBar

