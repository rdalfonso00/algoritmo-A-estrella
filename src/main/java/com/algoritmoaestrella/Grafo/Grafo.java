package com.algoritmoaestrella.Grafo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Estructura de datos simple que representa un grafo dirigido
 *
 * @author poncho
 */
public class Grafo<T> { //T = tipo de las aristas

    private HashMap<T, ArrayList<Arista<T>>> listaAdyacencia;
    private boolean esDirigido;

    private ArrayList<T> listaVertices;

    public Grafo(boolean esDirigido) {
        this.esDirigido = esDirigido;
        listaAdyacencia = new HashMap<T, ArrayList<Arista<T>>>();
        listaVertices = new ArrayList<T>();
    }

    /**
     * Agrega un nuevo vertice de tipo T al grafo
     *
     * @param vertice
     * @param verticesConectados
     */
    public void add(T vertice, ArrayList<Arista<T>> verticesConectados) {
        if (verticesConectados.isEmpty()) // si la lista de vertices está vacía, termina
        {
            return;
        }
        // Aniade el nueuvo vertice a la listaAdyacencia con la que se 
        // conectan sus nodos
        listaAdyacencia.put(vertice, verticesConectados);
        listaVertices.add(vertice);

        for (Arista<T> verticeConectadoAlVertAgregado : verticesConectados) {
            ArrayList<Arista<T>> listaConectada = listaAdyacencia
                .get(verticeConectadoAlVertAgregado);
            // Las conexiones del vertice pueden no estar representadas 
            //en el grafo aun, asi que se aniaden implicitamente
            if (listaConectada == null) {
                listaAdyacencia.put(verticeConectadoAlVertAgregado.getVertice(),
                    new ArrayList<Arista<T>>());
                listaVertices.add(verticeConectadoAlVertAgregado.getVertice());
                listaConectada = listaAdyacencia
                    .get(verticeConectadoAlVertAgregado);
            }/*
            if (!esDirigido) {
                // The weight from one vertex back to another in an undirected
                // graph is equal
                int peso = verticeConectadoAlVertAgregado.getPeso();
                listaConectada.add(new Arista<T>(vertice,peso));
            }*/
        }
    }

    public void add(ArrayList<T> listaV) {
        for (T t : listaV) {
            ArrayList<Arista<T>> tempList = new ArrayList<>();
            listaVertices.add(t);
            listaAdyacencia.put(t, tempList);
        }

    }

    public boolean addArco(T v1, T v2, int peso) {
        if (!esDirigido) {
            return false;
        }
        if (!listaAdyacencia.containsKey(v1)) {
            ArrayList<Arista<T>> tempList = new ArrayList<>();
            tempList.add(new Arista<T>(v2, peso));
            add(v1, tempList);
            return true;
        }
        if (!listaAdyacencia.containsKey(v2)) {
            ArrayList<Arista<T>> tempList = new ArrayList<>();
            add(v2, tempList);
        }
        listaAdyacencia.get(v1).add(new Arista<T>(v2, peso));
        return true;
    }

    public boolean addArista(T v1, T v2, int peso) {
        if (esDirigido) {
            return false;
        }
        if (!listaAdyacencia.containsKey(v1)) {
            ArrayList<Arista<T>> tempList = new ArrayList<>();
            tempList.add(new Arista<T>(v2, peso));
            add(v1, tempList);
            return true;
        }

        if (!listaAdyacencia.containsKey(v2)) {
            ArrayList<Arista<T>> tempList = new ArrayList<>();
            tempList.add(new Arista<T>(v1, peso));
            add(v2, tempList);
            return true;
        }

        listaAdyacencia.get(v1).add(new Arista<T>(v2, peso));
        listaAdyacencia.get(v2).add(new Arista<T>(v1, peso));
        return true;
    }

    public ArrayList<T> getVerticesAdyacentes(T vertice) {
        ArrayList<T> listaRetorno = new ArrayList<>();
        for (Arista<T> arista : listaAdyacencia.get(vertice)) {
            listaRetorno.add(arista.getVertice());
        }
        return listaRetorno;
    }

    public int getDistanciaEntre(T origen, T destino) {
        for (Arista<T> arista : listaAdyacencia.get(origen)) {
            if (arista.getVertice() == destino) {
                return arista.getPeso();
            }
        }
        return Integer.MAX_VALUE; // (no existe conexion)
    }

    public ArrayList<T> getVertexList() {
        return listaVertices;
    }

    public boolean existeEnGrafo(T vertice) {
        if (vertice == null) {
            return false;
        }
        for (T temp : listaVertices) {
            if (temp.equals(vertice)) {
                return true;
            }
        }
        return false;
    }

    public int buscarIndice(T vertice) {
        if (vertice == null) {
            return -1;
        }

        for (int i = 0; i < listaVertices.size(); i++) {
            if (listaVertices.get(i).equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        String s = "";
        for (T vertice : listaVertices) {
            s += vertice;
            s += " : ";
            s += listaAdyacencia.get(vertice);
            s += "\n";
        }
        return s;
    }

}
