package com.mpathozulu.soft.ibudget2.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.mpathozulu.soft.ibudget2.BudgetEntryAdapter;
import com.mpathozulu.soft.ibudget2.R;
import com.mpathozulu.soft.ibudget2.model.BudgetMonth;
import com.mpathozulu.soft.ibudget2.model.BudgetPlan;
import com.mpathozulu.soft.ibudget2.model.BudgetEntry;
import com.mpathozulu.soft.ibudget2.model.BudgetEntryType;

public class ItemsActivity extends AppCompatActivity {

	private BudgetPlan budgetPlan;

	private BudgetEntryDialog dialog;

	private BudgetEntryType budgetEntryType;

	private ListView items;

	private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			BudgetEntryAdapter budgetEntryAdapter = (BudgetEntryAdapter) items.getAdapter();
			BudgetEntry budgetEntry = (BudgetEntry) budgetEntryAdapter.getItem(position);
			dialog = new BudgetEntryDialog(ItemsActivity.this);
			dialog.show(budgetEntry);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		Intent intent = getIntent();
		budgetEntryType = (BudgetEntryType) intent.getSerializableExtra("Type");
		BudgetMonth month = (BudgetMonth) intent.getSerializableExtra("BudgetMonth");
		int year = intent.getIntExtra("BudgetYear", 10);
		budgetPlan = BudgetPlan.getBudgetPlan(year, month);
		items = (ListView) findViewById(R.id.items);
		items.setOnItemClickListener(onItemClickListener);
		items.setAdapter(new BudgetEntryAdapter(this, budgetPlan.getItems(budgetEntryType)));
	}

	public void save(View view) {
		BudgetEntry budgetEntry = dialog.getBudgetEntry();
		budgetPlan.save(budgetEntry);
		update();
		dialog.dismiss();
	}

	public void create(View view) {
		dialog = new BudgetEntryDialog(ItemsActivity.this);
		dialog.show(new BudgetEntry(budgetPlan.getYear(), budgetPlan.getMonth(), budgetEntryType));
	}

	public void cancel(View view) {
		dialog.dismiss();
	}

	public void delete(View view) {
		budgetPlan.delete((BudgetEntry) view.getTag());
		update();
	}

	private void update() {
		BudgetEntryAdapter c = (BudgetEntryAdapter) items.getAdapter();
		c.notifyDataSetChanged();
	}

	private class BudgetEntryDialog extends Dialog {

		private BudgetEntry budgetEntry;
		private EditText name;
		private EditText amount;
		private EditText frequency;

		private BudgetEntryDialog(Context context) {
			super(context);
			setContentView(R.layout.dialog_budget);
			name = (EditText) findViewById(R.id.budget_item_name);
			amount = (EditText) findViewById(R.id.budget_item_amount);
			frequency = (EditText) findViewById(R.id.budget_item_frequency);
			getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
		}

		private void show(BudgetEntry entry) {
			budgetEntry = entry;
			double rands = budgetEntry.getAmount();
			String name = budgetEntry.getName();
			int frequency = budgetEntry.getFrequency();
			if (rands != 0 && name != null && !name.isEmpty() && frequency != 0) {
				amount.setText(String.valueOf(rands));
				this.name.setText(name);
				this.frequency.setText(String.valueOf(frequency));
			}
			show();
		}

		private void update() {
			budgetEntry.setName(getName());
			budgetEntry.setAmount(getAmount());
			budgetEntry.setFrequency(getFrequency());
		}

		private BudgetEntry getBudgetEntry() {
			update();
			return budgetEntry;
		}

		private int getFrequency() {
			return Integer.valueOf(frequency.getText().toString());
		}

		private String getName() {
			return String.valueOf(name.getText());
		}

		private Double getAmount() {
			return Double.valueOf(amount.getText().toString());
		}
	}
}
