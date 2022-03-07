package test;

import com.algoritmogreedy.Grafo.Arista;
import com.algoritmogreedy.Grafo.Grafo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author poncho
 */
public class TestAlgoritmoGreedy {

    private static int N = 20; // numero de ciudades de rumania

    public static void main(String[] args) throws FileNotFoundException {

        Grafo g = null;

        g = generarGrafoRumania();

        System.out.println(g + "\n");
        HashMap<String, Integer> heuristicas = generarHeuristicasParaBucharest();
        ArrayList<String> listaV = new ArrayList<>();
        listaV.add("Arad");
        listaV.add("Bucharest");
        listaV.add("Craiova");
        listaV.add("Dobreta");
        listaV.add("Eforie");
        listaV.add("Fagaras");
        listaV.add("Giurgiu");
        listaV.add("Hirsova");
        listaV.add("Iasi");
        listaV.add("Lugoj");
        listaV.add("Mehadia");
        listaV.add("Neamt");
        listaV.add("Oradea");
        listaV.add("Pitesti");
        listaV.add("Rimnicu Vilcea");
        listaV.add("Sibiu");
        listaV.add("Timisoara");
        listaV.add("Urziceni");
        listaV.add("Vaslui");
        listaV.add("Zerind");
        for (String ciudad : listaV) {
            algoritmoGreedyBucharest(g, ciudad, heuristicas);
        }

    }

    private static Grafo generarGrafoRumania() throws FileNotFoundException {
        Grafo g = new Grafo(false);
        ArrayList<String> listaV = new ArrayList<>();
        listaV.add("Arad");
        listaV.add("Bucharest");
        listaV.add("Craiova");
        listaV.add("Dobreta");
        listaV.add("Eforie");
        listaV.add("Fagaras");
        listaV.add("Giurgiu");
        listaV.add("Hirsova");
        listaV.add("Iasi");
        listaV.add("Lugoj");
        listaV.add("Mehadia");
        listaV.add("Neamt");
        listaV.add("Oradea");
        listaV.add("Pitesti");
        listaV.add("Rimnicu Vilcea");
        listaV.add("Sibiu");
        listaV.add("Timisoara");
        listaV.add("Urziceni");
        listaV.add("Vaslui");
        listaV.add("Zerind");
        g.add(listaV);
        g.addArista("Arad", "Sibiu", 140);
        g.addArista("Arad", "Timisoara", 118);
        g.addArista("Arad", "Zerind", 75);

        g.addArista("Bucharest", "Fagaras", 221);
        g.addArista("Bucharest", "Giurgiu", 90);
        g.addArista("Bucharest", "Pitesti", 101);
        g.addArista("Bucharest", "Urziceni", 85);

        g.addArista("Craiova", "Dobreta", 120);
        g.addArista("Craiova", "Pitesti", 138);
        g.addArista("Craiova", "Rimnicu Vilcea", 146);

        g.addArista("Dobreta", "Mehadia", 75);

        g.addArista("Eforie", "Hirsova", 86);

        g.addArista("Fagaras", "Sibiu", 99);

        g.addArista("Hirsova", "Urziceni", 98);

        g.addArista("Iasi", "Neamt", 87);
        g.addArista("Iasi", "Vaslui", 92);

        g.addArista("Lugoj", "Mehadia", 70);
        g.addArista("Lugoj", "Timisoara", 111);

        g.addArista("Oradea", "Sibiu", 151);
        g.addArista("Oradea", "Zerind", 71);

        g.addArista("Pitesti", "Rimnicu Vilcea", 97);

        g.addArista("Rimnicu Vilcea", "Sibiu", 80);

        g.addArista("Urziceni", "Vaslui", 142);

        return g;

    }

    private static HashMap<String, Integer> generarHeuristicasParaBucharest() {
        HashMap<String, Integer> heuristicas = new HashMap<>();
        heuristicas.put("Arad", 366);
        heuristicas.put("Bucharest", 0);
        heuristicas.put("Craiova", 160);
        heuristicas.put("Dobreta", 242);
        heuristicas.put("Eforie", 161);
        heuristicas.put("Fagaras", 178);
        heuristicas.put("Giurgiu", 77);
        heuristicas.put("Hirsova", 151);
        heuristicas.put("Iasi", 226);
        heuristicas.put("Lugoj", 244);
        heuristicas.put("Mehadia", 241);
        heuristicas.put("Neamt", 234);
        heuristicas.put("Oradea", 380);
        heuristicas.put("Pitesti", 98);
        heuristicas.put("Rimnicu Vilcea", 193);
        heuristicas.put("Sibiu", 253);
        heuristicas.put("Timisoara", 329);
        heuristicas.put("Urziceni", 80);
        heuristicas.put("Vaslui", 199);
        heuristicas.put("Zerind", 374);

        return heuristicas;
    }

    public static void algoritmoGreedyBucharest(Grafo g, String origen, HashMap<String, Integer> heuristicas) {
        String fin = "Bucharest";
        List<String> listaVisitados = new ArrayList<>();
        listaVisitados.add(origen);
        String actual = origen;
        System.out.println("Recorrido desde: "+actual);
        while (true) {
            System.out.print(actual + " -> ");
            ArrayList<String> vecinos = g.getVerticesAdyacentes(actual);
            String min = vecinos.get(0);
            for (int i = 0; i < vecinos.size(); i++) {
                if (heuristicas.get(vecinos.get(i)) < heuristicas.get(min)) {
                    min = vecinos.get(i);
                }
            }
            if (listaVisitados.contains(min)) {
                System.out.println("\nERROR, hubo un bucle :c\n");
                return;
            }
            if (actual.equals(fin)) {
                System.out.println("\nSe ha llegado a Bucharest c:\n");
                return;
            }
            actual = min;
            listaVisitados.add(actual);
        }
    }
}
