package Logica;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Juego {
	
	/**
	 * Clase Juego.
	 * Maneja la lógica del juego Sudoku, verifica que se respeten todas las condiciones.
	 * @author Crisafulli Sofiía con la colaboración de Paredes José.
	 */

	private Celda [][] tablero;
	private int cantFilas;
	private int [][] enteros;
	
	/**}
	 * Inicializa todos los atributos y verifica que el archivo sea corrrecto.
	 */

	public Juego() {
		cantFilas = 9;
		tablero = new Celda[cantFilas][cantFilas];
		enteros = new int[cantFilas][cantFilas];
		File file = new File("Sudoku.txt");
		Scanner s = null;
		int num;
		boolean existe = file.exists();
		if(existe) {
			try {
				s = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < cantFilas; i++) {
				for (int j = 0; j < cantFilas; j++) {
					num = s.nextInt();
					enteros[i][j] = num;
				}
			} 

			for (int i = 0; i < cantFilas; i++) {
				for (int j = 0; j < cantFilas; j++) {
					tablero[i][j] = new Celda(i,j,true);
					Random rand = new Random();
					int valor = 0;
					int value = rand.nextInt(3); 
					if(value == 0 || value == 1) {
						valor = enteros[i][j]-1;
						tablero[i][j].setValor(valor);
					}
				}
			}

		} 
	} 
	
	/**
	 * Actualiza la imágen de la celda.
	 * @param c celda al cual hay que actualizar.
	 */

	public void accionar(Celda c) {
		c.actualizar();
	}
	
	/**
	 * Devuelve la celda.
	 * @param i fila de la celda.
	 * @param j columna de la celda.
	 * @return celda con la fila y columna indicada.
	 */

	public Celda getCelda(int i, int j) {
		return tablero[i][j];
	}
	
	/**
	 * Devuelve la cantidad de filas que contiene la matriz de celdas.
	 * @return cantidad de filas que contiene la matriz de celdas.
	 */

	public int getCantFilas() {
		return cantFilas;
	}
	
	/**
	 * Verifica que la imágen que contiene celda sea la única en esa fila.
	 * @param celda que se debe verificar que sea la única con una determinada imágen.
	 * @return true si se verifica, de lo contrario flase.
	 */

	public boolean verificaFila(Celda celda) { 
		boolean verifica = true;
		int f = celda.getFila();
		for(int i = 0; i < cantFilas; i++) {
			if(tablero[f][i].getValor() != null && tablero[f][i] != celda) { 
				if(celda.getValor() == tablero[f][i].getValor() && tablero[f][i] != celda) {
					tablero[f][i].setVerifica(false);
					verifica = false;
				}
			}
		}
		return verifica; 
	}
	
	/**
	 * Verifica que la imágen que contiene la celda sea la única en esa columna.
	 * @param celda que se debe verificar que sea la única con una determinada imágen.
	 * @return true si se verifica, de lo contrario flase.
	 */


	public boolean verificaColumna(Celda celda) { 
		boolean verifica = true;
		int col = celda.getColumna(); 
		for(int i = 0; i < cantFilas; i++){
			if(tablero[i][col].getValor() != null && tablero[i][col] != celda)
				if(celda.getValor() == tablero[i][col].getValor()) {
					tablero[i][col].setVerifica(false);
					verifica = false;
				}
		}
		return verifica;
	}
	
	/**
	 * Verifica que la imágen que contiene la celda se la única en ese cuadrante.
	 * @param c celda que se debe verificar que sea la única con una determinada imágen.
	 * @return true si se verifica, de lo contrario false.
	 */

	private boolean verificaCuadrante(Celda c) {
		boolean verifica = true;
		int fila;
		int col;
		if(c.getFila() < 3)//Calculo en base a la posicion de la celda el sector.
			fila = 0;
		else {
			if(c.getFila() < 6)
				fila = 3;
			else
				fila = 6;
		}
		if(c.getColumna() < 3)
			col = 0;
		else {
			if(c.getColumna() < 6)
				col=3;
			else
				col = 6;
		}
		for(int i = fila;i < fila + 3;i++)
			for(int j = col;j < col + 3;j++) {
				if(tablero[i][j].getValor() != null) {
					if(c.getValor() == tablero[i][j].getValor() &&!(tablero[i][j].equals(c))){
						tablero[i][j].setVerifica(false);
						verifica = false;
					}
				}
			}
		return verifica;
	}
	
	/**
	 * Verifica si se cumplen las condiciones de fila, columna y cuadrante.
	 * @param c celda que se debe verificar las condiciones de fila, columna y cuadrante.
	 * @return true si se verifica, de lo contrario false.
	 */

	public boolean verifica(Celda c) {
		boolean f; 
		boolean col;
		boolean cua;
		pintarInvalidas(); 
		f = verificaFila(c);
		col = verificaColumna(c);
		cua = verificaCuadrante(c);
		pintarValidas();
		return f && col && cua;
	}
	
	/**
	 * Cambia el color de la celda a rojo si no se cumple alguna de las condiciones.
	 */

	private void pintarInvalidas() { 
		for(int i = 0; i < cantFilas; i++) 
			for(int j = 0; j < cantFilas; j++) { 
				if(!tablero[i][j].getSeVerifica())
					tablero[i][j].setBackground(Color.red);
			}
	} 
	
	/**
	 * Verifica todas las condiciones de  la matriz de celdas.
	 */

	public void reiniciar() { 
		for(int i = 0; i < cantFilas; i++) 
			for(int j = 0; j < cantFilas; j++) { 
				if(verifica(tablero[i][j]))
					tablero[i][j].setVerifica(true);
			}
	}
	
	/**
	 * Verifica las condiciones de fila, columna y cuadrante y si se cumplen las tres condiciones pinta la celda de color negro.
	 */

	public void pintarValidas() { 
		for(int i = 0; i < cantFilas; i++) 
			for(int j = 0; j < cantFilas; j++) { 
				if(tablero[i][j].getSeVerifica())
					tablero[i][j].setBackground(Color.black);
			}
	}
}
