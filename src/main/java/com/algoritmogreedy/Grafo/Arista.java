package com.algoritmogreedy.Grafo;

/**
 *
 * @author poncho
 */
public class Arista<T> {
    private T vertice;
    private int peso;
//en caso de haber peso, se usaría 
    public Arista(T vertice, int peso){
        this.vertice = vertice;
        this.peso = peso;
    }

    public T getVertice() {
        return vertice;
    }

    public void setVertice(T vertice) {
        this.vertice = vertice;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString(){
            return "( "+ vertice + ", " + peso + " )";
    }
}
