import DeveloperPackage.Pages;

public class Main {
    static int a = 2;

    // main method to start program
    public static void main(String[] args) {
//        Pages.pawnPromotion();
        // say welcome to user
        System.out.println("Welcome to Chess Game!");
        // starting program...
        Pages pages = new Pages();
        Pages.firstPage();
    }

    public void geta(int a) {
        this.a = a;
    }
}