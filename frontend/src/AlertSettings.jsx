import { useState, useEffect } from "react";

export default function AlertSettings({ alertSettings, setAlertSettings }) {
  const [localSettings, setLocalSettings] = useState({
    warningLevel: "",
    criticalLevel: "",
  });

  const [message, setMessage] = useState("");

  // ==========================
  // Hae nykyiset rajat backendistä
  useEffect(() => {
    // Backend-yhteys kommentoituna
    /*
    const fetchSettings = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/alert-settings");
        if (!response.ok) throw new Error("Asetusten haku epäonnistui");
        const data = await response.json();
        setLocalSettings({
          warningLevel: data.warningLevel.toString(),
          criticalLevel: data.criticalLevel.toString(),
        });
        setAlertSettings({
          warningLevel: data.warningLevel,
          criticalLevel: data.criticalLevel,
        });
      } catch (error) {
        console.error(error);
        setMessage("Ei voitu hakea nykyisiä asetuksia.");
      }
    };
    fetchSettings();
    */

    // Jos backend ei vielä valmis, aseta oletusarvot frontendistä
    setLocalSettings({
      warningLevel: alertSettings.warningLevel.toString(),
      criticalLevel: alertSettings.criticalLevel.toString(),
    });
  }, [setAlertSettings, alertSettings.warningLevel, alertSettings.criticalLevel]);
  // ==========================

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value === "" || (/^\d+$/.test(value) && Number(value) <= 100)) {
      setLocalSettings({ ...localSettings, [name]: value });
    }
  };

  const handleSave = async () => {
    const warningNum = Number(localSettings.warningLevel);
    const criticalNum = Number(localSettings.criticalLevel);

    if (isNaN(warningNum) || isNaN(criticalNum)) {
      setMessage("Syötä molemmat rajat ennen tallennusta.");
      return;
    }

    if (warningNum >= criticalNum) {
      setMessage("Varoitusraja ei voi olla suurempi tai yhtä suuri kuin kriittinen raja!");
      return;
    }

    setAlertSettings({ warningLevel: warningNum, criticalLevel: criticalNum });
    setMessage("Hälytysrajat päivitetty frontendille!");

    // ================================
    // Backend-yhteys kommentoituna
    /*
    try {
      const response = await fetch("http://localhost:8080/api/alert-settings", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ warningLevel: warningNum, criticalLevel: criticalNum }),
      });
      if (!response.ok) throw new Error("Tallennus epäonnistui");
      setMessage("Hälytysrajat päivitetty backendille!");
    } catch (error) {
      console.error(error);
      setMessage("Virhe tallennettaessa backendille.");
    }
    */
    // ================================
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", marginTop: "20px" }}>
      <div style={{ padding: "20px", maxWidth: "400px", width: "100%" }}>
        <h2>Hälytysrajat</h2>
        <p>Määritä kriittiset ja varoitusrajat (%).</p>

        {message && <div className="alert alert-info">{message}</div>}

        <div className="mb-3">
          <label>Varoitusraja (%)</label>
          <input
            type="text"
            name="warningLevel"
            value={localSettings.warningLevel}
            onChange={handleChange}
            className="form-control"
            placeholder="0–100"
          />
        </div>

        <div className="mb-3">
          <label>Kriittinen raja (%)</label>
          <input
            type="text"
            name="criticalLevel"
            value={localSettings.criticalLevel}
            onChange={handleChange}
            className="form-control"
            placeholder="0–100"
          />
        </div>

        <button className="btn btn-primary" onClick={handleSave}>
          Tallenna
        </button>
      </div>
    </div>
  );
}
