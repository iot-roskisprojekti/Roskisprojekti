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
  <div>
    <div className="alert alert-warning" style={{ maxWidth: "400px", margin: "20px auto 0" }}>
      ⚠️ Hälytysrajojen muokkaus ei ole käytössä tässä demoversiossa. Rajat on määritelty järjestelmään kiinteästi.
    </div>

    <div style={{ maxWidth: "400px", margin: "16px auto 0", padding: "16px", backgroundColor: "#fff", borderRadius: "8px", boxShadow: "0 2px 6px rgba(0,0,0,0.1)" }}>
      <h5 style={{ marginBottom: "12px" }}>Voimassa olevat raja-arvot</h5>
      <table className="table table-bordered mb-0">
        <thead>
          <tr>
            <th>Tila</th>
            <th>Täyttöaste</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><span style={{ color: "#198754", fontWeight: "bold" }}>● Normaali</span></td>
            <td>0–69 %</td>
          </tr>
          <tr>
            <td><span style={{ color: "#fd7e14", fontWeight: "bold" }}>● Varoitus</span></td>
            <td>70–84 %</td>
          </tr>
          <tr>
            <td><span style={{ color: "#dc3545", fontWeight: "bold" }}>● Kriittinen</span></td>
            <td>85–100 %</td>
          </tr>
        </tbody>
      </table>
    </div>

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
  </div>
);
}