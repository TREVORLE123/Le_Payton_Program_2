package src;
import java.util.*;

/**
 * This class represents the main entry point for running the game.
 */
public class Run {

    // Planner instance for planning the game actions
    /**
     * Starts the game by prompting the user for initial configurations and initiating the planning process.
     */
    public void start(){
        Scanner userInput = new Scanner(System.in);

        String monkeyLocation;
        String boxLocation;
        String bananaLocation;

        // Prompt user to select monkey's starting room
        try{
            promptMonkeyStart();
            monkeyLocation = userInput.next();
            if(!(monkeyLocation.equals("A") || monkeyLocation.equals("B") || monkeyLocation.equals("C"))){
                throw new Exception("Wrong Input");
            }
        }
        catch(Exception e){
            System.out.println("Wrong input...");
            monkeyLocation = userInput.next();
        }
        
        // Prompt user to select box's starting room
        try{
            promptBoxStart();
            boxLocation = userInput.next();
            if(!(boxLocation.equals("A") || boxLocation.equals("B") || boxLocation.equals("C"))){
                throw new Exception("Wrong Input");
            }
        }
        catch(Exception e){
            System.out.println("Wrong input...");
            boxLocation = userInput.next();
        }

        // Prompt user to select bananas' starting room
        try{
            promptBananasStart();
            bananaLocation = userInput.next();
            if(!(bananaLocation.equals("A") || bananaLocation.equals("B") || bananaLocation.equals("C"))){
                throw new Exception("Wrong Input");
            }
        }
        catch(Exception e){
            System.out.println("Wrong input...");
            bananaLocation = userInput.next();
        }
        finally{
            userInput.close();
        }

        // Set initial state of the game
        Planner planObj = new Planner(monkeyLocation, boxLocation, bananaLocation);
        
        // Find and execute the plan to solve the game
        List<String> plan = planObj.findPlan();

        System.out.println("Plan: ");
        for(String action : plan){
            System.out.println(action);
        }
    }

    /**
     * Prompt the user to select the monkey's starting room.
     */
    public void promptMonkeyStart(){
        System.out.println("Select which room the monkey starts in:");
        System.out.println("[1] Room A");
        System.out.println("[2] Room B");
        System.out.println("[3] Room C");
    }

    /**
     * Prompt the user to select the box's starting room.
     */
    public void promptBoxStart(){
        System.out.println("Select which room the box starts in:");
        System.out.println("[1] Room A");
        System.out.println("[2] Room B");
        System.out.println("[3] Room C");
    }

    /**
     * Prompt the user to select the bananas' starting room.
     */
    public void promptBananasStart(){
        System.out.println("Select which room the bananas starts in:");
        System.out.println("[1] Room A");
        System.out.println("[2] Room B");
        System.out.println("[3] Room C");
    }
}
