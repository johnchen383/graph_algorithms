package MST;

import java.util.ArrayList;
import java.util.List;

import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;
import utils.GraphUtil;

public class Prim {
    public static void run(Graph graph, Node s){
        run(graph, s, false);
    }

    public static void run(Graph graph, Node s, boolean showIterations){
        graph.sortWithWeight();
        List<Node> S = new ArrayList<Node>();
        List<Arc> E = new ArrayList<Arc>();
        S.add(s);

        int iterationNum = 0;

        if (showIterations) {
            System.out.println("Iteration " + iterationNum);
            GraphUtil.printSetOfArcs(E);
        }

        while (S.size() < graph.getOrder()){
            Arc arc = getMinimumArc(graph, S);
            
            if (arc == null){
                System.out.println("Some failure :(");
                break;
            }

            S.add(arc.getTail());
            E.add(arc);

            if (showIterations) {
                iterationNum++;
                System.out.println("Iteration " + iterationNum);
                GraphUtil.printSetOfArcs(E);
            }
        }

        if (!showIterations) GraphUtil.printSetOfArcs(E);

        GraphUtil.printMSTWeight(E);
    }

    public static void runPrimsDiagram(){
        Graph graph = new Graph();
        graph.addEdge('a', 'b', 1);
        graph.addEdge('a', 'd', 8);
        graph.addEdge('a', 'h', 2);
        graph.addEdge('d', 'h', 5);
        graph.addEdge('d', 'b', 10);
        graph.addEdge('h', 'i', 9);
        graph.addEdge('d', 'i', 1);
        graph.addEdge('d', 'f', 8);
        graph.addEdge('f', 'b', 5);
        graph.addEdge('i', 'f', 6);
        graph.addEdge('c', 'b', 13);
        graph.addEdge('c', 'f', 7);
        graph.addEdge('g', 'c', 4);
        graph.addEdge('f', 'g', 13);
        graph.addEdge('g', 'j', 2);
        graph.addEdge('f', 'j', 15);
        graph.addEdge('i', 'j', 12);

        Prim.run(graph, new Node("a", 1), true);
    }

    public static void runExample33_3(){
        Graph graph = new Graph();
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(4, 2, 6);
        graph.addEdge(1, 4, 2);
        graph.addEdge(4, 3, 1);

        Prim.run(graph, graph.getNode(String.valueOf(0)), true);
    }

    private static Arc getMinimumArc(Graph graph, List<Node> S){
        for (Arc arc : graph.getArcSet()){
            if (S.contains(arc.getHead()) && (!S.contains(arc.getTail()))){
                return arc;
            }
        }

        return null;
    }

}
