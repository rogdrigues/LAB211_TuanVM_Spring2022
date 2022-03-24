/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0071;

import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class TaskManager {

    private ArrayList<Task> taskList;
    private int taskID;

    public TaskManager() {
        taskList = new ArrayList<>();
        taskID = 0;
    }

    public int addTask(String requirementName, String taskTypeID, String date,
            String planFrom, String planTo, String assignee, String reviewer
    ) throws Exception {
        Task newTask = null;
        HandleToFile handleToFile = new HandleToFile();
        double planto = Double.parseDouble(planTo);
        double planfrom = Double.parseDouble(planFrom);

        //Check if list of task is  empty then set taskID by 1.
        if (taskList.isEmpty()) {
            taskID = 1;
        }//Otherwise, increase taskID by 1 
        else {
            taskID++;
        }

        /*Check if planTo is after planFrom. If not then cancell the process of 
        create an task*/
        if (planto <= planfrom) {
            throw new Error("Task overlap!");
        } else {
            boolean isExist = checkDuplicateTask(date, assignee, planfrom,
                    planto);
            if (isExist) {
                throw new Exception("Plan to(" + planTo + ") must be after "
                        + "plan from (" + planFrom + ")!");
            } else {
                newTask = new Task(taskID, taskTypeID, requirementName, date,
                        planfrom, planto, assignee, reviewer);
            }
        }
        taskList.add(newTask);
        handleToFile.WriteToFile(taskList);
        return taskID;
    }

    public void deleteTask(String id) throws Exception {
        HandleToFile handleToFile = new HandleToFile();
        taskID = Integer.parseInt(id);
        taskList = handleToFile.ReadFile(taskList);
        
        //Loop through the list of task from the first to the last task
        for (Task task : taskList) {
            if (task.getTaskID() == taskID) {
                taskList.remove(task);
                handleToFile.WriteToFile(taskList);
                return;
            }
        }
        //If task's user input is not found in list of task then get exception
        throw new Exception("ID not found in list of tasks.");
    }

    public void getDataTasks() {
        HandleToFile handleToFile = new HandleToFile();
        taskList = handleToFile.ReadFile(taskList);
        
        //Check if taskList's file is exist or not
        if (taskList == null) {
            System.err.println("File does not exist!");
            return;
        }
        //Check if taskList is empty or not
        if (taskList.isEmpty()) {
            System.err.println("List of task is empty!");
        } else {
            System.out.println("----------------------------Task-------------------------------------");
            System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n", "ID",
                    "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            //loop through the list of taskList in file from the first to the last element
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

    public void deleteTask() {
        //Check if task list is empty or not
        if (taskList.isEmpty()) {
            System.err.println("List of task is empty!");
        } else {
            GetDataInput getData = new GetDataInput();
            System.out.println("---------------Delete Task---------------");
            String taskIdChoice = String.valueOf(getData.getInt("Enter ID to "
                    + "Delete: ", "", 0,
                    Integer.MAX_VALUE));
            try {
                deleteTask(taskIdChoice);
            } catch (Exception taskNotFound) {
                System.err.println("ID not found in list of tasks.");
            }
        }
    }

    public int addTask() {
        GetDataInput getData = new GetDataInput();

        System.out.println("---------------Add Task---------------");
        /*
        [a-z]: Input matches a character in the range "a" to "z"
        [A-Z]: Input matches a character in the range "A" to "Z"
        [0-9]: Input matches a character in the range "0" to "9"
        [+]: Matches 1 or more
        [ ]: Input contains a space character
         */
        String requirementName = getData.getString("Requirement name: ",
                "Input not match format require", "[a-zA-Z ]+");
        String taskType = getData.GetTaskType("Task: ");
        String date = getData.getDate("Date: ");
        double planFrom = getData.getDouble("From: ", "Plan from must be within "
                + "8.0 to 17.0", 8.0, 17.0);
        double planTo = getData.getDouble("To: ", "Plan to must be within "
                + (planFrom + 0.5) + " to 17.5", planFrom + 0.5, 17.5);
        String assignee = getData.getString("Assignee: ",
                "Input not match format require", "[a-zA-Z0-9 ]+");
        String reviewer = getData.getString("Reviewer: ",
                "Input not match format require", "[a-zA-Z0-9 ]+");

        try {
            String planto = String.valueOf(planTo);
            String planfrom = String.valueOf(planFrom);
            taskID = addTask(requirementName, taskType, date, planfrom, planto,
                    assignee, reviewer);
        } catch (Exception taskOverLap) {
            System.err.println("Task overlap!");
            taskID--;
        } catch (Error timeException) {
            System.err.println("Plan to(" + planTo + ") must be after plan from"
                    + "(" + planFrom + ")!");
            taskID--;
        }
        return taskID;
    }

    private boolean checkDuplicateTask(String date, String assignee, double planFrom,
            double planTo) {
        boolean isExist = false;
        /*Loop through every elements of arraylist from the first to the last 
        element*/
        for (Task task : taskList) {
            /*compare date in list with date input and assignee in list and 
            assignee input*/
            if (date.equals(task.getDate()) && assignee.equals(task.getAssignee())) {
                /*Check if planTo and planFrom input have planTo less than planFrom
                in list or planFrom large than planTo in the list
                 */
                if (planTo < task.getPlanFrom() || planFrom > task.getPlanTo()) {
                    isExist = false;
                } else {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }
}
