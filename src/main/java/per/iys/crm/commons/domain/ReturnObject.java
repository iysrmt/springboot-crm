package per.iys.crm.commons.domain;

public class ReturnObject {

    // 处理成功或失败的标记
    private String status;

    // 提示信息
    private String message;

    // 其他数据
    private Object retData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
