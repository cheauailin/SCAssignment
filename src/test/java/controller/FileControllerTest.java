package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.ControllerConstants;
import files.controller.FileController;
import files.model.Attachment;
import files.model.AttachmentRequest;
import files.model.FileDifference;
import files.services.FileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FileService fileService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new FileController(fileService)).build();
    }

    @Test
    public void whenSuccessfulAttachmentRequestReturnValidResponse() throws Exception {
        List<Attachment> attachments = new ArrayList<>();
        List<FileDifference> differences = new ArrayList<>();

        FileDifference testFileDifferences = new FileDifference("testDiff","testText");

        differences.add(testFileDifferences);

        Attachment attachment = new Attachment("testBase64", "testFileName");
        attachments.add(attachment);

        AttachmentRequest attachmentRequest = new AttachmentRequest(attachments);

        when(fileService.diffAttachment(eq(attachmentRequest))).thenReturn(differences);

        ObjectMapper mapper = new ObjectMapper();

        MockHttpServletRequestBuilder param = post(ControllerConstants.BASE64_COMPARISON_PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(attachmentRequest));

        MvcResult perform = mockMvc.perform(param).andReturn();

        MockHttpServletResponse response = perform.getResponse();
        Assert.assertEquals(200, response.getStatus());
    }

}
