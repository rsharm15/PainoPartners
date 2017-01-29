/* Rahul Sharma
	04/04/15
	IT 206-001
	Assignment 6-IMPLEMENTATION CLASS
 * This class will be ask whether if the user wants to add a child, print child info, find most talented 
 * child or quit. If the user decides to add a child, he/she will be asked if they want to add a competition 
 * child or a regular child. If the user selects to enter a normal child, he/she will be prompted for the 
 * child name, age, address, phone number, child level, number of lessons, lesson location. If the user 
 * selects competition, the user will be prompted for the number of trophies and a list of competition in 
 * addition to the info for the normal child. If the user selects the second option (print child info), a 
 * list is displayed with all the names of the children entered. Then the user is prompted to select the 
 * child whose info they would like to see. All the info of the selected child is displayed. Option 3, find
 * most talented child, will display the name and age of the child who has most trophies. The 4th option 
 * will simply end the program. After doing any of the first there actions (add a child, print child info 
 * and find most talented child), the user is prompted for one of the options, add a child, print child 
 * info and find most talented child until the user chooses to exit the program.  
 */
  import javax.swing.JOptionPane;
  public class PianoPartners {
	public static void main(String[] args) {
		final int EXIT_MENU_CHOICE =4;
		final int MAX_CHILDREN = 30;
        //getting the menuChoice between the value of 1 and 4
		int menuChoice=getMenuChoice();
		//allocating space for 30 objects
	    Child[] childrenTable  =new Child[MAX_CHILDREN];
	    /*
		 * A while loop that keep asking the user for the option 1 to 4, 1 = add new child, 2 = print child info 
		 * 3 = findMostTalentedChild and 4 to quit the program. After doing each of those tasks, the user will
		 * be prompted for those options till the user decides to exit the program. 
		 */
		while(menuChoice!=EXIT_MENU_CHOICE){
			switch(menuChoice){			
			//if menuChoice is 1, a regular child or a competition child is added one at a time
			case 1:
				addNewChild(childrenTable);
				break;
				/*
				 * if menuChoice is 2, the names of all the children that have been added will be displayed. Then
				 * the user will type in the name of the child whose complete info they want to see. Then, that 
				 * child's complete info will be displayed. 
				 */
			case 2:
				printChildInfo(childrenTable);
				break;
				/*
				 * if menuChoice is 3, the children(s) name and age will be displayed that have the highest amount of trophies.
				 */
			case 3:  
				findMostTalentedChild(childrenTable);
                 break;
			default: 
				 JOptionPane.showMessageDialog(null, "unknown error");
			}
		//the user is prompted once again for the 1-4 options
		menuChoice = getMenuChoice();
		}
}
	

	/*
	Method Name:	findMostTalentedChild
	Purpose:		This method runs through all the Child objects in the array and checks if they are instance of CompChild. 
	If so, it checks which instance of CompChild has the most trophies. Then it displays the childName and childAge.
	Return Value:	void
	Parameters:		Child[] childrenTable
	*/	
private static void findMostTalentedChild(Child[] childrenTable) {
		int highestNumOfTrophies=0;
		String message = "NAME--------------------AGE\n";
		if(Child.getNumOfChildren()> 0){
			//for loop to run through all the Child objects to find the CompChild with the most trophies
			for(int pos =0; pos <Child.getNumOfChildren(); pos++ ){
				//if statement to check if its an CompChild object
	            if(childrenTable[pos] instanceof CompChild){
	               if(((CompChild)childrenTable[pos]).getNumOfTrophies()>highestNumOfTrophies){
	      			 highestNumOfTrophies = ((CompChild)childrenTable[pos]).getNumOfTrophies();
	      	      }
	            }
			}
			//for loop to check all the CompChild that have the same amount of trophies
			for(int pos =0; pos < Child.getNumOfChildren(); pos++){
	            if(childrenTable[pos] instanceof CompChild){
	   				if(((CompChild)childrenTable[pos]).getNumOfTrophies() == highestNumOfTrophies){
	   				     message+= childrenTable[pos].getChildName() + "                     "+childrenTable[pos].getChildAge()+"\n";
	   				}
	            }
			}
		    JOptionPane.showMessageDialog(null, message);
		}
		else{
			JOptionPane.showMessageDialog(null, "There aren't any competition children");
		}
}
/*
Method Name:	printChildInfo
Purpose:		This method runs through all the Child objects in the array and displays their childName.
                Then, the user is prompted to enter the number of the child whose info they want displayed.
                Afterwards, a message with all child info is displayed 
Return Value:	void
Parameters:		Child[] childrenTable
*/	
private static void printChildInfo(Child[] childrenTable) {
		
		final int FIRST_LINE =1;
		int lineNum=FIRST_LINE;
		String message ="";
		int userChoice =0;
		//if statement to check if there are more than 0 children
		if(Child.getNumOfChildren() > 0){
			//for loop to display the names of all the children
			for(int pos = 0; pos<Child.getNumOfChildren(); pos++){
				message+= lineNum++ + ". " +childrenTable[pos].getChildName()+"\n";
			}
			//do-loop to keep asking until a value shown on screen is entered
			do{
				try{
					//user is promted for a number that corresponds to one of the students that are displayed
					userChoice = Integer.parseInt(JOptionPane.showInputDialog(null, message + "Enter the NUMBER that corresponds with the child whose info is needed"));
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "ERROR!!!The input must be NUMBERIC");
				}
				if(userChoice <=0){
					JOptionPane.showMessageDialog(null, "The input must be GREATER than 0");
				}
				if(userChoice > Child.getNumOfChildren()){
					JOptionPane.showMessageDialog(null, "The input must be LESS THAN OR EQUAL to the number of students on the screen");
				}
			}while((userChoice<=0) || (userChoice > Child.getNumOfChildren()));
			//the selected students info is displayed
			if(userChoice<= Child.getNumOfChildren()){
				JOptionPane.showMessageDialog(null,childrenTable[(userChoice - FIRST_LINE)].toString());
			}
  		}
		else{
			JOptionPane.showMessageDialog(null, "There aren't any children");
		}
}

