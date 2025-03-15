import socket
import json
import math

HOST, PORT = 'localhost', 5001

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
                coords = json.loads(line.strip())
                x, y = coords["x"], coords["y"]
                distance = math.hypot(x, y)

                # Map distance to a colour intensity (0-255), capped at 255
                red_intensity = min(int(distance / 3), 255)
                color = {"r": red_intensity, "g": 0, "b": 255 - red_intensity}

                conn.sendall((json.dumps(color) + "\n").encode('utf-8'))
