package com.algoritmoaestrella.Grafo;

/**
 *
 * @author poncho
 */
public class NodoAStar<T> {
    
    private int valorH = Integer.MAX_VALUE;
    private int valorG = 0;
    private int valorF = 0;
    private NodoAStar<T> padre;
    private T estado;

    public NodoAStar(T estado) {
        this.padre = null;
        this.estado = estado;
    }
    
    public int getValorH() {
        return valorH;
    }
    
    public void setValorH(int valorH) {
        this.valorH = valorH;
    }
    
    public int getValorG() {
        return valorG;
    }
    
    public void setValorG(int valorG) {
        this.valorG = valorG;
    }
    
    public int getValorF() {
        return valorF;
    }
    
    public void setValorF(int valorF) {
        this.valorF = valorF;
    }
    
    public NodoAStar<T> getPadre() {
        return padre;
    }
    
    public void setPadre(NodoAStar<T> padre) {
        this.padre = padre;
    }
    
    public T getEstado() {
        return estado;
    }
    
    public void setEstado(T estado) {
        this.estado = estado;
    }
    
    public boolean equals(Object nodo) {
        return this.estado.equals(nodo);
    }
}
