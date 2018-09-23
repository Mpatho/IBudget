package com.mpathozulu.soft.ibudget.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.mpathozulu.soft.ibudget.R;
import com.mpathozulu.soft.ibudget.model.BudgetMonth;
import com.mpathozulu.soft.ibudget.model.BudgetPlan;
import com.mpathozulu.soft.ibudget.model.BudgetEntryType;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private BudgetPlan budgetPlan;
	private int year;
	private int month;
	private TextView yearMonth;
	private TextView unbudgected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		yearMonth = (TextView) findViewById(R.id.year_month);
		unbudgected = (TextView) findViewById(R.id.unbudgeted);
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH);
	}

	@Override
	protected void onResume() {
		super.onResume();
		update();
	}

	private void update() {
		BudgetMonth budgetMonth = BudgetMonth.values()[month];
		budgetPlan = BudgetPlan.getBudgetPlan(year, budgetMonth);
		unbudgected.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getSavings()));
		yearMonth.setText(String.format(Locale.getDefault(), "%d %s",year, budgetMonth.getName()));
	}

	public void selectIncome(View view) {
		select(BudgetEntryType.Income);
	}

	public void selectPersonal(View view) {
		select(BudgetEntryType.Expenditure);
	}

	public void selectTransportation(View view) {
		select(BudgetEntryType.Transportation);
	}

	public void selectMedical(View view) {
		select(BudgetEntryType.Medical);
	}

	public void selectFood(View view) {
		select(BudgetEntryType.Food);
	}

	public void selectShelter(View view) {
		select(BudgetEntryType.Shelter);
	}

	public void selectClothing(View view) {
		select(BudgetEntryType.Clothing);
	}

	public void selectInsurance(View view) {
		select(BudgetEntryType.Supplies);
	}

	public void selectSupplies(View view) {
		select(BudgetEntryType.Supplies);
	}

	public void selectDebtReduction(View view) {
		select(BudgetEntryType.Supplies);
	}

	public void selectFunMoney(View view) {
		select(BudgetEntryType.Supplies);
	}

	public void selectSavings(View view) {
		select(BudgetEntryType.Saving);
	}

	private void select(BudgetEntryType type) {
		Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
		intent.putExtra("Type", type);
		intent.putExtra("BudgetMonth", budgetPlan.getMonth());
		intent.putExtra("BudgetYear", budgetPlan.getYear());
		startActivity(intent);
	}

	public void next(View view) {
		month++;
		if (month == 12) {
			month = 0;
			year++;
		}
		update();
	}

	public void back(View view) {
		month--;
		if (month == -1) {
			month = 11;
			year--;
		}
		update();
	}
}
