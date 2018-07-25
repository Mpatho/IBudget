package com.mpathozulu.soft.ibudget2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mpathozulu.soft.ibudget2.model.BudgetEntry;

import java.util.Locale;
import java.util.SortedSet;


public class BudgetEntryAdapter extends BaseAdapter {


	private Context context;

	private SortedSet<BudgetEntry> items;

	public BudgetEntryAdapter(Context context, SortedSet<BudgetEntry> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.toArray()[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView != null ? convertView : LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView amount = (TextView) view.findViewById(R.id.amount);
		ImageView button = (ImageView) view.findViewById(R.id.deleteBtn);

		BudgetEntry budgetEntry = (BudgetEntry) getItem(position);

		name.setText(budgetEntry.getName());
		amount.setText(String.format(Locale.getDefault(),"Amount : R %.2f ", budgetEntry.getAmount() * budgetEntry.getFrequency()));
		button.setTag(budgetEntry);
		return view;
	}
}
