package files.services;

import files.model.AttachmentRequest;
import files.model.FileDifference;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface FileService {

    List<FileDifference> diffAttachment (AttachmentRequest attachmentRequest) throws JSONException;
}
