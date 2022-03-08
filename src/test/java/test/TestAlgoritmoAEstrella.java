package test;

import com.algoritmoaestrella.Grafo.Arista;
import com.algoritmoaestrella.Grafo.Grafo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author poncho
 */
public class TestAlgoritmoAEstrella {

    private static int N = 20; // numero de ciudades de rumania

    public static void main(String[] args) throws FileNotFoundException {

        Grafo g = null;

        g = generarGrafoPrueba();

        System.out.println(g + "\n///////////////////////////////////");
        HashMap<String, Integer> heuristicas = generarHeuristicas();
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

    private static HashMap<String, Integer> generarHeuristicas() {
        HashMap<String, Integer> heuristicas = new HashMap<>();
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

    public static void algoritmoAEstrella(Grafo g, String origen, String fin, HashMap<String, Integer> heuristicas) {
        ArrayList<Pair<String, String>> listaVisitados = new ArrayList<>();
        Comparator<Pair<String, Integer>> comparadorNodos
            = (Pair<String, Integer> s1, Pair<String, Integer> s2) -> s1.getValue() - s2.getValue();
        PriorityQueue<Pair<String, Integer>> listaPorExplorar = new PriorityQueue<>(comparadorNodos);

        listaVisitados.add(new Pair<>(origen, "-"));
        Pair<String, Integer> actual = new Pair<>(origen, 0);
        System.out.println("Recorrido desde: " + actual);
        while (true) {
            System.out.print(actual + " -> ");
            ArrayList<String> vecinos = g.getVerticesAdyacentes(actual.getKey());
            //costoRutaActual += heuristicas.get(actual);
            for (String vecino : vecinos) {
                if (!existeVisitado(listaVisitados, vecino)) {
                    listaPorExplorar.add(new Pair<>(vecino, actual.getValue() + heuristicas.get(vecino)));
                }
            }

            Pair<String, Integer> min = listaPorExplorar.poll();
            if (actual.getKey().equals(fin)) {
                for (int i = listaVisitados.size()-1; i >=0 && listaVisitados.get(i).ge; i--) {
                    
                }
                System.out.println("\nFIN DEL RECORRIDO CON EXITO\n");
                break;
            }
            listaVisitados.add(new Pair<>(min.getKey(), actual.getKey()));
            actual = min;
        }

    }

    public static void algoritmoAEstrella(Grafo g, HashMap<String, Integer> heuristicas) {
        algoritmoAEstrella(g, "Origen", "Destino", heuristicas);
    }

    private static boolean existeVisitado(List<Pair<String, String>> listaVisitados, String vecino) {
        for (Pair<String, String> e : listaVisitados) {
            if (vecino.equals(e.getKey())) {
                return true;
            }
        }
        return false;
    }

}
