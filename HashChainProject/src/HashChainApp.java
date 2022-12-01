import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HashChainApp
   {
   public static void main(String[] args) throws IOException
      {
      int aKey;
      Link aDataItem;
      int size, n, keysPerCell = 100;
                                     // get sizes
      System.out.print("Enter size of hash table: ");
      size = getInt();
      System.out.print("Enter initial number of items: ");
      n = getInt();
                                     // make table
      HashTable theHashTable = new HashTable(size);

      for(int j=0; j<n; j++)         // insert data
         {
         aKey = (int)(java.lang.Math.random() *
                                          keysPerCell * size);
         aDataItem = new Link(aKey);
         theHashTable.insert(aDataItem);
         }
      while(true)                    // interact with user
         {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, delete, or find: ");
         char choice = getChar();
         switch(choice)
            {
            case 's':
               theHashTable.displayTable();
               break;
            case 'i':
               System.out.print("Enter key value to insert: ");
               aKey = getInt();
               aDataItem = new Link(aKey);
               long startTime = System.currentTimeMillis();
               theHashTable.insert(aDataItem);
               long endTime = System.currentTimeMillis();
               System.err.println("Insert time : "+(startTime - endTime)/1000 + " s.");
               break;
            case 'd':
               System.out.print("Enter key value to delete: ");
               aKey = getInt();
               long startTime2 = System.currentTimeMillis();
               theHashTable.delete(aKey);
               long endTime2 = System.currentTimeMillis();
               System.err.println("Delete time : "+(startTime2 - endTime2)/1000 + " s.");
               break;
            case 'f':
               System.out.print("Enter key value to find: ");
               aKey = getInt();
               long startTime3 = System.currentTimeMillis();
               aDataItem = theHashTable.find(aKey);
               long endTime3 = System.currentTimeMillis();
               if(aDataItem != null)
                  System.out.println("Found " + aKey);
               else
                  System.out.println("Could not find " + aKey);
               System.err.println("Search time : "+(startTime3 - endTime3)/1000 + " s.");
               break;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
//--------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//-------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
//--------------------------------------------------------------
   }  // end class HashChainApp