# author Zaganelli Alessandro
# this file is used to simualte a client with the server on the same folder here
import socket
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(("127.0.0.1", 64258))
openMsg = "Welcome to [inserire nome software figo]\n-type ?help for some instrucions"
print(openMsg)
msg = "GET-"  # for example i use an hardcoded valie
while msg != "end_connection":
    msg += input('Your key: ') + '-' + input('Your text: ') + \
        '-' + input('Cesar(c) or Vigenere(v)') + '-'

    if (input('Do you wanto to cifrate?(Y/N) ') == 'Y'):
        msg += "T"
    else:
        msg += "F"

    print(msg)

    client.send(msg.encode())
    print(client.recv(1024).decode())
