/* Iliya Klishin
 * Dr. Steinberg
 * COP 3503 Summer 2022
 * Programming Assignment 3
 */
 
 import java.util.Arrays;
 
 public class LCS
 {
 
  private String x;
  private String y;
  private String z;
  private String pz;
  private int [][] numarr;
  private Character [][] chr;
    
    public LCS(String x, String y) // Overloaded constructor to assign new string values to x and y
                                  // and initialize helper strings.
    {
      this.x = " " + x;
      this.y = " " + y;
      this.z = "";
      this.pz = "";
    }
    
    public void lcsDynamicSol() //This method performs dynamic programming solution to find Longest common subsequence in x and y strings
    {
      int xl = x.length();
      int yl = y.length();
      
      this.numarr = new int[x.length()][y.length()]; //Initializing numarr with numbers
      this.chr = new Character[x.length()][y.length()];// Initializing character array with letters 'd' for diagonal, 'u' for up(vertical) , and 'l' for left
                                                       //(horizontal) directions.
      
       
       
       //System.out.println("x's length: " + x.length() + ", y's length: " + y.length());
      
        for(int i = 0; i < xl; i++)
        {
          numarr[i][0] = 0;
          chr[i][0] = '0';        //Filling in first column in numarray and character array with 0 and '0'
        }
        
        for(int j = 1; j < yl; j++)
        {
          numarr[0][j] = 0;      //filling in first row if numarray and character array with 0 and '0'
          chr[0][j] = '0';
        }
        
        
      for(int i = 1; i < xl; i++)
      {
        for(int j = 1; j < yl; j++)
        {
          if(x.charAt(i) == y.charAt(j))
          {
            numarr[i][j] = numarr[i - 1][j - 1] + 1; // if letters in X and Y arrays match, increasing numarr at i and j by 1
            chr[i][j] = 'd';                         // and putting 'd' in character array which means going in diagonal direction
          }
          
          else if(numarr[i - 1][j] >= numarr[i][j - 1])
          {
            numarr[i][j] = numarr[i - 1][j];        // if X and Y does not match, but the value at row above is bigger than in horizontal,
            chr[i][j] = 'u';                        // putting that value in current index of numarr, and putting 'u' in char array as up direction
          }
          
          else
          {
            numarr[i][j] = numarr[i][j - 1];    //if X and Y does not match, the value to the left (j - 1, previous column) bigger than in the prev. row,
            chr[i][j] = 'l';                    // putting that value in the current index, and putting 'l' in the char array which means left direction
          }
        }
      }
      
      //System.out.println(Arrays.deepToString(numarr).replace("], ", "]\n"));
      //System.out.println(Arrays.deepToString(chr).replace("], ", "]\n"));
    }
    
    public String getLCS()//This method calls printLCS that will make the correct LCS string and returns the string.
    {
      int i = x.length() - 1;
      int j = y.length() - 1;
      
      printLCS(i,j);
      z = pz;
      pz = "";
      //System.out.println("The LCS of the x and y strings is:" + z);
      return z;
     }
     
     public void printLCS(int i,int j)//This method finds the correct indexes using character array chr[][] and builds pz string.
     {
       pz = "";
       
       if(i == 0 || j == 0) //if we reach the first index, stop recursive call.
         return;
         
       if(chr[i][j] == 'd')
       {
         printLCS(i - 1, j - 1); // if we reach 'd' in the 2d char array which means diagonal, it means that the values at X and Y
         pz = pz + x.charAt(i);  // are equal, and we go back diagonally decreasing i and j, and after that printing that values at index i in X array.
         //System.out.print(z);
       }
       
       else if(chr[i][j] == 'u')
       {
         printLCS(i - 1, j); //The values in X and Y are not equal, and there is 'u' in the char array. Then, going up by decreasing i by 1.
       }
       
       else
       {
         printLCS(i, j - 1); //The values in X and Y are not equal, and there is 'l' in the char array. Going to the previous column by decreasing j by 1.
       }
       
     }
     
  }