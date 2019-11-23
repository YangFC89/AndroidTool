package com.yfc.androidu.address;


/**
 * Created by zzq on 16/9/26.
 */
public interface AddressCallBack {
    void selectProvince(AddressManager.Province province);
    void selectCity(AddressManager.City city);
    void selectDistrict(AddressManager.District district);
    void selectTown(AddressManager.Town town);
}
