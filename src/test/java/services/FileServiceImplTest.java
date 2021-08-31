package services;

import files.model.Attachment;
import files.model.AttachmentRequest;
import files.model.FileDifference;
import files.services.FileService;
import files.services.FileServiceImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class FileServiceImplTest {

    @Test
    public void base64StringProducesValidAttachmentResponse() throws JSONException {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment("testBase64", "testFileName");
        Attachment attachment2 = new Attachment("testBase64-2", "testFileName-2");
        attachments.add(attachment);
        attachments.add(attachment2);

        AttachmentRequest attachmentRequest = new AttachmentRequest(attachments);

        List<FileDifference> differences = new ArrayList<>();
        FileDifference testFileDifferences = new FileDifference("EQUAL","testBase64");
        FileDifference testFileDifferences2 = new FileDifference("INSERT","-2");
        differences.add(testFileDifferences);
        differences.add(testFileDifferences2);

        FileServiceImpl fileService = new FileServiceImpl();
        List<FileDifference> actualResponse = fileService.diffAttachment(attachmentRequest);

        Assert.assertEquals(differences.get(0).getText(), actualResponse.get(0).getText());
        Assert.assertEquals(differences.get(1).getText(), actualResponse.get(1).getText());

    }
}
