import paho.mqtt.client as mqtt
import json
import time
import random
from datetime import datetime

client = mqtt.Client()

#
client.connect("127.0.0.1", 1884, 60)


bin_memory = {1: 0.0, 2: 0.0, 3: 0.0, 4: 0.0, 5: 0.0}


print("Simulaatio käynnissä (MQTT)...")

while True:
    
    bin_id = random.randint(1, 5)
    
    
    lisays = random.uniform(0.5, 5.0)
    
    
    bin_memory[bin_id] = round(bin_memory[bin_id] + lisays, 2)
    
    # Jos menee yli 100, pidetään se sadassa (kunnes kuski tyhjentää)
    if bin_memory[bin_id] > 100:
        bin_memory[bin_id] = 100.0
    
    current_fill = bin_memory[bin_id]
    # --- UUSI LOGIIKKA LOPPUU ---

    data = {
        "binId": bin_id,
        "fillPercent": current_fill, # Käytetään muistista haettua arvoa
        "timestamp": datetime.now().strftime("%Y-%m-%dT%H:%M:%S")
    }
    
    client.publish(f"telemetry/bins/{data['binId']}", json.dumps(data))
    print("Lähetetty:", data)

    time.sleep(2)