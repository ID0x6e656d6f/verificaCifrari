public class helper {
    public helper() {

    }

    public String solve(String msg) {
        String answer = "";

        if (msg.equals("general")) { // general help
            answer = "okay here's some instructions about the program:\n"
                    + "-try to type your text first\n"
                    + "-type then the key that you want to use\n"
                    + "-type the cypher that...yeah...you want to use\n"
                    + "-type y or Y if you want to encrypt, n or N if you want to decrypt your text\n";
        }else if(msg.equals("cesar")){
            answer = "okay here's some instructions about the cesar cypher\n"
            + "it's pretty simple:\n"
            + "-first of all you have to type the text. Pay attention: you text can be your clear text or the already encrypred text, it doesn't metter\n"
            + "-then you have to choose the kay that you wanto to use or with which the message you received was encrypted. (In the seconda case the key must be the same)\n"
            + "-after all you have to type Y or y if you want to encrypt, n or N if you want to decrypt your text\n";
        }else if(msg.equals("vigenere")){

        }
        return answer;
    }
}
