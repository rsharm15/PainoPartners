/* Rahul Sharma
	4/04/15
	IT 206-001
	Assignment 6-DATA DEFENITION CLASS(SUPER CLASS)
 * This class will store the name, age, address, phone number, the child level, the number of lessons and 
 * lesson location regarding a child. Based on the value entered for the number of lessons and lesson 
 * location, the weekly cost is calculated for the child.
 */
public class Child {
    //instance variables
	private String childName;
	private int childAge;
	private String childAddress;
	private String childPhoneNumber;
	private String childLevel;
	private int numOfLessons;
	private int lessonLocation;
	private int weeklyCost;
	private static final int MIN_AGE =5;
	private static final int MAX_AGE =17;
	private static final int MIN_NUM_OF_LESSONS =2;
	private static final int MAX_NUM_OF_LESSONS =7;
	private static final int LESSON_FEE = 206;
	private static final int STUDIO_SURCHARGE = 26;
	private static int numOfChildren;
    
	/*
	 * The default constructor which passes empty string, 0 , empty string, empty string, empty string , 
	 * empty string, 0  and 0 to the constructor that accepts String, int, String , String, String 
	 * int and int.  
	 */
	public Child(){
		this("",0,"","","",0,0);
	}
	
	/*
	 * The specific constructor that accepts name, age, address, phoneNum, level, lessonNum and location 
	 * and sets the instance variable to those values. Afterwards, The calcCost method is called with the 
	 * arguments lessonNum and location. Then, the numOfChildren is incremented by 1.
	 */
	public Child(String name, int age, String address, String phoneNum, String level, int lessonNum, int location){
		this.childName = name;
		this.childAge = age;
		this.childAddress = address;
		this.childPhoneNumber = phoneNum;
		this.childLevel = level;
		this.numOfLessons = lessonNum;
		this.lessonLocation = location;
		calcCost(lessonNum , location);
		numOfChildren++;
	}
	
	/*
	Method Name:	setChildName
	Purpose:		This method sets the instance variable childName to name(the value sent in from the implementation class) 
	if the name isn't an empty string and returns a true value. Otherwise, it returns a false value. 
	
	Return Value:	boolean
	Parameters:		(String) name
	*/
	public boolean setChildName(String name){
		if(name.equals("")){
			return false;
		}
		else{
			this.childName = name;
			return true;
		}
	}
	
