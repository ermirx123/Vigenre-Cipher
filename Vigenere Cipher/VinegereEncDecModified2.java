import java.util.*;

public class VinegereEncDecModified2 {
      

   public static void main(String[] args) {
      
      Scanner s = new Scanner(System.in);
      
      System.out.println("Shkruani fjalen Kyqe: ");
      String fjalaKyqe = s.nextLine();
      
      char[][] mat = rotateMatrix(createMatrix(fjalaKyqe));
   
      printMatrix(mat);
      
      System.out.println();
      
      System.out.println("Shkruani mesazhin:");
      String message = s.nextLine();
      System.out.println("Shkruani keyword:");
      String keyword = s.nextLine();
      
      System.out.println();
      
    /*    String t = encryption("TAKOHEMINEORADHJETE","SIGURIASIGURIASIGUR");   per "ERMIR"
          System.out.println(t);
          String v = decription("HDODXJJEUGDDDIYPGKU","SIGURIASIGURIASIGUR");
          System.out.println(v);
     */
      
      
      String t = encryption(message,keyword,mat);   // per "KRYPTOS"
      String v = decription(t,keyword,mat); 
     
      System.out.println("Teksti i enkiptuar(cipherTeksti) eshte : " + t);
      System.out.println("Dekriptimi eshte : " + v);
      System.out.println();
      
    /*  String t1 = encryption("URIMEFITOREN","ERMIRERMIRER",mat);
        String v1 = decription("XBQQUCZOVBBT","ERMIRERMIRER",mat); 
     
        System.out.println(t1);
        System.out.println(v1);
      
      */
      
      
      
     // System.out.println();
     // System.out.println();
      
    /* String t2 = encryption("TRUTHWILLOUT","CLOUDCLOUDCL"); per String bosh ""
       String v2 = decription("VCINKYTZFRWE","CLOUDCLOUDCL"); 
      
       System.out.println(t2);
       System.out.println(v2);  */
     
     
   }
   
   public static String decription(String encMessage,String keyword,char[][] mat) {
     
      char[] decMessage = new char[encMessage.length()],encMessageLine = stringToChar(encMessage);
      int[] decMessageNr = new int[decMessage.length],encMessageLineNr = new int[encMessageLine.length]; 
      
   
      for(int i = 0; i < encMessageLine.length; i++) {  
      
         int nr = (encMessageLine[i] - 65) % 26; 
         encMessageLineNr[i] = nr;         
      }  
      
      char[] keywordLine = stringToChar(keyword);
      int[] keywordLineNr = new int[keywordLine.length];
      
      for(int i = 0; i < keywordLine.length; i++) {
      
         int nr =  (keywordLine[i] - 65) % 26; 
         keywordLineNr[i] = nr;  
      }
      
      int[] dec = decryptIt(encMessageLine,keywordLineNr,mat);
   
      for(int i = 0; i < dec.length; i++) {
       
         char ch = (char)(dec[i] + 65);
         decMessage[i] = ch;
      }
   
      String decStr = charToString(decMessage);
   
      return decStr;
   }
   
   public static int[] decryptIt(char[] encryptedMessage, int[] keyword,char[][] mat) {
    
      int[] varg = new int[encryptedMessage.length];
      
      int inc = 0;
    
      for(int i = 0; i < keyword.length; i++) {
       
         int t = keyword[i];
         char tc = encryptedMessage[i];
                  
         for(int v1 = 0; v1 < mat.length; v1++) {
         
            for(int v2 = 0; v2 < mat[0].length; v2++) {
                
               if(mat[v1][v2] == mat[t][v2] && mat[v1][v2] == tc) {
                   
                  varg[inc++] = v2; 
                  
                  if(inc == encryptedMessage.length) {
                    
                     return varg;
                  }
               }
               
            }
         
         }
          
       
      }
   
    
      
     
      return varg;
   }

   
   public static String encryption(String message,String keyword,char[][] mat) {
    
      char[] messageLine   = stringToChar(message),keywordLine = stringToChar(keyword);
      int [] messageLineNr = new int[messageLine.length],keywordLineNr = new int[keywordLine.length];     
      
      
      for(int i = 0; i < messageLine.length; i++) {  
      
         int nr = (messageLine[i] - 65) % 26; 
         messageLineNr[i] = nr;         
      }  
      
      for(int i = 0; i < keywordLine.length; i++) {
      
         int nr =  (keywordLine[i] - 65) % 26; 
         keywordLineNr[i] = nr;  
      }      
      
      char[] chipherText = new char[message.length()];
      
      
      for(int i = 0; i < chipherText.length; i++) {
        
         chipherText[i] = mat[keywordLineNr[i]] [messageLineNr[i]];   
      }
      
      String cipherTextStr = charToString(chipherText);      
      
      return cipherTextStr;     
   }

   
    
