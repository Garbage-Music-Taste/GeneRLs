import socket
import json
import math

HOST, PORT = 'localhost', 5001

def magnitude(x, y):
    return math.hypot(x, y)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    print("✅ Python server started, waiting for Processing...")
    conn, addr = s.accept()
    with conn:
        print('Connected by', addr)
        buffer = ""
        while True:
            data = conn.recv(1024).decode('utf-8')
            if not data:
                break

            buffer += data
            while "\n" in buffer:
                line, buffer = buffer.split("\n", 1)
                print(line,buffer)
                info = json.loads(line.strip())
                mouseX, mouseY = info["mouseX"], info["mouseY"]
                WIDTH, HEIGHT = info["WIDTH"], info["HEIGHT"]

                angle = 180 + 360 * magnitude(mouseX, mouseY) / magnitude(WIDTH, HEIGHT)
                angle = angle % 360  # optional, to keep within [0,360]

                response = {"colour": angle}
                conn.sendall((json.dumps(response) + "\n").encode('utf-8'))
