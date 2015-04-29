package com.example.notepad;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomGridViewAdapter extends ArrayAdapter<Item> {
	Context context;
	int layoutResourceId;
	ArrayList<Item> data = new ArrayList<Item>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Item> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.txtFile = (TextView) row.findViewById(R.id.item_text1);
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text2);
			holder.txtContent = (TextView) row.findViewById(R.id.item_text3);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		Item item = data.get(position);
		holder.txtContent.setText(item.getContent());
		holder.txtTitle.setText(item.getTitle());
		holder.txtFile.setText(item.getFileName());
		return row;

	}

	static class RecordHolder {
		TextView txtTitle;
		TextView txtFile;
		TextView txtContent;

	}
}
