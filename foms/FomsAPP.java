package foms;

import foms.management.branch.Branch;

import java.io.*;

public class FomsAPP implements Serializable {
    public static void main(String[] args) {
        Branch branch = new Branch("jp","jp");
    try {
        FileOutputStream fileOut = new FileOutputStream("BranchInfo.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(branch);
        out.close();
        fileOut.close();
    } catch (IOException e) {
        System.out.println("we are screwed");
    }

        try {
            FileInputStream fileIn = new FileInputStream("foms/management/BranchInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println("this didnt work");
        }

    }
}
