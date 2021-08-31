package files.model;

import java.util.HashMap;
import java.util.List;

public class AttachmentRequest {
    //gets a list of attachment object from the request (base64string + fileName)
    private List<Attachment> attachmentList;

    public AttachmentRequest() {
    }

    public AttachmentRequest(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }
}
