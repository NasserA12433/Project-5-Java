import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
public class Driver
{
   public static Graph<String> buildGraph()
    {
    Graph<String> graph = new Graph<String>(9);
		
		graph.setLabel(0, "A");
		graph.setLabel(1, "B");
		graph.setLabel(2, "C");
		graph.setLabel(3, "D");
		graph.setLabel(4, "E");
		graph.setLabel(5, "F");
		graph.setLabel(6, "G");
		graph.setLabel(7, "H");
		graph.setLabel(8, "I");

		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(1, 4);
		graph.addEdge(2, 1);
		graph.addEdge(3, 6);
		graph.addEdge(4, 7);
		graph.addEdge(4, 5);
		graph.addEdge(5, 2);
		graph.addEdge(5, 7);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		graph.addEdge(8, 5);
		
		return graph;
    }
        public static void main(String[] args) throws Exception
   {
      Graph<String> graph1 = new Graph<String>(9);
      String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
      for(int i = 0; i < alpha.length; i++)
         graph1.setLabel(i, alpha[i]);
      graph1.addEdge(0, 1);
      graph1.addEdge(0, 3);
      graph1.addEdge(0, 4);
      graph1.addEdge(1, 4);
      graph1.addEdge(2, 1);
      graph1.addEdge(3, 6);
      graph1.addEdge(4, 5);
      graph1.addEdge(4, 7);
      graph1.addEdge(5, 2);
      graph1.addEdge(5, 7);
      graph1.addEdge(6, 7);
      graph1.addEdge(7, 8); 
      graph1.addEdge(8, 5);
      
      QueueInterface<String> test;
      test = graph1.BFSTraversal(0);
      while (!test.isEmpty())
      {
         System.out.print(test.dequeue());
      }
       System.out.println();
      Graph<String> graph = buildGraph();
	    String origin = new String("A");
       QueueInterface<String> dft = graph.getDepthFirstTraversal(origin);
		 System.out.println(dft);
    }
     
       
}