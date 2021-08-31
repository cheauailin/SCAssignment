package files.services;

import files.model.Attachment;
import files.model.AttachmentRequest;
import files.model.FileDifference;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public List<FileDifference> diffAttachment (AttachmentRequest attachmentRequest) throws JSONException {
        //get the list of attachment request
        List<Attachment> attachmentList = attachmentRequest.getAttachmentList();

        //used diffmatchpatch to get the differences between 2 string
        DiffMatchPatch dmp = new DiffMatchPatch();

        //simply get the base64string from the request and get all differences
        LinkedList<DiffMatchPatch.Diff> all_diff = dmp.diffMain(attachmentList.get(0).getBase64String(),  attachmentList.get(1).getBase64String(), true);


        List<FileDifference> fileDifferenceList = new ArrayList<>();
        //get all diffs and store inside a response list object
        for (DiffMatchPatch.Diff difference_obj : all_diff) {
            FileDifference fileDifference = new FileDifference();
            fileDifference.setOperation(difference_obj.operation);
            fileDifference.setText(difference_obj.text);
            fileDifferenceList.add(fileDifference);
        }

        //this returns a list of differences in the format of
        //[
        //        {
        //            "operation": "EQUAL",
        //                "text": "aGV"
        //        },
        //        {
        //            "operation": "DELETE",
        //                "text": "BB"
        //        },
        //        {
        //            "operation": "INSERT",
        //                "text": "AA"
        //        },
        //        {
        //            "operation": "EQUAL",
        //                "text": "sbG8geW"
        //        },
        //        {
        //            "operation": "DELETE",
        //                "text": "="
        //        },
        //        {
        //            "operation": "EQUAL",
        //                "text": "9"
        //        },
        //        {
        //            "operation": "INSERT",
        //                "text": "1"
        //        }
        //]
        return fileDifferenceList;
    }
}
