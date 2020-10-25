package Logica;

import javax.swing.ImageIcon;

/**
 * Clase Celda 
 * Maneja la estructura inerna de la celda para la matriz de botones de la GUI
 * @author Crisafulli Sofía con la colaboracón de Paredes José
 */
import javax.swing.JButton;

public class Celda extends JButton{
	/**
	 * Clase Celda.
	 * Maneja a los botones del juego.
	 * @author Crisafulli Sofía con la colaboración de Paredes José.
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
	 * Actualiza las imágenes de acuerdo al tamaño de la matriz de celdas.
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
	 * Este método retorna la cantidad de imágenes.
	 * @return cantidad de imágenes.
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
	 * Devuelve la imágen.
	 * @return la imágen.
	 */

	public ImageIcon getGrafico() {
		return this.grafico;
	} 
	
	/**
	 * Setea el gráfico de una celda.
	 * @param g gráfico de una celda.
	 */

	public void setGrafico(ImageIcon g) {
		grafico = g;
	}
	
	/**
	 * Devuelve las imágenes.
	 * @return imágenes.
	 */

	public String[] getImagenes() {
		return imagenes;
	} 
	
	/**
	 * Setea las imágenes.
	 * @param i imágenes.
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

