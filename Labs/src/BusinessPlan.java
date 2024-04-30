
import java.io.Serializable;

public class BusinessPlan extends MobilePlan implements Serializable {

    protected int numberOfEmployees;
    protected int ABN;

    public BusinessPlan(String userName, MobilePhone handSet, int internetQuota, int capLimit,
            MyDate expiryDate,
            int numberOfEmployees, int ABN) throws PlanException {
        super(userName, handSet, internetQuota, capLimit, expiryDate);
        this.numberOfEmployees = numberOfEmployees;
        this.ABN = ABN;
    }

    public BusinessPlan(String userName, int id, MobilePhone handSet, int internetQuota, int capLimit,
            MyDate expiryDate,
            int numberOfEmployees, int ABN) throws PlanException, UNException {
        super(userName, id, handSet, internetQuota, capLimit, expiryDate);
        this.numberOfEmployees = numberOfEmployees;
        this.ABN = ABN;
    }

    public BusinessPlan(BusinessPlan businessPlan) throws PlanException {
        super(businessPlan);
        numberOfEmployees = businessPlan.numberOfEmployees;
        ABN = businessPlan.ABN;
    }

    public int getNumE() {
        return numberOfEmployees;
    }

    public void setABN(int ABN) {
        this.ABN = ABN;
    }
     public void setNumE(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
    

    public BusinessPlan clone() throws CloneNotSupportedException {
        return (BusinessPlan) super.clone();
    }

    public void print() {
        super.print();
        System.out.println(" ABN : " + ABN + " number of employees : " + numberOfEmployees);
    }

    public String toString() {
        return super.toString() + " ABN : " + ABN + " number of employees : " + numberOfEmployees;
    }

    public double calcPay(int flatRate) {
        double bussinesPayment = handSet.getPrice() / 24 + capLimit / 20 + internetQuota * 5 + flatRate;
        if (numberOfEmployees > 10) {
            bussinesPayment += ((numberOfEmployees - 10) * 50);
        }
        return bussinesPayment;
    }
    // lab 6 

    public String toDelimitedString() {
        return "bP" + "," + super.toDelimitedString() + "," + ABN + "," + numberOfEmployees;
    }

}
