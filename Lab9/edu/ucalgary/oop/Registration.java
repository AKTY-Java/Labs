/*
Copyright Ann Barcomb and Emily Marasco, 2022-2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import java.sql.*;

public class Registration{

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    private Connection dbConnect;
    private ResultSet results;
    
    public Registration(String url, String user, String pw){

        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }


//Must create a connection to the database, no arguments, no return value    
    public void initializeConnection(){

/***********ADD CODE HERE***********/                
/* Throw SQL exception if connection does not work */
        try {
            dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/competition", "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    String getDburl(){
        return this.DBURL;
    }

    String getUsername(){
        return this.USERNAME;
    }
    
    String getPassword(){
        return this.PASSWORD;
    }

    
    public String selectAllNames(String tableName){     

/***********ADD CODE HERE***********/                
/* Use selectAllNames method to return a list of competitiors and a list of teachers (two separate calls) Must take in a String for the table name and return a String */
    String query = "SELECT lname, fname FROM " + tableName;
    String strResult = "";
    try {
        //PreparedStatement statement = dbConnect.prepareStatement(query);
        //statement.setString(1, tableName);
        //System.out.println("STATEMENT: " + statement);
        
        Statement statement = dbConnect.createStatement();
        results = statement.executeQuery(query);

        while (results.next()){
            strResult += results.getString("lname");
            strResult += ", ";
            strResult += results.getString("fname");
            strResult += "\n";
        }
        statement.close();
    } catch (SQLException ex) {
    ex.printStackTrace();
    }
    return strResult;
}    
    
    
    public void insertNewCompetitor(String id, String lName, String fName, int age, String instrument, String teacherID){
       
        if(!validateTeacher(teacherID)){
            throw new IllegalArgumentException("Student must have a registered teacher.");
        }

        if(age < 5 || age > 18){
            throw new IllegalArgumentException("Student must be between the ages of 5 and 18.");
        }
             

/***********ADD CODE HERE***********/                


    }    

    
    // Used to ensure that any new students are connected to an existing teacher    
    private boolean validateTeacher(String teacherID){
        
        boolean validTeacher = false;
                    
        try {                    
            Statement myStmt = dbConnect.createStatement();
            
            // Execute SQL query
            results = myStmt.executeQuery("SELECT * FROM teacher");
            
            // Process the results set
            while (results.next()){
                if(results.getString("TeacherID").equals(teacherID))
                    validTeacher = true;
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return validTeacher;

    }    
    
 
    public void deleteCompetitor(String id){

/***********ADD CODE HERE***********/                


    }    

    public void close() {
        
/***********ADD CODE HERE***********/                

    }
    
    public static void main(String[] args) {

	// Remember that each time you execute the given .sql file, the database will be reset.
	// You should reset the database before each test run of your code.

        Registration myJDBC = new Registration("jdbc:postgresql://localhost/competition","oop","ucalgary");
        
        myJDBC.initializeConnection();
                
        System.out.println("------------------------------");
        System.out.println("***Printing list of competitors:***");
        System.out.println(myJDBC.selectAllNames("competitor"));
        System.out.println("------------------------------");
        System.out.println("***Printing list of teachers:***");
        System.out.println(myJDBC.selectAllNames("teacher"));

        System.out.println("------------------------------");
        System.out.println("***Now testing insert statements...***");                

        myJDBC.insertNewCompetitor("234", "Robertson", "Ebba", 15, "Trombone", "0023");
        System.out.println("Competitor #234 Robertson, Ebba should now be added.");
        //Check to make sure the database has been updated with a new competitor        

        try{
            myJDBC.insertNewCompetitor("678", "Jordan", "Ali", 10, "Oboe", "9807");
        }catch(IllegalArgumentException e){
            System.out.println("Sucessfully threw exception when no registered teacher can be found for a competitor.");
        }
        
        try{
            myJDBC.insertNewCompetitor("654", "Smyth", "Ace", 4, "Oboe", "1012");
        }catch(IllegalArgumentException e){
            System.out.println("Sucessfully threw exception when competitor is outside valid age range.");
        }
        
        System.out.println("------------------------------");
        System.out.println("***Now testing delete statements...***");                        
        myJDBC.deleteCompetitor("205");
        System.out.println("Competitor #205 Kamilla, Mala should now be deleted.");

        myJDBC.close();
        System.out.println("------------------------------");
        System.out.println("***End of tests.***"); 
    }
}


