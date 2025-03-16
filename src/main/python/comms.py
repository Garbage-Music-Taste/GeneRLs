import math
from time import sleep

from server import ProcessingServer
from src.main.python.calc import compute

server = ProcessingServer()


def main():
    try:
        while True:
            try:
                received = server.receive()
                if received:
                    response = compute(received)

                    print(f"📤 Sending: {response}")
                    server.send(response)
                else:
                    # If no data received, sleep briefly to avoid CPU spin
                    sleep(0.1)
            except Exception as e:
                print(f"Error in main loop: {e}")
                sleep(1)  # Sleep briefly before trying again

    except KeyboardInterrupt:
        print("\n🛑 Interrupted by user.")

    finally:
        server.close()


if __name__ == '__main__':
    main()
