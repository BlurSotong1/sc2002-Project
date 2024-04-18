package foms.management;

import java.util.ArrayList;
public class BranchList {

    private static ArrayList<Branch> branchList;

    public BranchList (){
        branchList = new ArrayList<Branch>();
    }
    public static ArrayList<Branch> getBranchList (){
        return branchList;
    }

}
