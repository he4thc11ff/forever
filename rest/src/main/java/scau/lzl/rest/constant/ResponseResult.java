package scau.lzl.rest.constant;

public class ResponseResult {

    // 成功状态
    private boolean isSuccess;
    // 返回内容
    private Object content;
    // 注释、描述
    private String description;

    public static class Builder {
        private final boolean isSuccess;
        private final Object content;

        private String description;

        public Builder(boolean isSuccess, Object content) {
            this.isSuccess = isSuccess;
            this.content = content;
        }

        // 可选
        public Builder description(String val) {
            this.description = val;
            return this;
        }

        public ResponseResult build() {
            return new ResponseResult(this);
        }
    }

    public static Builder builder(boolean isSuccess, Object content) {
        return new Builder(isSuccess, content);
    }

    private ResponseResult(Builder builder) {
        this.isSuccess = builder.isSuccess;
        this.content = builder.content;
        this.description = builder.description;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "isSuccess=" + isSuccess +
                ", content=" + content +
                ", description='" + description + '\'' +
                '}';
    }

    // get set 方法仅供jackson返回JSON数据使用
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
