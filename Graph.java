public class Graph<T>
{
    private boolean[][] graphEdges; 
    private T[] graphLabels; 
    private boolean[] visitedNodes;
//This initializes a graph by itself without any values in order to have the graph made for input by the driver.
// A constructor method for the graph 
    public Graph(int n)
    {
        graphLabels = (T[]) new Object[n]; 
        visitedNodes= new boolean[graphLabels.length];
        graphEdges = new boolean[n][n];
        //since the value of the array is starting at 0 the lenght must also take into account the 0 value.
        for (int i=0;i<=graphLabels.length-1;i++)
        //checks to see if the node itself is visited and marks it false because all these node values are null/not true 
			visitedNodes[i]=false;
    }
/*
 * 
 * 
 * These methods all declare the labels and edges, allowing us to add whatever node we chose to
 * The neighbor method works to show the neighbor and what is connected to what
 * these connections and labels all are defined in the driver and allow the user or coder to alter the code without coming here
 * 
 * 
 * 
 */
 // Allows us to set the vertex instead of having just 1` static one that we cannot manipulate and search through after we do it
    public void setLabel(int vertex, String newLabel)
    {
       graphLabels[vertex] = (T) newLabel;
    }  
//lets us see what is the vertex at the current position of the code and loop
    public T getLabel(int vertex)
    {
        System.out.println(graphLabels[vertex]);
        return graphLabels[vertex];
    }
   
 // establishes the method to add a link between nodes/vertexes in the driver file.
    public void addEdge(int source, int target)
    {
        graphEdges[source][target] = true;
    }
//returns a boolean value on weather or not we established a link between the vertex and their neighbor in the driver file.
    public boolean isEdge(int source, int target)
    {
        return graphEdges[source][target];
    }
    /*
    * This method is allowing the code to view the neighbors of the vertex that we assigned
    * gives us the direct link from the vertex to whatever we choose to connect it to
    */
    public int[] neighborNode(int vertex)
    {
        int i;
        int[] answer;
        int count = 0;
        

        for(i = 0; i <graphLabels.length; i++)
        {
            if(graphEdges[vertex][i])
                count++;
        }
        answer = new int[count];
        count = 0;
        for(i = 0; i <graphLabels.length; i++)
        {
            if(graphEdges[vertex][i])
                answer[count++] = i;
        }

        return answer;
    }
//-----------------------------------------------------------------------------------------------------------------//
/*
  

*/

private int getVertIndex (T vertex)
	{
		int vertIndex = 0;
		while (!vertex.equals(graphLabels[vertIndex]))
		{
			vertIndex++;
		}
		return vertIndex;
	}
    boolean[] markedVertex;
    private void markedVertex (T vertex)
	{
		int vertexIndex = getVertIndex(vertex);
		
        markedVertex[vertexIndex] = true;
	}
	
	private boolean unvisitedVertex (T vertex)
	{
		int vertexIndex = getVertIndex(vertex);
		
		int[] neighbors = neighborNode(vertexIndex);
		boolean unvisitedVertex = false;
		for (int nextNeighbor = 0; nextNeighbor <= neighbors.length - 1; nextNeighbor++)
		{
			if (!visitedNodes[neighbors[nextNeighbor]])
			{
				unvisitedVertex = true;
				nextNeighbor = neighbors.length;
			}
		}
		return unvisitedVertex;
	}
	
	private T getNextUnvisitedNeighbor(T vertex)
	{
		int vertexIndex = getVertIndex(vertex);
		
		int[] neighbors = neighborNode(vertexIndex);
		T nextUnvisitedNeighbor = graphLabels[0];
		for (int nextNeighbor = 0; nextNeighbor <= neighbors.length - 1; nextNeighbor++)
		{
			if (!visitedNodes[neighbors[nextNeighbor]])
			{
				nextUnvisitedNeighbor = graphLabels[neighbors[nextNeighbor]];
				nextNeighbor = neighbors.length;
			}
		}
		return nextUnvisitedNeighbor;
	}

    //--------------------------------------------------------------------------------------------------------------------//
    public QueueInterface<T> BFSTraversal (int origin)
    { 
        //we established 2 queues to store what we passed by and what we visited
        // also established a list array that will tell us if we have visted the vertex's neighbors before
        boolean[] visitedNodes = new boolean[(graphLabels.length)];
        QueueInterface<T> vertQueue = new ArrayQueue<>(); 
        QueueInterface<T> travQueue = new ArrayQueue<>();
       
        visitedNodes[0]= true; //this is the vertex
        // by setting the vertex to visited, we dont have to mark it visited again
        //now we add the vertex to the vertexqueue and the traversal queue 
        T firstRoot; 
        int assignedVertexNumber= origin;
        T nextNeighbor;
        int[] neighbors;
        while(!vertQueue.isEmpty()) //keep going through vertex Queue
        {
            firstRoot = vertQueue.dequeue();
            for(int i = 0; i < graphLabels.length; i++) //double checks and ensures position of the front Vertex Number
                if(graphLabels[i] == firstRoot)
                    origin = i;
            if(neighborNode(origin).length != 0 ) //part one of frontVertex has Neighbor
            {
                neighbors = neighborNode(origin);
                for(int i = 0; i < neighbors.length; i++)//part two
                {
                    nextNeighbor =graphLabels[neighbors[i]];                    
                    if(!visitedNodes[neighbors[i]]) // assigns neighbor to travaersal order and vertex queue
                    {
                        visitedNodes[neighbors[i]] = true;
                        travQueue.enqueue(nextNeighbor);
                        vertQueue.enqueue(nextNeighbor);
                    }
                }
            }
        }
        return travQueue;
    }
 public QueueInterface<T> getDepthFirstTraversal(T origin)
	{
		QueueInterface<T> traversalOrder = new ArrayQueue<T>();
		StackInterface<T> vertexStack = new LinkedStack<T>();
		
		visitedNodes[0] = true;
		traversalOrder.enqueue(graphLabels[getVertIndex(origin)]);
		vertexStack.push(graphLabels[getVertIndex(origin)]);
		while (!vertexStack.isEmpty())
		{
			T topVertex = vertexStack.peek();
			if (getNextUnvisitedNeighbor(topVertex) != null)
			{
				T nextNeighbor = getNextUnvisitedNeighbor(topVertex);
				markedVertex(nextNeighbor);
				traversalOrder.enqueue(nextNeighbor);
				vertexStack.push(nextNeighbor);
			}
			else
			{
				vertexStack.pop();
			}
		}
		
		return traversalOrder;
	}
     

}
