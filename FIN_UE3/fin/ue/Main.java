package fin.ue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import java.awt.Canvas;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main {

	private JFrame frmPortfoliooptimierungMitVerschiedenen;
	private JTextField zinssatz;
	private JTextField result_e;
	private JTextField result_o;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmPortfoliooptimierungMitVerschiedenen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPortfoliooptimierungMitVerschiedenen = new JFrame();
		frmPortfoliooptimierungMitVerschiedenen.setResizable(false);
		frmPortfoliooptimierungMitVerschiedenen.setTitle("Finanzwirtschaft UE - Portfoliooptimierung mit verschiedenen Assetklassen\t\t\t\t\t\t\t\t\t");
		frmPortfoliooptimierungMitVerschiedenen.setBounds(100, 100, 785, 563);
		frmPortfoliooptimierungMitVerschiedenen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().setLayout(null);
		
		JButton btnAddStock = new JButton("Add New Stock");
		btnAddStock.setFont(new Font("Open Sans", Font.PLAIN, 14));
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(btnAddStock);
		
		JList stock_list = new JList();
		stock_list.setBounds(10, 40, 151, 213);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(stock_list);
		
		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setBackground(Color.WHITE);
		lblStocks.setForeground(SystemColor.textHighlight);
		lblStocks.setHorizontalAlignment(SwingConstants.CENTER);
		lblStocks.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblStocks.setBounds(10, 11, 151, 29);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblStocks);
		
		final JSlider gewichtung_slider = new JSlider();
		gewichtung_slider.setBounds(10, 449, 151, 26);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(gewichtung_slider);
		
		JLabel lblGewichtung = new JLabel("Gewichtung");
		lblGewichtung.setForeground(SystemColor.textHighlight);
		lblGewichtung.setHorizontalAlignment(SwingConstants.CENTER);
		lblGewichtung.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblGewichtung.setBounds(10, 409, 151, 29);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblGewichtung);
		
		JLabel lblStock = new JLabel("Stocks:");
		lblStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblStock.setBounds(10, 486, 54, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblStock);
		
		JLabel lblCash = new JLabel("Cash:");
		lblCash.setHorizontalAlignment(SwingConstants.LEFT);
		lblCash.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCash.setBounds(10, 506, 72, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblCash);
		
		JLabel lblCash_1 = new JLabel("Cash");
		lblCash_1.setForeground(SystemColor.textHighlight);
		lblCash_1.setBackground(Color.WHITE);
		lblCash_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCash_1.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblCash_1.setBounds(11, 320, 151, 29);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblCash_1);
		
		JLabel lblZinssatz = new JLabel("Zinssatz (%)");
		lblZinssatz.setHorizontalAlignment(SwingConstants.LEFT);
		lblZinssatz.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblZinssatz.setBounds(10, 352, 151, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblZinssatz);
		
		zinssatz = new JTextField();
		zinssatz.setHorizontalAlignment(SwingConstants.CENTER);
		zinssatz.setBounds(10, 372, 151, 20);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(zinssatz);
		zinssatz.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 309, 171, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(separator);
		
		final JLabel gewichtung_stock = new JLabel("50%");
		gewichtung_stock.setHorizontalAlignment(SwingConstants.LEFT);
		gewichtung_stock.setFont(new Font("Open Sans", Font.PLAIN, 12));
		gewichtung_stock.setBounds(74, 486, 54, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(gewichtung_stock);
		
		final JLabel gewichtung_cash = new JLabel("50%");
		gewichtung_cash.setHorizontalAlignment(SwingConstants.LEFT);
		gewichtung_cash.setFont(new Font("Open Sans", Font.PLAIN, 12));
		gewichtung_cash.setBounds(74, 508, 54, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(gewichtung_cash);
		
		JButton btn_addStock = new JButton("ADD STOCK");
		btn_addStock.setFont(new Font("Open Sans", Font.PLAIN, 14));
		btn_addStock.setBounds(10, 264, 151, 37);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(btn_addStock);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(171, 0, 11, 534);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 402, 171, 17);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(separator_2);
		
		JButton button_start = new JButton("START");
		button_start.setFont(new Font("Open Sans", Font.BOLD, 22));
		button_start.setBounds(182, 451, 151, 74);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(button_start);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(SystemColor.inactiveCaption);
		canvas.setBounds(183, 9, 585, 434);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(canvas);
		
		JLabel lblErwartetePortfoliorendite = new JLabel("Erwartete Portfoliorendite");
		lblErwartetePortfoliorendite.setHorizontalAlignment(SwingConstants.LEFT);
		lblErwartetePortfoliorendite.setFont(new Font("Open Sans", Font.PLAIN, 13));
		lblErwartetePortfoliorendite.setBounds(343, 478, 157, 20);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblErwartetePortfoliorendite);
		
		result_e = new JTextField();
		result_e.setEditable(false);
		result_e.setHorizontalAlignment(SwingConstants.CENTER);
		result_e.setColumns(10);
		result_e.setBounds(509, 478, 253, 20);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(result_e);
		
		JLabel lblPortfolioVolatilitt = new JLabel("Portfolio Volatilit\u00E4t");
		lblPortfolioVolatilitt.setHorizontalAlignment(SwingConstants.LEFT);
		lblPortfolioVolatilitt.setFont(new Font("Open Sans", Font.PLAIN, 13));
		lblPortfolioVolatilitt.setBounds(343, 503, 157, 20);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblPortfolioVolatilitt);
		
		gewichtung_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int value = gewichtung_slider.getValue();
				gewichtung_stock.setText(value + "%");
				gewichtung_cash.setText(((value - 100) * -1) + "%");
			}
		});
		
		result_o = new JTextField();
		result_o.setHorizontalAlignment(SwingConstants.CENTER);
		result_o.setEditable(false);
		result_o.setColumns(10);
		result_o.setBounds(509, 503, 253, 20);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(result_o);
		
		JLabel lblPortfolio = new JLabel("Portfolio");
		lblPortfolio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPortfolio.setForeground(SystemColor.textHighlight);
		lblPortfolio.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblPortfolio.setBackground(Color.WHITE);
		lblPortfolio.setBounds(343, 449, 151, 26);
		frmPortfoliooptimierungMitVerschiedenen.getContentPane().add(lblPortfolio);
	}
}
