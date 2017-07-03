package operations;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingHistory {
    private boolean saveHistory = false;
    protected final static String NEW_LINE = "\n";
    private String filePath = "file.txt";

    public SavingHistory() {
    }

    public String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date())
                + NEW_LINE;
    }

    public void writeToFile(String toFile) throws IOException {
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(fos));
            w.write(getCurrentTimestamp());
            w.write(toFile);
            w.newLine();
            w.newLine();
            w.close();
        } else {
            throw new IOException();
        }
    }

    public boolean getSaveHistory() {
        return saveHistory;
    }

    public void setSaveHistory(boolean value) {
        saveHistory = value;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}