package io;

public class ArgZip {

    private final String[] args;
    private String directory;
    private String exclude;
    private String output;

    public ArgZip(String[] args) {
        this.args = args;

    }

    public boolean valid() {
        boolean val = false;
        if (args.length == 0) {
            throw new IllegalArgumentException("Please check arg");
        }
        for (String arg : args) {
            String[] keyValue = arg.split("=");
            if (keyValue.length == 2) {
                if ("-d".equals(keyValue[0])) {
                    directory = keyValue[1];
                    val = true;
                }
                if ("-e".equals(keyValue[0])) {
                    exclude = "." + keyValue[1];
                    val = true;
                }
                if ("-o".equals(keyValue[0])) {
                    output = keyValue[1];
                    val = true;
                }
            }

        }
        return val;
    }
    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }
}

