import java.io.Serializable;

public class PersonalPlan extends MobilePlan implements  Serializable{
   protected String city;

   public PersonalPlan(String userName, MobilePhone handSet, int internetQuota, int capLimit, MyDate expiryDate,
         String city) throws PlanException{
      super(userName, handSet, internetQuota, capLimit, expiryDate);
      this.city = city;
   }
   public PersonalPlan(String userName,int id,  MobilePhone handSet, int internetQuota, int capLimit, MyDate expiryDate,
         String city) throws PlanException, UNException{
      super(userName,id, handSet, internetQuota, capLimit, expiryDate);
      this.city = city;
   }


   public PersonalPlan(PersonalPlan personalPlan )throws PlanException{
      super(personalPlan);
      city = personalPlan.city;
   }

   public PersonalPlan clone() throws CloneNotSupportedException {
      return (PersonalPlan) super.clone();
   }

   public void setCity(String city) {
      this.city = city;
   }
public String getCity() {
     return city;
   }
   public void print() {
      super.print();
      System.out.println(" city : " + city);
   }

   public String toString() {
      return super.toString() + " city : " + city;
   }

   public double calcPay(int flatRate) {
      return handSet.getPrice() / 24 + capLimit / 20 + internetQuota * 5 + flatRate;
   }

   // lab 6

   public String toDelimitedString() {
      return "pP" + "," + super.toDelimitedString() + "," + city;
   }
}
