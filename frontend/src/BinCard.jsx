import React from "react";

export default function BinCard({
  id,
  name,
  location,
  fillLevel,
  capacity,
  lastUpdate,
  isOnline,
  hasTask
}) {

  // Status logiikka (käytetään sekä väriin että badgeen)
  const getStatus = (level) => {
    if (level >= 85) return { text: "Kriittinen", color: "#dc3545" };
    if (level >= 70) return { text: "Varoitus", color: "#fd7e14" };
    return { text: "Normaali", color: "#198754" };
  };

  const status = getStatus(fillLevel);

  // Fill bar väri
  let fillColor = status.color;

  // Offline override
  if (!isOnline) {
    fillColor = "#6c757d";
  }

  const cardBg = isOnline ? "#fff" : "#f5f5f5";
  const textColor = isOnline ? "#000" : "#666";

  return (
    <div
      style={{
        backgroundColor: cardBg,
        color: textColor,
        padding: "1rem",
        borderRadius: "1rem",
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
        width: "260px",
        textAlign: "center",
        margin: "0.5rem",
        border: hasTask ? "3px solid #0d6efd" : "3px solid transparent",
        transition: "all 0.2s ease"
      }}
    >
      {/* Nimi */}
      <h3 style={{ marginBottom: "0.3rem" }}>
        {name || `Roska-astia ${id}`}
      </h3>

      {/* Status badge */}
      <div
        style={{
          display: "inline-block",
          padding: "4px 10px",
          borderRadius: "12px",
          backgroundColor: isOnline ? status.color : "#6c757d",
          color: "white",
          fontSize: "12px",
          fontWeight: "bold",
          marginBottom: "10px"
        }}
      >
        {isOnline ? status.text : "Offline"}
      </div>

      {/* Sijainti */}
      <p style={{ marginBottom: "0.5rem" }}>
        📍 {location}
      </p>

      {/* Fill bar */}
      <div
        style={{
          height: "16px",
          backgroundColor: "#eee",
          borderRadius: "8px",
          overflow: "hidden",
          margin: "0.5rem 0"
        }}
      >
        <div
          style={{
            width: `${fillLevel}%`,
            backgroundColor: fillColor,
            height: "100%",
            transition: "width 0.5s"
          }}
        />
      </div>

      <p>{fillLevel}% täynnä</p>

      {/* Lisätiedot */}
      <p>Kapasiteetti: {capacity} L</p>
      <p>Viimeksi päivitetty: {lastUpdate}</p>
      <p>Status: {isOnline ? "Online" : "Offline"}</p>

      {/* Task indicator */}
      {hasTask && (
        <div
          style={{
            marginTop: "10px",
            color: "#0d6efd",
            fontWeight: "bold"
          }}
        >
          🚛 Tyhjennys tilattu
        </div>
      )}
    </div>
  );
}
