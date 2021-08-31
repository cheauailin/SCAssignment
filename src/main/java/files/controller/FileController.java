package files.controller;

import files.model.AttachmentRequest;
import files.model.FileDifference;
import files.services.FileService;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    //takes in a list of attachment request which compares between 2 different base64String
    @PostMapping(value="/diff")
    public ResponseEntity<List<FileDifference> > diff(@RequestBody AttachmentRequest attachmentRequest) throws JSONException {

        //calls the interface fileService which is overriden by the FileServiceImpl diffAttachment
        List<FileDifference> response = fileService.diffAttachment(attachmentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
