import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class conversorMonedas {

	private JFrame frame;
	private final JTextField txt = new JTextField();
	private JLabel lbl;
	private JComboBox cmb;
	private JButton btn;
	
	public enum Moneda{
		
		pesos_dolar,
		pesos_euro,
		pesos_yuanchino,
		pesos_libra,
		pesos_yuanjapones,
		dolar_pesos,
		euro_pesos,
		yuanchino_pesos,
		libra_pesos,
		yuanjapones_pesos,
	}
	
	public double dolar = 17.09;
	public double euro = 18.57;
	public double yuanchino = 2.39;
	public double libra= 21.51;
	public double yuanjapones = 20.36;
	
	
	public double  valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conversorMonedas window = new conversorMonedas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public conversorMonedas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		txt.setBounds(46, 11, 112, 37);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(46, 84, 112, 23);
		frame.getContentPane().add(cmb);
		
		// seccion de boton 
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
				
			}
		});
		
		btn.setBounds(179, 84, 112, 23);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(203, 16, 102, 26);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir () {
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			
			switch (moneda) {
			
			case pesos_dolar:
				pesosAMoneda(dolar);
				break;
				
			case pesos_euro:
				pesosAMoneda(euro);
				break;
				
			case pesos_yuanchino:
				pesosAMoneda(yuanchino);
				break;
				
			case pesos_libra:
				pesosAMoneda(libra);
				break;
				
			case pesos_yuanjapones:
				pesosAMoneda(yuanjapones);
				break;
				
			case dolar_pesos:
				MonedaAPesos(dolar);
				break;
				
			case euro_pesos:
				MonedaAPesos(euro);
				break;
				
			case yuanchino_pesos:
				MonedaAPesos(yuanchino);
				break;
				
			case libra_pesos:
				MonedaAPesos(libra);
				break;
				
			case yuanjapones_pesos:
				MonedaAPesos(yuanjapones);
				break;
				
			default:
				throw new IllegalArgumentException ("Unexpected value: " + moneda);
				
			}
		}
		
		}
		
	public void pesosAMoneda(double moneda) {
		double res = valorInput/moneda;
		lbl.setText(Redondear(res));
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	public String Redondear (double valor) {
		DecimalFormat df  = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0);
			valorInput = x;
			return true ;
		} catch (NumberFormatException e) {
			lbl.setText("solamente numeros!!");
			return false;
		}
		
	}
}
