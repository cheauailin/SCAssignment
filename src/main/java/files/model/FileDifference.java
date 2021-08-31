package files.model;

//this is the json response back to user showing what is the differences
public class FileDifference {
    private Object operation;
    private Object text;

    public FileDifference() {
    }

    public FileDifference(Object operation, Object text) {
        this.operation = operation;
        this.text = text;
    }

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
        this.operation = operation;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }
}