   public static char[][] rotateMatrix(char[][] matrix) {
   
      char[][] rotatedMatrix = new char[matrix.length][matrix[0].length];
   
      for(int i = 0; i < matrix.length; i++) {
       
         char[] mat;
         mat = matrix[i];
         leftRotate(mat, i);
         rotatedMatrix[i] = mat;
      }
   
      return rotatedMatrix;
   }
   
   
   public static char[][] createMatrix(String Kcode) {
   
      char[][] mat = new char[26][26];
      
      char[] kode = theCode(Kcode);
      char[] otherAlpha = otherAlpha(kode);
   
      for(int i = 0; i < mat.length; i++) {
      
         int k;
      
         for(k = 0; k < kode.length; k++) {
         
            mat[i][k] = kode[k];    
         }
        
         for(int j = k, t = 0; j < mat[0].length; j++,t++) {    
           
            mat[i][j] = otherAlpha[t];  
         } 
      
      }  
     
      return mat;
   }
   
   public static char[] otherAlpha(char[] kode) {
   
      char[] otherAlpha = new char[26-kode.length];
   
      char start = 'A';
      
      for(int i = 0; i < otherAlpha.length; i++) {
       
         if(testCond(start,kode)) {
         
            otherAlpha[i] = start;
         
         } else {
         
            i--; 
         } 
         start++;
      }
            
      return otherAlpha;
   }
   
   public static boolean testCond(char ch, char[] varg) {
   
      if(ch >= 'A' && ch <= 'Z') {
        
         for(int i = 0; i < varg.length; i++) {
             
            if(ch == varg[i]) {
               
               return false;
            }
           
         }
        
         return true;
      }
   
      return false;
   }
   
   public static char[] theCode(String code) {
   
      char[] kode = new char[code.length()];
   
      for(int i = 0; i < code.length(); i++) {
        
         kode[i] = code.charAt(i);
      }
      
      return kode;
   }
   
   public static void leftRotate(char[] varg, int nr) {
   
      for(int i = 0; i < nr; i++) {
      
         rotateByOne(varg);
      
      }
   
   }
   
   public static void rotateByOne(char[] varg) {
   
      int i;
      char temp;
   
      temp = varg[0];   
   
      for(i = 0; i < varg.length-1; i++) {
      
         varg[i] = varg[i+1];      
      }
      
      varg[i] = temp;      
   }
   
   public static void printMatrix(char[][] mat) {
   
      for(int i = 0; i < mat.length; i++) {
      
         for(int j = 0; j < mat[0].length; j++) {
         
            System.out.print(mat[i][j] + " ");
         }
         
         System.out.println();
      }
   
   }
   
   public static char[] stringToChar(String word) {
   
      char[] characters = new char[word.length()];
   
      for(int i = 0; i < word.length(); i++) {
       
         characters[i] = word.charAt(i);
      }
      
      return characters;
   }
   
   public static String charToString(char[] varg) {
   
      String word = "";
     
      for(int i = 0; i < varg.length; i++) {
      
         word = word + varg[i];
      }
     
      return word;
   }

}