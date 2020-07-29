package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.findview.FindViewInit;

import java.util.List;

public class FunListAdapter extends BaseAdapter {
    Context context;
    List<CityItem> list;
    LayoutInflater layoutInflater;
    public FunListAdapter(Context _context, List<CityItem> _list,LayoutInflater layoutInflater) {
        this.list = _list;
        this.context = _context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_find_fun_list_item, null);
        System.out.println(convertView);
        TextView tvCity = (TextView) convertView.findViewById(R.id.tv_city);
        CityItem city = list.get(position);

        tvCity.setText(city.getCityName());
        return convertView;
    }
}
