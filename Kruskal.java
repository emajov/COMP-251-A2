import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

    	WGraph mst = new WGraph();
    	// list edges by weight
        ArrayList<Edge> edges = g.listOfEdgesSorted();
        int nodes = g.getNbNodes();
        // make separate disjoint sets for each node
        DisjointSets sets = new DisjointSets(nodes);
        for (Edge edge : edges) {
        	if (IsSafe(sets, edge)) {
        		mst.addEdge(edge);
        		sets.union(edge.nodes[0], edge.nodes[1]);
        		if (mst.getNbNodes() == g.getNbNodes()) {
        			break;
        		}
        	}
        }  
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
    	
    	if (p.find(e.nodes[0]) != p.find(e.nodes[1])) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
