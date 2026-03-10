import React from "react";

export default function Tasks({ tasks, onCompleteTask }) {

  if (!tasks || tasks.length === 0) {
    return <p style={{ textAlign: "center" }}>Ei avoimia tehtäviä</p>;
  }

  return (
    <div
      style={{
        padding: "20px",
        display: "flex",
        justifyContent: "center",
      }}
    >
      <div style={{ display: "flex", flexDirection: "column", gap: "15px", width: "100%", maxWidth: "500px" }}>
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Työtehtävät</h2>
        {tasks.map((task) => (
          <div
            key={task.id}
            style={{
              padding: "15px",
              backgroundColor: "#fff",
              borderRadius: "12px",
              boxShadow: "0 2px 6px rgba(0,0,0,0.1)",
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <span>
              {task.containerName} - {task.alertLevel}% täyttöaste - {task.assignedTo}
            </span>

            {/* -------------------------------
                Simulaatio: paikallinen kuittaus
                Poistetaan, kun backend käytössä
            ---------------------------------- */}
            <button
              style={{
                backgroundColor: "#28a745",
                color: "white",
                border: "none",
                borderRadius: "8px",
                padding: "5px 10px",
                cursor: "pointer",
              }}
              onClick={() => {
                // Paikallinen simulaatio
                onCompleteTask && onCompleteTask(task.id, task.containerId);
              }}
            >
              Kuittaa
            </button>

            {/* -------------------------------
                Backend-valmis versio:
                Poista yllä oleva simulaatiopainike
                ja käytä tätä:
            ---------------------------------- */}
            {/*
            <button
              style={{
                backgroundColor: "#28a745",
                color: "white",
                border: "none",
                borderRadius: "8px",
                padding: "5px 10px",
                cursor: "pointer",
              }}
              onClick={async () => {
                try {
                  const response = await fetch(`http://localhost:8080/api/tasks/${task.id}/complete`, {
                    method: "POST"
                  });

                  if (!response.ok) throw new Error("Palvelinvirhe");

                  // UI päivitys backendin jälkeen
                  onCompleteTask && onCompleteTask(task.id, task.containerId);

                } catch (error) {
                  alert("Tehtävän kuittaaminen epäonnistui: " + error.message);
                }
              }}
            >
              Kuittaa
            </button>
            */}
          </div>
        ))}
      </div>
    </div>
  );
}