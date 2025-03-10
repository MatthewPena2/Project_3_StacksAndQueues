package Queue;

//import QueuePackage interfaces and resources
import QueuePackage.*;
import java.util.*;

//start of class
public class SimulationEventQueue implements SimulationEventQueueInterface {

    private Vector <SimulationEvent> queue;
    private double simulationTime;

    //constructor - will be used later in BankLine
    public SimulationEventQueue() {
        queue = new Vector <SimulationEvent>();
        simulationTime = 0.0;
    }

    //add method - save for later
    public void add(SimulationEvent newEntry) {
        if(newEntry.getTime() < simulationTime){ //if the time is less than the simulation time, throw exception
            //Since a test is designed to fail, use a print statement as an exception would prevent further testing
            System.out.println("Cannot add an event with a time less than the current time");

            //exception statement would appear as follows:
            //throw new EmptyQueueException("Cannot add an event with a time less than the current time");

        }else if(isEmpty()){// if the queue is empty, add new entry
            queue.add(newEntry); //using built-in vector add method, add new entry into the event queue

        }else{
            //look through the queue to find an appropriate position to add the entry
            //can iterate through the queue using the Java iterator object
            //saves from having to write our own code that iterates through the queue
            Iterator<SimulationEvent> iterate = queue.iterator();
            boolean largerEntry = false;
            int index = -1;
            //since an index is an items position - 1, we can initialize the index at -1
            //otherwise, we would subtract one from the position to get the index
            while(iterate.hasNext() && !largerEntry ){
                //while the queue still has entries and a larger time value has not been found
                index++; //increment index
                SimulationEvent nextEntry = iterate.next(); //grab next entry
                if(nextEntry.getTime() > newEntry.getTime()){ //compare new entry with next entry
                    largerEntry = true; //if the next entry's time is larger, set largerEntry to true
                }
            }

            if(largerEntry){ //if a larger entry has been found
                queue.add(index, newEntry); //add at index before larger entry
            }else{
                queue.add(newEntry); //otherwise, add it at the back of the queue
            }
        }
    }

    //peek method
    public SimulationEvent peek() {
        SimulationEvent first = null;
        if(isEmpty()) {
            throw new EmptyQueueException ("The queue is empty. There is no event to pull.");
            //since null values can be valid entries, throw an exception instead of returning null
        }else{
            first = queue.get(0); //using the built-in vector .get method, return the first element
        }
        return first;
    }

    //remove method
    public SimulationEvent remove(){
        SimulationEvent item = null;
        if(isEmpty()) { //if the queue is empty, throw out empty queue exception
            throw new EmptyQueueException ("The queue is empty. There is no event to remove");
        }else{ //if the queue is not empty...
            item = queue.get(0); //get the first event in the queue and store it in the item variable
            queue.remove(0); //remove the top event
            simulationTime = item.getTime(); //set the simulation time as the time from last removed
        }
        return item;
    }

    //we can get the return values for each method using the built-in Java Vector methods
    public boolean isEmpty() {
        return queue.isEmpty(); //check if a queue is empty
    }

    public int getSize(){
        return queue.size(); //return the size of the queue
    }

    public void clear(){
        queue.clear(); //return nothing; will only clear the queue
    }

    public double getCurrentTime(){
        return simulationTime; //return the current time of the event
    }

}
