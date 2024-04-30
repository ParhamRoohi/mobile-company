import java.io.Serializable;

public class MobilePhone implements Cloneable, Serializable {
   private String model;
   private MobileType mobileType;
   private int memorySize;
   private double price;

   public MobilePhone(String model, MobileType mobileType, int memorySize, double price) {
      this.model = model;
      this.mobileType = mobileType;
      this.memorySize = memorySize;
      this.price = price;
   }

   public MobilePhone(MobilePhone handSet) {
      model = handSet.model;
      memorySize = handSet.memorySize;
      price = handSet.price;
      mobileType = handSet.mobileType;
   }

   public MobilePhone clone() throws CloneNotSupportedException {
      return (MobilePhone) super.clone();
   }

   public void print() {
      System.out.print(
            " model : " + model + " mobile type : " + mobileType + " memory size : " + memorySize + " price : "
                  + price);
   }

   public String toString() {
      return " model : " + model + " mobile type : " + mobileType + " memory size : " + memorySize + " price : "
            + price;
   }

   public double priceRise(double rise) {
      return price *= (1 + rise);
   }

   public double getPrice() {
      return price;
   }

   public int getMemorySize() {
      return memorySize;
   }

   public String getModel() {
      return model;
   }

   public MobileType getMobileType() {
      return mobileType;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public void setModel(String model) {
      this.model = model;
   }

   // lab 6
   
   public String toDelimitedString() {
      return  model + "," + mobileType + "," + memorySize + "," + price;
   }
}
