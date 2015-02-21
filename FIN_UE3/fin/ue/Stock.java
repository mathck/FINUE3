package fin.ue;

import java.util.ArrayList;

public class Stock {
	private ArrayList<Double> _close;
	private Double _erwarteteRendite;
	private Double _volatilitaet;
	
	public Stock(ArrayList<Double> close) {
		_close = close;
		_erwarteteRendite = CalculateErwarteteRendite();
		_volatilitaet = CalculateVolatilitaet();
	}
	
	public Double GetRendite() {
		return _erwarteteRendite;
	}
	
	public Double GetVolatilitaet() {
		return _volatilitaet;
	}
	
	public Double CalculateErwarteteRendite() {
		ArrayList<Double> renditeList = getErwarteteList(_close);
		
		double sum = 0;

		for(int i=0; i < renditeList.size(); i++){
		   sum += Double.valueOf( renditeList.get(i) ); 
		}

		double average = sum / renditeList.size();
		
		return average;
	}
	
	private ArrayList<Double> getErwarteteList(ArrayList<Double> historical) {
		ArrayList<Double> rendite = new ArrayList<Double>();
		int next = 1;
		while(next < historical.size()){
			double result = (historical.get(next-1) / historical.get(next)) - 1;
			rendite.add(result);
			next++;
		}
		return rendite;	
	}
	
	private double getStdDev(ArrayList<Double> renditeList){
		double stdDev = 0;
		double sum = 0;
		for(Double d: renditeList){
			sum += Math.pow(d-_erwarteteRendite, 2);
		}
		
		stdDev = Math.sqrt(sum/(renditeList.size()-1));
		return stdDev;
	}
	
	public Double CalculateVolatilitaet() {
		return getStdDev(getErwarteteList(_close));
	}
}