package Logica;

import javax.swing.ImageIcon;

/**
 * Clase Celda 
 * Maneja la estructura inerna de la celda para la matriz de botones de la GUI
 * @author Crisafulli Sof�a con la colaborac�n de Paredes Jos�
 */
import javax.swing.JButton;

public class Celda extends JButton{
	/**
	 * Clase Celda.
	 * Maneja a los botones del juego.
	 * @author Crisafulli Sof�a con la colaboraci�n de Paredes Jos�.
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer valor;
	private int fila;
	private int columna;
	private boolean SeVerifica;
	private ImageIcon grafico;
	private String[] imagenes;
	
	/**
	 * Inicaliza los atributos.
	 * @param f es la fila de la celda.
	 * @param c es la columna de la celda.
	 * @param v es el valor de la celda.
	 */
	
	public Celda(int f, int c, boolean v) {
		valor = null;
		fila = f;
		columna = c;
		SeVerifica = v;
		grafico = new ImageIcon(); 
		imagenes = new String[] {"/Img/1 chico.png", "/Img/2 chico.png","/Img/3 chico.png","/Img/4 chico.png","/Img/5 chico.png","/Img/6 chico.png","/Img/7 chico.png","/Img/8 chico.png","/Img/9 chico.png"};
	}
	
	/**
	 * Actualiza las im�genes de acuerdo al tama�o de la matriz de celdas.
	 */

	public void actualizar() { 
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		ImageIcon img= new ImageIcon(this.getClass().getResource(this.imagenes[valor]));
		this.grafico.setImage(img.getImage());
	}
	
	/**
	 * Este m�todo retorna la cantidad de im�genes.
	 * @return cantidad de im�genes.
	 */

	public int getCantElementos() {
		return imagenes.length;
	}
	
	/**
	 * Devuelve el valor.
	 * @return el valor.
	 */

	public Integer getValor() {
		return this.valor;
	}
	
	/**
	 * Setea el valor de la celda.
	 * @param valor de la celda.
	 */

	public void setValor(Integer valor) {
		if (valor != null && valor < this.getCantElementos()) {
			this.valor = valor;
			if (valor < this.imagenes.length) {
				ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[valor]));
				this.grafico.setImage(imageIcon.getImage());
			}
		}else {
			this.valor = null;
		}
	}
	
	/**
	 * Setea la fila.
	 * @param f fila de la celda.
	 */

	public void setFila(int f) {
		fila = f;
	}
	
	/**
	 * Setea la columna.
	 * @param c columna de la celda.
	 */

	public void setColumna(int c) {
		columna = c;
	}
	
	/**
	 * Devuelve la fila de la celda.
	 * @return la fila de la celda.
	 */

	public int getFila() {
		return fila;
	}
	
	/**
	 * Devuelve la columna de la celda.
	 * @return la columna de la celda.
	 */

	public int getColumna() {
		return columna;
	}
	
	/**
	 * Devuelve la im�gen.
	 * @return la im�gen.
	 */

	public ImageIcon getGrafico() {
		return this.grafico;
	} 
	
	/**
	 * Setea el gr�fico de una celda.
	 * @param g gr�fico de una celda.
	 */

	public void setGrafico(ImageIcon g) {
		grafico = g;
	}
	
	/**
	 * Devuelve las im�genes.
	 * @return im�genes.
	 */

	public String[] getImagenes() {
		return imagenes;
	} 
	
	/**
	 * Setea las im�genes.
	 * @param i im�genes.
	 */

	public void setImagenes(String[] i) {
		imagenes = i;
	}
	
	/**
	 * Devuelve seVerifica.
	 * @return seVerifica.
	 */

	public boolean getSeVerifica() {
		return SeVerifica;
	}
	
	/**
	 * Setea seVerifica.
	 * @param v booleano para setear seVerifica.
	 */

	public void setVerifica(boolean v) {
		SeVerifica = v;
	}
}

