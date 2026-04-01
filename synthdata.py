
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
    bin_id = random.randint(1, 5)

    data = {
        "binId": bin_id,
        "distance": random.randint(0, 1200),
        "timestamp": datetime.now().isoformat()
    }

    topic = f"telemetry/bins/{bin_id}"
    client.publish(topic, json.dumps(data))
    print("Lähetetty:", data)

    time.sleep(4)