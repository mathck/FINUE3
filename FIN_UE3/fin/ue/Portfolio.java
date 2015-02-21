package fin.ue;

import java.util.ArrayList;

public class Portfolio {
	private ArrayList<Stock> _stocks;
	private Double _zinssatz;
	private Double _gewichtungsSchritte = 0.05d;
	
	public Portfolio() {
		_stocks = new ArrayList<Stock>();
		_zinssatz = 0d;
	}
	
	public void AddStock(Stock newStock) {
		_stocks.add(newStock);
	}
	
	// siehe Excel Markowitz!C4
	public ArrayList<Double> GetKorrMatrix() {
		// TODO DO ME
		return null;
	}
	
	// siehe Excel Markowitz!B14:V14
	public ArrayList<Double> GetErwarteteRendite() {
		// TODO DO ME
		return null;
	}
	
	// siehe Excel Markowitz!B13:V13
	public ArrayList<Double> GetVolatilitaet() {
		// TODO DO ME
		return null;
	}
	
	public Double GetMVP() {
		// TODO DO ME
		return null;
	}
}
