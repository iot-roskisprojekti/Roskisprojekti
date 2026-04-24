import React from "react";

export default function Tasks({ tasks, setTasks }) {

  if (!tasks || tasks.length === 0) {
    return <p style={{ textAlign: "center" }}>Ei avoimia tehtäviä</p>;
  }

  return (
    <div style={{ padding: "20px", display: "flex", justifyContent: "center" }}>
      <div style={{ display: "flex", flexDirection: "column", gap: "15px", width: "100%", maxWidth: "500px" }}>
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Työtehtävät</h2>

        {tasks.map((task) => (
          <div
            key={task.taskId}
            style={{
              padding: "15px",
              backgroundColor: "#fff",
              borderRadius: "12px",
              boxShadow: "0 2px 6px rgba(0,0,0,0.1)",
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              gap: "10px",
            }}
          >
            <span style={{ flex: 1 }}>
              {task.binName}
              {task.fillLevel != null ? ` — ${task.fillLevel}% täyttöaste` : ""}
            </span>

            <button
              style={{
                backgroundColor: "#28a745",
                color: "white",
                border: "none",
                borderRadius: "8px",
                padding: "8px 12px",
                cursor: "pointer",
                minWidth: "160px",
                whiteSpace: "nowrap",
                flexShrink: 0,
              }}
              onClick={async () => {
                try {
                  const response = await fetch(`http://localhost:8080/api/tasks/${task.taskId}/complete`, {
                    method: "POST",
                  });
                  if (!response.ok) throw new Error("Palvelinvirhe");

                  setTasks(prev => prev.filter(t => t.taskId !== task.taskId));
                } catch (error) {
                  alert("Kuittaaminen epäonnistui: " + error.message);
                }
              }}
            >
              Kuittaa tyhjennetyksi
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}