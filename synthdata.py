
import mysql.connector
import random
import time
from datetime import datetime

URL_CONFIG = {
    "host": "localhost",
    "port": 3306,
    "database": "iot_bins",
    "user": "root",
    "password": "N!ppon861",
}


def hae_viimeisin_taytto(conn, site_id: int) -> float:
    sql = """
        SELECT fill_percent FROM measurement
        WHERE bin_id = %s
        ORDER BY measured_at DESC
        LIMIT 1
    """
    cursor = conn.cursor()
    cursor.execute(sql, (site_id,))
    row = cursor.fetchone()
    cursor.close()
    return float(row[0]) if row else random.uniform(0, 20)


def tallenna_mittaus(conn, site_id: int, fill: float) -> None:
    sql = "INSERT INTO measurement (bin_id, fill_percent, measured_at) VALUES (%s, %s, %s)"
    cursor = conn.cursor()
    cursor.execute(sql, (site_id, fill, datetime.now()))
    conn.commit()
    cursor.close()


def luo_tehtava(conn, alert_id: int) -> None:
    sql = "INSERT INTO task (alert_id) VALUES (%s)"
    cursor = conn.cursor()
    cursor.execute(sql, (alert_id,))
    conn.commit()
    print(f"!!! Uusi työtehtävä luotu hälytykselle ID: {alert_id}")
    cursor.close()


def luo_halytys(conn, site_id: int, tyyppi: str) -> None:
    tarkistus_sql = "SELECT COUNT(*) FROM alert WHERE bin_id = %s AND state = 'AVOIN'"
    cursor = conn.cursor()
    cursor.execute(tarkistus_sql, (site_id,))
    count = cursor.fetchone()[0]
    cursor.close()

    if count > 0:
        return

    insert_sql = "INSERT INTO alert (bin_id, alert_type) VALUES (%s, %s)"
    cursor = conn.cursor()
    cursor.execute(insert_sql, (site_id, tyyppi))
    conn.commit()
    alert_id = cursor.lastrowid
    cursor.close()

    print(f">>> HUOMIO: {tyyppi} tallennettu kohteelle ID {site_id}")

    if tyyppi == "HÄLYTYS":
        luo_tehtava(conn, alert_id)


def tarkista_halytys(conn, site_id: int, fill: float) -> None:
    if fill >= 80.0:
        print(f"⚠️ TESTI-ILMOITUS: Kohde {site_id} ylitti 80% (Nykyinen: {fill}%)")

    if fill >= 95.0:
        luo_halytys(conn, site_id, "HÄLYTYS")
    elif fill >= 80.0:
        luo_halytys(conn, site_id, "VAROITUS")


def paivita_mittaukset(conn) -> None:
    cursor = conn.cursor()
    cursor.execute("SELECT id, name FROM site")
    sites = cursor.fetchall()
    cursor.close()

    for site_id, nimi in sites:
        edellinen_taytto = hae_viimeisin_taytto(conn, site_id)
        uusi_taytto = min(100.0, edellinen_taytto + random.uniform(0, 5))
        uusi_taytto = round(uusi_taytto, 2)

        tallenna_mittaus(conn, site_id, uusi_taytto)
        print(f"{nimi} päivitetty: {uusi_taytto}%")

        tarkista_halytys(conn, site_id, uusi_taytto)


def main():
    try:
        conn = mysql.connector.connect(**URL_CONFIG)
        print("IoT-simulaatio käynnissä... Paina Ctrl+C lopettaaksesi.")

        while True:
            paivita_mittaukset(conn)
            time.sleep(5)

    except KeyboardInterrupt:
        print("\nSimulaatio pysäytetty.")
    except mysql.connector.Error as e:
        print(f"Virhe simulaatiossa: {e}")
    finally:
        if conn.is_connected():
            conn.close()


if __name__ == "__main__":
    main()