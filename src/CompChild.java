/* Rahul Sharma
	2/25/15
	IT 206-001
	Assignment 4-DATA DEFENITION CLASS
 * This class will store the number of trophies that each child has. In addition it will store a list of 
 * all competitions for the child. 
 */
public class CompChild extends Child {

	//instance varaibles
	private int numOfTrophies;
	private int compListCounter=0;
	private int weeklyCost;
	private static final int MAX_COMPETITIONS = 100;
	private String[] competitionList = new String[MAX_COMPETITIONS];
	private static final int COMP_CHILD_LESSON_FEE = 206;
	private static final int COMP_CHILD_STUDIO_SURCHARGE = 26;
	private static final int COMP_CHILD_CHARGE = 15;
    
	/*
	 * The default constructor which passes empty string, 0 , empty string, empty string, empty string , 
	 * empty string, 0, 0 and 0 to the constructor that accepts String, int, String , String, String 
	 * int, int and int.  
	 */
	public CompChild(){
		this("", 0,"", "","", 0,0,0);
	}
	
	/*
	 * The specific constructor that accepts name, age, address, phoneNum, level, lessonNum, location and
	 * numOfTrophiesand sets the instance variable to those values. Next, the constructor from the Child(Super Class is called)
	 * and numOfTrophies is assigned to the instance variable numOfTrophies. Afterwards, The calcCost method is called with the 
	 * arguments lessonNum and location. 
	 */
	public CompChild(String name, int age, String address, String phoneNum, String level, int lessonNum, int location, int numOfTrophies){
		super( name,  age, address,  phoneNum, level, lessonNum, location);
		this.numOfTrophies = numOfTrophies;
		this.calcCost(lessonNum, location);
	}
	
	/*
	Method Name:	setNumOfTrophies
	Purpose:		This method sets the instance variable numOfTrophies to num(the value sent in from the implementation class) 
	if the num is more than 0 and returns a true value. Otherwise, it returns a false value. 
	Return Value:	 boolean
	Parameters:		(int) age
	*/
	public boolean setNumOfTrophies(int num){
		if(num>0){
			this.numOfTrophies = num;
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	Method Name:	setCompetition
	Purpose:		This method sets the position in the array compListCounter to comp(the value sent in from the implementation class) 
	if there is space in the array. 
	Return Value:	void
	Parameters:		(String) comp
	*/
	public void setCompetition(String comp){
		if(compListCounter<competitionList.length){
			competitionList[compListCounter++] = comp;
		}
	}
	
	/*
	Method Name:	calcCost
	Purpose:		This method calculates the instance variable weekly cost based on the num and locationChoice. 
	The num of lessons are multiplied by 206 and if the locationChoice is 2(studio), an extra 26 is added per lesson.
	An additional 15 is applied to the weeklyCost per lesson
	Return Value:	void
	Parameters:		(int) num , (int) locationChoice
	*/
	private void calcCost(int numOfLessons, int locationChoice){
		final int STUDIO_LOCATION =2;
		this.weeklyCost = (numOfLessons * COMP_CHILD_LESSON_FEE) + (numOfLessons * COMP_CHILD_CHARGE) ;
		if(locationChoice==STUDIO_LOCATION){
			this.weeklyCost +=numOfLessons * COMP_CHILD_STUDIO_SURCHARGE;
		}
	}
	
	/*
	Method Name:	getNumOfTrophies
	Purpose:		This method returns the instance variable numOfTrophies
	Return Value:	(int) numOfTrophies
	Parameters:		none
	*/
	public int getNumOfTrophies(){return this.numOfTrophies;}
	
	
	/*
	Method Name:	toString
	Purpose:		This method returns a message consisting of childName, childAge, childAddress, ChildPhoneNumber
	childLevel, numOfLessons, weeklyCost, numOfTrophies and the list of competitions
	Return Value:	(String) message
	Parameters:		none
	*/
	public String toString(){
		String message  = super.toString().substring(0,super.toString().length()-3);
		message +=this.weeklyCost +
		         "\nNUMBER OF TROPHIES: " + this.numOfTrophies+
		         "\nTHE COMPETITIONS: ";
		        
		for(int i=0; i<compListCounter;i++){
			message+=competitionList[i]+"\n";
		}
		return message;
	}	
}
