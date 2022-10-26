package jdbc1;

public class Class1 {
    static int a = 1;


    static {
        System.out.println("static initializer : " + a);
    }

    {
        System.out.println("instance initializer : " + ++a);
    }

    public static void main(String[] args) {
        new Class1();
        new Class1();
        new Class1();
        new Class1();
    }

}
