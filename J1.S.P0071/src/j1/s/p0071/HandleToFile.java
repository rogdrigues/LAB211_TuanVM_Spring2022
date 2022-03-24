/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0071;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class HandleToFile {

    void WriteToFile(ArrayList<Task> list) {
        try {
            FileOutputStream file = new FileOutputStream("DataTask.txt", false);
            ObjectOutputStream objectTaskOutput = new ObjectOutputStream(file);
            //Loop through from the first to the last element of list
            for (Task task : list) {
                objectTaskOutput.writeObject(task);
            }
            objectTaskOutput.close();
            file.close();
        } catch (Exception fileException) {
        }
    }

    ArrayList<Task> ReadFile(ArrayList<Task> taskList) {
        File fileinput = new File("DataTask.txt");
        if (fileinput.exists() == false) {
            return null;
        }
        try {
            FileInputStream file = new FileInputStream(fileinput);
            ObjectInputStream objectTaskInput = new ObjectInputStream(file);
            Task task = (Task) objectTaskInput.readObject();
            //loop until there are no more objects to read 
            while (task != null) {
                taskList.add(task);
                task = (Task) objectTaskInput.readObject();
            }
            objectTaskInput.close();
            file.close();
        } catch (Exception fileException) {
        }
        return taskList;
    }
}
