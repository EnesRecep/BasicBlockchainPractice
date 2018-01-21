package sample;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Blockchain {


    //Getting hash according to SHA256
    private static String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));

            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //Bytes to Hex
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

    //To add block
    public static void addBlock(String data) {

        File file = getFiles()[getFiles().length - 1];  //To get lash file of the folder
        String[] fileComponents = readFile(file);       //To call readFile method to get components of the file (fileName, data, prevHash, prevFileName)

        String toHash = fileComponents[0] + fileComponents[1] + fileComponents[2] + fileComponents[3]; //Getting the hash of the previous file
        String hashed = getSHA256Hash(toHash);

        Blocks block = new Blocks(String.valueOf(Integer.parseInt(fileComponents[0]) + 1), data, hashed, fileComponents[0]);    //creating new block
        System.out.println(block.toString());       //Log block info into console

        writeFile(block);   //Write block into file
    }

    //To get all the file names in the folder
    public static File[] getFiles(){
        File folder = new File("C:\\Users\\samsungnb\\Desktop\\Blockchain");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    //To control whether there is change in the previous blocks
    public static boolean control(){
        boolean flag = true; //Default true, it means there is no change
        int numberOfFiles = getFiles().length;  //To get number of files in the folder

        for(int i = 0 ; i < numberOfFiles - 1 ; i++){   //Check all the files

            File file = getFiles()[i];  //To get previous file file
            String[] fileComponents = readFile(file);   //To get content of the previous file

            String toHash = fileComponents[0] + fileComponents[1] + fileComponents[2] + fileComponents[3];  //To find hash of previous file
            String hashed = getSHA256Hash(toHash);

            //System.out.println("prev "+hashed);

            file = getFiles()[i+1];     //to get current file
            fileComponents = readFile(file);    //to get content of the current file

            //System.out.println("last "+fileComponents[2]);

            if(!hashed.equals(fileComponents[2])){   //if there is no change, return true; otherwise false
                flag = false;
            }
        }
        return flag;
    }
    //To read files
    public static String[] readFile(File file){

        //Initial values are null
        String fileName = null;
        String previousData = null;
        String previousHash = null;
        String previousFileName = null;

        try {
            // Use this for reading the data.
            FileInputStream inputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(inputStream);

            while(scanner.hasNextLine()) {      //To read contents of the file
                fileName = scanner.nextLine();
                previousData = scanner.nextLine();
                previousHash = scanner.nextLine();
                previousFileName = scanner.nextLine();
            }
            inputStream.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + file + "'");
        }

        String[] fileComponents = {fileName, previousData, previousHash, previousFileName};

        return fileComponents;
    }

    public static void writeFile(Blocks block){

        try {
            String newFileName = block.getFileName();
            String path = "C:\\Users\\samsungnb\\Desktop\\Blockchain\\" + newFileName + ".txt";     //Creating a new file
            FileWriter fileWriter = new FileWriter(new File(path));

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Writing content of the file
            bufferedWriter.write(block.getFileName());
            bufferedWriter.newLine();
            bufferedWriter.write(block.getData());
            bufferedWriter.newLine();
            bufferedWriter.write(block.getPreviousHash());
            bufferedWriter.newLine();
            bufferedWriter.write(block.getPreviousFileName());

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + "'");
        }
    }
}