/*
Method Name:	addNewChild
Purpose:		This method asks for the childName, childAge, childAddress, childPhoneNum, childLevel
numOfLessons and lessonLocation for a regular child. If the user wants to enter a competition child,
they are also prompted for numOfTrophies and a list of competition. Then an job object is created with 
those values and stored in an array 
Return Value:	void
Parameters:		Child[] childrenTable
*/	
private static void addNewChild(Child[] childrenTable) {
		
   		int pos = Child.getNumOfChildren();
		String childName ="";
		int childAge =0;
		String childAddress="";
		String childPhoneNumber="";
		String childLevel ="";
		int numOfLessons =0;
		int locationChoice =0;
		int numOfTrophies =0;
		String competitionName="";
		int numOfCompetitions =0;
      
	    if(childrenTable.length > pos){
	    	   
	    	int userOption = JOptionPane.showConfirmDialog(null, "Is it a competition child?");
	    	
	    	childName = getChildName();
	    	childAge = getChildAge();
	    	childAddress = getChildAddress();
	    	childPhoneNumber = getChildPhoneNumber();
	    	childLevel = getChildLevel();
	    	numOfLessons = getNumOfLessons();
	    	locationChoice = getLessonLocation();
	    	
	    	if(userOption == JOptionPane.NO_OPTION){
	    		//the specific constructor from the Child(SUPER) class is called 
	    		childrenTable[pos]= new Child(childName , childAge , childPhoneNumber , childAddress, childLevel ,numOfLessons , locationChoice);
	    	}
	        
	    	if(userOption == JOptionPane.YES_OPTION){
	    	     numOfTrophies = getNumOfTrophies();
	    	   //the specific constructor from the CompChild(SUB) class is called
	    	     childrenTable[pos] = new CompChild(childName , childAge , childAddress, childPhoneNumber , childLevel ,numOfLessons , locationChoice , numOfTrophies);
	    	     //while loop to keep asking the user for competitions as long as they want to and till they have not excedded the numOfTrophies
	    	     do{
		    			competitionName = getCompetitionName();
		    			((CompChild)childrenTable[pos]).setCompetition(competitionName);
		    			numOfCompetitions++;
		    			if(numOfCompetitions < numOfTrophies){
		    				userOption = JOptionPane.showConfirmDialog(null, "Do you have more competitions to enter?");
		    			}
		    			else{
		    				userOption = JOptionPane.NO_OPTION;
		    			}
		    	 }while(userOption == JOptionPane.YES_OPTION && numOfCompetitions < numOfTrophies);
	    	}
		}
		else{
			JOptionPane.showMessageDialog(null,"The program has reached it's limit of 30 students");
		}
	}

