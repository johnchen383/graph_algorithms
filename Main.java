import APSP.DikstraAPSP;
import APSP.Floyd;
import MST.Kruskal;
import MST.Prim;
import SSSP.BellmanFord;
import SSSP.Dijkstra;
import Traversal.BFS;
import Traversal.DFS;
import Traversal.DFSRecursive;
import Traversal.General;
import models.Arc;
import models.Graph;
import models.Node;

@SuppressWarnings("unused")

public class Main{
    private static Graph graph;

    public static void main(String[] args) {
        // BFS.runExampleMultiForest();
        // DFSRecursive.runExampleMultiForest();
        // DFS.runExampleMultiForest();
        Dijkstra.runExample29_10();
        // BellmanFord.runT11();
        // Prim.runPrimsDiagram();
        // Kruskal.runKruskalDiagram();
        // DikstraAPSP.runExample29_11();
        // constructGraph();
        // Floyd.runExampleTutorial();
        // graph.printGraph();
        // graph.getNodeSetWithPriority();
        // printAdjacencyList();
        // General.runT7Example();
    }

    private static void constructGraph(){
        graph = new Graph();
        graph.addArc(2, 4, 6);
        graph.addArc(0, 1, 3);
        graph.addArc(0, 2, 1);
        graph.addArc(0, 3, 5);
        graph.addArc(2, 1, 1);
        graph.addArc(1, 4, 2);
        graph.addArc(3, 4, 1);
    }

    private static void printAdjacencyMatrix(){
        graph.sortWithPriority();
        for (Node u : graph.getNodeSet()){
            for (Node v : graph.getNodeSet()){
                Arc arc = graph.getArc(u, v);
                if (arc == null){
                    System.out.print("0 \t");
                } else{
                    System.out.print(Integer.toString(arc.getWeight()) + "\t");
                }
            }
            System.out.println();
        }
    }

    private static void printAdjacencyList(){
        graph.sortWithPriority();
        for (Node u : graph.getNodeSet()){
            System.out.print( u + " | ");
            for (Node v : graph.getNodeSet()){
                Arc arc = graph.getArc(u, v);
                if (arc != null){
                    System.out.print(v+ ", ");
                    System.out.print(arc.getWeight()+ ", ");        //comment out if normal adjacency list
                }
            }
            System.out.println();
        }
    }
}