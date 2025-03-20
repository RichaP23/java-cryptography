
import static java.lang.System.exit;
import java.util.Scanner;

/**
 * Cryptographer is s a menu driven program in which we have options to converted a 
 * plain text into different text using these two cryptography techniques
 * 1.ceaser cipher
 * 2.polyaphabetic cipher
 * @author Pragya Richa
 */
public class Cryptograph {
    Scanner scanner=new Scanner(System.in);
    public static void main(String[] args){
        
        Cryptograph cryptograph=new Cryptograph();
        cryptograph.displayMenu();
        
    }
    /**
     * This function displays the user menu to the user takes the input choice and calls
     * required function to encrypt the data.
     */
    public void displayMenu(){
        final int INVALID_CHOICE=-1;
        final int EXIT_CHOICE=3;
        final int SUCCESSFUL_EXIT=0;
        int choice=INVALID_CHOICE;
        String inputText;
        while(choice!=EXIT_CHOICE){
            displayMenuOptions();
            choice=Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    inputText=userDataInput();
                    ceaserCipher(inputText);
                    break;
                case 2:
                    inputText=userDataInput();
                    polyalphaCipher(inputText);
                    break;
                case 3:
                    exit(SUCCESSFUL_EXIT);
                    break;
                default:
                    System.out.println("\nEnter a valid choice .");
                    break;
        }}
    }
     /**
     * This function converts the plain text to cipher text using the Ceaser cipher algorithm
     * @param inputText is the plain text that will be converted to cipher text.
     */
    public void ceaserCipher(String inputText){
        //configuration constants for readability.
        final int ASCII_A=65;
        final int ASCII_a=97;
        final int TOTAL_CHAR=26;
        String encryptedText="";
        int asciiVal;
        System.out.println("Enter a key for ceaser cipher : ");
        int key=Integer.parseInt(scanner.nextLine());
        //we iterate through every character to convert it into ceaser cipher text.
        for(char c : inputText.toCharArray()){
            //we apply formula on all characters except space character.
            if(c!=' '){
                //storing the ascii value of character .
                asciiVal=(int)c;
                //for the capital characters
                if(isUpperCase(c)){
                    //encryption of c takes place:
                    c=(char)(((asciiVal-ASCII_A+key)%TOTAL_CHAR)+ASCII_A);
                    
                }else{
                    //for the lower case characters:
                    //encryption of c takes place:
                    c=(char)(((asciiVal-ASCII_a+key)%TOTAL_CHAR)+ASCII_a);
                }
            }
            //adding character to the cipher text
            encryptedText+=c;
        }
        //printing the cipher text.
        System.out.println("The given text after encryption : ");
        System.out.println(encryptedText);
        
    }
    /**
     * This function changes plain text to cipher text using the polyaplhabetic cipher 
     * @param inputText is the plain text that will be converted to cipher text.
     */
    public void polyalphaCipher(String inputText){
        final int FIRST_INDEX=0;
        final int LAST_INDEX=26;
        final int LOW_TO_HIGH_CONVERTER=32;
        final int ASCII_a=97;
        final String alphabets="abcdefghijklmnopqrstuvwxyz";
        char[][] alphaTable=new char[LAST_INDEX][LAST_INDEX];
        String keyString="", key;
        int firstCharIndex;
        //entry of aplhabets in the alphaTable
        //creation of the alphaTable
        for(int row=FIRST_INDEX;row<LAST_INDEX;row++){
            firstCharIndex=row;
            for(int column=FIRST_INDEX;column<LAST_INDEX;column++){
                alphaTable[row][column]=alphabets.charAt((firstCharIndex++)%LAST_INDEX);
            }
        }
        for(int row=FIRST_INDEX;row<LAST_INDEX;row++){
            firstCharIndex=row;
            for(int column=FIRST_INDEX;column<LAST_INDEX;column++){
                System.out.print(alphaTable[row][column]+" ");
            }System.out.println("");
        }
        //take entry for the key for each word in the sentence.
        System.out.println("Now we will enter the key for each word.");
        String[] wordsArr=inputText.split(" ");
        for(String word : wordsArr){
            //taking entry for each word.
            System.out.println("Enter the key for the word "+word+" : ");
            do{
            key=scanner.nextLine();
            }while(checkKeyValidity(key,word)!=true);
            keyString+=key+" ";
            
        }
        char charKey,charPlain;
        int row,column;
        //now we search the word for each plain text and given key.
        String plainText=inputText.toLowerCase();
        keyString=keyString.toLowerCase();
        String encryptedText="";
        char replacementChar;
        for(int index=FIRST_INDEX;index<inputText.length();index++){
            //if it is a space we ignore it.
            if(inputText.charAt(index)==' '){
                encryptedText+=' ';
                continue;
            }
            charKey=keyString.charAt(index);
            charPlain=inputText.charAt(index);
            //extracting row and column from the above characters
            //row is the plain word character
            row=(int)charPlain-ASCII_a;
            //column is the key word character
            column=(int)charKey-ASCII_a;
            //extracting character with the required character from the array.
            replacementChar=alphaTable[row][column];
            //we convert to char to upper case if it was upper case in plain text.
            if(isUpperCase(plainText.charAt(index))==true){
                replacementChar=(char)((int)replacementChar+LOW_TO_HIGH_CONVERTER);
            }
            
            encryptedText=encryptedText+replacementChar;
        }
        System.out.println("The given text after encryption : ");
        System.out.println(encryptedText);
        
    }
    public boolean checkKeyValidity(String key, String plainWord){
        if(key.length()!=plainWord.length()){
            System.out.println("The key and entered word should have same length");
            System.out.print("Please enter the key again : ");
            return false;
        }
        if(!key.matches("^[a-z]+$")){
            System.out.println("The key can only have small case alphabets.");
            System.out.print("Please enter the key again : ");
            return false;
        }
        return true;
    }
    
    /**
     * This function takes the user data as input and returns the input data
     * @return the text message input by the user.
     */
    public String userDataInput(){
        String inputText;
        do{ 
            System.out.println("Please enter the text that you want to encrypt: ");
            inputText=scanner.nextLine();
        }while(checkForValidText(inputText)!=true);
        
        return inputText;
    }
    /**
     * This function prints the menu otpion for every choice of the user.
     */
    public void displayMenuOptions(){
        System.out.println("===========================================================");
        System.out.println("                 WELCOME TO CRYPTOPGRAPHER ");
        System.out.println("===========================================================");
        System.out.println("Choose from the given cryptography techniques: ");
        System.out.println("1.Ceaser Cipher");
        System.out.println("2.Polyalphabetic Cipher");
        System.out.println("3.Exit");
        System.out.println("===========================================================");
        System.out.print("Enter your choice ==> ");
    }
    /**
     * This functions checks the validity of the text 
     * @param text the string that was input by the user.
     * @return true if the string is valid false otherwise.
     */
    public boolean checkForValidText(String text){
        String[] textArr=text.split(" ");
        for(String words: textArr){
            if(!words.matches("^[a-zA-Z]+$")){
                System.out.println("Your text can only contain alphabets.\nPlease enter your text again.");
                return false;
            }
        }
        return true;
    }
    /**
     * checks if a char is lower case or not
     * @param ch is the char to be checked
     * @return true if character is lower case
     */
    public boolean isLowerCase(char ch){
        final int ASCII_a=97;
        final int ASCII_z=122;
        return (ch>=ASCII_a && ch<=ASCII_z);
    }
    /**
     * checks if a char is upper case or not
     * @param ch is the char to be checked
     * @return true for upper case char 
     */
    public boolean isUpperCase(char ch){
        final int ASCII_A=65;
        final int ASCII_Z=90;
        return (ch>=ASCII_A && ch<=ASCII_Z);
    }
}
