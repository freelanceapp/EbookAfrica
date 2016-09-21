package com.apporio.ebookafrica.fragmentyourbooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.ebookafrica.R;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by spinnosolutions on 4/26/16.
 */
public class AdapterQuickReturn extends BaseAdapter implements StickyListHeadersAdapter {

    private LayoutInflater inflater;
//    String [] prices ;
    String [] dates ;

    public AdapterQuickReturn(Context context ,  String [] prices ,  String [] dates) {
        inflater = LayoutInflater.from(context);
        this.dates = dates ;
       // this.prices = prices ;
    }

    @Override
    public int getCount() {
        return dates.length;
    }

    @Override
    public Object getItem(int position) {
        return dates[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.all_books_list_item, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.bokname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

     //   holder.text.setText(""+prices[position]);

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = "" + dates[position];
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        //   Logger.d("character "+dates[position].subSequence(0, 1).charAt(0));
        return dates[position].subSequence(0, 1).charAt(0);

    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }

}
