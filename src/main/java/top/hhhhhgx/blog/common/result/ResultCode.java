package top.hhhhhgx.blog.common.result;


public enum ResultCode {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    TWO(2, "自定义"),
    THREE(3, "自定义");

    private final int value;
    private final String message;

    ResultCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int value() {
        return this.value;
    }

    public String message() {
        return this.message;
    }
}
