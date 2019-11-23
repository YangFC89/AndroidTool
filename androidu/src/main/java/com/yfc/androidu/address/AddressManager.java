package com.yfc.androidu.address;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 地址管理类，包括更新、删除、添加地址，以及从数据库中读取所有省、市、区信息
 *
 * @author zzq
 */
public class AddressManager {
    public static final int VERSION = 2;

    private static AddressManager ins = null;

    final String DB_NAME = "dbo.db"; //保存的数据库文件名
    final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/";  //在手机里存放数据库的位置
    String dbfile = "";

    DbManager.DaoConfig config = new DbManager.DaoConfig();

    static DbManager manager = null;

    public synchronized static AddressManager newInstance(Context context) {
        if (ins == null) {
            ins = new AddressManager(context);
        }
        return ins;
    }

    protected AddressManager(Context context) {
        initDataFromDB(context);

    }

    /**
     * 从数据库中读取所有的省、市、区
     */
    private void initDataFromDB(Context context) {
        dbfile = DB_PATH + context.getPackageName() + "/" + DB_NAME;
        copyDBFromAssets(dbfile, context);

        config.setDbName(DB_NAME); //db名
        config.setDbDir(new File(DB_PATH + context.getPackageName()));
        config.setDbVersion(VERSION);
        manager = x.getDb(config);
    }

    private List<Province> provinces = new ArrayList<Province>();

