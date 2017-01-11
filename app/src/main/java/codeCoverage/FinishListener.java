package codeCoverage;

/**
 * Created by fc on 17-1-10.
 */

public interface FinishListener {
    void dumpIntermediateCoverage(String filePath);
    void onActivityFinished();
}
