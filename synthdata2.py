import json
import time
from datetime import datetime, timezone
import random

import requests
import paho.mqtt.client as mqtt

# Roskisten API
API_URL = "http://localhost:8080/api/bins"

MAX_SENSOR_DISTANCE = 1200  # millimetriä
MIN_SENSOR_DISTANCE = 0
SECONDS = 10  # sekuntia

client = mqtt.Client()
client.connect("localhost", 1884, 60)

bin_distances = {}
previous_backend_fill = {}


def now():
    return datetime.now(timezone.utc).isoformat().replace("+00:00", "Z")


def initialize(bin_id):
    if bin_id not in bin_distances:
        bin_distances[bin_id] = MAX_SENSOR_DISTANCE


def fetch_bins():
    res = requests.get(API_URL, timeout=5)
    res.raise_for_status()
    return res.json()


def reset_bin(bin_id):
    bin_distances[bin_id] = MAX_SENSOR_DISTANCE


print("Simulaatio käynnissä (MQTT)... :)")

while True:
    try:
        bins = fetch_bins()

        if not bins:
            print("Ei binejä API:ssa.")
            time.sleep(SECONDS)
            continue

        for b in bins:
            bin_id = b["binId"]
            fill_level = float(b.get("fillLevel", 0.0))

            initialize(bin_id)

            prev_fill = previous_backend_fill.get(bin_id)

            #Toimisiko näin -- Jos tyhjenee bakcendin puolella niin myös tämän ohjelman muistiin päivitys
            if prev_fill is not None and prev_fill > 20 and fill_level < 10:
                reset_bin(bin_id)
                print(f"Bin {bin_id}: havaittu tyhjennys backendistä ({prev_fill:.1f}% -> {fill_level:.1f}%)")

            previous_backend_fill[bin_id] = fill_level

            current_distance = bin_distances[bin_id]

            # Jos bin täynnä, odotetaan käyttöliittymän kautta tehtävää tyhjennystä
            if current_distance <= MIN_SENSOR_DISTANCE:
                print(f"Bin {bin_id}: täynnä, odotetaan tyhjennystä käyttöliittymästä")
                continue

            step = random.uniform(10, 150)
            new_distance = max(MIN_SENSOR_DISTANCE, current_distance - step)
            bin_distances[bin_id] = new_distance

            payload = {
                "binId": bin_id,
                "distance": round(new_distance, 2),
                "timestamp": now()
            }

            topic = f"telemetry/bins/{bin_id}"
            client.publish(topic, json.dumps(payload))

            print(
                f"Bin {bin_id}: distance {current_distance:.2f} mm -> {new_distance:.2f} mm "
                f"(backend fillLevel {fill_level:.1f}%, step {step:.2f})"
            )

        time.sleep(SECONDS)

    except KeyboardInterrupt:
        print("Simulaatio pysäytetty.")
        break
    except Exception as e:
        print("Virheitä sattuu:", e)
        time.sleep(SECONDS)

client.disconnect()


