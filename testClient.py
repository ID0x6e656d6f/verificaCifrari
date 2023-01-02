# author Zaganelli Alessandro
# this file is used to simualte a client with the server on the same folder here
import socket
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(("127.0.0.1", 64258))
openMsg = "Welcome to [inserire nome software figo]\n-type ?help + (nothing, cesar or vigenere) for some instrucions"
print(openMsg)
msg = "GET-"  # for example i use an hardcoded value
while msg != "end_connection":
    if input('Need help?: ') == "Y":
        request = input(
            'leave empty for general instruction, or type cesar of vigenere: ')
        if request == "":
            client.send((("GET-?help-" + "general")).encode())
        else:
            client.send((("GET-?help-" + request)).encode())
        print(client.recv(1024).decode())
    else:
        msg = "GET-"
        msg += input('Your text: ') + '-' + input('Your key: ') + \
            '-' + input('Cesar(c) or Vigenere(v)') + '-'
        method = input('Do you wanto to cifrate?(Y/N) ')
        if (method == 'Y' or method == 'y'):
            msg += "T"
        else:
            msg += "F"
        client.send(msg.encode())
        print(client.recv(1024).decode())
        if input('end connection?(Y/N): ') == "Y":
            client.send(("closed connection by client").encode)
            break
