package com.erfan.android.huskeliste;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
    public static final String FILENAME="Listinfo.dat";
    public static void writeData(ArrayList<String> items, Context context) throws IOException {

        try {
            FileOutputStream fos=context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context){
        ArrayList<String> itemsList =null;

        try {
            FileInputStream fis =context.openFileInput(FILENAME);
            ObjectInput ois= new ObjectInputStream(fis);
            itemsList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemsList = new ArrayList<>();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemsList;

    }
}
