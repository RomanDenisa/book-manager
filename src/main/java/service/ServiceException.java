package service;

public class ServiceException extends RuntimeException{

    public ServiceException(String errorMsg){
        super(errorMsg);
    }
}
