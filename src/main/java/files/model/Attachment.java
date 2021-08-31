package files.model;

public class Attachment {
    private String base64String;
    private String fileName;

    public Attachment() {
    }

    public Attachment(String base64String, String fileName) {
        this.base64String = base64String;
        this.fileName = fileName;
    }

    public String getBase64String() {
        return base64String;
    }

    public String getFileName() {
        return fileName;
    }
}
