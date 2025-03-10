package BankQueue;

import animatedapp.AnimatedApplication;
import animatedapp.ActionThread;

/**
 * This is an animated application for a simulation of a bank line
 *  
 * @author Charles Hoot 
 * @version 5.0
 */
    
public class BankApplication 
{
    
    public static void main (String args[]) 
    {
        
        ActionThread myThread = new BankActionThread();     //Change this line for different applications
        AnimatedApplication.startWith(myThread);
    }
    
    
 
}
