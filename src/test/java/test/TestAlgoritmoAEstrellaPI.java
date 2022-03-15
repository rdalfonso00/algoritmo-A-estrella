package test;

import com.algoritmoaestrella.Grafo.Arista;
import com.algoritmoaestrella.Grafo.Grafo;
import com.algoritmoaestrella.Grafo.NodoAStar;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.util.Pair;

/**
 *
 * @author poncho
 */
public class TestAlgoritmoAEstrellaPI {

    private static int N = 20; // numero de ciudades de rumania
    static HashMap<NodoAStar, Integer> heuristicas = generarHeuristicas();
    static NodoAStar fin = new NodoAStar("Origen");

    public static void main(String[] args) throws FileNotFoundException {

        Grafo g = null;

        g = generarGrafoPrueba();

        System.out.println(g + "\n///////////////////////////////////");
        algoritmoAEstrella(g, heuristicas);

    }

    private static Grafo generarGrafoPrueba() {
        Grafo g = new Grafo(false);
        ArrayList<String> listaV = new ArrayList<>();
        listaV.add("Origen");
        listaV.add("A");
        listaV.add("B");
        listaV.add("C");
        listaV.add("D");
        listaV.add("E");
        listaV.add("F");
        listaV.add("G");
        listaV.add("H");
        listaV.add("I");
        listaV.add("J");
        listaV.add("K");
        listaV.add("L");
        listaV.add("Destino");
        g.add(listaV);

        g.addArista("Origen", "A", 1);
        g.addArista("Origen", "B", 1);
        g.addArista("Origen", "C", 1);
        g.addArista("A", "D", 1);
        g.addArista("A", "E", 1);
        g.addArista("A", "F", 3);
        g.addArista("E", "Destino", 3);
        g.addArista("B", "G", 4);
        g.addArista("B", "H", 1);
        g.addArista("B", "I", 2);
        g.addArista("C", "L", 1);
        g.addArista("C", "J", 1);
        g.addArista("C", "K", 1);
        g.addArista("G", "Destino", 3);
        g.addArista("I", "Destino", 3);
        g.addArista("K", "Destino", 2);

        return g;

    }

    private static HashMap<NodoAStar, Integer> generarHeuristicas() {
        HashMap<String, Integer> heuristicas = new HashMap<>();
        heuristicas.put("-", 0);
        heuristicas.put("Origen", 0);
        heuristicas.put("A", 3);
        heuristicas.put("B", 2);
        heuristicas.put("C", 3);
        heuristicas.put("D", 3);
        heuristicas.put("E", 1);
        heuristicas.put("F", 3);
        heuristicas.put("G", 2);
        heuristicas.put("H", 1);
        heuristicas.put("I", 2);
        heuristicas.put("J", 3);
        heuristicas.put("K", 2);
        heuristicas.put("L", 3);
        heuristicas.put("Destino", 2);
        return heuristicas;
    }

    /**
     * Comienza la búsqueda IDA*. Retornará null si no se encontró el estado
     * objetivo. Retorna NodoAStar si el último nodo del camino fue encontrado
     * en un camino óptiimo. El camino puede ser recuperado al recorrer el
     * camino por medio de los nodos padres de cada nodo hasta encontrar null en
     * el nodo raíz (el padre de todos)
     *
     * @return null si el camino no existe, de otra forma, el último nodo del
     * camino óptimo
     */
    public static NodoAStar algoritmoAEstrellaPI(Grafo g, NodoAStar origen) {
        // obtener el costo de camino F inicial (0)
        int cota_f = origen.getValorF();
        // marcar a la raíz como el inicio del camino
        ArrayList<NodoAStar> camino = new ArrayList<>();
        camino.add(0, origen);
        // se busca una nueva cota_f hasta que ocurra uno de los 2 siguientes escenarios:
        // retorna 0                    - el nodo objetivo se encontró en un camino óptimo
        // retorna Integer.MAX_VALUE    - no fue encontrado un nodo que cumpla con la condición de cota máxima
        int nueva_cota_f;
        do {
            nueva_cota_f = busquedaLimitada(g, camino, 0, cota_f);
            if (nueva_cota_f == 0) {
                return camino.get(camino.size() - 1);
            }
            // se asigna una nueva_cota_f como la cota actual            
            cota_f = nueva_cota_f;
        } while (cota_f != Integer.MAX_VALUE);
        return null;
    }

    public static int busquedaLimitada(Grafo g, ArrayList<NodoAStar> camino, int costoCamino, int cota_f) {
        NodoAStar actual = camino.get(camino.size() - 1);
        // calcular valores del nodo actual H, G y F
        actual.setValorH(heuristicas.get(actual.getEstado()));
        actual.setValorG(costoCamino);
        actual.setValorF(costoCamino + actual.getValorH());

        // nueva cota_f
        if (actual.getValorF() > cota_f) {
            return actual.getValorF();
        }
        // se encontró el fin -> caso base de la recursión
        if (actual.equals(fin)) {
            return 0;
        }
        int min_cota_f = Integer.MAX_VALUE;
        // explorar y buscar caminos inválidos
        List<NodoAStar> vecinos = g.getVerticesAdyacentes(actual);
        for (NodoAStar vecino : vecinos) {
            if (!camino.contains(vecino)) {
                // si el camino no contiene al vértice vecino, se exploran los caminos de este nodo
                camino.add(vecino);
                int min_cota_f_limite = busquedaLimitada(g, camino,
                    actual.getValorG() + g.getDistanciaEntre(actual, vecino), cota_f);
                if (min_cota_f_limite == 0) { // es nodo objetivo
                    return 0;
                }
                // llevar registro de la nueva cota minima y eliminar el hijo actual antes de explorar el siguiente
                if (min_cota_f_limite < min_cota_f) {
                    min_cota_f = min_cota_f_limite;
                }
                camino.remove(camino.size() - 1);
            }
        }
        return min_cota_f;
    }

}
