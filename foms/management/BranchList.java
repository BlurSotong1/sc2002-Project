package foms.management;

import java.util.ArrayList;
public class BranchList {

    private static ArrayList<Branch> branchList = new ArrayList<Branch>();


    public static ArrayList<Branch> getBranchList (){
        return branchList;
    }

    public static void displayBranch() {
        int i;
        int length;

        length = branchList.size();
        for (i = 0; i < length; i++) {
            System.out.print(i+1 + ". " + branchList.get(i).getName());
        }


    }


}
