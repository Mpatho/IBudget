package com.mpathozulu.ibudget.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.mpathozulu.ibudget.R;
import com.mpathozulu.ibudget.model.BudgetMonth;
import com.mpathozulu.ibudget.model.BudgetPlan;
import com.mpathozulu.ibudget.model.BudgetEntryType;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private BudgetPlan budgetPlan;
	private int year;
	private int month;
	private TextView yearMonth;
	private TextView unbudgected;
	private TextView income;
	private TextView personal;
	private TextView transportation;
	private TextView medical;
	private TextView food;
	private TextView shelter;
	private TextView clothing;
	private TextView insurance;
	private TextView supplies;
	private TextView debtReduction;
	private TextView funMoney;
	private TextView saving;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		yearMonth = (TextView) findViewById(R.id.year_month);
		unbudgected = (TextView) findViewById(R.id.unbudgeted);
		income = (TextView) findViewById(R.id.income);
		personal = (TextView) findViewById(R.id.personal);
		transportation = (TextView) findViewById(R.id.transportation);
		medical = (TextView) findViewById(R.id.medical);
		food = (TextView) findViewById(R.id.food);
		shelter = (TextView) findViewById(R.id.shelter);
		clothing = (TextView) findViewById(R.id.clothing);
		insurance = (TextView) findViewById(R.id.insurance);
		supplies = (TextView) findViewById(R.id.supplies);
		debtReduction = (TextView) findViewById(R.id.debtReduction);
		funMoney = (TextView) findViewById(R.id.funMoney);
		saving = (TextView) findViewById(R.id.saving);

		month = Calendar.getInstance().get(Calendar.MONTH);
		year = Calendar.getInstance().get(Calendar.YEAR);
	}

	@Override
	protected void onResume() {
		super.onResume();
		update();
	}

	private void update() {
		BudgetMonth budgetMonth = BudgetMonth.values()[month];
		budgetPlan = BudgetPlan.getBudgetPlan(year, budgetMonth);
		unbudgected.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getUnbudgeted()));
		yearMonth.setText(String.format(Locale.getDefault(), "%d %s",year, budgetMonth.getName()));
		income.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getIncome()));
		personal.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getPersonal()));
		transportation.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getTransportation()));
		medical.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getMedical()));
		food.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getFood()));
		shelter.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getShelter()));
		clothing.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getClothing()));
		insurance.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getInsurance()));
		supplies.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getSupplies()));
		debtReduction.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getDebtReduction()));
		funMoney.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getFunMoney()));
		saving.setText(String.format(Locale.getDefault(), "R %.2f", budgetPlan.getSaving()));
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
		select(BudgetEntryType.Insurance);
	}

	public void selectSupplies(View view) {
		select(BudgetEntryType.Supplies);
	}

	public void selectDebtReduction(View view) {
		select(BudgetEntryType.DebtReduction);
	}

	public void selectFunMoney(View view) {
		select(BudgetEntryType.FunMoney);
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
