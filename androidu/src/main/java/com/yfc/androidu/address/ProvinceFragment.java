package com.yfc.androidu.address;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yfc.androidu.R;

import java.util.List;


/**
 * 省选择界面
 */
public class ProvinceFragment implements AdapterView.OnItemClickListener {
    private AddressCallBack callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;


    public ProvinceFragment(Context context, AddressCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        initView();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ListView getListview() {
        return listview;
    }

    public View initView() {
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.select_address_pop_listview, null);
        adapter = new AddressAdapter(AddressManager.newInstance(context).getAllProvinces());
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        code = AddressManager.newInstance(context).getAllProvinces().get(i).getCode();
        if (callBack != null) {
            callBack.selectProvince(AddressManager.newInstance(context).getAllProvinces().get(i));
        }
        adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private List<AddressManager.Province> list;

        public AddressAdapter(List<AddressManager.Province> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.address_listiew_item_textview, null);
            TextView text = (TextView) view.findViewById(R.id.tvTextName);
            ImageView ivSelect = (ImageView) view.findViewById(R.id.ivSelect);
            text.setText(list.get(i).getName());
            if (list.get(i).getCode().equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.new_redbg));
                ivSelect.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }
}
