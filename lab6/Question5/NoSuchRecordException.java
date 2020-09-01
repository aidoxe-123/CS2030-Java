import java.util.NoSuchElementException;

class NoSuchRecordException extends NoSuchElementException {
    String e;
    
    public NoSuchRecordException(String e) {
        this.e = e;
    }

    public String getMessage() {
        return "No such record: " + this.e;
    }
}
