package Exceptions;

public class ExcecaoRuntime extends RuntimeException{
    public ExcecaoRuntime() { }
    public ExcecaoRuntime(String msg) {
        super(msg);
    }
}
