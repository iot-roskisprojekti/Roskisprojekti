import React, { useEffect } from "react";

const Alerts = ({ alerts, setAlerts }) => {

  // Hae hälytykset backendistä
  useEffect(() => {

    const fetchAlerts = async () => {

      // OTA KÄYTTÖÖN KUN BACKEND TOIMII
      
      try {
        const response = await fetch("http://localhost:8080/api/alerts");

        if (!response.ok) throw new Error("Hälytysten haku epäonnistui");

        const data = await response.json();

        const mapped = data.map(a => ({
          id: a.id,
          containerName: a.siteName,
          level: a.fillPercent
        }));

        setAlerts(mapped);

      } catch (error) {
        console.error("Virhe hälytysten haussa:", error);
      }
      

    };

    fetchAlerts();

  }, [setAlerts]);



  const handleCreateTask = async (alertId) => {

    // BACKENDILLE KUN API VALMIS
    
    try {
      const response = await fetch("http://localhost:8080/api/tasks", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ alertId }),
      });

      if (!response.ok) throw new Error("Tehtävän luonti epäonnistui");

    } catch (error) {
      console.error("Virhe tehtävän luonnissa:", error);
    }
    

    // TESTI FRONTENDILLE
    alert("Tehtävä luotu hälytyksestä ID: " + alertId);
  };


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
              className="p-3 mb-2 bg-yellow-100 rounded-2xl shadow-sm flex justify-between items-center"
            >
              <span>
                {a.containerName} - {a.level}% täyttöaste
              </span>

              <button
                onClick={() => handleCreateTask(a.id)}
                className="px-3 py-1 bg-blue-500 text-white rounded-xl"
              >
                Luo tehtävä
              </button>

            </li>
          ))}
        </ul>
      )}
    </section>
  );
};

export default Alerts;