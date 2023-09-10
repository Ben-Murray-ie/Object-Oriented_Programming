package ie.atu.sw;

public class Builder {

    public void build(String directory, int n, String outLoc, int type) throws Exception {
        System.out.println("Building frequency table. Please wait.");
        long start = System.nanoTime();
        FileParser p = new FileParser();
        Outputter o = new Outputter();

        p.parseDir(directory, n, type);

        o.save(p.getTable, outLoc);

        System.out.println("Build complete.");

        long end = System.nanoTime();
        long total = end - start;
        double timeMillis = total / 1000000.0;

        System.out.println("Build Time = " + timeMillis);

        keepRunning = false;
    }

}
