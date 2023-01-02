public class messageHandler {
    private cypher c;
    private helper hp;
    private String regex;

    public messageHandler() {
        hp = new helper();
        this.regex = "-";
    }

    public String hand(String msg) {
        String out = "";
        String[] protocolParts = msg.split(this.regex);
        if (protocolParts[1].equals("?help")) {
            return (this.hp.solve(protocolParts[2]));
        } else {
            String method = protocolParts[0]; // protocol used, default hardcoded is GET
            String text = protocolParts[1];// input text
            int key = Integer.parseInt(protocolParts[2]);// key
            String cipher = protocolParts[3];// cesar o vigenere
            Boolean isCif = protocolParts[4].equals("T") ? true : false; // cifer or decifer

            if (cipher.equals("c")) {
                if (isCif) {
                    c = new cesar();
                    out = c.cypher_process(text, key);
                } else {
                    c = new cesar();
                    out = c.decipher_process(text, key);
                }
            } else {
                if (isCif) {
                    c = new vigenere();
                    out = c.cypher_process(text, key);
                } else {
                    c = new vigenere();
                    out = c.decipher_process(text, key);
                }
            }
        }
        return out;
    }
}