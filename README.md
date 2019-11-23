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
	        implementation 'com.github.q7938182:AndroidTool:V1.3'
	}
```
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

# 图片处理
https://github.com/jeasonlzy/ImagePicker
