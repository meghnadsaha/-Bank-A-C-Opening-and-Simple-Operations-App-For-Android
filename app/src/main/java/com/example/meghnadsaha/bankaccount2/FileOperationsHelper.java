package com.example.meghnadsaha.bankaccount2;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Meghnad Saha on 01-02-2017.
 */
public class FileOperationsHelper {

    private static FileOperationsHelper ourInstance;

    public static FileOperationsHelper getInstance() {
        if (ourInstance == null)
            ourInstance = new FileOperationsHelper();
        return ourInstance;
    }

    private FileOperationsHelper() {
    }

    public void saveFile(Context context, String fileName, String data) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.close();
    }

    public String readFile(Context context, String fileName) throws IOException {

        FileInputStream fileInputStream = context.openFileInput(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            buffer.append(line);
        }

        return buffer.toString();
    }

    //For external Storage
    public void saveExternalFile(String fileName, String data) throws IOException {
         if (isExternalStorageWritable()){
             File file = new File(Environment
                     .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);
             if(!file.exists())
             {
                 file.createNewFile();
             }
//             FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
             FileOutputStream fileOutputStream = new FileOutputStream(file);
             fileOutputStream.write(data.getBytes());
             fileOutputStream.close();
         }
    }

    public  String readExternalFile(String fileName) throws IOException {

        if (isExternalStorageReadable()){

            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);

            if (!file.exists()){
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();
            }
            return  null;
            }
        return null;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
