package utilities;

public enum RDMS {

    mysqlSakila(1,"a"),
    mysqlTembdb(2,"c"),
    sqlite(3,"d");

    private int a;
    private String s;


    RDMS(int a, String s) {
        this.a = a;
        this.s = s;
    }

    public int getA() {
        return a;
    }


    public String getS() {
        return s;
    }

}
