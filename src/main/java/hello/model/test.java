package hello.model;

/**
 * Created by sumitjai on 12/1/14.
 */
public class test {
    private String a;
    private String b;

    public String getC() {
        return c;
    }

    private String c;

    public test(String a,String b, String c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void ll(test test){
        System.out.println("@@@@@");
    }


    public static void main(String[] args) {
        test test = new hello.model.test("s", "b", null);
        System.out.println(test.getC());
        test.ll(new test("a","b",null));

    }
}
