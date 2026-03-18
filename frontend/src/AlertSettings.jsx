import { useState } from "react";

export default function AlertSettings({ alertSettings, setAlertSettings }) {

  // Tallennetaan arvot ensin stringinä, jotta tyhjä input toimii
  const [localSettings, setLocalSettings] = useState({
    warningLevel: alertSettings.warningLevel.toString(),
    criticalLevel: alertSettings.criticalLevel.toString(),
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    // Sallitaan tyhjä string
    if (value === "" || /^\d*$/.test(value)) {
      setLocalSettings({ ...localSettings, [name]: value });
    }
  };

  const handleSave = () => {
    const warningNum = Number(localSettings.warningLevel);
    const criticalNum = Number(localSettings.criticalLevel);

    if (isNaN(warningNum) || isNaN(criticalNum)) {
      alert("Syötä molemmat rajat ennen tallennusta.");
      return;
    }

    if (warningNum >= criticalNum) {
      alert("Varoitusraja ei voi olla suurempi tai yhtä suuri kuin kriittinen raja!");
      return;
    }

    setAlertSettings({ warningLevel: warningNum, criticalLevel: criticalNum });
    alert("Hälytysrajat päivitetty!");
  };

  return (
    <div style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
    }}>
      <div style={{ padding: "20px", maxWidth: "400px", width: "100%" }}>
        <h2>Hälytysrajat</h2>
        <p>Määritä varoitus- ja kriittiset rajat (%).</p>

        <div className="mb-3">
          <label>Varoitusraja (%)</label>
          <input
            type="text"   // <-- tärkeää, ei "number"
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
            type="text"   // <-- tärkeää, ei "number"
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