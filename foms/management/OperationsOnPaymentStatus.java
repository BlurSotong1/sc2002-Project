package foms.management;

import foms.workers.AdminWorker;

public class OperationsOnPaymentStatus {
    /**
     * Changes on payment status is performed by admin worker.
     */
    private AdminWorker admin;

    /**
     * Constructor for OperationsOnPaymentStatus class
     * @param admin is the admin worker performing the change of payment status.
     */
    public OperationsOnPaymentStatus(AdminWorker admin){
        this.admin = admin;
    }


}
