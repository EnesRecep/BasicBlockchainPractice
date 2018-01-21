package sample;

public class Blocks {

    private String fileName;
    private String data;
    private String previousHash;
    private String previousFileName;

    public Blocks(String fileName, String data, String previousHash, String previousFileName) {
        this.fileName = fileName;
        this.data = data;
        this.previousHash = previousHash;
        this.previousFileName = previousFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getData() {
        return data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getPreviousFileName() {
        return previousFileName;
    }

    @Override
    public String toString() {
        return "Blocks{" +
                "fileName='" + fileName + '\'' +
                ", data='" + data + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", previousFileName='" + previousFileName + '\'' +
                '}';
    }
}
