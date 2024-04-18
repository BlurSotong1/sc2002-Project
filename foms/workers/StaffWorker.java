package foms.workers;

public class StaffWorker {
    Branch Branch;
    Branch branchObj = new Branch();
    StaffWorker(String name, int age, char gender, String loginID, Branch branch) {
        super(name, age, gender, loginID);
        this.branch = branch;

    }
}
