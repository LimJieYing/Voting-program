/* ***************************************
  @author    Lim Jie Ying
  @date      27th DEC 2023
  @version   2

A program that allow a set of people to hold a simple vote amongst themselves
****************************************/
import java.util.Scanner; 

class candidate
{
    String name;
    int votes;
}


class votings
{
    public static void main (String [] a)
    {
        final int MAX_CANDIDATES = 10;
        candidate [] candidateData = new candidate[MAX_CANDIDATES];
        set_all_to_zero(candidateData, MAX_CANDIDATES);
        
        askNames(candidateData, MAX_CANDIDATES);
        askVotes(candidateData, MAX_CANDIDATES);
        
        int WinningVote = getLargestVote(candidateData, MAX_CANDIDATES); 
        printWinners(candidateData, MAX_CANDIDATES, WinningVote);
        return;
        
    } // END main

    public static void printWinners(candidate[] c, int MAX_CANDIDATES, int largestVote)
    {
        System.out.print("Winner(s): ");
        for(int i = 0; i < MAX_CANDIDATES; i++)
        {
            int currentVote = getVotes(c[i]);
            if(currentVote == largestVote)
            {
                System.out.print(getName(c[i]) + " ");
            }
        }
        return;
    }

    public static int getLargestVote(candidate[] c, int MAX_CANDIDATES)
    {
        int largest = 0;
        for(int i = 0; i < MAX_CANDIDATES; i++)
        {
            int currentvote = getVotes(c[i]);
            if(currentvote > largest)
            {
                largest = currentvote;
            }
        }
        
        return largest;
    }

    public static void askVotes(candidate[] c, int MAX_CANDIDATES)
    {
        printOptions(c, MAX_CANDIDATES);

        for(int i = 0; i < MAX_CANDIDATES; i++)
        {
            int voteNumber = inputInt(getName(c[i]) + " who do you vote for?");
            
            while(voteNumber <= 0 || voteNumber > MAX_CANDIDATES)
            {
                print("Please input a value more than 0 and less than " + MAX_CANDIDATES);
                voteNumber = inputInt(getName(c[i]) + " who do you vote for?");
            }
            
            int adjustNumber = voteNumber - 1;
            
            c[adjustNumber] = setVote(c[adjustNumber]);
        }
        blankLine();
        return;
    }

    public static void printOptions(candidate[] c, int MAX_CANDIDATES)
    {
        System.out.print("Votings ");
        for(int i = 0; i < MAX_CANDIDATES; i++)
        {
            System.out.print((i+1) + "=" + getName(c[i]) + "\t");
        }
        print("");
    }

    public static void askNames(candidate[] c, int MAX_CANDIDATES)
    {
        for(int i = 0; i < MAX_CANDIDATES; i++)
        {
            int adjusti = i + 1;
            String currentName = inputAnswer("Name " + adjusti + "?");
            c[i] = setName(c[i], currentName);
        }
        blankLine();
        return;
    }

    public static void set_all_to_zero(candidate[] c, int MAX_CANDIDATES)
    {
        for(int i = 0; i < MAX_CANDIDATES; i++)
        {
            c[i] = createCandidate("", 0);
        }
        return;
    }


    /*
    methods defining a candidate ADT
    - create a candidate record
    - set name and vote of a candidate
    - return the Name and vote of a candidate
    */
    public static candidate createCandidate(String name_of_candidate, int no_of_votes)
    {
        candidate c = new candidate();
        c.name = name_of_candidate;
        c.votes = no_of_votes;

        return c;
    }

    public static candidate setName(candidate c, String name_of_candidate)
    {
        c.name = name_of_candidate;
        return c;
    }

    
    public static candidate setVote(candidate c)
    {
        c.votes = c.votes + 1;
        return c;
    }

    public static String getName(candidate c)
    {
        return c.name;
    }

    public static int getVotes(candidate c)
    {
        return c.votes;
    }

    //END OF ADT

    //COMMON METHODS
    
    //Method to print any message
    public static void print(String message)
    {
        System.out.println(message);
        return;
    }//End print
    
    public static void blankLine()
    {
        print("\n");
    }
    
    //Method for users to input strings
    public static String inputAnswer(String question)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);
    
        print(question);
        answer = scanner.nextLine();
    
        return answer;
    }//End inputAnswer
    
    //to convert Strings to integers
    public static int inputInt(String message)
    {
        int answerint = 0;
        String answer = inputAnswer(message);
        boolean input_is_an_int = checkInt(answer);
    
        while (input_is_an_int == false)
        {
            answer = inputAnswer(message);
            input_is_an_int = checkInt(answer);
        }
    
            answerint = Integer.parseInt(answer);
       
        return answerint;
    }//End inputInt

//to check if the input is an int or not
    public static boolean checkInt (String answer)
    {
        boolean input_is_an_int = false;

        for(int i = 0; i <= answer.length()-1; i++)
        {
            char current = answer.charAt(i);
        
            if(current == '-' ||current == '0' || current =='1' || current =='2' || current == '3' || current == '4' || current == '5' || current =='6' ||current == '7' ||current =='8' ||current == '9')
            {
                input_is_an_int = true;
            } 
            else
            {
                print("Please input an INTEGER ONLY!");
                return false;
            }
        }
    return input_is_an_int;
    }//End checkInt

    
}//End class