import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////
public class HashDoubleApp
   {
   public static void main(String[] args) throws IOException
      {
      int aKey;
      DataItem aDataItem;
      int size, n;
      Scanner scan = new Scanner(System.in); // use Scanner
                                  // get sizes
      System.err.print("Input size of hash table: ");
      size = scan.nextInt();
      System.err.print("Input initial number of items: ");
      n = scan.nextInt();
                                  // make table
      HashTable theHashTable = new HashTable(size);

      for(int j=0; j<n; j++)      // insert data
         {
         aKey = (int)(java.lang.Math.random() * 2 * size);
         aDataItem = new DataItem(aKey);
         theHashTable.insert(aKey, aDataItem);
         }

      while(true)                 // interact with user
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
               aDataItem = new DataItem(aKey);
               long startTime1 = System.currentTimeMillis();
               theHashTable.insert(aKey, aDataItem);
               long endTime1 = System.currentTimeMillis();
               System.err.println("Insert Time : "+(endTime1-startTime1));
               break;
            case 'd':
               System.out.print("Enter key value to delete: ");
               aKey = getInt();
               long startTime2 = System.currentTimeMillis();
               theHashTable.delete(aKey);
               long endTime2 = System.currentTimeMillis();
               System.err.println("Delete Time : "+(endTime2-startTime2));
               break;
            case 'f':
               System.out.print("Enter key value to find: ");
               aKey = getInt();
               long startTime3 = System.currentTimeMillis();
               aDataItem = theHashTable.find(aKey);
               long endTime3 = System.currentTimeMillis();
               System.err.println("Search Time : "+(endTime3-startTime3));
               if(aDataItem != null)
                  System.out.println("Found " + aKey);
               else
                  System.out.println("Could not find " + aKey);
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
//--------------------------------------------------------------
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
   }  // end class HashDoubleApp
////////////////////////////////////////////////////////////////

class DataItem
   {                                 // (could have more items)
   private int iData;                // data item (key)
//--------------------------------------------------------------
   public DataItem(int ii)           // constructor
      { iData = ii; }
//--------------------------------------------------------------
   public int getKey()
      { return iData; }
//--------------------------------------------------------------
   }  // end class DataItem
////////////////////////////////////////////////////////////////
class HashTable
   {
   private DataItem[] hashArray;     // array is the hash table
   private int arraySize;
   private DataItem nonItem;         // for deleted items
// -------------------------------------------------------------
   HashTable(int size)               // constructor
      {
      arraySize = size;
      hashArray = new DataItem[arraySize];
      nonItem = new DataItem(-1);
      }
// -------------------------------------------------------------
   public void displayTable()
      {
      System.out.print("Table: ");
      for(int j=0; j<arraySize; j++)
         {
         if(hashArray[j] != null)
            System.out.print(hashArray[j].getKey()+ " ");
         else
            System.out.print("** ");
         }
      System.out.println("");
      }
// -------------------------------------------------------------
   public int hashFunc1(int key)
      {
      return key % arraySize;
      }
// -------------------------------------------------------------
   public int hashFunc2(int key)
      {
      // non-zero, less than array size, different from hF1
      // array size must be relatively prime to 5, 4, 3, and 2
      return 5 - key % 5;
      }
// -------------------------------------------------------------
                                     // insert a DataItem
   public void insert(int key, DataItem item)
   // (assumes table not full)
      {
      int hashVal = hashFunc1(key);  // hash the key
      int stepSize = hashFunc2(key); // get step size
                                     // until empty cell or -1
      while(hashArray[hashVal] != null &&
                      hashArray[hashVal].getKey() != -1)
         {
         hashVal += stepSize;        // add the step
         hashVal %= arraySize;       // for wraparound
         }
      hashArray[hashVal] = item;     // insert item
      }  // end insert()
// -------------------------------------------------------------
   public DataItem delete(int key)   // delete a DataItem
      {
      int hashVal = hashFunc1(key);      // hash the key
      int stepSize = hashFunc2(key);     // get step size

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // is correct hashVal?
         if(hashArray[hashVal].getKey() == key)
            {
            DataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            return temp;                        // return item
            }
         hashVal += stepSize;            // add the step
         hashVal %= arraySize;           // for wraparound
         }
      return null;                   // can't find item
      }  // end delete()
// -------------------------------------------------------------
   public DataItem find(int key)     // find item with key
   // (assumes table not full)
      {
      int hashVal = hashFunc1(key);      // hash the key
      int stepSize = hashFunc2(key);     // get step size

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // is correct hashVal?
         if(hashArray[hashVal].getKey() == key)
            return hashArray[hashVal];   // yes, return item
         hashVal += stepSize;            // add the step
         hashVal %= arraySize;           // for wraparound
         }
      return null;                   // can't find item
      }
// -------------------------------------------------------------
   }  // end class HashTable
////////////////////