import math
from typing import Dict


def magnitude(x, y):
    return math.hypot(x, y)


def compute(received: Dict):
    mouseX, mouseY = received['mouseX'], received['mouseY']
    WIDTH, HEIGHT = received['WIDTH'], received['HEIGHT']

    angle = 180 + 360 * magnitude(mouseX, mouseY) / magnitude(WIDTH, HEIGHT)
    response = {"col": angle % 360}
    return response
