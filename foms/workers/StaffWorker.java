package foms.workers;

public class StaffWorker {
    String Branch;
    Branch branchObj = new Branch();
    StaffWorker(String name, int age, char gender, String loginID, String branch) {
        super(name, age, gender, loginID);
        this.branch = branch;
        setRole('S');
    }
    public void displayWorkerInformation(){}
}
