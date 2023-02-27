/**
   A class that implements a queue of objects by using an array.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class ArrayQueue<T> implements QueueInterface<T>
{
   private T[] queue; // Circular array of queue entries and one unused element
   private int frontIndex;
   private int backIndex;
   private boolean integrityOK;
   private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
   
   public ArrayQueue()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
   
   public ArrayQueue(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempQueue = (T[]) new Object[initialCapacity + 1];
      queue = tempQueue;
      frontIndex = 0;
      backIndex = initialCapacity;
      integrityOK = true;
   } // end constructor

   public void enqueue(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      backIndex = (backIndex + 1) % queue.length;
      queue[backIndex] = newEntry;
   } // end enqueue

   public T dequeue()
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyQueueException();
      else
      {
         T front = queue[frontIndex];
         queue[frontIndex] = null;
         frontIndex = (frontIndex + 1) % queue.length;
         return front;
      } // end if
   } // end dequeue

   public T getFront()
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyQueueException();
      else
         return queue[frontIndex];
   } // end getFront

   public boolean isEmpty()
   {
      checkIntegrity();
      return frontIndex == ((backIndex + 1) % queue.length);
   } // end isEmpty

   public void clear()
   {
      checkIntegrity();
      if(!isEmpty())
      { //Deallocates only the used portion
         for(int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
         {
            queue[index] = null;
         }//end for

         queue[backIndex] = null;
      }//end if

      frontIndex = 0;
      backIndex = queue.length - 1;

   }//end clear

   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   } // end checkIntegrity

   private void ensureCapacity()
   {
      if (frontIndex == ((backIndex + 2) % queue.length)) // If array is full,
      {                                                   // double size of array
         T[] oldQueue = queue;
         int oldSize = oldQueue.length;
         int newSize = 2 * oldSize;
         checkCapacity(newSize - 1);
         integrityOK = false;

         // The cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] tempQueue = (T[]) new Object[newSize];
         queue = tempQueue;
         for (int index = 0; index < oldSize - 1; index++)
         {
            queue[index] = oldQueue[frontIndex];
            frontIndex = (frontIndex + 1) % oldSize;
         } // end for
         
         frontIndex = 0;
         backIndex = oldSize - 2;
         integrityOK = true;
      } // end if
   } // end ensureCapacity

   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a queue whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   } // end checkCapacity


} // end ArrayQueue