    /**
     * 获取数据库中读取的所有省信息
     *
     * @return
     */
    public List<Province> getAllProvinces() {
        if (provinces == null || provinces.size() < 1) {
            try {
                Cursor cursor = manager.execQuery("select * from Whir_Cmn_Area where Pid=0");
                while (cursor.moveToNext()) {
                    String provinceName = cursor.getString(cursor.getColumnIndex("Name"));
                    String provinceCode = cursor.getString(cursor.getColumnIndex("Id"));
                    Log.d("province", provinceCode + " ;" + provinceName);
                    Province province = new Province(provinceName, provinceCode);
                    provinces.add(province);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return provinces;
    }

    public int getProvinceCount() {
        return provinces.size();
    }

    /**
     * 通过省或直辖市的code找到省或直辖市
     *
     * @param code 省或直辖市的code，如：110000表示北京市
     * @return
     */
    public Province findProvinceByCode(String code) {

        getAllProvinces();
        for (Province province : provinces) {
            if (province.getCode().equals(code)) {
                return province;
            }
        }
        return null;
    }

    /**
     * 根据省或直辖市的名称找到省或直辖市的信息
     *
     * @param name 省名称，如：北京市
     * @return
     */
    public Province findProvinceByName(String name) {
        getAllProvinces();
        for (Province province : provinces) {
            if (province.getName().equals(name)) {
                return province;
            }
        }
        return null;
    }

    /**
     * 通过省、市和区三个code获得详细地址信息
     *
     * @param provinceCode                     省或直辖市code，如110000，表示北京
     * @param cityCode                         市code，如110100，表示市辖区
     * @param districtCode，区code，如110101，表示东城区 例如三个code分别是110000, 110100, 110101，则输出为北京市市辖区东城区
     * @return
     */
    public String getAddress(String provinceCode, String cityCode, String districtCode) {
        String addr = "";
        Province province = findProvinceByCode(provinceCode);
        if (province != null) {
            addr += province.getName();
            City city = province.findCityByCode(cityCode);
            if (city != null) {

                addr += city.getName();
                District district = city.findDistrictByCode(districtCode);
                if (district != null) {
                    addr += district.getName();
                }
            }
        }
        return addr;
    }

    public String getAddress(String provinceCode, String cityCode, String districtCode, String townCode) {
        String addr = "";
        Province province = findProvinceByCode(provinceCode);
        if (province != null) {
            addr += province.getName();
            City city = province.findCityByCode(cityCode);
            if (city != null) {

                addr += city.getName();
                District district = city.findDistrictByCode(districtCode);
                if (district != null) {
                    addr += district.getName();
                    Town town = district.findTownByCode(townCode);
                    if (town != null) {
                        addr += town.getName();
                    }
                }
            }
        }
        return addr;
    }

    public String[] getAddressName(String provinceCode, String cityCode, String districtCode, String townCode) {
        String[] strings = new String[4];
        Province province = findProvinceByCode(provinceCode);
        if (province != null) {
            strings[0] = province.getName();
            City city = province.findCityByCode(cityCode);
            if (city != null) {
                strings[1] = city.getName();
                District district = city.findDistrictByCode(districtCode);
                if (district != null) {
                    strings[2] = district.getName();
                    Town town = district.findTownByCode(townCode);
                    if (town != null) {
                        strings[3] = town.getName();
                    }
                }
            }
        }
        return strings;
    }


    private void copyDBFromAssets(String dbfile, Context context) {
        InputStream in = null;
        FileOutputStream out = null;
        File file = new File(dbfile);
        if (!file.exists()) {
            try {
                in = context.getAssets().open(DB_NAME); // 从assets目录下复制
                out = new FileOutputStream(file);
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 从数据库读取某个省或直辖市下辖的市信息
     *
     * @param db
     * @param province
     */
    private void readCitiesOfProvince(DbManager db, Province province) {
        Cursor cursor = null;
        try {
            cursor = db.execQuery("select * from Whir_Cmn_Area where Pid='" + province.code + "'");
            while (cursor.moveToNext()) {
                String cityName = cursor.getString(cursor.getColumnIndex("Name"));
                String cityCode = cursor.getString(cursor.getColumnIndex("Id"));
                Log.d("city", cityCode + " ;" + cityName + " ;" + province.getName());
                City city = new City(cityName, cityCode, province.getCode());
                //readDistrictOfCity(db, city);
                province.addCity(city);
            }
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 从数据库读取某个城市下辖的区信息
     *
     * @param db
     * @param city
     */
    private void readDistrictOfCity(DbManager db, City city) {
        Cursor cursor = null;
        try {
            cursor = db.execQuery("select * from Whir_Cmn_Area where Pid='" + city.code + "'");
            while (cursor.moveToNext()) {
                String districtName = cursor.getString(cursor.getColumnIndex("Name"));
                String districtCode = cursor.getString(cursor.getColumnIndex("Id"));
                District district = new District(districtName, districtCode, city.getCode(), city.getProvinceCode());
                city.addDistrict(district);
            }
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    private List<ReceiveAddress> addresses = new ArrayList<ReceiveAddress>();
    //缓存已选中的地址（本地数据） 做容错处理
    private Map<Integer, ReceiveAddress> selectAddresss = new HashMap<Integer, ReceiveAddress>();

    public List<ReceiveAddress> getAllAddresses() {
        return addresses;
    }

    public void clear() {
//		try {
////			if(manager!=null)
////				manager.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		selectAddresss.clear();
//		clearSelectAddress();
    }

    /**
     * 设置所有地址未选中
     */
    public void clearSelectAddress() {
        for (ReceiveAddress address : addresses) {
            address.isSelected = false;
        }
    }

    /**
     * 是否已选中
     */
    public boolean containsKeySelectAddress(int id) {
        return selectAddresss.containsKey(id);
    }


    /**
     * 设置哪一个地址被选中或取消选中（多选）
     *
     * @param pos
     */
    public void setSelectedAddress(int pos) {
        ReceiveAddress address = addresses.get(pos);
        address.isSelected = !address.isSelected;
        if (address.isSelected) {
            if (!selectAddresss.containsKey(address.id)) {
                selectAddresss.put(address.id, address.clone());
            }
        } else {
            if (selectAddresss.containsKey(address.id)) {
                selectAddresss.remove(address.id);
            }
        }
    }

    /**
     * 设置单个地址选中（单选）
     *
     * @param pos
     */
    public void setSingleSelectedAddress(Context context, int pos) {
        for (int i = 0; i < addresses.size(); ++i) {
            ReceiveAddress address = addresses.get(i);
            address.isSelected = i == pos;
        }
    }

    /**
     * 指定选项是否被选中
     *
     * @param pos
     * @return
     */
    public boolean isSelectAddressPos(int pos) {
        ReceiveAddress address = addresses.get(pos);
        return address.isSelected;
    }

    /**
     * 指定选项被选中
     *
     * @param id
     * @return
     */
    public void selectAddressId(int id) {
        ReceiveAddress address = getAddressById(id);
        address.isSelected = true;
    }

    public List<Integer> getAllSelectedAddressIds() {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < addresses.size(); ++i) {
            ReceiveAddress address = addresses.get(i);
            if (address.isSelected) {
                indexes.add(address.id);
            }
        }
        return indexes;
    }

    public ReceiveAddress getAddressById(int id) {
        for (ReceiveAddress address : addresses) {
            if (address.id == id) {
                return address;
            }
        }
        return null;
    }


    /**
     * 省或直辖市类
     *
     * @author frontier
     */
    public static class Province {
        private String name;
        private String code;

        public Province(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        /**
         * 省或直辖市下辖的市列表
         */
        private List<City> cities = new ArrayList<City>();

        public void addCity(City city) {
            cities.add(city);
        }

        public List<City> getAllCities() {
            if (cities.size() < 1) {
                Cursor cursor = null;
                try {
                    cursor = manager.execQuery("select * from Whir_Cmn_Area where Pid='" + code + "'");
                    while (cursor.moveToNext()) {
                        String cityName = cursor.getString(cursor.getColumnIndex("Name"));
                        String cityCode = cursor.getString(cursor.getColumnIndex("Id"));
                        Log.d("city", cityCode + " ;" + cityName + " ;" + getName());
                        City city = new City(cityName, cityCode, getCode());
                        //readDistrictOfCity(db, city);
                        addCity(city);
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            return cities;
        }

        /**
         * 通过市的code查找市信息
         *
         * @param code
         * @return
         */
        public City findCityByCode(String code) {
            getAllCities();
            for (City city : cities) {
                if (city.getCode().equals(code)) {
                    return city;
                }
            }
            return null;
        }

        /**
         * 通过市的名字查找市的信息
         *
         * @param name
         * @return
         */
        public City findCityByName(String name) {
            for (City city : cities) {
                if (city.getName().equals(name)) {
                    return city;
                }
            }
            return null;
        }

        public int getCityCount() {
            return cities.size();
        }
    }

    /**
     * 城市类
     *
     * @author frontier
     */
    public static class City {
        private String name;
        private String code;
        private String provinceCode;

        public City(String name, String code, String provinceCode) {
            this.name = name;
            this.code = code;
            this.provinceCode = provinceCode;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        /**
         * 城市下辖的区列表
         */
        private List<District> districts = new ArrayList<District>();

        public void addDistrict(District district) {
            districts.add(district);
        }

        public List<District> getAllDistricts() {
            if (districts.size() < 1) {
                Cursor cursor = null;
                try {
                    cursor = manager.execQuery("select * from Whir_Cmn_Area where Pid='" + code + "'");
                    while (cursor.moveToNext()) {
                        String districtName = cursor.getString(cursor.getColumnIndex("Name"));
                        String districtCode = cursor.getString(cursor.getColumnIndex("Id"));
                        District district = new District(districtName, districtCode, getCode(), getProvinceCode());
                        Log.d("district", districtCode + " ;" + districtName + " ;" + getName());
                        addDistrict(district);
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            return districts;
        }

        /**
         * 通过区code获得区信息
         *
         * @param code
         * @return
         */
        public District findDistrictByCode(String code) {
            for (District district : districts) {
                if (district.getCode().equals(code)) {
                    return district;
                }
            }
            return null;
        }

        /**
         * 通过区的名称获得区的信息
         *
         * @param name
         * @return
         */
        public District findDistrictByName(String name) {
            for (District district : districts) {
                if (district.getName().equals(name)) {
                    return district;
                }
            }
            return null;
        }

        public int getDistrictCount() {
            return districts.size();
        }
    }

    /**
     * 区类
     *
     * @author frontier
     */
    public static class District {
        private String name;
        private String code;
        private String cityCode;
        private String provinceCode;

        public District(String name, String code, String cityCode, String provinceCode) {
            this.name = name;
            this.code = code;
            this.cityCode = cityCode;
            this.provinceCode = provinceCode;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public String getCityCode() {
            return cityCode;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        /**
         * 城市下辖的区列表
         */
        private List<Town> towns = new ArrayList<Town>();

        public void addTown(Town town) {
            towns.add(town);
        }

        public List<Town> getAllTowns() {
            if (towns.size() < 1) {
                Cursor cursor = null;
                try {
                    cursor = manager.execQuery("select * from Whir_Cmn_Area where Pid='" + code + "'");
                    while (cursor.moveToNext()) {
                        String townName = cursor.getString(cursor.getColumnIndex("Name"));
                        String townCode = cursor.getString(cursor.getColumnIndex("Id"));
                        Town town = new Town(townName, townCode, getCode(), cityCode, getProvinceCode());
                        addTown(town);
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            return towns;
        }

        /**
         * 通过区code获得区信息
         *
         * @param code
         * @return
         */
        public Town findTownByCode(String code) {
            for (Town town : towns) {
                if (town.getCode().equals(code)) {
                    return town;
                }
            }
            return null;
        }

        public Town findTownByName(String name) {
            for (Town town : towns) {
                if (town.getName().equals(code)) {
                    return town;
                }
            }
            return null;
        }
    }

    /**
     * 区类
     *
     * @author frontier
     */
    public static class Town {
        private String name;
        private String code;
        private String districtCode;
        private String cityCode;
        private String provinceCode;

        public Town(String name, String code, String districtCode, String cityCode, String provinceCode) {
            this.name = name;
            this.code = code;
            this.districtCode = districtCode;
            this.cityCode = cityCode;
            this.provinceCode = provinceCode;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public String getCityCode() {
            return cityCode;
        }

        public String getProvinceCode() {
            return provinceCode;
        }


    }
}
