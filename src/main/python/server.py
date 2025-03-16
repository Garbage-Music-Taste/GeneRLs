import socket
import json
from time import sleep


class ProcessingServer:
    def __init__(self, host='localhost', port=5001):
        self.host, self.port = host, port
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        # Add this to allow port reuse immediately after closure
        self.s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.s.bind((self.host, self.port))
        self.s.listen()
        self.conn = None
        self.buffer = ""
        print(f"✅ Server started at {self.host}:{self.port}, waiting for Processing...")
        self.accept_connection()

    def accept_connection(self):
        print("Waiting for connection...")
        self.conn, addr = self.s.accept()
        print(f"🔗 Connection established with {addr}")
        self.buffer = ""

    def receive(self):
        if not self.conn:
            self.accept_connection()
            return None

        try:
            data = self.conn.recv(1024).decode('utf-8')
            if not data:
                print("⚠️ Connection closed, waiting for new connection...")
                self.conn.close()
                self.conn = None
                self.accept_connection()
                return None

            self.buffer += data
            if "\n" in self.buffer:
                line, self.buffer = self.buffer.split("\n", 1)
                try:
                    json_data = json.loads(line.strip())
                    print(f"📥 Received: {json_data}")
                    return json_data
                except json.JSONDecodeError as e:
                    print(f"⚠️ JSON Decode Error: {line.strip()}")
        except socket.error:
            print("⚠️ Socket error, resetting connection...")
            if self.conn:
                self.conn.close()
            self.conn = None
            self.accept_connection()
        return None

    def send(self, data_dict):
        if not self.conn:
            print("⚠️ No active connection, cannot send data")
            return

        try:
            self.conn.sendall((json.dumps(data_dict) + "\n").encode('utf-8'))
            print(f"📤 Sent: {data_dict}")
        except socket.error:
            print("⚠️ Socket error while sending, resetting connection...")
            self.conn.close()
            self.conn = None
            self.accept_connection()

    def close(self):
        if self.conn:
            self.conn.close()
        self.s.close()
        print("🚪 Connection closed gracefully.")