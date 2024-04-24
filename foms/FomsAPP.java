package foms;

import foms.management.Branch;

import java.io.*;

public class FomsAPP implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Branch branch = new Branch("jp","jp", 15);

        FileOutputStream fileOut = new FileOutputStream("BranchInfo.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(branch);
        out.close();
        fileOut.close();


        Branch branch2;
        FileInputStream fileIn = new FileInputStream("foms/management/BranchInfo.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        branch2 = (Branch) in.readObject();
        in.close();
        fileIn.close();
    }
}
