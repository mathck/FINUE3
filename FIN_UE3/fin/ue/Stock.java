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
		//SOMETHING IS GOING WRONG HERE
		double stdDev = 0;
		double sum = 0;
		int i = 0;
		while(i < renditeList.size()-1){
			sum += Math.pow(renditeList.get(i)-((renditeList.get(i)-renditeList.get(i+1)/2)),2.0);
			System.out.println(sum);
			i++;
		}
		
		stdDev = Math.sqrt(sum*(1/(double)renditeList.size()));
		return stdDev;
	}
	
	public Double CalculateVolatilitaet() {
		return getStdDev(_close);
	}
}