
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws CloneNotSupportedException, PlanException, IOException, UNException {
        int flatRate = 2;
        MobileCompany mobileCompany = new MobileCompany("apple", "admin", "admin", flatRate);
        User user1 = new User("parham", 01, new Address(22, "haft e tir", "Ab o bargh", "mashhad"),
                "parham.123");
        BusinessPlan businessPlan1 = new BusinessPlan("USR000000U", 3000001,
                new MobilePhone("x24", MobileType.Windows, 128, 7000),
                86, 45, new MyDate(2023, 6, 17), 9, 56);
        PersonalPlan personalPlan1 = new PersonalPlan("USR000001U", 3000002,
                new MobilePhone("Iphone 12", MobileType.Ios, 128, 9000), 9, 88,
                new MyDate(2026, 5, 26), "tehran");

        User user2 = new User("sepehr", 02, new Address(24, "jomhori", "tehran", "tehran"),
                "sepehr.123");
        PersonalPlan personalPlan2 = new PersonalPlan("USR000002U", 3000003,
                new MobilePhone(" s23 ", MobileType.Android, 256, 2000),
                87, 77, new MyDate(2002, 4, 15), "mashhad");
        BusinessPlan businessPlan2 = new BusinessPlan("USR000003U", 3000004,
                new MobilePhone("Iphone 13", MobileType.Ios, 512, 5000), 45, 12,
                new MyDate(2023, 8, 19), 98, 90);
        user1.addPlan(personalPlan1);
        user1.addPlan(businessPlan1);
        user2.addPlan(personalPlan2);
        user2.addPlan(businessPlan2);
        mobileCompany.addUser(user1);
        mobileCompany.addUser(user2);
//       Ui ui = new Ui();
//       Ui.mainMenu(mobileCompany);
        new Login(mobileCompany).setVisible(true);
    }
}
