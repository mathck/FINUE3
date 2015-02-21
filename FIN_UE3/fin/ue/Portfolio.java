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
	
	public Double CalculateKorr(Stock j, Stock k){
		Double result;
		Double sum1 = 0d;
		Double sum2 = 0d;
		Double sum3 = 0d;
		//Korrelation nach https://support.office.com/en-us/article/PEARSON-function-22f752ac-ad5a-4f1f-a2cf-1ef7ed96e1af?CorrelationId=bcdd2d15-1fec-4e1b-aea2-35757d00efee&ui=en-US&rs=en-US&ad=US
		for(int i = 0; i < j.GetRenditeList().size(); i++){
			sum1 += (j.GetRenditeList().get(i)-j.GetRendite())*(k.GetRenditeList().get(i)-k.GetRendite());
			sum2 += Math.pow(j.GetRenditeList().get(i)-j.GetRendite(),2.0);
			sum3 += Math.pow(k.GetRenditeList().get(i)-k.GetRendite(),2.0);
		}
		result = sum1 / Math.sqrt(sum2*sum3);
		System.out.println(result);
		
		return result;
		
	}
	
}
