/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tec.estudiantes;


import java.io.*;


/**
 *
 * @author Valentin
 */
public class FileManager implements Serializable {

    
    // read txt file, para ller el query
    public static String readFile (String path) throws FileNotFoundException, IOException
    {

        String everything;

        try(BufferedReader br = new BufferedReader(new FileReader(path))) 
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        everything = sb.toString();
        }   

        return everything;
    }
    
    
        // read txt file, para ller el query
    public static void writeFile (String path, String value) throws IOException 
    {


        try(BufferedWriter bw = new BufferedWriter (new FileWriter(path, true))) 
        {
            bw.write(value);
            bw.close();
        }
        
    }
    
    // escribe un objeto
    public static void writeObject (Object obj, String filePath)
    {
        try{
            //use buffering
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try{
                // escribe el objeto
              output.writeObject(obj);
            }
            finally{
              output.close();
            }
          }  
          catch(IOException ex){
              
          }
        
    }
    
      // escribe un objeto
    public static Object readObject(String filePath)
    {
        try{
            //use buffering
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
            try{
              //deserialize the List
              return input.readObject();
            }
            finally{
              input.close();
            }
          }
          catch(ClassNotFoundException ex){
           
          }
          catch(IOException ex){
              System.out.println(ex);
              ex.printStackTrace();
          }
        return null;
    }  
    
    
    
}// fin clase
