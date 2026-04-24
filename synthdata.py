import json
import time
from datetime import datetime, timezone

import requests
import paho.mqtt.client as mqtt
import random



#Roksikset API
API_URL = "http://localhost:8080/api/bins"

MAX_SENSOR_DISTANCE = 1200 #millimetriä
MIN_SENSOR_DISTANCE = 0
SECONDS = 5 # sekuntia


RESET_FILL = 1

#Satunnaiseen tyhjennykseen
EMPTY_IF_FILL_LEAST = 70 # jos filLevel min 70 %
EMPTY_PROBABILITY = 0.1 # 10 % mahdollisuus joka kierroksella tyhjennykseen

client = mqtt.Client()

# Yhdistä MQTT brokeriin
client.connect("localhost", 1884, 60)

bin_distances = {}
previous_backend_fill = {}


def now():
    return datetime.now(timezone.utc).isoformat().replace('+00:00', 'Z')

#Alustetaan binit tyhjäksi
def initialize(bin_id):
    if bin_id not in bin_distances:
        bin_distances[bin_id] = MAX_SENSOR_DISTANCE

def fetch_bins():
    res = requests.get(API_URL, timeout=5)
    res.raise_for_status()
    return res.json()

#Tarvittaessa tyhjennetään
def reset_bin(bin_id):
    bin_distances[bin_id] = MAX_SENSOR_DISTANCE

print("Simulaatio käynnissä (MQTT)... :) ")

while True:
    try:
        bins = fetch_bins()

        if not bins:
            print("Ei binejä API:ssa.")
            time.sleep(SECONDS)
            continue

        for b in bins:
            bin_id = b["binId"]
            fill_level = float(b.get("fillLevel"))

            initialize(bin_id) # alustus

            prev_fill = previous_backend_fill.get(bin_id)

            #Havaitaan tyhjennys vain jos tiputus suuri, muutoin meni solomuun
            if prev_fill is not None and prev_fill > 20 and fill_level < 5:
                reset_bin(bin_id)
                print(f"Bin {bin_id} tyhjennetty")

            previous_backend_fill[bin_id] = fill_level

            current_distance = bin_distances[bin_id]

            #Jos täynnä, odotetaan joko tyhjennystä tai satunnaista tyhjennystä

            if current_distance <= MIN_SENSOR_DISTANCE:



                payload = {
                "binId": bin_id,
                "distance": MAX_SENSOR_DISTANCE,
                "timestamp": now()
                }

                topic = f"telemetry/bins/{bin_id}"
                client.publish(topic, json.dumps(payload))

                print(f"Bin {bin_id}: satunnainen tyhjennys")
                continue

            step = random.uniform(10,150)
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

    except Exception as e:
        print("Virheitä sattuu: ", e)
        time.sleep(SECONDS)

client.disconnected()