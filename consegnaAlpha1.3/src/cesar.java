public class cesar extends cypher {
    private final String letters = new String("abcdefghijklmnopqrstuvwxyz");// international letters

    public cesar() {
    }

    @Override
    String cypher_process(String message, int Key) {
        String out = "";
        int index;
        int j = message.length();
        char at;
        for (int i = 0; i < j; i++) {
            at = message.charAt(i);
            index = this.letters.indexOf(at);
            if (index > 18) {
                at = this.letters.charAt(index - 23);
                out = out + at;
            } else {
                at = this.letters.charAt(index + Key);
                out = out + at;
            }
        }
        return (out + " --> key: " + Key);
    }

    @Override
    String decipher_process(String message, int Key) {
        String out = "";
        int index;
        int j = message.length();
        char at;
        for (int i = 0; i < j; i++) {
            at = message.charAt(i);
            index = this.letters.indexOf(at);
            if (index > 18) {
                at = this.letters.charAt(index + 23);
                out = out + at;
            } else {
                at = this.letters.charAt(index - Key);
                out = out + at;
            }
        }
        return (out + " --> key: " + Key);
    }

}