/*
Method Name:	getCompetitionName
Purpose:		This method asks for the name of the competition until its not an empty value 
Return Value:	(String) competitionName
Parameters:		none
*/
private static String getCompetitionName() {
	String competitionName;
	do{
		competitionName = JOptionPane.showInputDialog(null, "Enter Competition Name");
		if(competitionName.equals("")){
			JOptionPane.showMessageDialog(null, "Competition name can't be empty");
		}
	}while(competitionName.equals(""));
	return competitionName;
}


/*
Method Name:	getMenuChoice
Purpose:		This method asks for a menuChoice. 1 = addNewChild, 2 = printChildInfo, 3 = findMostTalentedChild, 4 = quit program
Return Value:	(int) menuChoice
Parameters:		none
*/	
	private static int getMenuChoice() {
		int menuChoice=0;
		final int MIN_CHOICE = 1;
		final int MAX_CHOICE = 4;
		do{
			try{
				menuChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of the operation you would like to perform\n" +
																				"1 - ADD NEW child \n"+
																				"2 - PRINT child info\n" +
																				"3 - FIND most talented child\n"+
																				"4 - QUIT"));
				if(menuChoice < MIN_CHOICE || menuChoice > MAX_CHOICE){
					JOptionPane.showMessageDialog(null,"The menu choice must be between 1 and 4");
				}
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "ERROR!!!!The input must be numeric");
			}
		}while(menuChoice < MIN_CHOICE || menuChoice > MAX_CHOICE);
		return menuChoice;
	}

	/*
	Method Name:	getChildName
	Purpose:		This method asks for the name of the child until its not an empty value 
	Return Value:	(String) childName
	Parameters:		none
	*/
public static String getChildName(){
	String childName = "";
	do{
		childName = JOptionPane.showInputDialog(null, "Enter child name");
		if(childName.equals("")){
			JOptionPane.showMessageDialog(null, "The child name can't be an empty value. ");
		}
	}while(childName.equals(""));
	return childName;
}

/*
Method Name:	getChildAge
Purpose:		This method asks for the age of the child until its between 5 and 17 inclusively 
Return Value:	(int) childAge
Parameters:		none
*/
public static int getChildAge(){
	int age = 0;
	final int MIN_AGE =5;
	final int MAX_AGE =17;
	do{
		try{
			age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the age of the child"));
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "ERROR!!!!The input must be numeric");
		}
		if(age < MIN_AGE || age > MAX_AGE){
			JOptionPane.showMessageDialog(null, "The age must be between 5 and 17. ");
		}
	}while(age < MIN_AGE || age > MAX_AGE);
	return age;
}

/*
Method Name:	getChildAddress
Purpose:		This method asks for the address of the child until its not an empty value 
Return Value:	(String) childAddress
Parameters:		none
*/
public static String getChildAddress(){
	String childAddress = "";
	do{
		childAddress = JOptionPane.showInputDialog(null, "Enter child's address");
		if(childAddress.equals("")){
			JOptionPane.showMessageDialog(null, "The child address can't be an empty value. ");
		}
	}while(childAddress.equals(""));
	return childAddress;
}

/*Method Name:	getChildPhoneNumber
Purpose:		This method asks for the phone Number of the child until its the format xxx-xxx-xxxx(x's must be digits) 
Return Value:	(String) childPhoneNumber
Parameters:		none
*/
public static String getChildPhoneNumber(){
	String phoneNum = "";
	String digitsOnly = "";
	final int VALID_LENGTH  =12;
	final int FOURTH_CHAR_POS  =3;
	final int EIGHTH_CHAR_POS  =7;
	int numOfDigits = 0;
	boolean isValid=false;
	do{
		phoneNum = JOptionPane.showInputDialog(null, "Enter the phone number in the format xxx-xxx-xxxx");
		digitsOnly = phoneNum.replaceAll("-", "");
		if(phoneNum.length() == VALID_LENGTH){
			if(phoneNum.charAt(FOURTH_CHAR_POS) == '-' && phoneNum.charAt(EIGHTH_CHAR_POS) == '-'){
			   numOfDigits =0;
			   for(int pos =0; pos< digitsOnly.length(); pos++){
				   if(Character.isDigit(digitsOnly.charAt(pos))){
					   numOfDigits++;
				   }
			   }
			}
	}
		if(phoneNum.length() == VALID_LENGTH){
			if(numOfDigits == digitsOnly.length()){
				isValid  = true;
			}
		}
		if(isValid == false){
			JOptionPane.showMessageDialog(null, "Error!!!!The input has to be in the format xxx-xxx-xxxx");
		}
	}while(!isValid);
	return phoneNum;
}

