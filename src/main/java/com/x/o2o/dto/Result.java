package com.x.o2o.dto;

/**
 * 
 * 
 * @ClassName: Result
 * 
 * @Description: ��װjson�������з��ؽ����ʹ����
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018��6��16�� ����1:55:55
 * 
 * @param <T>
 */
public class Result<T> {

    // �Ƿ�ɹ��ı�ʶ
    private boolean success;

    // �ɹ�ʱ���ص�����
    private T data;

    // ������
    private int errorCode;

    // ������Ϣ
    private String errMsg;

    /**
     * 
     * @Title:Result
     * 
     * @Description:�յĹ��캯��
     */
    public Result() {
        super();
    }

    /**
     * 
     * @Title:Result
     * 
     * @Description:���ݻ�ȡ�ɹ�ʱʹ�õĹ�����
     * 
     * @param success
     * @param data
     */
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /***
     * 
     * 
     * @Title:Result
     * 
     * @Description:���ݻ�ȡʧ��ʱʹ�õĹ�����
     * 
     * @param success
     * @param errorCode
     * @param errMsg
     */
    public Result(boolean success, int errorCode, String errMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errMsg = errMsg;

    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}