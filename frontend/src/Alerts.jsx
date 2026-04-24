import React, { useEffect } from "react";

const Alerts = ({ alerts, setAlerts }) => {

  useEffect(() => {
    const fetchAlerts = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/alerts");
        if (!response.ok) throw new Error("Hälytysten haku epäonnistui");

        const data = await response.json();

        const mapped = data.map(a => ({
          id: a.id,
          binId: a.binId,
          type: a.type,
          state: a.state,
          triggeredAt: a.triggeredAt,
        }));

        setAlerts(mapped);
      } catch (error) {
        console.error("Virhe hälytysten haussa:", error);
      }
    };

    fetchAlerts();
  }, [setAlerts]);

  return (
    <section className="mb-6">
      <h2 className="text-xl font-semibold mb-2">Hälytykset</h2>

      {alerts.length === 0 ? (
        <p>Ei aktiivisia hälytyksiä</p>
      ) : (
        <ul>
          {alerts.map((a) => (
            <li
              key={a.id}
              className="p-3 mb-2 bg-yellow-100 rounded-2xl shadow-sm"
            >
              Astia #{a.binId} — tyyppi: {a.type} — tila: {a.state}
            </li>
          ))}
        </ul>
      )}
    </section>
  );
};

export default Alerts;