/*Method Name:	getChildLevel
Purpose:		This method asks for the level of the child until its the format "1-4" then a dash(-) and a letter "A-C"
Return Value:	(String) childLevel
Parameters:		none
*/
public static String getChildLevel(){
	char[] acceptableNums = {'1','2','3','4'};
	char[] acceptableLetters = {'A','B','C'};
	int indexOfDash =0;
	final int VALID_LENGTH = 3;
	char firstPart = 0;
	char secondPart = 0;
	boolean numIsValid =false;
	boolean letterIsValid=false;
	String level ="";
	do{
		level = JOptionPane.showInputDialog(null, "Enter child level");
		 
		if(level.contains("-")){
		    indexOfDash = level.indexOf('-');
		    firstPart = level.charAt(indexOfDash-1);
		    secondPart = level.charAt(indexOfDash+1);
		}
		if(level.length() == VALID_LENGTH){
			for(int pos =0; pos<acceptableNums.length; pos++){
				if(firstPart == (acceptableNums[pos])){
					numIsValid = true;
				}
			}
			for(int pos =0; pos<acceptableLetters.length; pos++){
				if(secondPart == (acceptableLetters[pos])){
					letterIsValid = true;
				}
			}
		}
		if(numIsValid == false || letterIsValid == false){
			JOptionPane.showMessageDialog(null, "The level must be written with a number from 1-4 then a dash and a upper case letter from A-C");
		}
		
	}while(numIsValid == false || letterIsValid == false);
    return level;
}

/*
Method Name:	getNumOfLessons
Purpose:		This method asks for the numOfLessons until its between 2 and 7 inclusively 
Return Value:	(int) numOfLessons
Parameters:		none
*/
public static int getNumOfLessons(){
	final int MIN_NUM_OF_LESSONS =2;
	final int MAX_NUM_OF_LESSONS =7;
	int numOfLessons = 0;
	
		do{
			try{
				numOfLessons = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of lessons"));
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "ERROR!!!!The input must be numeric");
			}
			if(!(numOfLessons >= MIN_NUM_OF_LESSONS && numOfLessons <=MAX_NUM_OF_LESSONS )){
				JOptionPane.showMessageDialog(null, "The number of lessons must be between 2 and 7 ");
			}
		}while(!(numOfLessons >= MIN_NUM_OF_LESSONS && numOfLessons <=MAX_NUM_OF_LESSONS ));
		return numOfLessons;
}

/*
Method Name:	getLessonLocation
Purpose:		This method asks for the lessonLocation until its 1 or 2 
Return Value:	(int) lessonLocation
Parameters:		none
*/
public static int getLessonLocation(){
	final int CHILD_HOME_LOCATION =1;
	final int STUDIO_LOCATION =2;
	int locationChoice =0;
		
		do{
			try{
				locationChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the location choice " +
																					"\n 1 - HOME" +
																					"\n 2 - STUDIO "
																					+ "\n Enter the number that corresponds to the location"));
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "ERROR!!!!The input must be numeric");
			}
			if(!(locationChoice == CHILD_HOME_LOCATION || locationChoice ==STUDIO_LOCATION)){
				JOptionPane.showMessageDialog(null, "The location choice must be 1 or 2 ");
			}
		}while(!(locationChoice == CHILD_HOME_LOCATION || locationChoice ==STUDIO_LOCATION));
		return locationChoice;
}

/*
Method Name:	getNumOfTrophies
Purpose:		This method asks for the numOfTrophies until its more than 0
Return Value:	(int) numOfTrophies
Parameters:		none
*/
public static int getNumOfTrophies(){
	int numOfTrophies =0;
	do{
		try{
			numOfTrophies = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of trophies"));
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "ERROR!!!!The input must be numeric");
		}
		if(numOfTrophies <= 0){
			JOptionPane.showMessageDialog(null, "The number of trophies must be greater than 0");
		}
	}while(numOfTrophies <= 0);
	return numOfTrophies;
}
}