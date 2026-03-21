
import paho.mqtt.client as mqtt
import json
import time
import random
from datetime import datetime

client = mqtt.Client()

# Yhdistä MQTT brokeriin
client.connect("localhost", 1884, 60)

print("Simulaatio käynnissä (MQTT)...")

while True:
    data = {
        "binId": random.randint(1, 5),
        "distance": random.randint(0, 2150),
        "timestamp": datetime.now().isoformat()
    }

    client.publish("telemetry/bins/1", json.dumps(data))
    print("Lähetetty:", data)

    time.sleep(2)