package kw.test.utils.exception;

/**
 * auther   kangwang
 */
public class MyException extends Exception {
    private String msg;
    public MyException(String msg){
        this.msg=msg;
    }
    public String getMsg(String msg){
        return msg;
    }
}
