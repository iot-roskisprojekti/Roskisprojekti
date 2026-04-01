import gpiod
import time
import sys

# On Ubuntu/Pi 4, the main header is on gpiochip0
CHIP_PATH = '/dev/gpiochip0'
LINE_OFFSET = 17 # This is GPIO 17 (Physical Pin 11)

try:
    with gpiod.Chip(CHIP_PATH) as chip:
        line = chip.get_line(LINE_OFFSET)
        line.request(consumer="LA_Test", type=gpiod.LINE_REQ_DIR_OUT)

        print(f"Success! Toggling {CHIP_PATH} pin {LINE_OFFSET} at 100Hz.")
        print("Target: 5ms High / 5ms Low. Press Ctrl+C to stop.")

        while True:
            line.set_value(1)
            time.sleep(0.005)
            line.set_value(0)
            time.sleep(0.005)

except FileNotFoundError:
    print(f"Error: {CHIP_PATH} not found. Check 'ls /dev/gpiochip*'.")
except PermissionError:
    print("Error: Permission Denied. Try 'sudo python jitter_test.py'")
    print("Or run: sudo usermod -aG gpio $USER (then log out and back in)")
except KeyboardInterrupt:
    print("\nExiting...")