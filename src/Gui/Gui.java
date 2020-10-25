package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Logica.Celda;
import Logica.Juego;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class Gui extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Celda [][] m; 
	private Juego juego;
	private Timer timer;
	private int horas;
	private int min; 
	private int seg;
	private int cs;
	private ActionListener acciones; 
	private JLabel []tiempo;
	private String [] numeros;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Img/2 chico.png"));
		setTitle("Sudoku");
		juego = new Juego();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.black); 
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 11, 406, 402);
		contentPane.add(panelBotones);
		panelBotones.setBackground(Color.black);
		panelBotones.setLayout(new GridLayout(9,9));
		
		m = new Celda[juego.getCantFilas()][juego.getCantFilas()];
		for(int i = 0; i < juego.getCantFilas(); i++)
			for(int j = 0; j < juego.getCantFilas(); j++) { 
				Celda c = juego.getCelda(i, j);
				c.setBackground(Color.black);
				c.setVisible(true);
				c.setEnabled(true);
				c.setBorder(BorderFactory.createLineBorder(Color.WHITE)); 
				c.setBorder(BorderFactory.createBevelBorder(1,Color.white,Color.black));
				ImageIcon icono = c.getGrafico();
				c.setIcon(icono);
				if(c.getValor() != null)
					c.setEnabled(false);
				c.addComponentListener(new ComponentAdapter(){
                    @Override
                    public void componentResized(ComponentEvent e) {
                        reDimensionar(c,icono);
                        c.setIcon(icono);
                        c.setBorder(BorderFactory.createBevelBorder(1,Color.white,Color.black));
                       c.setBackground(Color.black);
                    }
                });

				c.addMouseListener(new MouseAdapter() {

                    public void mouseClicked(MouseEvent e) {
                        if(c.isEnabled()) {
                        	c.actualizar();
                            reDimensionar(c,icono);
                            juego.reiniciar();
                            juego.verifica(c);
                        }

                    }
                });
                panelBotones.add(c);
			} 
		
		JPanel panelTimer = new JPanel();
		panelTimer.setBackground(Color.BLACK);
		panelTimer.setBounds(426, 34, 351, 105);
		contentPane.add(panelTimer);
		panelTimer.setLayout(null);
	
	
		JLabel h0 = new JLabel();
		h0.setBounds(39, 0, 37, 105);
		h0.setIcon(new ImageIcon(getClass().getResource("/Img/0.png")));
		panelTimer.add(h0);
		
		JLabel h1 = new JLabel();
		h1.setBounds(73, 0, 37, 105);
		h1.setIcon(new ImageIcon(getClass().getResource("/Img/0.png")));
		panelTimer.add(h1);
		
		JLabel p1 = new JLabel(":");
		p1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		p1.setForeground(Color.WHITE);
		p1.setBounds(110, 0, 37, 105);
		panelTimer.add(p1);
		
		JLabel m0 = new JLabel();
		m0.setBounds(138, 0, 37, 105);
		m0.setIcon(new ImageIcon(getClass().getResource("/Img/0.png")));
		panelTimer.add(m0);
		
		JLabel m1 = new JLabel();
		m1.setBounds(173, 0, 37, 105);
		m1.setIcon(new ImageIcon(getClass().getResource("/Img/0.png")));
		panelTimer.add(m1);
		
		JLabel s0 = new JLabel();
		s0.setBounds(242, 0, 37, 105);
		s0.setIcon(new ImageIcon(getClass().getResource("/Img/0.png")));
		panelTimer.add(s0);
	
		JLabel s1 = new JLabel();
		s1.setBounds(278, 0, 37, 105);
		s1.setIcon(new ImageIcon(getClass().getResource("/Img/0.png")));
		panelTimer.add(s1);
		
		JLabel p1_1 = new JLabel(":");
		p1_1.setForeground(Color.WHITE);
		p1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		p1_1.setBounds(220, 0, 37, 105);
		panelTimer.add(p1_1);
		
		JLabel m0_1 = new JLabel();
		m0_1.setBounds(10, 0, 37, 105);
		panelTimer.add(m0_1);
		

		panelTimer.repaint();
		
		numeros = new String [] {"/Img/0.png", "/Img/1.png", "/Img/2.png", "/Img/3.png", "/Img/4.png", "/Img/5.png", "/Img/6.png", "/Img/7.png", "/Img/8.png", "/Img/9.png"};
 		tiempo = new JLabel[] {h0, h1, m0, m1, s0, s1};
 		
		
		acciones = new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				  cs++;
	                if(cs == 100) {
	                    cs = 0;
	                    ++seg;
	                }
				if(seg == 60) { 
					seg = 0; 
					++min;
				}
				if(min == 60) { 
					min = 0; 
					++horas;
				}
				actualizarLabel(); 
		}
	};
	
	timer = new Timer(10,acciones); 
	timer.start();
	
	
		
		JButton btnFinalizar = new JButton("Finaizar");
		btnFinalizar.setForeground(Color.WHITE);
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop(); 
				boolean seVerifica = true;
				for(int i = 0; i < juego.getCantFilas() && seVerifica; i++)
					for(int j = 0; j < juego.getCantFilas() && seVerifica; j++) {
						Celda c = juego.getCelda(i, j);
						if(!c.getSeVerifica() || c.getValor() == null) 
							seVerifica = false; 
					}
				if(seVerifica)
					JOptionPane.showMessageDialog(null, "Felicitaciones, ganaste!!");
				else 
					JOptionPane.showMessageDialog(null, "Lo siento, perdiste");
			}
		});
		btnFinalizar.setBounds(537, 288, 128, 39);
		btnFinalizar.setBackground(Color.black);
		btnFinalizar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		contentPane.add(btnFinalizar);
	}
	
	private void reDimensionar(JButton boton, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			boton.repaint();
		}
	}
	
	public void actualizarLabel() { 
		int h, m, s;
		if(horas <= 9) { 
			tiempo[0].setIcon(new ImageIcon(getClass().getResource(numeros[0])));
			tiempo[1].setIcon(new ImageIcon(getClass().getResource(numeros[horas])));
			tiempo[0].repaint();
			tiempo[1].repaint();
		}
		else { 
			h = horas / 10;
			tiempo[0].setIcon(new ImageIcon(getClass().getResource(numeros[h])));
			h = horas % 10;
			tiempo[1].setIcon(new ImageIcon(getClass().getResource(numeros[h])));
			tiempo[0].repaint(); 
			tiempo[1].repaint(); 
		}
		if(min <= 9) {
			tiempo[2].setIcon(new ImageIcon(getClass().getResource(numeros[0])));
			tiempo[3].setIcon(new ImageIcon(getClass().getResource(numeros[min])));
			tiempo[2].repaint();
			tiempo[3].repaint();
		}
		else { 
			m = min / 10; 
			tiempo[2].setIcon(new ImageIcon(getClass().getResource(numeros[m])));
			m = min % 10;
			tiempo[3].setIcon(new ImageIcon(getClass().getResource(numeros[m])));
			tiempo[2].repaint();
			tiempo[3].repaint();
		}
		if(seg <= 9) { 
			tiempo[4].setIcon(new ImageIcon(getClass().getResource(numeros[0])));
			tiempo[5].setIcon(new ImageIcon(getClass().getResource(numeros[seg])));
			tiempo[4].repaint(); 
			tiempo[5].repaint();
		}
		else { 
			s = seg / 10; 
			tiempo[4].setIcon(new ImageIcon(getClass().getResource(numeros[s])));
			s = seg % 10;
			tiempo[5].setIcon(new ImageIcon(getClass().getResource(numeros[s])));
			tiempo[4].repaint(); 
			tiempo[5].repaint();
		}
	}
}