	/*
	Method Name:	setChildAge
	Purpose:		This method sets the instance variable childAge to age(the value sent in from the implementation class) 
	if the age is between 5 and 17 inclusively and returns a true value. Otherwise, it returns a false value. 
	Return Value:	boolean
	Parameters:		(int) age
	*/
	public boolean setChildAge(int age){
		if(age >= MIN_AGE && age <= MAX_AGE){
			this.childAge = age;
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	Method Name:	setChildAddress
	Purpose:		This method sets the instance variable childAddress to address(the value sent in from the implementation class) 
	if the address isn't an empty string and returns a true value. Otherwise, it returns a false value. 
	
	Return Value:	boolean
	Parameters:		(String) address
	*/
	public boolean setChildAddress(String address){
		if(address.equals("")){
			return false;
		}
		else{
			this.childAddress = address;
			return true;
		}
	}
	
	/*
	Method Name:	setChildPhoneNumber
	Purpose:		This method sets the instance variable childPhoneNumber to phoneNum(the value sent in from the implementation class) 
	if the phoneNum is in the format xxx-xxx-xxxx(x's must be digits) and returns a true value. Otherwise, it returns a false value. 
	Return Value:	boolean
	Parameters:		(String) phoneNum
	*/
	public boolean setChildPhoneNumber(String phoneNum){
		String digitsOnly = phoneNum.replaceAll("-", "");
		final int VALID_LENGTH  =12;
		final int FOURTH_CHAR_POS  =3;
		final int EIGHTH_CHAR_POS  =7;
		int numOfDigits = 0;
		boolean isValid=false;
		if(phoneNum.length() == VALID_LENGTH){
			if(phoneNum.charAt(FOURTH_CHAR_POS) == '-' && phoneNum.charAt(EIGHTH_CHAR_POS) == '-'){
			   for(int pos =0; pos< digitsOnly.length(); pos++){
				   if(Character.isDigit(digitsOnly.charAt(pos))){
					   numOfDigits++;
				   }
			   }
			}
		}
		if(phoneNum.length() == VALID_LENGTH){
			if(numOfDigits == digitsOnly.length()){
				this.childPhoneNumber = phoneNum;
				isValid  = true;
			}
		}
		else{
			isValid = false;
		}
		return isValid;
	}
	
	/*
	Method Name:	setChildLevel
	Purpose:		This method sets the instance variable childLevle to level(the value sent in from the implementation class) 
	if the level is in the format - a number "1-4" followed by a dash(-) and then a upper case letter from "A-C"(x's must be digits) 
	and returns a true value. Otherwise, it returns a false value. 
	Return Value:	boolean
	Parameters:		(String) level
	*/
	public boolean setChildLevel(String level){
		char[] acceptableNums = {'1','2','3','4'};
		char[] acceptableLetters = {'A','B','C'};
		int indexOfDash =0;
		char firstPart = 0;
		char secondPart= 0;
		boolean numIsValid =false;
		boolean letterIsValid=false;
		final int VALID_LENGTH =3;
		if(level.length() == VALID_LENGTH){
			if(level.contains("-")){
			    indexOfDash = level.indexOf('-');
			    firstPart = level.charAt(indexOfDash-1);
			    secondPart = level.charAt(indexOfDash+1);
			}
			for(int pos =0; pos<acceptableNums.length; pos++){
				if(firstPart == acceptableNums[pos]){
					numIsValid = true;
				}
			}
			for(int pos =0; pos<acceptableLetters.length; pos++){
				if(secondPart == acceptableLetters[pos]){
					letterIsValid = true;
				}
			}
		}
		if(numIsValid && letterIsValid){
			this.childLevel=level;
			return true;
		}
		else{
			return false;
		}
	}

	/*
	Method Name:	setNumOfLessons
	Purpose:		This method sets the instance variable numOfLessons to num(the value sent in from the implementation class) 
	if the num is between 2 and 7 inclusively and returns a true value. Otherwise, it returns a false value. 
	Return Value:	boolean
	Parameters:		(int) num
	*/
	public boolean setNumOfLessons(int num){
		if(num >= MIN_NUM_OF_LESSONS && num <=MAX_NUM_OF_LESSONS ){
			this.numOfLessons = num;
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	Method Name:	setLessonLocation
	Purpose:		This method sets the instance variable locationChoice to locationChoice(the value sent in from the implementation class) 
	if the locationChoice is between 1 or 2 and returns a true value. Otherwise, it returns a false value. 
	Return Value:	boolean
	Parameters:		(int) locationChoice
	*/
	public boolean setLessonLocation(int locationChoice){
		final int CHILD_HOME_LOCATION =1;
		final int STUDIO_LOCATION =2;
		if(locationChoice == CHILD_HOME_LOCATION || locationChoice ==STUDIO_LOCATION){
			this.lessonLocation = locationChoice;
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	Method Name:	calcCost
	Purpose:		This method calculates the instance variable weekly cost based on the num and locationChoice. 
	The num of lessons are multiplied by 206 and if the locationChoice is 2(studio), an extra 26 is added per lesson.
	Return Value:	void
	Parameters:		(int) num , (int) locationChoice
	*/
	private void calcCost(int num , int locationChoice){
		final int STUDIO_LOCATION =2;
		this.weeklyCost = num * LESSON_FEE;
		if(locationChoice==STUDIO_LOCATION){
			this.weeklyCost +=num * STUDIO_SURCHARGE;
		}
	}
	
	/*
	Method Name:	getChildName
	Purpose:		This method returns the instance variable childName
	Return Value:	(String) childName
	Parameters:		none
	*/
	public String getChildName(){return this.childName;}
	
	/*
	Method Name:	getChildAge
	Purpose:		This method returns the instance variable childAge
	Return Value:	(int) childAge
	Parameters:		none
	*/
	public int getChildAge(){return this.childAge;}
	
	/*
	Method Name:	getChildAddress
	Purpose:		This method returns the instance variable childAddress
	Return Value:	(String) childAddress
	Parameters:		none
	*/
	public String getChildAddress(){return this.childAddress;}
	
	/*
	Method Name:	getChildPhoneNumber
	Purpose:		This method returns the instance variable childPhoneNumber
	Return Value:	(String) childPhoneNumber
	Parameters:		none
	*/
	public String getChildPhoneNumber(){return this.childPhoneNumber;}
	
	/*
	Method Name:	getChildLevel
	Purpose:		This method returns the instance variable childLevel
	Return Value:	(String) childLevel
	Parameters:		none
	*/
	public String getChildLevel(){return this.childLevel;}
	
	/*
	Method Name:	getNumOfChildren
	Purpose:		This method returns the static variable numOfChildren
	Return Value:	(int) numOfChildren
	Parameters:		none
	*/
	public static int getNumOfChildren(){return numOfChildren;}
	
	/*
	Method Name:	getNumOfLessons
	Purpose:		This method returns the static variable numOfLessons
	Return Value:	(int) numOfLessons
	Parameters:		none
	*/
	public int getNumOfLessons(){return this.numOfLessons;}
	
	/*
	Method Name:	getLessonLocation
	Purpose:		This method returns the static variable lessonLocation
	Return Value:	(int) lessonLocation
	Parameters:		none
	*/
	public int getLessonLocation(){return this.lessonLocation;}

	/*
	Method Name:	toString
	Purpose:		This method returns a message consisting of childName, childAge, childAddress, ChildPhoneNumber
	childLevel, numOfLessons and the weeklyCost. 
	Return Value:	(String) message
	Parameters:		none
	*/
	public String toString(){
		return "NAME: "+ this.childName.toUpperCase() +
				   "\nAGE: " + this.childAge +
				   "\nSTREET ADDRESS: " + this.childAddress.toUpperCase()+
				   "\nPHONE-NUMBER: " + this.childPhoneNumber+
				   "\nLEVEL: " + this.childLevel +
				   "\nNUMBER OF WEEKLY LESSONS: " +this.numOfLessons +
				   "\nTOTAL WEEKLY COST IS: " +this.weeklyCost;         
				         
	}
	
}
