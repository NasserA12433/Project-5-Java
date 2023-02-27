
public interface graphInterface {
  /** Adds a given vertex to this graph.
       @param vertexLabel  An object that labels the new vertex and is
                           distinct from the labels of current vertices.
       @return  True if the vertex is added, or false if not. */
       public boolean addVertex(String vertexLabel);
    
       /** Adds an unweighted edge between two given distinct vertices 
           that are currently in this graph. The desired edge must not
           already be in the graph. In a directed graph, the edge points 
           toward the second vertex given.
           @param begin  An object that labels the origin vertex of the edge.
           @param end    An object, distinct from begin, that labels the end
                         vertex of the edge.
           @return  True if the edge is added, or false if not. */
       public boolean addEdge(String begin, String end);
    
       /** Sees whether an edge exists between two given vertices.
           @param begin  An object that labels the origin vertex of the edge.
           @param end    An object that labels the end vertex of the edge.
           @return  True if an edge exists. */
       public boolean hasEdge(String begin, String end);
    
}