package fin.ue;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class Portfolio {
	private ArrayList<Stock> _stocks;
	private ArrayList<Double> _rendite;
	private ArrayList<Double> _vola;
	private Double _zinssatz;
	private int _stockGewichtung;
	private Double _gewichtungsSchritte = 0.01d;
	
	public Portfolio() {
		_stocks = new ArrayList<Stock>();
		_zinssatz = 0d;
	}
	
	public void SetStockGewichtung(int gewichtung) {
		_stockGewichtung = gewichtung;
	}
	
	public void SetZinssatz(double zinssatz) {
		_zinssatz = zinssatz;
	}
	
	public double CashAndStockErwartungswert() {
		double result = 0;
		
		int cashGewichtung = 1 - _stockGewichtung;
		
		return result;
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
	
	public double GetMVPVola() throws InvalidAlgorithmParameterException {
		// ist einfach der kleinste Wert für die Volatilität in der Grafik
		return min(GetVolatilitaetArray()) * 100;
	}
	
	public double GetMVPRendite() throws InvalidAlgorithmParameterException {
		// ist einfach der kleinste Wert für die Volatilität in der Grafik
		return GetErwarteteRenditeArray()[(int) minIndex(GetVolatilitaetArray())] * 100;
	}
	
	private double min(double[] array)
	{
		List<Double> list = Arrays.asList(ArrayUtils.toObject(array));
        return Collections.min(list);
	}
	
	private double minIndex(double[] array)
	{
		List<Double> list = Arrays.asList(ArrayUtils.toObject(array));
        return list.indexOf(Collections.min(list));
	}
	
	public Double CalculateKorr(Stock stock1, Stock stock2){
		Double result;
		Double sum1 = 0d;
		Double sum2 = 0d;
		Double sum3 = 0d;
		//Korrelation nach https://support.office.com/en-us/article/PEARSON-function-22f752ac-ad5a-4f1f-a2cf-1ef7ed96e1af?CorrelationId=bcdd2d15-1fec-4e1b-aea2-35757d00efee&ui=en-US&rs=en-US&ad=US
		for(int i = 0; i < stock1.GetRenditeList().size(); i++){
			sum1 += (stock1.GetRenditeList().get(i)-stock1.GetRendite())*(stock2.GetRenditeList().get(i)-stock2.GetRendite());
			sum2 += Math.pow(stock1.GetRenditeList().get(i)-stock1.GetRendite(),2.0);
			sum3 += Math.pow(stock2.GetRenditeList().get(i)-stock2.GetRendite(),2.0);
		}
		result = sum1 / Math.sqrt(sum2*sum3);
		//System.out.println(result);
		
		return result;
		
	}
	
	private double[] GetVolatilitaetArray() throws InvalidAlgorithmParameterException {
		ArrayList<Double> result = new ArrayList<Double>();
		
		try {
			double gewichtung_stock1 = 0;
			double gewichtung_stock2 = 1 - gewichtung_stock1;
			
			double stock1_vola = _stocks.get(0).CalculateVolatilitaet();
			double stock2_vola = _stocks.get(1).CalculateVolatilitaet();
			double korr = CalculateKorr(_stocks.get(0), _stocks.get(1));
			
			for(double i = 0; i <= 1; i += _gewichtungsSchritte)
			{
				gewichtung_stock1 = i;
				gewichtung_stock2 = 1 - gewichtung_stock1;
				
				result.add(Math.sqrt(	Math.pow(gewichtung_stock1, 2) * Math.pow(stock1_vola, 2) + 
										Math.pow(gewichtung_stock2, 2) * Math.pow(stock2_vola, 2) +
										2 * gewichtung_stock1 * gewichtung_stock2 * stock1_vola * stock2_vola * korr));
			}
		}
		catch(Exception e) {
			throw new InvalidAlgorithmParameterException();
		}
		_vola = result;
		return getDoubles(result.toArray(new Double[result.size()]));
	}
	
	private double[] GetErwarteteRenditeArray() throws InvalidAlgorithmParameterException {
		ArrayList<Double> result = new ArrayList<Double>();
		
		try {
		double gewichtung_stock1 = 0;
		double gewichtung_stock2 = 1 - gewichtung_stock1;
		
		double stock1_rendite = _stocks.get(0).CalculateErwarteteRendite();
		double stock2_rendite = _stocks.get(1).CalculateErwarteteRendite();
		
			for(double i = 0; i <= 1; i += _gewichtungsSchritte)
			{
				gewichtung_stock1 = i;
				gewichtung_stock2 = 1 - gewichtung_stock1;
				
				result.add(gewichtung_stock1*stock1_rendite + gewichtung_stock2*stock2_rendite);
			}
		}
		catch(Exception e) {
			throw new InvalidAlgorithmParameterException();
		}
		_rendite = result;
		return getDoubles(result.toArray(new Double[result.size()]));
	}
	
	public XYDataset GetDataset() throws InvalidAlgorithmParameterException {

        DefaultXYDataset ds = new DefaultXYDataset();
        
        //Um Arraylists in Arrays umzuwandeln 
        //Integer[] bar = foo.toArray(new Integer[foo.size()]);
        
        double[][] data = { GetVolatilitaetArray(), GetErwarteteRenditeArray() };
        
        ds.addSeries("Tobin", GetTobinData());
        ds.addSeries("Portfolio", data);
        return ds;
    }
	
	double[] getDoubles(Double[] data) {
	    return ArrayUtils.toPrimitive(data);
	}
	
	public double[][] GetTobinData() throws InvalidAlgorithmParameterException{
		 ArrayList<Double> steigung = new ArrayList<Double>();
		 double x0 = 0;
		 double y0 = _zinssatz/10;
		 
		 for(int i = 0; i < _vola.size(); i++){
			 double x1 = ((1-_stockGewichtung) * (y0)) + (_stockGewichtung * _vola.get(i));
			 double y1 = _stockGewichtung * _rendite.get(i);
			 steigung.add((y1-y0)/(x1-x0));
		 }
		 
		 int maxIndex = steigung.indexOf(Collections.max(steigung));
		 double[] data1 = {_vola.get(maxIndex).doubleValue()};
		 double[] data2 = {_rendite.get(maxIndex).doubleValue()};
		 double[][] data = {data1,data2};
		 
		 return data;

	}
	
	public double[][] GetTobinData1() throws InvalidAlgorithmParameterException{
		return null;
		
		/*ArrayList<Double> yData = new ArrayList<Double>();
		ArrayList<Double> xData = new ArrayList<Double>();
		double steigung = GetTobinSteigung();
		
		for(double i = 0; i < Collections.max(_vola) * 1.1; i+=0.00005){
			xData.add(i);
			yData.add(i*steigung + _zinssatz);
		}
		
		double[][] data = {getDoubles(xData.toArray(new Double[xData.size()])), getDoubles(yData.toArray(new Double[yData.size()]))};
		return data;
		ArrayList<Double> renData = new ArrayList<Double>();
		ArrayList<Double> volData = new ArrayList<Double>();
		
		double gewichtung_stock1 = GetTobinGewichtung();
		double gewichtung_stock2 = 1 - gewichtung_stock1;
		
		double stock1_rendite = _stocks.get(0).CalculateErwarteteRendite();
		double stock2_rendite = _stocks.get(1).CalculateErwarteteRendite();
		double rendite = gewichtung_stock1*stock1_rendite + gewichtung_stock2*stock2_rendite;
		
		double stock1_vola = _stocks.get(0).CalculateVolatilitaet();
		double stock2_vola = _stocks.get(1).CalculateVolatilitaet();
		double korr = CalculateKorr(_stocks.get(0), _stocks.get(1));
		
		double vola = Math.sqrt(	Math.pow(gewichtung_stock1, 2) * Math.pow(stock1_vola, 2) + 
				Math.pow(gewichtung_stock2, 2) * Math.pow(stock2_vola, 2) +
				2 * gewichtung_stock1 * gewichtung_stock2 * stock1_vola * stock2_vola * korr);
		
		double pGewichtung = 0;
		double gewichtungsSchritte = 0.001;
		while(pGewichtung <= 0.05){
			renData.add((_zinssatz/100) + ((rendite-(_zinssatz/100))/ (vola) * pGewichtung));
			volData.add(pGewichtung);
			pGewichtung += gewichtungsSchritte;
		}
		double[][] data = { getDoubles(volData.toArray(new Double[volData.size()])), getDoubles(renData.toArray(new Double[renData.size()]))};
		return data;
		*/
	}
}
